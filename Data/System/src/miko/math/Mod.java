package miko.math;

public final class Mod // REFACTOR: some kind of operator super
// class/interface??
{
	private Mod()
	{
		super();
	}

	public static final double eval( double x, double y )
	{
		double ans = 0;

		if ( y >= 0 )
		{
			if ( x >= 0 )
			{
				ans = x % y;
			}
			else
			/* x < 0 */{
				ans = y + x % y;
			}
		}
		else
		/* y < 0 */{
			if ( x >= 0 )
			{
				ans = y + x % Math.abs( y );
			}
			else
			/* x < 0 */{
				ans = -( Math.abs( x ) % Math.abs( y ) );
			}
		}

		return ans;
	}
}