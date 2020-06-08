package sweep;

import java.util.Collections;

import miko.loader.DynamicClassloader;

import org.dom4j.Document;
import org.dom4j.Element;

import sweep.simulation.Simulation;
import sweep.util.builder.Builder;


public class SWEEP implements Runnable
{
	private Simulation simulation;
	private long seed;
	private final Document document;

	public SWEEP( Document document, long seed )
	{
		this.document = document;
		this.seed = seed;
	}

	// REFACTOR: an initialize() so a sweep object can be reused??

	public void run()
	{
		// This code doesn't belong here, but until the constructor is threaded
		// or something
		// for example, the network animation probe can open up a socket in the
		// probe builder, which is blocking i/o but since that code is in the
		// SWEEP constructor the thread is not started yet.
		Element root = document.getRootElement();

		String builderClass = root.attributeValue( "builder" );
		Class[] formalParameters = new Class[] {Element.class };
		Object[] actualParameters = new Object[] {root };

		Builder builder = ( Builder )DynamicClassloader.newInstance( builderClass, formalParameters, actualParameters );

		simulation = ( Simulation )builder.create( Collections.EMPTY_MAP );

		simulation.initialize( seed );
		simulation.run();
	}
}