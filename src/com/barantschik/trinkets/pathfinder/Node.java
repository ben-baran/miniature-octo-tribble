package com.barantschik.trinkets.pathfinder;

public class Node
{
	public int x, y;
	public NodeType type = NodeType.NONE;
	public int walkCost = 0;
	
	private Node parent = null;
	
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setParent(Node n, int walkCost)
	{
		parent = n;
		this.walkCost = walkCost;
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public int getGCost()
	{
		int sum = 0;
		Node curNode = this;
		while(curNode != null)
		{
			sum += curNode.walkCost;
			curNode = curNode.parent;
		}
		return sum;
	}
}