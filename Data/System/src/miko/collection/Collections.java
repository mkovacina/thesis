package miko.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


/**
 * The library is probably not thread safe....
 * 
 * @author orbital
 */
public final class Collections
{
	private static final Random INTERNAL_RANDOM_NUMBER_GENERATOR = new Random();

	private Collections()
	{
	}

	public static Object randomSelection( Collection collection )
	{
		return randomSelelction( new ArrayList( collection ), INTERNAL_RANDOM_NUMBER_GENERATOR );
	}

	public static Object randomSelelction( List list, Random rng )
	{
		if ( list == null || list.size() == 0 )
		{
			// REFACTOR: exception here?? or maybe Error???
			return null;
		}

		return list.get( rng.nextInt( list.size() ) );
	}
}