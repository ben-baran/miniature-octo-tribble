package com.barantschik.trinkets.conway;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Conway extends JPanel implements MouseListener, KeyListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	
	public Conway()
	{
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);
		
		addMouseListener(this);
		addKeyListener(this);
	}

	public void keyPressed(KeyEvent e)
	{
		
	}
	public void mouseClicked(MouseEvent e)
	{
		
	}
	
	//Unimplemented methods
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
}