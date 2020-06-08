package rule;

public abstract class AbstractRule implements Rule
{
	public boolean eval()
	{
		return eval( null );
	}
}