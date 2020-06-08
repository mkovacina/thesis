package sweep.viewer.engine;

import java.awt.Component;

import javax.swing.event.ChangeListener;


public interface Engine
{
	public boolean getEnvironmentView();

	public boolean getAgentView();

	public void setEnvironmentView( boolean b );

	public void setAgentView( boolean b );

	public void toggleEnvironmentView();

	public void toggleAgentView();

	public Component getScreen();

	public Component getControls();

	public void nextFrame();

	public void addEndOfFileListener( ChangeListener listener );
}