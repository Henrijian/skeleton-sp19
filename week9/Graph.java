import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class Graph {
    private static final String NEW_LINE = System.getProperty("line.separator");
    /**
     * V: number of vertices in graph.
     * E: number of edges in graph.
     * adj: adjacency list of graph, which is vertex indexed list of adjacency of each vertex.
     * */
    private final int V;
    private int E;
    private HashSet<Integer>[] adj;

    /**
     * Initiate a graph with V separated vertices.
     * */
    public Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Value of vertices for initiating Graph cannot be negative.");
        }
        this.V = V;
        this.E = 0;
        this.adj = (HashSet<Integer>[]) new HashSet[V];
        for (int v = 0; v < V; v++) {
            this.adj[v] = new HashSet<Integer>();
        }
    }

    /**
     * Initiate a graph with V vertices and E edges.
     */
    public Graph(int V, int E) {
        this(V);
        if (E < 0) {
            throw new IllegalArgumentException("Value of edges for initiating Graph cannot be negative.");
        }
        if (V * V < E) {
            throw new IllegalArgumentException("Value of edges for initiating Graph exceeding max value according to vertices.");
        }
        Random random = new Random();
        while (this.E < E) {
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            this.addEdge(v, w);
        }
    }

    /**
     * Return number of vertices.
     * */
    public int V() {
        return this.V;
    }

    /**
     * Return number of edges.
     */
    public int E() {
        return this.E;
    }

    /**
     * Add edge connect vertex v and w.
     * */
    public void addEdge(int v, int w) {
        validate(v);
        validate(w);
        if (!adj[v].contains(w)) {
            adj[v].add(w);
            E++;
        }
    }

    /**
     * Return iterable vertices which are adjacent to vertex v.
     * */
    public Iterable<Integer> adj(int v) {
        validate(v);
        return adj[v];
    }

    /**
     * String representation of graph.
     * */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(String.format("%s vertices, %s edges.", V, E)).append(NEW_LINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w: adj[v]) {
                s.append(w).append(" ");
            }
            s.append(NEW_LINE);
        }
        return s.toString();
    }

    /**
     * Check if v is valid vertex index.
     * */
    private void validate(int v){
        if (v < 0 || V <= v) {
            throw new IllegalArgumentException(String.format("vertex %d is not between 0 and %d.", v, V - 1));
        }
    }
}
