package graph_package;

import java.util.ArrayList;

public class Node {

	// Ball colour
	private int colour;
	private int xPos, yPos; // Position of the node in the matrix view
	// Adjacent nodes
	private ArrayList<Node> adjacentNodes;

	/**
	 * Constructor.
	 */
	public Node(int xPos, int yPos, int color) {
		this.setxPos(xPos);
		this.setyPos(yPos);
		this.colour = color;

		adjacentNodes = new ArrayList<Node>();
	}

	public Node() {
		adjacentNodes = new ArrayList<Node>();
	}

	/**
	 * @return node colour
	 */
	public int getColour() {
		return colour;
	}

	/**
	 * @param colour
	 */
	public void setColour(int colour) {
		this.colour = colour;
	}

	/**
	 * Add New Child
	 * 
	 * @param adjacent
	 */
	public void addAdjacent(Node adjacent) {
		adjacentNodes.add(adjacent);
	}

	/**
	 * 
	 * @return Children
	 */
	public ArrayList<Node> getAdjacents() {
		return adjacentNodes;
	}

	/**
	 * 
	 * @return x pos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * 
	 * @param xPos
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * 
	 * @return y pos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * 
	 * @param yPos
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * Check if this node is connected to any other node or no
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return getAdjacents().size() != 0;
	}
}
