package graph_package;

import java.util.ArrayList;

public class Node {

	// Ball colour
	private int colour;
	// Outer Edges
	private ArrayList<Node> children;
	// Inner Edges
	private ArrayList<Node> parents;

	/**
	 * Constructor.
	 */
	public Node() {
		children = new ArrayList<Node>();
		parents = new ArrayList<Node>();
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
	 * @param child
	 */
	public void addChild(Node child) {
		child.addChild(child);
	}

	/**
	 * 
	 * @return Children
	 */
	public ArrayList<Node> getChildren() {
		return children;
	}

	/**
	 * Add new Parent
	 * 
	 * @param parent
	 */
	public void addParent(Node parent) {
		parent.addChild(parent);
	}

	/**
	 * 
	 * @return Parents
	 */
	public ArrayList<Node> getParents() {
		return parents;
	}
}
