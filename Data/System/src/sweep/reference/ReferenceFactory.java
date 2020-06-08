package sweep.reference;

import java.awt.Point;
import java.util.StringTokenizer;


/**
 * Description: ReferenceFactory A factory object that handles the creation of
 * SWEEP references. Currently supported reference types are:
 * <ul>
 * <li>Constant (Point only)</li>
 * <li>Environment</li>
 * <li>Avatar</li>
 * </ul>.
 * 
 * @author Michael A. Kovacina
 */
public class ReferenceFactory
{
	/**
	 * @param spec
	 * @return A fully constructed reference
	 */
	public static Reference createReference( String spec )
	{
		Reference reference = null;
		StringTokenizer tok = new StringTokenizer( spec, ":" );

		String type = tok.nextToken();

		// XXX: This is a very rudimentary implementation
		if ( type.equals( "env" ) )
		{
			reference = createEnvironmentReference( tok );
		}
		else if ( type.equals( "av" ) )
		{
			reference = createAvatarReference( tok );
		}
		else if ( type.equals( "const" ) )
		{
			reference = createConstantReference( tok );
		}

		return reference;
	}

	/**
	 * @param tok
	 * @return A fully constructed constant reference
	 */
	private static Reference createConstantReference( StringTokenizer tok )
	{
		String value = tok.nextToken();
		// XXX: ok, this is where dynamic constructors should be called, but
		// right now I will just hardcode in parsing a Point object
		StringTokenizer pointTok = new StringTokenizer( value, "," );
		int x = Integer.parseInt( pointTok.nextToken() );
		int y = Integer.parseInt( pointTok.nextToken() );
		return new ConstantReference( new Point( x, y ) );
	}

	/**
	 * @param tok
	 * @return A fully constructed avatar reference
	 */
	private static Reference createAvatarReference( StringTokenizer tok )
	{
		String attrValue = tok.nextToken();
		return new AvatarReference( attrValue );
	}

	/**
	 * @param tok
	 * @return A fully constructed environment reference
	 */
	private static Reference createEnvironmentReference( StringTokenizer tok )
	{
		String envName = tok.nextToken();
		String attrValue = tok.nextToken();

		return new EnvironmentReference( envName, attrValue );
	}
}