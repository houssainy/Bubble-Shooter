package graph_package;

public class Graph {
	// Start node
	private Node startNode;

	/**
	 * Constructor
	 * 
	 * @param startNode
	 */
	public Graph(Node startNode) {
		this.setStartNode(startNode);
	}

	/**
	 * Connect the destination node to the given source node
	 * 
	 * @param oldNode
	 * @param newNode
	 */
	public void addAdjacentNode(Node oldNode, Node newNode) {
		oldNode.addAdjacent(newNode);
		newNode.addAdjacent(oldNode);
	}

	/**
	 * Remove all the nodes which is connected to this nodes.
	 * 
	 * @param node
	 */
	public void removeNode(Node node) {
		for (int i = 0; i < node.getAdjacents().size(); i++) {
			// Cut the edge from my adjacent to the given Node
			node.getAdjacents().get(i).getAdjacents().remove(node);
		}

		// Cut all the edges from the given node to all the adjacent nodesf
		node.getAdjacents().clear();
	}

	/**
	 * 
	 * @return startNode
	 */
	public Node getStartNode() {
		return startNode;
	}

	/**
	 * Set start Node
	 * 
	 * @param startNode
	 */
	public void setStartNode(Node startNode) {
		this.startNode = startNode;
	}

}
