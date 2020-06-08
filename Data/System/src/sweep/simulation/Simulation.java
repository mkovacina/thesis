package sweep.simulation;

import sweep.info.Info;


public interface Simulation
{
	public Info getInfo();

	public void initialize( long seed );

	public void run();
}