package rule;

import java.util.Map;


public interface Rule
{
	boolean eval();

	boolean eval( Map input );

	Rule copy();
}