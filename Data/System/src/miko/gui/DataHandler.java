/*
 * Created on Oct 14, 2004
 */
package miko.gui;

/**
 * @author orbital
 */
public interface DataHandler
{
	/**
	 * @return the headers for each column
	 */
	String[] getColumnNames();

	/**
	 * @param key
	 * @return the value of the property of <code>object</code> indexed by
	 *         <code>key</code>
	 */
	Object get( Object object, String key );

	/**
	 * @param object
	 * @param col
	 * @return the value of the property of <code>object</code> indexed by
	 *         <code>col</code>
	 */
	Object get( Object object, int col );

	/**
	 * @param object
	 * @param columnIndex
	 * @param value
	 */
	void set( Object object, int columnIndex, Object value );
}