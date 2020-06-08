package sweep.model;

import java.util.Map;


/**
 * Description: SweepModel
 * 
 * @author Michael A. Kovacina
 */
public class SweepModel extends StatefulModel
{
	/**
	 * @param attributes
	 */
	public SweepModel( final Map attributes )
	{
		super();
		this.state.putAll(attributes);
	}
	
	/** 
	 * @see miko.query.Queryable#query(java.lang.String)
	 */
	public Object query( String queryString )
	{
		return state.get( queryString );
	}
}