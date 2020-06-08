package grid.model.action;

import java.util.Map;


/**
 * Description: MoveRightAction moves the agent right on the screen by one unit
 * 
 * @author Michael A. Kovacina
 */
public class MoveRightAction extends AbstractMoveAction
{
	/**
	 * @param attributes
	 */
	public MoveRightAction( Map attributes )
	{
		super( attributes, "action.move.grid", 1, 0 );
	}
}