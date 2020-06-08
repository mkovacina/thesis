package sweep.probe;

import sweep.core.probe.Probe;


public class CounterProbe implements Probe
{
	private final int maxIter;
	private int counter;
	private final boolean printIteration;

	public CounterProbe( int maxIter, boolean printIteration )
	{
		this.maxIter = maxIter;
		this.printIteration = printIteration;
		counter = 0;
	}

	public void initialize()
	{
	}

	public boolean update()
	{
		counter++;

		if ( printIteration )
		{
			System.out.println( "--> Iteration: " + counter );
		}

		return counter < maxIter;
	}

	public void destroy()
	{
	}

}