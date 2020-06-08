package miko.mvc.view;

import java.awt.Point;


/**
 * @author mkovacina
 */
public interface Connectable
{
	// XXX: this will slowly expand...getCP(index), getCP(degrees)

	Point getControlBottomPoint();

	Point getControlTopPoint();
}