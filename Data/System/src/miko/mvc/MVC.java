package miko.mvc;

import miko.mvc.controller.Controller;
import miko.mvc.model.Model;
import miko.mvc.view.View;


public interface MVC
{
	/**
	 * @return Returns the controller.
	 */
	public Controller getController();

	/**
	 * @return Returns the model.
	 */
	public Model getModel();

	/**
	 * @return Returns the view.
	 */
	public View getView();
}