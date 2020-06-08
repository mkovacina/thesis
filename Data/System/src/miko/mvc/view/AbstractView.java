package miko.mvc.view;

import miko.mvc.controller.Controller;
import miko.mvc.model.Model;


/**
 * @author mkovacina
 */
public abstract class AbstractView implements View
{
	protected Model model;
	protected Controller controller;

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
}