/*
 * Created on Jan 30, 2004
 */
package standard.info;

import java.util.Map;

import org.dom4j.Element;

import sweep.info.Info;
import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public abstract class InfoBuilder extends Builder
{
	/**
	 * @param containerElement
	 */
	public InfoBuilder( Element infoElement )
	{
		super( infoElement );
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
	protected void process( Element infoElement )
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return createInfo( parameters );
	}

	abstract protected Info createInfo( Map parameters );
}