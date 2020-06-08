package miko;

import java.util.Random;


public final class Math
{
	private static Random rng = new Random();

	private Math()
	{
	}

	public static final int mod( int x, int y )
	{
		return x - ( int )java.lang.Math.floor( x / ( double )y ) * y;
	}

	public static final void seed( long seedValue )
	{
		rng = new Random( seedValue );
	}

	public static int nextInt( int n )
	{
		return rng.nextInt( n );
	}
}