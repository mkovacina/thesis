/*
 * Created on Jan 30, 2004
 */
package standard.probe;

import java.util.Map;

import org.dom4j.Element;

import sweep.core.probe.Probe;
import sweep.ice.ProbeConnector;
import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public abstract class ProbeBuilder extends Builder
{
	/**
	 * @param containerElement
	 */
	public ProbeBuilder( Element probeElement )
	{
		super( probeElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#initialize()
	 */
	protected void initialize()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element probeElement )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		ProbeConnector connector = ( ProbeConnector )parameters.get( "connector" );

		return createProbe( parameters, connector );
	}

	abstract protected Probe createProbe( Map parameters, ProbeConnector connector );
}