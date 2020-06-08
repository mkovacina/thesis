package sweep.model;

import miko.query.Queryable;
import sweep.core.agent.State;


public interface Model extends Queryable
{
	// used for Stateful models
	void initialize();

	void update();

	State getState();

	// used for Stateless models
	void initialize( State state );

	void update( State state );
}