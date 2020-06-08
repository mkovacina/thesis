/*
 * Created on Jan 30, 2004
 */
package standard.environment;

import java.util.Map;

import miko.loader.DynamicClassloader;

import org.dom4j.Element;

import standard.model.Util;
import sweep.core.environment.Environment;
import sweep.environment.EnvironmentModel;
import sweep.environment.Initializer;
import sweep.environment.SweepEnvironment;
import sweep.environment.Update;
import sweep.util.builder.Builder;


/**
 * @author orbital
 */
public class EnvironmentBuilder extends Builder
{
	private Map attributes;
	private Initializer[] initializers;
	private Update[] updates;
	private Class[] formalParameters;
	private Object[] actualParameters;
	private String clazz;

	/**
	 * @param containerElement
	 */
	public EnvironmentBuilder( Element environmentElement )
	{
		super( environmentElement );
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
	protected void process( Element environmentElement )
	{
		// TODO: NB: only the first model is processed!!
		Element modelElement = environmentElement.element( "model" );

		clazz = modelElement.attributeValue( "class" );
		attributes = Util.createAttributeMap( modelElement.element( "attributes" ) );
		initializers = ( Initializer[] )Util.collectInitializers( modelElement.element( "initializers" ) ).toArray(
				new Initializer[0] );
		updates = ( Update[] )Util.collectUpdates( modelElement.element( "updates" ) ).toArray( new Update[0] );
		formalParameters = new Class[] {Initializer[].class, Update[].class, Map.class };
		actualParameters = new Object[] {initializers, updates, attributes };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see standard.experiment.builder.Builder#create(java.util.Map)
	 */
	public Object create( Map parameters )
	{
		return createEnvironment( parameters );
	}

	protected Environment createEnvironment( Map parameters )
	{
		EnvironmentModel model = ( EnvironmentModel )DynamicClassloader.newInstance( clazz, formalParameters,
				actualParameters );
		Environment environment = new SweepEnvironment( model );

		return environment;
	}
}