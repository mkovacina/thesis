/*
 * Created on Jan 30, 2004
 */
package sweep.probe;

import java.util.Map;

import org.dom4j.Element;

import standard.probe.ProbeBuilder;
import sweep.core.probe.Probe;
import sweep.ice.ProbeConnector;


/**
 * @author orbital
 */
public class CounterProbeBuilder extends ProbeBuilder
{
	protected int iter;
	protected boolean iteration;

	/**
	 * @param probeElement
	 */
	public CounterProbeBuilder( Element probeElement )
	{
		super( probeElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element probeElement )
	{
		super.process( probeElement );

		iter = Integer.parseInt( probeElement.element( "iterations" ).attributeValue( "num" ) );
		iteration = probeElement.element( "print" ) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.probe.ProbeBuilder#createProbe(java.util.Map)
	 */
	protected Probe createProbe( Map parameters, ProbeConnector connector )
	{
		return new CounterProbe( iter, iteration );
	}
}