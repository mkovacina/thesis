package sweep.model.sensor;

import java.util.Map;

import sweep.model.AvatarModel;


public interface Sensor
{
	Value getValue();

	void initialize( AvatarModel model, Map environmentMap );

	void update( AvatarModel model, Map environmentMap );
}