package sweep.avatar;

import sweep.ice.AvatarConnector;
import sweep.model.AvatarModel;


/**
 * Description: SweepAvatar is the base class for all avatars in SWEEP.
 * 
 * @author Michael A. Kovacina
 */
public class SweepAvatar implements Avatar
{
	protected final AvatarModel model;
	protected final AvatarConnector connector;

	/**
	 * @param model
	 * @param connector
	 */
	public SweepAvatar( AvatarModel model, AvatarConnector connector )
	{
		this.model = model;
		this.connector = connector;
	}

	/** 
	 * @see sweep.avatar.Avatar#initialize()
	 */
	public void initialize()
	{
		model.initialize();
	}

	/** 
	 * @see sweep.avatar.Avatar#update()
	 */
	public void update()
	{
		// REFACTOR: why, why, why......
		//model.update();
		throw new Error( "not supported" );
	}

	/**
	 * 
	 */
	public void updateSensors()
	{
		model.updateSensors();
	}

	/**
	 * 
	 */
	public void executeNextAction()
	{
		model.executeNextAction();
	}

	/** 
	 * @see sweep.avatar.Avatar#destroy()
	 */
	public void destroy()
	{
	}

	/** 
	 * @see miko.query.Queryable#query(java.lang.String)
	 */
	public Object query( String queryString )
	{
		return model.query( queryString );
	}
}