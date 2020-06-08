/*
 * Created on Mar 18, 2004
 */

package miko.query;

/**
 * @author orbital <p/>The purpose of this class is to hide the exposed
 *         non-Queryable interface of a class that implements the Queryable
 *         interface
 */
public class QueryProxy implements Queryable
{
	private final Queryable target;

	/**
	 *  
	 */
	public QueryProxy( Queryable target )
	{
		this.target = target;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.query.Queryable#query(java.lang.String)
	 */
	public Object query( String queryString )
	{
		return target.query( queryString );
	}
}