/*
 * Created on Oct 3, 2004
 */
package grid.environment;

import grid.model.GridAvatar;

import java.awt.Point;


/**
 * @author orbital
 */
public interface LocationManager
{
	public boolean locationClear( Point p );

	public boolean locationClear( int x, int y );

	public boolean move( GridAvatar model, int dx, int dy );
}