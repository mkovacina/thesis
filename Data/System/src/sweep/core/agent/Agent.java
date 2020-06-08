package sweep.core.agent;

import miko.query.Queryable;


public interface Agent extends Queryable
{
	public void initialize();

	// XXX: maybe put back the Strategy parameter to update
	public void update();

	public void destroy();
}