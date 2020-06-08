/*
 * Created on Jan 30, 2004
 */
package sweep.model.sensor;

/**
 * @author orbital
 */
public abstract class Value implements Comparable
{
	public static final int EQUALS = 0;
	public static final int LESS_THAN = -1;
	public static final int GREATER_THAN = 1;

	public abstract int compareTo( Object o );

	public abstract boolean equals( Object o );

	// REFACTOR: put in other relational operators
	// REFACTOR: can really set up a test harness for these classes
}