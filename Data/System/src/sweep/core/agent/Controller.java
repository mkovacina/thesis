package sweep.core.agent;

public interface Controller
{
	void initialize();

	String[] update( State state );
}