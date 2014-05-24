package graph_package;

import java.util.ArrayList;

public class Node {

	// Ball colour
	private int colour;
	// Adjacent nodes
	private ArrayList<Node> adjacentNodes;

	/**
	 * Constructor.
	 */
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
