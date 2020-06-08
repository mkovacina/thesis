/*
 * Created on Jan 30, 2004
 */
package sweep.info;

import java.util.Map;

import org.dom4j.Element;

import standard.info.InfoBuilder;


/**
 * @author orbital
 */
public class NullInfoBuilder extends InfoBuilder
{
	/**
	 * @param containerElement
	 */
	public NullInfoBuilder( Element infoElement )
	{
		super( infoElement );
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
	 * @see standard.info.InfoBuilder#createInfo(java.util.Map)
	 */
	protected Info createInfo( Map parameters )
	{
		return new NullInfo();
	}

	public class NullInfo implements Info
	{
		public String description()
		{
			return "";
		}
	}
}