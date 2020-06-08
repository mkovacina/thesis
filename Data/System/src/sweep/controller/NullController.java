package sweep.controller;

import sweep.core.agent.Controller;
import sweep.core.agent.State;


public class NullController implements Controller
{
	public NullController()
	{
	}

	public void initialize()
	{
	}

	public String[] update( State state )
	{
		return new String[0];
	}
}