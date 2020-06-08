/*
 * Created on Jul 17, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package miko.net;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;


/**
 * @author mike
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class NetSend
{
	protected NetSend()
	{
	}

	public static String get( Reader reader ) throws IOException
	{
		char[] sizeBuf = new char[10];
		readFully( reader, sizeBuf );
		int size = Integer.parseInt( new String( sizeBuf ).trim() );
		//System.out.println("NetSend.get => size = " + size);

		char[] documentBuf = new char[size];
		readFully( reader, documentBuf );

		//System.out.println("NetSend.get => document = " + new
		// String(documentBuf));

		return new String( documentBuf );
	}

	public static void send( Writer writer, String s ) throws IOException
	{
		StringBuffer sizeBuf = new StringBuffer( "" + s.length() );

		while( sizeBuf.length() < 10 )
		{
			sizeBuf.append( ' ' );
		}

		writer.write( sizeBuf.toString() );
		writer.write( s );
		writer.flush();

		//System.out.println("NetSend.send => size = "+sizeBuf);
		//System.out.println("NetSend.send => document = "+s);
	}

	public static void readFully( Reader reader, char[] buffer ) throws IOException
	{
		int bytesRead = 0;

		while( bytesRead < buffer.length )
		{
			int newBytesRead = reader.read( buffer, bytesRead, buffer.length - bytesRead );

			if ( newBytesRead == -1 )
			{
				throw new IOException( "Socket Terminated" );
			}

			bytesRead += newBytesRead;
		}
	}
}