package sweep.viewer.engine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Reader;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sweep.parser.SweptFrame;
import sweep.parser.SweptParser;


public class SweptEngine extends AbstractEngine
{
	private SweptParser parser;

	//------------------------
	protected int width;
	protected int height;

	private int defaultWidth;
	private int defaultHeight;

	protected int rows;
	protected int cols;
	protected int numAgents;

	protected boolean drawGrid = false;

	private JPanel drawingPanel;
	private JPanel controlPanel;
	protected BufferedImage image;
	protected Graphics graphics;

	private ChangeListener changeListener;

	protected int scale = 1;

	protected Color[] agentColorTable = {Color.orange, Color.blue, Color.red };

	protected Color[] environmentColorTable = {Color.black, Color.white, Color.cyan, Color.yellow, Color.pink,
			Color.magenta, Color.gray, Color.lightGray, Color.darkGray };

	public SweptEngine( Reader reader )
	{
		super( reader );

		parser = new SweptParser( reader );

		rows = parser.getNumberOfRows();
		cols = parser.getNumberOfColumns();
		numAgents = parser.getNumberOfAgents();

		init();
	}

	protected void init()
	{
		//initEnvironmentColorTable();
		initSettings();
		initGraphics();
	}

	protected void initEnvironmentColorTable()
	{
		environmentColorTable = new Color[10];

		environmentColorTable[0] = Color.black;
		environmentColorTable[1] = Color.green;

		for( int x = 2; x < environmentColorTable.length; x++ )
		{
			environmentColorTable[x] = environmentColorTable[x - 1].darker().darker();
		}
	}

	protected void initSettings()
	{
		// can the scale factor be calculated somehow???

		defaultWidth = rows * scale;
		defaultHeight = cols * scale;

		setEnvironmentView( false );
	}

	protected void initGraphics()
	{
		drawingPanel = new JPanel()
		{
			// override processMouseEvent so as to give the gridblock (x,y)
			// not the global
			protected void processMouseEvent( MouseEvent me )
			{
				if ( me.getID() == MouseEvent.MOUSE_CLICKED )
				{
					int x = me.getX();
					int y = me.getY();

					x /= scale;
					y /= scale;

					me.translatePoint( x - me.getX(), y - me.getY() );
				}

				super.processMouseEvent( me );
			}

			//New from JPM
			public void paintComponent( Graphics g )
			{
				if ( image != null )
				{
					image.flush();
				}
				else
				{
					System.err.print( "null" );
				}

				g.drawImage( image, 0, 0, this );
			}
		};

		drawingPanel.addComponentListener( new ComponentAdapter()
		{
			public void componentResized( ComponentEvent e )
			{
				JPanel panel = ( JPanel )e.getSource();

				int w = panel.getWidth();
				int h = panel.getHeight();

				scale = ( ( w < h ) ? w : h ) / rows;

				width = rows * scale;
				height = cols * scale;

				if ( width <= 0 )
				{
					width = rows;
				}
				if ( height <= 0 )
				{
					height = cols;
				}

				//The below added by JPM
				BufferedImage newImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
				Graphics newGraphics = newImage.createGraphics();
				if ( image != null )
				{
					newGraphics.drawImage( image, 0, 0, width, height, panel );
					graphics.dispose();
				}
				graphics = newGraphics;
				image = newImage;
			}
		} );

		drawingPanel.setPreferredSize( new Dimension( defaultWidth, defaultHeight ) );

		//-----------------

		controlPanel = new JPanel( new GridLayout( 2, 1, 10, 10 ) );

		/*
		 * URL agentURL = SwarmSimEngine.class.getResource("/images/agent.gif");
		 * URL environmentURL =
		 * SwarmSimEngine.class.getResource("/images/environment.gif");
		 * 
		 * if (agentURL == null) { //throw new Error("Could not load button
		 * icons: agent"); }
		 * 
		 * if (environmentURL == null) { //throw new Error("Could not load
		 * button icons: environment"); }
		 */

		//JButton agentButton = new ToggleIconButton(new ImageIcon(agentURL),
		// new ImageIcon(agentURL), getAgentView());
		JButton agentButton = new JButton( "Agents" );
		agentButton.addActionListener( new AgentListener() );

		//		JButton environmentButton =
		//			new ToggleIconButton(new ImageIcon(environmentURL), new
		// ImageIcon(environmentURL), getEnvironmentView());

		JButton environmentButton = new JButton( "env" );

		environmentButton.addActionListener( new EnvironmentListener() );

		controlPanel.add( agentButton );
		controlPanel.add( environmentButton );
	}

