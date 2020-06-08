package sweep.swarm;

public class Swarm
{
	// REFACTOR: may want to make Swarm an Entity, and make Entity an Agent

	protected Entity[] entities;

	public Swarm( Entity[] entities )
	{
		this.entities = entities;
	}

	// REFACTOR: initialization strategy
	public void initialize()
	{
		for( int x = 0; x < entities.length; x++ )
		{
			entities[x].initialize();
		}
	}

	// REFACTOR: update strategy
	public void update()
	{
		for( int x = 0; x < entities.length; x++ )
		{
			entities[x].update();
		}
	}

	// REFACTOR: destruction strategy
	public void destroy()
	{
		for( int x = 0; x < entities.length; x++ )
		{
			entities[x].destroy();
		}
	}
}