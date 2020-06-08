/*
 * Created on Mar 19, 2004
 */

package sweep.probe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import standard.probe.ProbeBuilder;
import sweep.core.probe.Probe;
import sweep.ice.ProbeConnector;


/**
 * @author orbital
 */
public class SummaryProbeBuilder extends ProbeBuilder
{
	// XXX: oh, nasty bug, since the builder super constructor calls initialize,
	// the object is not fully initialized, thus member fields are not
	// initialized and thus get NPEs
	private List sumSpecs;
	private OutputSpec outputSpec;

	/**
	 * @param probeElement
	 */
	public SummaryProbeBuilder( Element probeElement )
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
		sumSpecs = new ArrayList();
		List sumElements = probeElement.elements( "sum" );

		for( int i = 0; i < sumElements.size(); i++ )
		{
			Element sumElement = ( Element )sumElements.get( i );
			SumSpec spec = new SumSpec();
			spec.swarmName = sumElement.attributeValue( "swarm" );
			spec.varName = sumElement.attributeValue( "var" );
			sumSpecs.add( spec );
		}

		outputSpec = new OutputSpec();

		outputSpec.onScreen = probeElement.element( "screen" ) != null;
		outputSpec.toFile = probeElement.element( "file" ) != null;
		if ( outputSpec.toFile )
		{
			outputSpec.filename = probeElement.element( "file" ).attributeValue( "name" );
		}
	}

	/**
	 * Description: SumSpec
	 * 
	 * @author Michael A. Kovacina
	 */
	public class SumSpec
	{
		/**
		 * <code>swarmName</code>
		 */
		public String swarmName;
		/**
		 * <code>varName</code>
		 */
		public String varName;
	}

	/**
	 * Description: OutputSpec
	 * 
	 * @author Michael A. Kovacina
	 */
	public class OutputSpec
	{
		/**
		 * <code>onScreen</code>
		 */
		public boolean onScreen = false;
		/**
		 * <code>toFile</code>
		 */
		public boolean toFile = false;
		/**
		 * <code>filename</code>
		 */
		public String filename = "";
	}

	protected Probe createProbe( Map parameters, ProbeConnector connector )
	{
		return new SummaryProbe( sumSpecs, outputSpec, connector );
	}
}