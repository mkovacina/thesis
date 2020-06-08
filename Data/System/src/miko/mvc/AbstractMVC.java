package miko.mvc;

import miko.mvc.controller.Controller;
import miko.mvc.model.Model;
import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public abstract class AbstractMVC implements MVC
{
	protected Model model;
	protected View view;
	protected Controller controller;

	protected AbstractMVC()
	{
	}

	protected AbstractMVC( Model model, View view, Controller controller )
	{
		init( model, view, controller );
	}

	protected void init( Model model, View view, Controller controller )
	{
		this.model = model;
		this.view = view;
		this.controller = controller;

		connect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MVC#getController()
	 */
	public Controller getController()
	{
		return controller;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MVC#getModel()
	 */
	public Model getModel()
	{
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MVC#getView()
	 */
	public View getView()
	{
		return view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.MVC#connect()
	 */
	protected void connect()
	{
		model.add( view );

		view.add( model );
		view.add( controller );

		controller.add( view );
		controller.add( model );

		model.initialize();
		view.initialize();
		controller.initialize();
	}
}