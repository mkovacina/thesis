package miko.math;

import java.util.Random;


public class SynchronizedRandom
{
	private static long rootSeed;
	private static Random seedGenerator;

	private SynchronizedRandom()
	{
	}

	public static void setSeed( long seed )
	{
		SynchronizedRandom.rootSeed = seed;
		SynchronizedRandom.seedGenerator = new Random( SynchronizedRandom.rootSeed );
	}

	public Random createRandom()
	{
		return new Random( seedGenerator.nextLong() );
	}
}