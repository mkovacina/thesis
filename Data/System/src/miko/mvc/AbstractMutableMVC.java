package miko.mvc;

import miko.mvc.controller.Controller;
import miko.mvc.model.Model;
import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public abstract class AbstractMutableMVC extends AbstractMVC implements MutableMVC
{
	protected AbstractMutableMVC()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MutableMVC#setController(miko.pattern.Controller)
	 */
	public void setController( Controller controller )
	{
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MutableMVC#setModel(miko.pattern.Model)
	 */
	public void setModel( Model model )
	{
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MutableMVC#setView(miko.pattern.View)
	 */
	public void setView( View view )
	{
		this.view = view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MutableMVC#connect()
	 */
	public void connect()
	{
		super.connect();
	}
}