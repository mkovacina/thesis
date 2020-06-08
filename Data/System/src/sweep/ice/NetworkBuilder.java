/*
 * Created on Mar 10, 2004
 */

package sweep.ice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author orbital
 */
public class NetworkBuilder
{
	private Map environmentMap;
	protected List networks;

	/**
	 * @param environmentMap
	 */
	public NetworkBuilder( Map environmentMap )
	{
		this.environmentMap = environmentMap;
		networks = new ArrayList();
	}

	public EnvironmentConnector createEnvironmentConnector()
	{
		int size = networks.size();
		List avatars = new ArrayList( size );
		List models = new ArrayList( size );

		for( int x = 0; x < size; x++ )
		{
			AetherNet net = ( AetherNet )networks.get( x );

			avatars.add( net.avatar );
			models.add( net.model );
			//XXX:
			//avatars.add(new QueryProxy(net.avatar));
			//models.add(new QueryProxy(net.model));
		}

		return new EnvironmentConnector( avatars, models );
	}

	public ProbeConnector createProbeConnector()
	{
		int size = networks.size();
		List avatars = new ArrayList( size );
		List models = new ArrayList( size );
		List agents = new ArrayList( size );

		for( int x = 0; x < size; x++ )
		{
			AetherNet net = ( AetherNet )networks.get( x );
			avatars.add( net.avatar );
			models.add( net.model );
			agents.add( net.agent );
		}

		return new ProbeConnector( environmentMap, agents, avatars, models );
	}

	public AetherNet createNetwork()
	{
		AetherNet aethernet = new AetherNet( environmentMap );

		// this is where the environments get hooked in
		networks.add( aethernet );

		return aethernet;
	}
}