package miko.mvc.controller;

import miko.mvc.model.Model;
import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public abstract class AbstractController implements Controller
{
	protected Model model;
	protected View view;

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.Controller#add(miko.pattern.Model)
	 */
	public void add( Model model )
	{
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.Controller#add(miko.pattern.View)
	 */
	public void add( View view )
	{
		this.view = view;
	}
}