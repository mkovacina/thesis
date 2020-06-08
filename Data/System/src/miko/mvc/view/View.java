package miko.mvc.view;

import miko.message.Messagable;
import miko.mvc.controller.Controller;
import miko.mvc.model.Model;


/**
 * @author mkovacina
 */
public interface View extends Messagable
{
	/**
	 * @author mkovacina
	 * @param model
	 */
	void add( Model model );

	/**
	 * @author mkovacina
	 * @param controller
	 */
	void add( Controller controller );

	/**
	 * @author mkovacina
	 */
	void initialize();
}