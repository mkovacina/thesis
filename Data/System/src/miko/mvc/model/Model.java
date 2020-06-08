package miko.mvc.model;

import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public interface Model
{
	/**
	 * @param view
	 * @author mkovacina
	 */
	void add( View view );

	/**
	 * @author mkovacina
	 */
	void initialize();
}