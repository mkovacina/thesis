/*
 * Created on Oct 15, 2004
 */
package miko.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;


/**
 * @author orbital
 */
public class List2 implements java.util.List
{
	private List list;
	private Vector listeners = new Vector();

	/**
	 *  
	 */
	public List2()
	{
		super();

		list = new ArrayList();
	}

	public List2( List list )
	{
		this.list = list;
	}

	public void addListDataListener( ListDataListener listener )
	{
		listeners.add( listener );
		fireEvent( listener );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#size()
	 */
	public int size()
	{
		return list.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains( Object o )
	{
		return list.contains( o );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#iterator()
	 */
	public Iterator iterator()
	{
		return list.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray()
	 */
	public Object[] toArray()
	{
		return list.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	public Object[] toArray( Object[] a )
	{
		return list.toArray( a );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	public boolean add( Object o )
	{
		boolean result = list.add( o );

		if ( result == true )
		{
			fireEvent();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove( Object o )
	{
		boolean result = list.remove( o );

		if ( result == true )
		{
			fireEvent();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll( Collection c )
	{
		return list.containsAll( c );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll( Collection c )
	{
		boolean result = list.addAll( c );

		if ( result == true )
		{
			fireEvent();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll( int index, Collection c )
	{
		boolean result = list.addAll( index, c );

		if ( result == true )
		{
			fireEvent();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll( Collection c )
	{
		boolean result = list.removeAll( c );

		if ( result == true )
		{
			fireEvent();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll( Collection c )
	{
		boolean result = list.retainAll( c );

		if ( result == true )
		{
			fireEvent();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Collection#clear()
	 */
	public void clear()
	{
		list.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#get(int)
	 */
	public Object get( int index )
	{
		return list.get( index );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public Object set( int index, Object element )
	{
		Object o = list.set( index, element );

		fireEvent();

		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add( int index, Object element )
	{
		list.add( index, element );

		fireEvent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#remove(int)
	 */
	public Object remove( int index )
	{
		Object o = list.remove( index );

		fireEvent();

		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf( Object o )
	{
		return list.indexOf( o );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf( Object o )
	{
		return list.lastIndexOf( o );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator()
	 */
	public ListIterator listIterator()
	{
		return list.listIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator listIterator( int index )
	{
		return list.listIterator( index );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.List#subList(int, int)
	 */
	public List subList( int fromIndex, int toIndex )
	{
		return list.subList( fromIndex, toIndex );
	}

	/**
	 *  
	 */
	private void fireEvent()
	{
		synchronized( this )
		{
			ListDataEvent e = new ListDataEvent( list, ListDataEvent.CONTENTS_CHANGED, -1, -1 );

			for( int i = 0, size = listeners.size(); i < size; i++ )
			{
				( ( ListDataListener )listeners.get( i ) ).contentsChanged( e );
			}
		}
	}

	private void fireEvent( ListDataListener listener )
	{
		synchronized( this )
		{
			ListDataEvent e = new ListDataEvent( list, ListDataEvent.CONTENTS_CHANGED, -1, -1 );
			listener.contentsChanged( e );
		}
	}
}