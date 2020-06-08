/*
 * Created on Jan 30, 2004
 */

package standard.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import miko.loader.DynamicClassloader;

import org.dom4j.Element;

import sweep.ice.ModelConnector;
import sweep.model.Initializer;
import sweep.model.Model;
import sweep.model.ModelUpdate;
import sweep.model.SensorUpdate;
import sweep.model.Update;
import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public class ModelBuilder extends Builder
{
	protected String clazz;
	protected Map attributes;
	protected Map sensors;
	protected Map actions;
	protected Initializer[] initializers;
	protected Update[] updates;
	protected Class[] formalParameters;
	protected Object[] actualParameters;

	/**
	 * @param modelElement XML element containing the model description
	 */
	public ModelBuilder( Element modelElement )
	{
		super( modelElement );
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
	protected void process( Element modelElement )
	{
		clazz = modelElement.attributeValue( "class" );
		attributes = Util.createAttributeMap( modelElement.element( "attributes" ) );
		sensors = Util.createMap( modelElement.element( "sensors" ) );
		actions = Util.createMap( modelElement.element( "actions" ) );

		if ( modelElement.elements( "initializers" ).size() != 0 )
		{
			initializers = ( Initializer[] )Util.collectInitializers( modelElement.element( "initializers" ) ).toArray(
					new Initializer[0] );
		}
		else
		{
			initializers = new Initializer[0];
		}

		/*
		 * if ( modelElement.elements("updates").size() != 0) { updates =
		 * (Update[])Util.collectUpdates(modelElement.element("updates")).toArray(new
		 * Update[0]); } else { updates = new Update[] { new
		 * SensorUpdate(Collections.EMPTY_MAP), new
		 * ModelUpdate(Collections.EMPTY_MAP)}; }
		 */

		updates = new Update[] {new SensorUpdate( Collections.EMPTY_MAP ), new ModelUpdate( Collections.EMPTY_MAP ) };

		formalParameters = new Class[] {ModelConnector.class, Initializer[].class, Update[].class, Map.class,
				Map.class, Map.class };
		actualParameters = new Object[] {null, initializers, updates, null, sensors, actions };
	}

	/** 
	 * @see sweep.util.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		ModelConnector connector = ( ModelConnector )parameters.get( "connector" );

		return createModel( connector, parameters );
	}

	protected Model createModel( ModelConnector connector, Map parameters )
	{
		Map extraAttributes = ( Map )parameters.get( "attributes" );

		Map finalAttributes = new HashMap();

		// NOTE: the order is important, seeing as how the extraAttributes
		// should override those in place already
		finalAttributes.putAll( attributes );
		finalAttributes.putAll( extraAttributes );

		actualParameters[0] = connector;
		actualParameters[3] = finalAttributes;

		Model model = ( Model )DynamicClassloader.newInstance( clazz, formalParameters, actualParameters );

		return model;
	}
}