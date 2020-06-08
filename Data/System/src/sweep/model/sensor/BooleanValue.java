/*
 * Created on Mar 16, 2004
 */

package sweep.model.sensor;

/**
 * @author orbital
 */
public class BooleanValue extends Value
{
	protected final boolean value;

	public static final BooleanValue TRUE;
	public static final BooleanValue FALSE;

	static
	{
		TRUE = new BooleanValue( true );
		FALSE = new BooleanValue( false );
	}

	public BooleanValue( boolean value )
	{
		this.value = value;
	}

	public boolean equals( Object o )
	{
		return compareTo( o ) == EQUALS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo( Object o )
	{
		if ( o instanceof BooleanValue != true )
		{
			// REFACTOR: don't like this....could go infinite, but is needed
			// for AnyValue to work on the right
			return ( ( Value )o ).compareTo( this );
		}

		// REFACTOR: what if it is not a BooleanValue??
		BooleanValue bv = ( BooleanValue )o;

		if ( bv.value == value )
		{
			return EQUALS;
		}
		else if ( value == false )
		{
			return LESS_THAN;
		}
		else
		{
			return GREATER_THAN;
		}
	}

	public String toString()
	{
		return "" + value;
	}
}