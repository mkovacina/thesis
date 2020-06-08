package grid.model.action;

import java.util.Map;


/**
 * Description: MoveDownAction moves the agent down the screen by one unit
 * 
 * @author Michael A. Kovacina
 */
public class MoveDownAction extends AbstractMoveAction
{
	/**
	 * @param attributes
	 */
	public MoveDownAction( Map attributes )
	{
		super( attributes, "action.move.grid", 0, 1 );
	}
}