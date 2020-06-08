package grid.model.action;

import java.util.Map;


/**
 * Description: MoveLeftAction moves the agent left on the screen by one unit
 * 
 * @author Michael A. Kovacina
 */
public class MoveLeftAction extends AbstractMoveAction
{
	/**
	 * @param attributes 
	 */
	public MoveLeftAction( Map attributes )
	{
		super( attributes, "action.move.grid", -1, 0 );
	}
}