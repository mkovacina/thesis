/*
 * Created on Jan 30, 2004
 */
package standard.controller;

import java.util.Map;

import org.dom4j.Element;

import sweep.core.agent.Controller;
import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public abstract class ControllerBuilder extends Builder
{
	/**
	 * @param containerElement
	 */
	public ControllerBuilder( Element controllerElement )
	{
		super( controllerElement );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#initialize()
	 */
	protected void initialize()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#process(org.dom4j.Element)
	 */
	protected void process( Element controllerElement )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return createController( parameters );
	}

	abstract protected Controller createController( Map parameters );
}