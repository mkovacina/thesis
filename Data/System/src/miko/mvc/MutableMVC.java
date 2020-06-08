package miko.mvc;

import miko.mvc.controller.Controller;
import miko.mvc.model.Model;
import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public interface MutableMVC
{
	/**
	 * @param controller
	 *            The controller to set.
	 */
	public void setController( Controller controller );

	/**
	 * @param model
	 *            The model to set.
	 */
	public void setModel( Model model );

	/**
	 * @param view
	 *            The view to set.
	 */
	public void setView( View view );

	/**
	 * Connects the model, view, and controller.
	 */
	public void connect();
}