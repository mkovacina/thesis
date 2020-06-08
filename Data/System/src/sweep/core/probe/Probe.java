package sweep.core.probe;

public interface Probe
{
	void initialize();

	boolean update();

	void destroy();
}