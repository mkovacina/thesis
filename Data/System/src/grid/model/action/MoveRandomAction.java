package grid.model.action;

import grid.model.GridAvatar;

import java.awt.Point;
import java.util.Collections;
import java.util.Map;

import miko.math.StaticRandom;
import sweep.core.agent.State;
import sweep.model.AvatarModel;


/**
 * Description: MoveRandomAction moves the agent in a random direction. A
 * direction and a duration are selected and stored in the agent's state.
 * 
 * @author Michael A. Kovacina
 */
public class MoveRandomAction extends AbstractMoveAction
{
	/**
	 * @param attributes
	 */
	public MoveRandomAction( Map attributes )
	{
		super( attributes, "environment", 0, 0 );
	}

	/**
	 *  
	 */
	public MoveRandomAction()
	{
		super( Collections.EMPTY_MAP, "environment", 0, 0 );
		// TODO Auto-generated constructor stub
	}

	private class RandomWalkParameters
	{
		/**
		 * <code>offX</code> offset in the x-direction
		 */
		public int offX;

		/**
		 * <code>offY</code> offset in the y-direction
		 */
		public int offY;

		/**
		 * <code>duration</code> number of time steps to move in this
		 * direction
		 */
		public int duration;

		/**
		 *  
		 */
		public RandomWalkParameters()
		{
			randomize();
		}

		/**
		 * Pick a new random direction and duration
		 */
		public void randomize()
		{
			// XXX: these should be adjustable somehow
			offX = StaticRandom.nextInt( -1, 2 );
			offY = StaticRandom.nextInt( -1, 2 );
			duration = StaticRandom.nextInt( 2, 7 );
		}

		/**
		 * This method decrememnts the duration counter. When the counter
		 * reaches 0, a new random direction and duration will be selected.
		 */
		public void used()
		{
			duration--;

			if ( duration == 0 )
			{
				randomize();
			}
		}
		
		/** 
		 * @see java.lang.Object#toString()
		 */
		public String toString()
		{
			return "<"+offX+","+offY+">*"+duration;
		}
	}

	/** 
	 * @see grid.model.action.AbstractMoveAction#invoke(sweep.model.AvatarModel, java.util.Map)
	 */
	public void invoke( AvatarModel model, Map environmentMap )
	{
		State state = model.getState();

		Object o = state.get( "random-walk" );
		RandomWalkParameters rwp = null;

		if ( o == null )
		{
			rwp = new RandomWalkParameters();
			state.put( "random-walk", rwp );
		}
		else
		{
			rwp = ( RandomWalkParameters )o;
		}

		Point p = ( Point )state.get( "position" );

		//int offX = StaticRandom.nextInt( -1, 2 );
		//int offY = StaticRandom.nextInt( -1, 2 );

		// XXX: check if at edge, and if true then re-randomize
		if ( checkValidity( p, rwp.offX, rwp.offY ) )
		{
			( ( GridAvatar )model ).move( rwp.offX, rwp.offY );
		}

		rwp.used();
	}
}