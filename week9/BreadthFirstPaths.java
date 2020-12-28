import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstPaths {
    private final Graph G;
    private int s;
    private final boolean[] marked;
    private final int[] distTo;
    private final int[] pathTo;

    public BreadthFirstPaths(Graph G, int s) {
        if (G == null) {
            throw new IllegalArgumentException("Graph G for initiating BreadthFirstPaths cannot be null.");
        }
        this.G = G;
        validate(s);
        this.distTo = new int[G.V()];
        this.pathTo = new int[G.V()];
        this.marked = new boolean[G.V()];
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> pq = new LinkedList<>();
        pq.offer(s);
        marked[s] = true;
        while (pq.size() != 0) {
            int v = pq.poll();
            for (int w: G.adj(v)) {
                if (marked[w]) {
                    continue;
                }
                marked[w] = true;
                distTo[w] = distTo[v] + 1;
                pathTo[w] = v;
                pq.offer(w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validate(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int w = v; pathTo[w] != s; w = pathTo[w]) {
            stack.push(w);
        }
        stack.push(s);
        return stack;
    }

    private void validate(int v) {
        int V = G.V();
        if (s < 0 || V <= s) {
            throw new IllegalArgumentException(String.format("vertex %d is not between 0 and %d.", s, V - 1));
        }
    }
}
