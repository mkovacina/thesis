/*
 * Created on Mar 19, 2004
 */

package sweep.probe;

import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import standard.probe.ProbeBuilder;
import sweep.core.probe.Probe;
import sweep.ice.ProbeConnector;


/**
 * @author orbital
 */
public class NetworkAnimationProbeBuilder extends ProbeBuilder
{
	protected String filename;

	/**
	 * @param probeElement
	 */
	public NetworkAnimationProbeBuilder( Element probeElement )
	{
		super( probeElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element probeElement )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.probe.ProbeBuilder#createProbe(java.util.Map,
	 *      sweep.ice.ProbeConnector)
	 */
	protected Probe createProbe( Map parameters, ProbeConnector connector )
	{
		String address = "";
		int port = 0;
		String environmentName = "";
		OutputStream ostream = null;

		List extraParameters = ( List )parameters.get( "extraParameters" );

		for( int x = 0; x < extraParameters.size(); x++ )
		{
			Element e = ( Element )extraParameters.get( x );

			// TODO: the socket creation may be put into a factory or something
			// so that the factory is passed to the probe, and the socket is
			// created upon initialization
			if ( "network".equalsIgnoreCase( e.getName() ) )
			{
				address = e.attributeValue( "ip" );
				port = Integer.parseInt( e.attributeValue( "port" ) );

				ostream = null;
				while( ostream == null )
				{
					try
					{
						Thread.sleep( 2000 );
						Socket s = new Socket( address, port );
						ostream = s.getOutputStream();
					}
					catch( Exception exception )
					{
						System.err.println( "Error: " + exception.getMessage() );
						System.err.println( "...connection refused...waiting a bit..." );
					}
				}
			}

			if ( "environment".equalsIgnoreCase( e.getName() ) )
			{
				environmentName = e.attributeValue( "name" );
			}
		}

		return new NetworkAnimationProbe( ostream, environmentName, connector );
	}
}