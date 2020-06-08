package sweep.core.environment;

import miko.query.Queryable;


public interface Environment extends Queryable
{
	void initialize();

	void update();
}