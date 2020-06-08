package sweep.viewer.engine;

import java.io.BufferedReader;
import java.io.Reader;


public abstract class AbstractEngine implements Engine
{
	protected BufferedReader reader;
	protected boolean showEnvironment = true;
	protected boolean showAgents = true;

	protected AbstractEngine()
	{
	}

	protected AbstractEngine( Reader reader )
	{
		this.reader = new BufferedReader( reader );
	}

	public boolean getEnvironmentView()
	{
		return showEnvironment;
	}

	public boolean getAgentView()
	{
		return showAgents;
	}

	public void setEnvironmentView( boolean b )
	{
		showEnvironment = b;
	}

	public void setAgentView( boolean b )
	{
		showEnvironment = b;
	}

	public void toggleEnvironmentView()
	{
		showEnvironment = !showEnvironment;
	}

	public void toggleAgentView()
	{
		showAgents = !showAgents;
	}
}