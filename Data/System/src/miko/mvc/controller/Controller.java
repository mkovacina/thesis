package miko.mvc.controller;

import miko.message.Messagable;
import miko.mvc.model.Model;
import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public interface Controller extends Messagable
{
	/**
	 * @param model
	 */
	void add( Model model );

	/**
	 * @param view
	 */
	void add( View view );

	/**
	 * @author mkovacina
	 */
	void initialize();
}