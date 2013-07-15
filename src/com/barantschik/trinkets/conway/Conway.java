package com.barantschik.trinkets.conway;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Conway extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
	private final int SIZE_X = 1000, SIZE_Y = 1000;
	private final int NUM_X = 100, NUM_Y = 100;
	private final int BLOCK_X = SIZE_X / NUM_X, BLOCK_Y = SIZE_Y / NUM_Y;
	private boolean running = false;
	private boolean[][] map = new boolean[NUM_X][NUM_Y];
	private boolean[][] premap = map;
	
	public Conway()
	{
		setSize(SIZE_X, SIZE_Y);
		setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
		setFocusable(true);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, SIZE_X, SIZE_Y);
		g.setColor(Color.BLACK);
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[0].length; j++)
			{
				if(map[i][j] == true) g.fillRect(i * BLOCK_X, j * BLOCK_Y, BLOCK_X, BLOCK_Y);
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			running ^= true;
		}
	}
	public void mouseClicked(MouseEvent e)
	{
		int selectedX = e.getX() / BLOCK_X, selectedY = e.getY() / BLOCK_Y;
		map[selectedX][selectedY] ^= true;
		repaint();
	}
	
	public void mouseDragged(MouseEvent e)
	{
		int selectedX = e.getX() / BLOCK_X, selectedY = e.getY() / BLOCK_Y;
		map[selectedX][selectedY] = true;
		repaint();
	}
	
	//Unimplemented methods
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
}