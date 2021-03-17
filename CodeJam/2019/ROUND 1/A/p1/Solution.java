import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class Solution {
	static class CaseSolver {
		static class HamiltonianCycle
		{
			int V; 
			int path[]; 

			/* A utility function to check if the vertex v can be 
			added at index 'pos'in the Hamiltonian Cycle 
			constructed so far (stored in 'path[]') */
			boolean isSafe(int v, int graph[][], int path[], int pos) 
			{ 
				/* Check if this vertex is an adjacent vertex of 
				the previously added vertex. */
				if (graph[path[pos - 1]][v] == 0) 
					return false;

				/* Check if the vertex has already been included. 
				This step can be optimized by creating an array 
				of size V */
				for (int i = 0; i < pos; i++) 
					if (path[i] == v) 
						return false; 

				return true; 
			} 

			/* A recursive utility function to solve hamiltonian 
			cycle problem */
			boolean hamCycleUtil(int graph[][], int path[], int pos) 
			{ 
				/* base case: If all vertices are included in 
				Hamiltonian Cycle */
				if (pos == V) 
				{ 
					// And if there is an edge from the last included 
					// vertex to the first vertex 
					if (graph[path[pos - 1]][path[0]] == 1) 
						return true; 
					else
						return false; 
				} 

				// Try different vertices as a next candidate in 
				// Hamiltonian Cycle. We don't try for 0 as we 
				// included 0 as starting point in in hamCycle() 
				for (int v = 1; v < V; v++) 
				{ 
					/* Check if this vertex can be added to Hamiltonian 
					Cycle */
					if (isSafe(v, graph, path, pos)) 
					{ 
						path[pos] = v; 

						/* recur to construct rest of the path */
						if (hamCycleUtil(graph, path, pos + 1) == true) 
							return true; 

						/* If adding vertex v doesn't lead to a solution, 
						then remove it */
						path[pos] = -1; 
					} 
				} 

				/* If no vertex can be added to Hamiltonian Cycle 
				constructed so far, then return false */
				return false; 
			} 

			/* This function solves the Hamiltonian Cycle problem using 
			Backtracking. It mainly uses hamCycleUtil() to solve the 
			problem. It returns false if there is no Hamiltonian Cycle 
			possible, otherwise return true and prints the path. 
			Please note that there may be more than one solutions, 
			this function prints one of the feasible solutions. */
			int[] hamCycle(int graph[][]) 
			{
				V = graph.length;
				path = new int[V]; 
				for (int i = 0; i < V; i++) 
					path[i] = -1; 

				/* Let us put vertex 0 as the first vertex in the path. 
				If there is a Hamiltonian Cycle, then the path can be 
				started from any point of the cycle as the graph is 
				undirected */
				path[0] = 0;
				if (hamCycleUtil(graph, path, 1) == false) 
				{ 
					return null;
				} 
				return path; 
			} 
		}
		static class IndexPair {
			int i, j;
			IndexPair(int i, int j) {
				this.i = i;
				this.j = j;
			}

			@Override
			public String toString() {
				return String.format("%d %d", i+1, j+1);
			}
		}
		
		static class GridGraph {
			static class Node {
				IndexPair pair;
				int index;
				Node(IndexPair pair, int index) {
					this.pair = pair;
					this.index = index;
				}

				@Override
				public String toString() {
					return pair.toString();
				}
			}

			List<Node> nodes = new ArrayList<>();
			int[][] adjacencyMatrix;
			int[] escapePath;

			GridGraph(int r, int c) {
				addNodes(r, c);
				adjacencyMatrix = new int[nodes.size()][nodes.size()];
				addNeighbors();
			}
			
			void addNodes(int r, int c) {
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < c; j++) {
						nodes.add(
							new Node(
								new IndexPair(i, j),
								nodes.size()
							)
						);
					}
				}
			}

			Boolean areNeighborsValid(Node a, Node b) {
				return !(
					(a.pair.i == b.pair.i) ||
					(a.pair.j == b.pair.j) ||
					(a.pair.i - a.pair.j == b.pair.i - b.pair.j) ||
					(a.pair.i + a.pair.j == b.pair.i + b.pair.j)
				);

			}

			void addNeighbors() {
				for (int i = 0; i < nodes.size(); i++) {
					for (int j = i; j < nodes.size(); j++) {
						if ((nodes.get(i) != nodes.get(j)) && areNeighborsValid(nodes.get(i), nodes.get(j))) {
							adjacencyMatrix[i][j] = 1;
							adjacencyMatrix[j][i] = 1;
						} else {
							adjacencyMatrix[i][j] = 0;
							adjacencyMatrix[j][i] = 0;
						}
					}
				}
			}

			void evaluatePath() {
				HamiltonianCycle hamiltonian = new HamiltonianCycle(); 
				int[] path = hamiltonian.hamCycle(adjacencyMatrix);
				if (path == null) {
					escapePath = null;
				} else {
					escapePath = path;
				}
			}

			String pathString() {
				String result = "";
				for (int i = 0; i < escapePath.length; i++) {
					result += "\n" + nodes.get(escapePath[i]).toString();
				}

				return result;
			}
		}
		
		int caseNumber;
		int r, c;
		String result;
		CaseSolver(int caseNumber, int r, int c) {
			this.caseNumber = caseNumber;
			this.r = r;
			this.c = c;
		}

		void solve() {
			GridGraph graph = new GridGraph(r, c);
			graph.evaluatePath();
			if (graph.escapePath == null) {
				result = "IMPOSSIBLE";
			} else {
				result = "POSSIBLE" + graph.pathString();
			}
		}

		@Override
		public String toString() {
			return String.format("Case #%d: %s", caseNumber, result);
		}
	}

	static class InputSolver {
		int numberOfCases;

		CaseSolver readCase(int caseNumber, Scanner scanner) {
			String[] input = scanner.nextLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			return new CaseSolver(caseNumber, r, c);
		}
		
		void solve() {
			Scanner scanner = new Scanner(System.in);
			numberOfCases = Integer.parseInt(scanner.nextLine());
			for (int i = 0; i < numberOfCases; i++) {
				CaseSolver caseSolver = readCase(i+1, scanner);
				caseSolver.solve();
				System.out.println(caseSolver);
			}
			scanner.close();
		}
	}
	
	public static void main(String[] args) {
		InputSolver solver = new InputSolver();
		solver.solve();
	}
}