	private class AgentListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			toggleAgentView();
		}
	}

	private class EnvironmentListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			toggleEnvironmentView();
		}
	}

	public void addEndOfFileListener( ChangeListener listener )
	{
		changeListener = listener;
	}

	public Component getScreen()
	{
		return drawingPanel;
	}

	// for additional controls specific to each engine
	public Component getControls()
	{
		return controlPanel;
	}

	public void nextFrame()
	{
		try
		{
			createNextImage();
		}
		catch( Exception e )
		{
			if ( changeListener != null )
			{
				changeListener.stateChanged( new ChangeEvent( this ) );
			}
		}

		drawingPanel.repaint(); //JPM
		//drawingPanel.getGraphics().drawImage(image, 0, 0, drawingPanel);
	}

	protected void createNextImage() throws Exception
	{
		SweptFrame frame = parser.getNextFrame();

		graphics.setColor( Color.black );
		graphics.fillRect( 0, 0, image.getWidth(), image.getHeight() );

		processEnvironmentSection( frame.environment );
		processAgentSection( frame.agents, frame.color );
	}

	protected void processEnvironmentSection( char[][] environment ) throws Exception
	{
		if ( !showEnvironment )
		{
			return;
		}

		for( int c = 0; c < environment.length; c++ )
		{
			char[] buffer = environment[c];

			for( int r = 0; r < rows; r++ )
			{
				if ( buffer[r] < '0' || '9' < buffer[r] )
				{
					throw new Error( "Viewer incountered a non-numeric character: " + buffer[r] );
				}

				if ( drawGrid )
				{
					graphics.setColor( Color.black );
					graphics.drawRect( c * scale, r * scale, scale, scale );

					graphics.setColor( environmentColorTable[( buffer[r] - '0' ) % environmentColorTable.length] );
					graphics.fillRect( ( c - 1 ) * scale, ( r - 1 ) * scale, scale - 1, scale - 1 );
				}
				else
				{
					graphics.setColor( environmentColorTable[( buffer[r] - '0' ) % environmentColorTable.length] );
					graphics.fillRect( c * scale, r * scale, scale, scale );
				}

			}
		}
	}

	protected void processAgentSection( Point[] agents, int[] color ) throws Exception
	{
		if ( !showAgents )
		{
			return;
		}

		for( int i = 0; i < agents.length; i++ )
		{
			Point p = agents[i];

			if ( drawGrid )
			{
				graphics.setColor( Color.black );
				graphics.drawRect( p.x * scale, p.y * scale, scale, scale );

				graphics.setColor( agentColorTable[color[i] % agentColorTable.length] );
				graphics.fillRect( ( p.x - 1 ) * scale, ( p.y - 1 ) * scale, scale - 1, scale - 1 );
			}
			else
			{
				graphics.setColor( agentColorTable[color[i] % agentColorTable.length] );
				graphics.fillRect( p.x * scale, p.y * scale, scale, scale );
			}
		}
	}

	protected Icon createImageIcon( String name )
	{
		java.net.URL iconURL = ClassLoader.getSystemResource( "sweep/viewer/" + name );

		if ( iconURL == null )
		{
			System.out.println( name );
			return new ImageIcon();
		}

		return new ImageIcon( iconURL );
	}
}