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
		this.xPos = xPos; 
		this.yPos = yPos;
		this.colour = color;
		
		adjacentNodes = new ArrayList<Node>();
	}

	public Node(){};
	
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
		adjacent.addAdjacent(adjacent);
	}

	/**
	 * 
	 * @return Children
	 */
	public ArrayList<Node> getAdjacents() {
		return adjacentNodes;
	}

}
