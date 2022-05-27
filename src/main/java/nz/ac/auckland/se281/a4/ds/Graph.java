package nz.ac.auckland.se281.a4.ds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nz.ac.auckland.se281.a4.TwitterHandle;
//*******************************
//YOU SHOUD MODIFY THE SPECIFIED 
//METHODS  OF THIS CLASS
//HELPER METHODS CAN BE ADDED
//REQUIRED LIBRARIES ARE ALREADY 
//IMPORTED -- DON'T ADD MORE
//*******************************

public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	protected Map<Node<String>, LinkedList<Edge<Node<String>>>> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	protected Node<String> root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */

	/**
	 * Creates a Graph
	 *
	 * @param edges a list of edges to be added to the graph
	 */
	public Graph(List<String> edges) {
		adjacencyMap = new LinkedHashMap<>();
		int i = 0;
		if (edges.isEmpty()) {
			throw new IllegalArgumentException("edges are empty");
		}

		for (String edge : edges) {
			String[] split = edge.split(",");
			Node<String> source = new Node<String>(split[0]);
			Node<String> target = new Node<String>(split[1]);
			Edge<Node<String>> edgeObject = new Edge<Node<String>>(source, target);

			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new LinkedList<Edge<Node<String>>>());
			}
			adjacencyMap.get(source).append(edgeObject);

			if (i == 0) {
				root = source;
			}
			i++;
		}
	}

	/**
	 * This method returns a boolean based on whether the input sets are reflexive.
	 * <p>
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 *
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are reflexive
	 */
	public boolean isReflexive(List<String> set, List<String> relation) {
		for (String element : set) {
			if (!relation.contains(element + "," + element)) {
				return false;
			}
		}
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input set is symmetric.
	 * <p>
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be symmetric tuple: " + requiredElement + " MUST
	 * be present"
	 * <p>
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 *
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is symmetric
	 */
	public boolean isSymmetric(List<String> relation) {
		for (String element : relation) {
			StringBuilder sb = new StringBuilder(element);
			sb.reverse();
			if (!relation.contains(sb.toString())) {
				System.out.printf("For the graph to be symmetric tuple: %s MUST be present \n", sb.toString());
				return false;
			}
		}
		return true;
	}

	/**
	 * This method returns a boolean based on whether the input set is transitive.
	 * <p>
	 * If the method returns false, then the following must be printed to the
	 * console: "For the graph to be transitive tuple: " + requiredElement + " MUST
	 * be present"
	 * <p>
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 *
	 * @param relation A relation between the TwitterHandles
	 * @return true if the relation is transitive
	 */
	public boolean isTransitive(List<String> relation) {
		for (String element : relation) {
			String nodeA = element.substring(0, 1);
			String nodeB = element.substring(2);
			for (String element1 : relation) {
				if (element1.substring(0, 1).equals(nodeB)) {
					String nodeC = element1.substring(2);
					if (!relation.contains(nodeA + "," + nodeC)) {
						System.out.printf("For the graph to be symmetric tuple: %s MUST be present \n",
								nodeA + "," + nodeC);
						return false;
					}
				}
			}

		}
		return true;

	}

	/**
	 * This method returns a boolean based on whether the input sets are
	 * anti-symmetric TODO: Complete this method (Note a set is not passed in as a
	 * parameter but a list)
	 *
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return true if the set and relation are anti-symmetric
	 */
	public boolean isEquivalence(List<String> set, List<String> relation) {
		return (isReflexive(set, relation) && isSymmetric(relation) && isTransitive(relation));
	}

	/**
	 * This method returns a List of the equivalence class
	 * <p>
	 * If the method can not find the equivalence class, then The following must be
	 * printed to the console: "Can't compute equivalence class as this is not an
	 * equivalence relation"
	 * <p>
	 * TODO: Complete this method (Note a set is not passed in as a parameter but a
	 * list)
	 *
	 * @param node     A "TwitterHandle" in the graph
	 * @param set      A list of TwitterHandles
	 * @param relation A relation between the TwitterHandles
	 * @return List that is the equivalence class
	 */
	public List<String> computeEquivalence(String node, List<String> set, List<String> relation) {

		if (!isEquivalence(set, relation)) {
			System.out.println("Can't compute equivalence class as this is not an equivalence relation");
			return null;
		}
		List<String> eqClass = new ArrayList<>();
		for (String element : relation) {
			if (element.substring(0, 1).equals(node)) {
				eqClass.add(element.substring(2));
			}
		}
		return eqClass;
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 *
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch() {
		return breadthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the BFS (Breadth First Search)
	 * algorithm
	 *
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the BFS algorithm
	 */
	public List<Node<String>> breadthFirstSearch(Node<String> start, boolean rooted) {// name to breadthFirstSearch
		return graphSearch(start, "bfs");
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 *
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch() {
		return depthFirstSearch(root, false);
	}

	/**
	 * This method returns a List nodes using the DFS (Depth First Search) algorithm
	 *
	 * @param start A "TwitterHandle" in the graph
	 * @return List of nodes (as strings) using the DFS algorithm
	 */
	public List<Node<String>> depthFirstSearch(Node<String> start, boolean rooted) {
		return graphSearch(start, "dfs");
	}

	/**
	 * Searches the graph based with either dfs or bfs, implementing a node/queue
	 *
	 * @param start A "TwitterHandle" in the graph
	 * @param type  either bfs or dfs
	 * @return List of nodes (as strings) using the DFS/BFS algorithm
	 */
	private List<Node<String>> graphSearch(Node<String> start, String algorithmType) {
		List<Node<String>> arr = new ArrayList<>();
		NodesStackAndQueue<Node<String>> queue = new NodesStackAndQueue<>();
		switch (algorithmType) {
		case "bfs" -> queue.append(start);
		case "dfs" -> queue.push(start);
		}
		Set<Node<String>> allNodes = adjacencyMap.keySet();
		while (arr.size() != allNodes.size()) {
			while (!queue.isEmpty()) {
				Node<String> node = queue.pop();
				if (!arr.contains(node)) {
					arr.add(node);
				}
				LinkedList<Edge<Node<String>>> edgeList = adjacencyMap.get(node);
				Node<Edge<Node<String>>> edgeNode = edgeList.getHead();
				while (edgeNode != null) {
					if (!arr.contains(edgeNode.getValue().getTarget())) {
						switch (algorithmType) {
						case "bfs" -> queue.append(edgeNode.getValue().getTarget());
						case "dfs" -> queue.push(edgeNode.getValue().getTarget());
						}
					}
					edgeNode = edgeNode.getNext();
				}

			}
			for (Node<String> n : allNodes) {
				if (!arr.contains(n)) {
					queue.push(n);
					break;
				}
			}
		}

		return arr;
	}

	/**
	 * @return returns the set of all nodes in the graph
	 */
	public Set<Node<String>> getAllNodes() {

		Set<Node<String>> out = new HashSet<>();
		out.addAll(adjacencyMap.keySet());
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i).getSource());
				out.add(list.get(i).getTarget());
			}
		}
		return out;
	}

	/**
	 * @return returns the set of all edges in the graph
	 */
	protected Set<Edge<Node<String>>> getAllEdges() {
		Set<Edge<Node<String>>> out = new HashSet<>();
		for (Node<String> n : adjacencyMap.keySet()) {
			LinkedList<Edge<Node<String>>> list = adjacencyMap.get(n);
			for (int i = 0; i < list.size(); i++) {
				out.add(list.get(i));
			}
		}
		return out;
	}

	/**
	 * @return returns the set of twitter handles in the graph
	 */
	public Set<TwitterHandle> getUsersFromNodes() {
		Set<TwitterHandle> users = new LinkedHashSet<TwitterHandle>();
		for (Node<String> n : getAllNodes()) {
			users.add(new TwitterHandle(n.getValue()));
		}
		return users;
	}

}
