package miko.stat;

public final class Stat
{
	private Stat()
	{
	}

	public static double mean( int[] data )
	{
		double sum = sum( data );
		return sum / data.length;
	}

	public static double mean( float[] data )
	{
		double sum = sum( data );
		return sum / data.length;
	}

	public static double mean( double[] data )
	{
		double sum = sum( data );
		return sum / data.length;
	}

	public static double meanHarmonic( int[] data )
	{
		double sum = 0;

		for( int i = 0; i < data.length; i++ )
		{
			sum = sum + 1.0 / data[i];
		}

		return data.length / sum;
	}

	public static double meanHarmonic( float[] data )
	{
		double sum = 0;

		for( int i = 0; i < data.length; i++ )
		{
			sum = sum + 1.0 / data[i];
		}

		return data.length / sum;
	}

	public static double meanHarmonic( double[] data )
	{
		double sum = 0;

		for( int i = 0; i < data.length; i++ )
		{
			sum = sum + 1.0 / data[i];
		}

		return data.length / sum;
	}

	public static int sum( int[] data )
	{
		int sum = 0;

		for( int x = 0; x < data.length; x++ )
		{
			sum += data[x];
		}

		return sum;
	}

	public static float sum( float[] data )
	{
		float sum = 0;

		for( int x = 0; x < data.length; x++ )
		{
			sum += data[x];
		}

		return sum;
	}

	public static double sum( double[] data )
	{
		double sum = 0;

		for( int x = 0; x < data.length; x++ )
		{
			sum += data[x];
		}

		return sum;
	}

	public static double stddev( double[] data )
	{
		double mean = mean( data );
		double sum = 0;

		for( int x = 0; x < data.length; x++ )
		{
			double temp = mean - data[x];
			sum += temp * temp;
		}

		return Math.sqrt( sum ) / ( data.length - 1 );
	}

	// Start a vectorization library

	// mean
	// mode??
	// standard deviation
	// median...
}