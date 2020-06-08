/*
 * Created on Jul 28, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package miko.distributed;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Observable;

import miko.net.NetSend;


/**
 * @author razor
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Worker extends Observable implements Runnable
{
	private static int id_counter = 0;

	private final int id = id_counter++;

	private boolean available = true;

	private final Reader reader;
	private final Writer writer;

	public Worker( InputStream istream, OutputStream ostream )
	{
		reader = new BufferedReader( new InputStreamReader( istream ) );
		writer = new BufferedWriter( new OutputStreamWriter( ostream ) );
	}

	public void exec( String s ) throws Exception
	{
		if ( available )
		{
			NetSend.send( writer, s );
			available = false;
			new Thread( this ).start();
		}
	}

	public void run()
	{
		try
		{
			String s = NetSend.get( reader );
			System.out.println( s );
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}

		setChanged();
		notifyObservers();

		available = true;
	}

	public String toString()
	{
		return "Worker-" + id;
	}
}