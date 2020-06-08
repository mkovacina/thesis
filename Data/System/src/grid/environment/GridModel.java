package grid.environment;

import java.awt.Point;


public interface GridModel
{
	// FUTURE: add replace(x,y,value) and replace(p,value)

	public abstract int get( Point p );

	public abstract int get( int x, int y );

	public abstract boolean set( Point p, int value );

	public abstract boolean set( int x, int y, int value );

	public abstract int remove( int x, int y );

	public abstract int remove( Point p );

	public abstract void clear( int x, int y );

	public abstract void clear( Point p );

	public abstract boolean isClear( int x, int y );

	public abstract boolean isClear( Point p );

	// FUTURE: these below might get replaced with a getInfo returning a general
	// object
	public abstract int getRows();

	public abstract int getColumns();
}