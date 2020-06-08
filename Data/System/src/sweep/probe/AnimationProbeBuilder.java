/*
 * Created on Mar 19, 2004
 */

package sweep.probe;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import standard.probe.ProbeBuilder;
import sweep.core.probe.Probe;
import sweep.ice.ProbeConnector;


/**
 * @author orbital
 */
public class AnimationProbeBuilder extends ProbeBuilder
{
	protected String filename;

	/**
	 * @param probeElement
	 */
	public AnimationProbeBuilder( Element probeElement )
	{
		super( probeElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sweep.util.builder.Builder#initialize()
	 */
	protected void initialize()
	{
		super.initialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.probe.ProbeBuilder#createProbe(java.util.Map,
	 *      sweep.ice.ProbeConnector)
	 */
	protected Probe createProbe( Map parameters, ProbeConnector connector )
	{
		String filename = "";
		String environmentName = "";

		List extraParameters = ( List )parameters.get( "extraParameters" );

		for( int x = 0; x < extraParameters.size(); x++ )
		{
			Element e = ( Element )extraParameters.get( x );

			if ( "file".equalsIgnoreCase( e.getName() ) )
			{
				filename = e.attributeValue( "name" );
			}

			if ( "environment".equalsIgnoreCase( e.getName() ) )
			{
				environmentName = e.attributeValue( "name" );
			}
		}

		return new AnimationProbe( filename, environmentName, connector );
	}
}