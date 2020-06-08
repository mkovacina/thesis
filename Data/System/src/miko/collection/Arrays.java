package miko.collection;

import java.util.Random;


public final class Arrays
{
	private static final Random INTERNAL_RANDOM_NUMBER_GENERATOR = new Random();

	private Arrays()
	{
	}

	public static Object randomSelection( Object[] array )
	{
		return randomSelelction( array, INTERNAL_RANDOM_NUMBER_GENERATOR );
	}

	public static Object randomSelelction( Object[] array, Random rng )
	{
		if ( array == null || array.length == 0 )
		{
			// REFACTOR: exception here?? or maybe Error???
			return null;
		}

		return array[rng.nextInt( array.length )];
	}
}