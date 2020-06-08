package sweep.model.action;

import java.util.Map;

import sweep.model.AvatarModel;


public interface Action
{
	void initialize( AvatarModel model, Map environmentMap );

	void invoke( AvatarModel model, Map environmentMap );
}