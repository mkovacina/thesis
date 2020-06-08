/*
 * Created on Jul 30, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package miko.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * @author razor
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NetPipe implements Runnable
{
	private final InputStream istream;
	private final OutputStream ostream;

	/**
	 *  
	 */
	public NetPipe( InputStream istream, OutputStream ostream )
	{
		this.istream = new BufferedInputStream( istream );
		this.ostream = new BufferedOutputStream( ostream );
	}

	public void run()
	{
		byte[] buffer = new byte[512];

		while( true )
		{
			int numBytesRead = 0;

			try
			{
				if ( ( numBytesRead = istream.read( buffer ) ) == -1 )
				{
					istream.close();
					ostream.close();
					return;
				}

				ostream.write( buffer, 0, numBytesRead );
			}
			catch( Exception e )
			{
				e.printStackTrace();
				return;
			}
		}
	}

	public static void main( String[] args ) throws UnknownHostException, IOException
	{
		int argc = 0;

		String inputMethod = args[argc++];

		InputStream istream;

		if ( inputMethod.equals( "stdin" ) )
		{
			istream = System.in;
		}
		else if ( inputMethod.equals( "file" ) )
		{
			String filename = args[argc++];

			istream = new FileInputStream( filename );
		}
		else
		{
			System.err.println( "Usage: NetPipe [stdin | file <filename>] ipAddr port" );
			return;
		}

		String ipAddr = args[argc++];
		int port = Integer.parseInt( args[argc++] );

		Socket s = new Socket( ipAddr, port );

		new Thread( new NetPipe( istream, s.getOutputStream() ) ).start();
	}
}