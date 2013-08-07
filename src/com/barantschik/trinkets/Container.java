package com.barantschik.trinkets;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public abstract class Container extends JFrame implements ActionListener
{
	protected JMenuBar menuBar = new JMenuBar();
	
	public Container()
	{
		setJMenuBar(menuBar);
	}
	
	public void addMenu(String menuName, String[] menuItems)
	{
		JMenu curMenu = new JMenu(menuName);
		
		for(int i = 0; i < menuItems.length; i++)
		{
			JMenuItem curItem = new JMenuItem(menuItems[i]);
			curItem.addActionListener(this);
			curMenu.add(curItem);
		}
		
		menuBar.add(curMenu);
	}
}
