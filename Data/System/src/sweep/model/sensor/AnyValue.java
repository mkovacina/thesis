/*
 * Created on Mar 16, 2004
 */
package sweep.model.sensor;

/**
 * @author orbital
 */
public class AnyValue extends Value
{
	public AnyValue()
	{
	}

	public boolean equals( Object o )
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo( Object o )
	{
		return EQUALS;
	}
}