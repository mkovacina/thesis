package sweep.controller;



/**
 * Description: TestValue stores the name of some variable and the target value for it
 * 
 * @author Michael A. Kovacina
 */
public class TestValue
{
	/**
	 * <code>name</code> the target variable name
	 */
	public final String name;
	/**
	 *  <code>targetValue</code> the target variable value
	 */
	public final String targetValue;

	/**
	 * @param name The name of the target variable
	 * @param value The desired value of the target vvariable
	 */
	public TestValue( String name, String value )
	{
		this.name = name;
		this.targetValue = value;
	}

	/**
	 * @param value The value to compare to
	 * @return True if the values are the same, false otherwise
	 */
	public boolean isEqualTo( String value )
	{
		// XXX: eventually move this into equals()/compare()
		return this.targetValue.equals( value );
	}
}