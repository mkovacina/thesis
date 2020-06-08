/*
 * Created on Jul 28, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package miko.distributed;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * @author razor
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WorkGroup
{
	protected final List freeWorkers = new ArrayList();
	protected final List jobQueue = new ArrayList();

	public WorkGroup( int port ) throws IOException
	{
		JobShop jobShop = new JobShop();

		new Thread( new ConnectionListener( port, jobShop ) ).start();
		new Thread( jobShop ).start();
	}

	public void addJob( String s )
	{
		jobQueue.add( s );
		System.err.println( "added job" );
	}

	private class JobShop implements Runnable, Observer
	{
		public void run()
		{
			while( true )
			{
				if ( jobQueue.size() > 0 && freeWorkers.size() > 0 )
				{
					String s = ( String )jobQueue.remove( 0 );
					Worker w = ( Worker )freeWorkers.remove( 0 );

					try
					{
						w.exec( s );
						System.err.println( "assigned job" );
					}
					catch( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Observer#update(java.util.Observable,
		 *      java.lang.Object)
		 */
		public void update( Observable arg0, Object arg1 )
		{
			freeWorkers.add( arg0 );
			System.err.println( "retrieved worker: " + arg0 );
		}
	}

	private class ConnectionListener implements Runnable
	{
		private final ServerSocket serverSocket;
		private final Observer observer;

		public ConnectionListener( int port, Observer observer ) throws IOException
		{
			serverSocket = new ServerSocket( port );
			this.observer = observer;
		}

		public void run()
		{
			while( true )
			{
				try
				{
					Socket s = serverSocket.accept();
					Worker w = new Worker( s.getInputStream(), s.getOutputStream() );
					w.addObserver( observer );
					freeWorkers.add( w );
					System.err.println( "registered worker" );
				}
				catch( IOException e )
				{
					e.printStackTrace();
				}
			}
		}
	}
}