/*
 * Created on Feb 23, 2004
 */
package rule;

/**
 * @author orbital
 */
public interface Operator
{
	boolean eval( Object l_val, Object r_val );
}