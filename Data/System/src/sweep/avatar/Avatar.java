/*
 * Created on Feb 26, 2004
 */

package sweep.avatar;

import miko.query.Queryable;


public interface Avatar extends Queryable
{
	void initialize();

	void update();

	void destroy();
}