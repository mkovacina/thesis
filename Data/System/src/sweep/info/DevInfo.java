package sweep.info;

public class DevInfo implements Info
{
	protected final String[] authors;
	protected final String project;
	protected final String description;

	public DevInfo( String authors[], String project, String description )
	{
		this.authors = authors;
		this.project = project;
		this.description = description;
	}

	public String description()
	{
		StringBuffer buffer = new StringBuffer( 10 );
		buffer.append( "Info\n" );
		buffer.append( "**********\n" );
		buffer.append( "* Project: " + project + "\n" );

		for( int x = 0; x < authors.length; x++ )
		{
			buffer.append( "* Author: " + authors[x] + "\n" );
		}

		buffer.append( "* Description: " + description + "\n" );
		buffer.append( "**********\n" );

		return buffer.toString();
	}
}