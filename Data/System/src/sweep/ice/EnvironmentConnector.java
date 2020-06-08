/*
 * Created on Mar 18, 2004
 */
package sweep.ice;

import java.util.List;

import sweep.avatar.SweepAvatar;
import sweep.model.SweepModel;


/**
 * @author orbital
 */
public class EnvironmentConnector
{
	// REFACTOR: this is bad form
	public SweepAvatar[] avatars;
	public SweepModel[] models;

	/**
	 *  
	 */
	public EnvironmentConnector( List avatars, List models )
	{
		super();

		this.avatars = ( SweepAvatar[] )avatars.toArray( new SweepAvatar[0] );
		this.models = ( SweepModel[] )models.toArray( new SweepModel[0] );

	}
}