/*
 * Created on Jan 28, 2004
 */
package sweep.util.builder;

import java.util.Map;

import org.dom4j.Element;


/**
 * @author Michael A. Kovacina
 */
public abstract class Builder
{
	public Builder( Element containerElement )
	{
		initialize();
		process( containerElement );
	}

	/**
	 * <p>
	 * Due to the design of this class, any objects that need to be initialized
	 * before the Builder() constructor returns from calling process cannot be
	 * processed. Thus, the Builder() constructor calls initialize() to give
	 * instance classes and opportunity to initialize properly before
	 * process()'ing.
	 * </p>
	 */
	abstract protected void initialize();

	abstract protected void process( Element containerElement );

	abstract public Object create( Map parameters );
}