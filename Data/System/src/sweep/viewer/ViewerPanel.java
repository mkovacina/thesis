/**
 * This is the top-level container for the animation viewer. It controls the
 * basic start/stop and framerate controlls, and delegates all other controls to
 * the <code>Engine</code>.
 * 
 * @author Michael Kovacina mkovacina@jcu.edu
 * @version 1.0
 * @since JDK1.4
 */

package sweep.viewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.Reader;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sweep.viewer.engine.Engine;
import sweep.viewer.engine.EngineBuilder;


public class ViewerPanel extends javax.swing.JPanel
{
	/**
	 * Handles animation rendering
	 */
	private Engine engine;

	/**
	 * Number of milliseconds between each frame
	 */
	protected int delay = 50;

	/**
	 * Used to implement the animation loop
	 */
	private Timer timer;

	/**
	 * Constructs a <code>ViewerPanel</code> viewer based on the
	 * <code>Reader</code> supplied
	 * 
	 * @param reader
	 *            the input media
	 * @since JDK1.4
	 */
	public ViewerPanel( Reader reader )
	{
		super();

		engine = EngineBuilder.createEngine( reader );

		engine.addEndOfFileListener( new ChangeListener()
		{
			public void stateChanged( ChangeEvent e )
			{
				stop();
			}
		} );

		timer = new Timer( delay, new ActionListener()
		{
			public void actionPerformed( ActionEvent e )
			{
				processNextFrame();
			}
		} );

		initInterface();
	}

	private void initInterface()
	{
		setLayout( new BorderLayout() );

		add( engine.getScreen(), BorderLayout.CENTER );
		add( engine.getControls(), BorderLayout.EAST );
		add( new ControlPanel( this ), BorderLayout.SOUTH );
	}

	public void start()
	{
		timer.start();
	}

	public void stop()
	{
		timer.stop();
	}

	protected void processNextFrame()
	{
		engine.nextFrame();
	}

	protected void setDelay( int delay )
	{
		this.delay = delay;

		timer.setDelay( delay );
	}

	public void addMouseListener( MouseListener mouseListener )
	{
		engine.getScreen().addMouseListener( mouseListener );
	}

	class ControlPanel extends javax.swing.JPanel
	{
		protected ControlPanel( final ViewerPanel svJPanel )
		{
			super( new BorderLayout() );

			/* create the North section */

			javax.swing.JPanel north = new javax.swing.JPanel();

			AbstractButton button;

			button = new JButton( new AbstractAction( "play" )
			{
				public void actionPerformed( ActionEvent e )
				{
					svJPanel.start();
				}
			} );

			north.add( button, BorderLayout.NORTH );

			button = new JButton( new AbstractAction( "stop" )
			{
				public void actionPerformed( ActionEvent e )
				{
					svJPanel.stop();
				}
			} );

			north.add( button, BorderLayout.NORTH );

			add( north, BorderLayout.NORTH );

			/* create the South section */

			JSlider slider = new JSlider( SwingConstants.HORIZONTAL, 0, 300, delay );

			slider.setInverted( true );
			slider.setPaintLabels( true );

			slider.addChangeListener( new ChangeListener()
			{
				public void stateChanged( ChangeEvent e )
				{
					JSlider slider = ( JSlider )e.getSource();

					//if ( !slider.getValueIsAdjusting() )
					{
						svJPanel.setDelay( slider.getValue() );
					}
				}
			} );

			java.util.Hashtable table = new java.util.Hashtable( 2 );

			table.put( new Integer( slider.getMinimum() ), new JLabel( "Fast" ) );
			table.put( new Integer( slider.getMaximum() ), new JLabel( "Slow" ) );

			slider.setLabelTable( table );

			add( slider, BorderLayout.SOUTH );
		}
	}
}