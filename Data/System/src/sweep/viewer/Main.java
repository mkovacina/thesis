/**
 * Main is the command-line interface to the animation viewer. It is currently
 * capable of handling normal animation files, gzipped animation files, and also
 * piped (uncompressed) animation files from the command-line.
 * 
 * @author Michael A. Kovacina <mkovacina@jcu.edu>
 * @version 1.0
 * @version JDK1.4
 */

package sweep.viewer;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.UID;
import java.util.Random;
import java.util.zip.GZIPInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import miko.loader.DynamicClassloader;
import miko.xml.Util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;


/**
 * Description: Main
 * 
 * @author Michael A. Kovacina
 */
public class Main
{
	/**
	 * The reader associated with the input meadia.
	 */
	protected Reader reader;

	/**
	 * Creates a new instance of Main, passing <code>args</code> as a
	 * parameter
	 * 
	 * @param args
	 *            array of command-line arguments
	 * @since JDK1.4
	 */
	public static void main( String[] args )
	{
		for( int x = 0; x < args.length; x++ )
		{
			System.out.println( args[x] );
		}

		Main exe = null;

		if ( args.length == 0 )
		{
			// REFACTOR: put in a choice about starting as a server, or
			// connecting to something

			JFileChooser fs = new JFileChooser();
			fs.setFileSelectionMode( JFileChooser.FILES_ONLY );

			int result = fs.showOpenDialog( new JFrame( "ff" ) );

			String filename = "";

			if ( result == JFileChooser.APPROVE_OPTION )
			{
				filename = fs.getSelectedFile().getAbsolutePath();
				exe = new Main( filename );
			}
			else
			{
				System.err.println( "usage: " + usage() );
				System.exit( -1 );
			}
		}
		else
		{
			exe = new Main( args );
		}

		exe.go();
	}

	/**
	 * Defined to conform to the Singleton pattern.
	 * 
	 * @since JDK1.4
	 */
	protected Main()
	{
	}

	/**
	 * @param filename
	 */
	protected Main( String filename )
	{
		reader = createReader( filename.endsWith( ".gz" ), filename );
	}

	/**
	 * Parses the command-line arguments. Determines if the file is a stream or
	 * a file. If the file ends in a <code>'.gz'</code> then the file is
	 * assumed to be gzipped. Finally, the appropriate <code>Reader</code> is
	 * constructed and stored in <code>reader</code>.
	 * <p>
	 * Defined to conform to the Singleton pattern.
	 * <p>
	 * 
	 * @param args
	 *            array of command-line arguments
	 * @since JDK1.4
	 */
	protected Main( String[] args )
	{
		boolean isZipped = false;
		String source = "";
		int sourceIdx = 0;

		if ( args.length == 2 )
		{
			if ( args[0].equals( "--zipped" ) )
			{
				isZipped = true;
			}

			sourceIdx = 1;
		}

		source = args[sourceIdx];

		reader = createReader( isZipped, source );
	}

	/**
	 * Creates a <code>Reader</code> specified by the parameters. Gzipping of
	 * animation files is handled by <code>GZIPInputStream</code>.
	 * 
	 * @param isZipped
	 *            true if the file is zipped
	 * @param source
	 * @return
	 * @since JDK1.4
	 * @see GZIPInputStream
	 */
	protected Reader createReader( boolean isZipped, String source )
	{
		InputStream istream = null;

		try
		{
			if ( source.equals( "-" ) )
			{
				istream = System.in;
			}
			else if ( source.equals( "socket" ) )
			{
				ServerSocket ss = new ServerSocket( 3333 );
				Socket s = ss.accept();
				istream = s.getInputStream();
			}
			else if ( source.endsWith( "xml" ) )
			{
				// FUTURE: this is a hack...whole viewer needs to be redone
				istream = createSweepInputStream( source );
			}
			else
			{
				istream = new FileInputStream( source );
			}

			if ( isZipped )
			{
				istream = new GZIPInputStream( istream );
			}
		}
		catch( Exception ioe )
		{
			ioe.printStackTrace();
			System.err.println( "[ERROR] Could not open '" + source + "'." );
			System.exit( 0 );
		}

		return new InputStreamReader( istream );
	}

	/**
	 * @param source
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private InputStream createSweepInputStream( String source ) throws DocumentException, IOException
	{
		final String PROBE_CLASS = "sweep.probe.NetworkAnimationProbeBuilder";
		Document doc = Util.createDocumentFromFile( source );

		Node node = doc.selectSingleNode( "/simulation/probes/probe/builder[@class='" + PROBE_CLASS + "']" );

		if ( node == null )
		{
			System.err.println( "...adding the required probes" );

			String localAddress = InetAddress.getLocalHost().getHostAddress();
			String probeTypeName = new UID().toString();

			Element probeElement = doc.getRootElement().element( "probes" ).addElement( "probe" );
			probeElement.addAttribute( "name", probeTypeName );
			probeElement.addElement( "builder" ).addAttribute( "class", PROBE_CLASS );

			String environmentName = ( ( Element )doc.selectSingleNode( "/simulation/main/environment[1]" ) )
					.attributeValue( "name" );

			probeElement = doc.getRootElement().element( "main" ).addElement( "probe" );
			probeElement.addAttribute( "name", new UID().toString() ).addAttribute( "type", probeTypeName );
			probeElement.addElement( "network" ).addAttribute( "ip", localAddress ).addAttribute( "port", "3333" );
			probeElement.addElement( "environment" ).addAttribute( "name", environmentName );
		}
		else
		{
			System.err.println( "...found an existing declaration of " + PROBE_CLASS );
		}

		System.err.println( "...starting sweep" );
		Runnable sweep = ( Runnable )DynamicClassloader.newInstance( "sweep.SWEEP", new Class[] {Document.class,
				long.class }, new Object[] {doc, new Long( new Random().nextLong() ) } );

		new Thread( sweep ).start();

		System.err.println( "...opening ServerSocket on port 3333" );
		ServerSocket ss = new ServerSocket( 3333 );

		Socket s = ss.accept();

		System.err.println( "...got a socket..." );

		return s.getInputStream();

		// TODO: yes, yes, ugly code....but it works for now
	}

	/**
	 * @return A string indicating the proper command-line usage
	 */
	private static String usage()
	{
		return "java sweep.sim.viewer [--zipped] (- | filename)";
	}

	/**
	 * Handles setting up the GUI.
	 * 
	 * @since JDK1.4
	 */
	protected void go()
	{
		JFrame frame = new JFrame( "SwarmSimViewer" );
		ViewerPanel panel = new ViewerPanel( reader );

		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setSize( new Dimension( 500, 500 ) );
		frame.getContentPane().add( panel );
		frame.show();
	}
}