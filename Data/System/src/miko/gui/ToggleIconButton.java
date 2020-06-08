package miko.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;


public class ToggleIconButton extends JButton
{
	private Icon trueIcon;
	private Icon falseIcon;
	private boolean state;

	public ToggleIconButton( Icon trueIcon, Icon falseIcon, boolean state )
	{
		super();

		this.trueIcon = trueIcon;
		this.falseIcon = falseIcon;
		this.state = state;

		addActionListener( new ToggleListener() );

		changeIcon();
	}

	private class ToggleListener implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			toggleIcon();
		}
	}

	protected void toggleIcon()
	{
		state = !state;

		changeIcon();
	}

	private void changeIcon()
	{
		if ( state )
		{
			setIcon( trueIcon );
		}
		else
		{
			setIcon( falseIcon );
		}
	}
}