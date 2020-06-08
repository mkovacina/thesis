package sweep.probe;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import sweep.avatar.SweepAvatar;
import sweep.core.probe.Probe;
import sweep.ice.ProbeConnector;
import sweep.probe.SummaryProbeBuilder.OutputSpec;
import sweep.probe.SummaryProbeBuilder.SumSpec;


/**
 * Description: SummaryProbe
 * 
 * @author Michael A. Kovacina
 */
public class SummaryProbe implements Probe
{
	SumSpec[] specs = new SumSpec[0];
	OutputSpec outputSpec;
	ProbeConnector connector;
	PrintWriter pw;

	/**
	 * @param sumSpecs
	 * @param outputSpec
	 * @param connector
	 */
	public SummaryProbe( List sumSpecs, OutputSpec outputSpec, ProbeConnector connector )
	{
		specs = ( SumSpec[] )sumSpecs.toArray( specs );
		this.outputSpec = outputSpec;
		this.connector = connector;
	}

	/**
	 * @see sweep.core.probe.Probe#initialize()
	 */
	public void initialize()
	{
		if ( outputSpec.toFile )
		{
			try
			{
				pw = new PrintWriter( new FileWriter( outputSpec.filename ) );
			}
			catch( IOException e )
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see sweep.core.probe.Probe#update()
	 */
	public boolean update()
	{
		return true;
	}

	/**
	 * @see sweep.core.probe.Probe#destroy()
	 */
	public void destroy()
	{
		SweepAvatar[] avatars = ( SweepAvatar[] )connector.avatars.toArray( new SweepAvatar[0] );

		for( int i = 0; i < specs.length; i++ )
		{
			SumSpec spec = specs[i];
			int sum = 0;

			for( int j = 0; j < avatars.length; j++ )
			{
				SweepAvatar avatar = avatars[j];

				if ( spec.swarmName.equals( avatar.query( "swarm" ) ) )
				{
					Object obj = avatar.query( spec.varName );

					if ( obj != null )
					{
						sum += ( ( Integer )obj ).intValue();
					}
				}
			}

			String output = spec.swarmName + ":" + spec.varName + ":" + sum;
			
			if ( outputSpec.onScreen )
			{
				System.out.println( output );
			}
			
			if ( outputSpec.toFile )
			{
				pw.println(output);
			}
		}
	}
}