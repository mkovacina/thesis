/*
 * Created on Oct 14, 2004
 */
package miko.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;


/**
 * @author orbital
 */
public class ObjectTableModel extends AbstractTableModel
{
	private List data;
	private DataHandler dataHelper;
	private String[] columnNames;
	private boolean isEditable = false;

	public ObjectTableModel( DataHandler dh )
	{
		data = new ArrayList();
		dataHelper = dh;
		columnNames = dh.getColumnNames();
	}

	public ObjectTableModel( DataHandler dh, boolean isEditable )
	{
		this( dh );
		this.isEditable = isEditable;
	}

	public ObjectTableModel( List data, DataHandler dh, boolean isEditable )
	{
		this.data = data;
		this.dataHelper = dh;
		this.isEditable = isEditable;
		this.columnNames = dh.getColumnNames();
	}

	public List getData()
	{
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable( int rowIndex, int columnIndex )
	{
		return isEditable;
	}

	public void addRow( Object o )
	{
		int row = data.size();
		data.add( o );
		fireTableRowsInserted( row, row );
	}

	public void addAllRows( Collection c )
	{
		Iterator iter = c.iterator();

		while( iter.hasNext() )
		{
			addRow( iter.next() );
		}
	}

	public void removeRow( Object o )
	{
		int row = data.indexOf( o );//data.size();
		if ( row == -1 )
			return;

		data.remove( row );
		fireTableRowsDeleted( row, row );
	}

	public void removeAllRows( Collection c )
	{
		Iterator iter = c.iterator();

		while( iter.hasNext() )
		{
			removeRow( iter.next() );
		}
	}

	/**
	 * If the Object <code>o</code> is in the data set, update its row
	 * 
	 * @param o
	 */
	public void updateRow( Object o )
	{
		int row = data.indexOf( o );

		if ( row != -1 )
			fireTableRowsUpdated( row, row );
	}

	public Object getObect( int row )
	{
		if ( row < 0 || row >= data.size() )
			return null;

		return data.get( row );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt( int rowIndex, int columnIndex )
	{
		if ( rowIndex < 0 || rowIndex >= data.size() )
			return null;

		return dataHelper.get( data.get( rowIndex ), columnIndex );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, java.lang.String)
	 */
	public Object getValueAt( int rowIndex, String key )
	{
		return dataHelper.get( data.get( rowIndex ), key );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount()
	{
		return data.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount()
	{
		return columnNames.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnName(int)
	 */
	public String getColumnName( int column )
	{
		if ( column >= columnNames.length )
			return "";

		return columnNames[column];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt( Object aValue, int rowIndex, int columnIndex )
	{
		dataHelper.set( data.get( rowIndex ), columnIndex, aValue );

		fireTableCellUpdated( rowIndex, columnIndex );
	}
}