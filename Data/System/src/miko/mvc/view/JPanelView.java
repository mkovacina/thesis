package miko.mvc.view;

import javax.swing.JPanel;

import miko.message.Message;
import miko.mvc.controller.Controller;
import miko.mvc.model.Model;


/**
 * @author mkovacina
 */
public class JPanelView extends JPanel implements View
{
	protected Model model;
	protected Controller controller;

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.pattern.View#add(miko.pattern.Model)
	 */
	public void initialize()
	{
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see miko.message.Messagable#message(miko.message.Message)
	 */
	public void message( Message message )
	{
	}
}