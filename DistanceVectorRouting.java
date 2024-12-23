import java.util.*;

class Graph {
    private int numVertices;
    private int[][] adjacencyMatrix;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyMatrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
        }
    }

    public void addEdge(int src, int dest, int cost) {
        adjacencyMatrix[src][dest] = cost;
        adjacencyMatrix[dest][src] = cost; // Since the graph is undirected
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getNumVertices() {
        return numVertices;
    }
}

class DistanceVectorRouting {

    public static void calculateRoutingTable(Graph graph) {
        int numVertices = graph.getNumVertices();
        int[][] dist = new int[numVertices][numVertices];
        int[][] nextHop = new int[numVertices][numVertices];

        // Initialize distance and nextHop tables
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                dist[i][j] = graph.getAdjacencyMatrix()[i][j];
                if (i == j) {
                    dist[i][j] = 0;
                    nextHop[i][j] = i;
                } else if (dist[i][j] != Integer.MAX_VALUE) {
                    nextHop[i][j] = j;
                } else {
                    nextHop[i][j] = -1;
                }
            }
        }

        // Distance Vector Algorithm (Bellman-Ford)
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        nextHop[i][j] = nextHop[i][k];
                    }
                }
            }
        }

        // Print the routing table
        System.out.println("Distance Vector Routing Table:");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Router " + i + ":");
            for (int j = 0; j < numVertices; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.println("To " + j + " -> unreachable");
                } else {
                    System.out.println("To " + j + " -> Next hop: " + nextHop[i][j] + ", Cost: " + dist[i][j]);
                }
            }
            System.out.println();
        }
    }
}

class LinkStateRouting {

    public static void calculateRoutingTable(Graph graph, int src) {
        int numVertices = graph.getNumVertices();
        int[] dist = new int[numVertices];
        boolean[] visited = new boolean[numVertices];
        int[] nextHop = new int[numVertices];

        // Initialize distance and nextHop tables
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(nextHop, -1);
        dist[src] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && graph.getAdjacencyMatrix()[u][v] != Integer.MAX_VALUE
                        && dist[u] + graph.getAdjacencyMatrix()[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph.getAdjacencyMatrix()[u][v];
                    nextHop[v] = (nextHop[u] == -1) ? v : nextHop[u];
                }
            }
        }

        // Print the routing table
        System.out.println("Link State Routing Table for Router " + src + ":");
        for (int i = 0; i < numVertices; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("To " + i + " -> unreachable");
            } else {
                System.out.println("To " + i + " -> Next hop: " + nextHop[i] + ", Cost: " + dist[i]);
            }
        }
        System.out.println();
    }

    private static int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a graph
        Graph graph = new Graph(5);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 1);

        // Calculate the routing tables using Distance Vector Routing
        DistanceVectorRouting.calculateRoutingTable(graph);

        // Calculate the routing table using Link State Routing for each router
        for (int i = 0; i < graph.getNumVertices(); i++) {
            LinkStateRouting.calculateRoutingTable(graph, i);
        }
    }
}
