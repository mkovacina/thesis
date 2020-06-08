package miko.mvc.view;

import java.awt.LayoutManager;
import java.awt.Point;

import javax.swing.JComponent;

import miko.message.Message;
import miko.mvc.controller.Controller;
import miko.mvc.model.Model;


/**
 * @author mkovacina
 */
public class ComponentView extends JComponent implements View, Connectable
{
	protected Model model;
	protected Controller controller;

	public ComponentView( LayoutManager lm )
	{
		setLayout( lm );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.View#add(miko.pattern.Model)
	 */
	public void initialize()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.View#add(miko.pattern.Model)
	 */
	public void add( Model model )
	{
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.View#add(miko.pattern.Controller)
	 */
	public void add( Controller controller )
	{
		this.controller = controller;
	}

	protected Point getLocationGlobal()
	{
		// [parent-local] get top-left location
		Point pt = getLocation();

		// [parent-global] get the parent's location in the local coordinate
		// system, relative to them
		Point parent = getParent().getLocation();

		// [parent-global] translate the component into the parent's coordinate
		// system
		pt.translate( parent.x, parent.y );

		return pt;
	}

	public Point getControlBottomPoint()
	{
		Point pt = getLocationGlobal();

		// [parent-global] translate to the bottom middle of the component
		pt.translate( getWidth() / 2, getHeight() );

		return pt;
	}

	public Point getControlTopPoint()
	{
		Point pt = getLocationGlobal();

		// [parent-global] translate to the top middle of the component
		pt.translate( getWidth() / 2, 0 );

		return pt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.message.Messagable#message(miko.message.Message)
	 */
	public void message( Message message )
	{
	}
}