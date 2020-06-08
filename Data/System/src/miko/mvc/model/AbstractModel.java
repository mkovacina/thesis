package miko.mvc.model;

import miko.mvc.view.View;


/**
 * @author mkovacina
 */
public abstract class AbstractModel implements Model
{
	protected View view;

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.Model#add(miko.pattern.View)
	 */
	public void add( View view )
	{
		this.view = view;
	}
}