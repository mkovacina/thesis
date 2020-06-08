package grid.model.action;

import java.util.Map;


/**
 * Description: MoveUpAction moves the agent up the screen by one unit
 * 
 * @author Michael A. Kovacina
 */
public class MoveUpAction extends AbstractMoveAction
{
	/**
	 * @param attributes
	 */
	public MoveUpAction( Map attributes )
	{
		super( attributes, "action.move.grid", 0, -1 );
	}
}