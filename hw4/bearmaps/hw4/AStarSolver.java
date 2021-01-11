package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    /** A star graph. */
    private AStarGraph<Vertex> G;
    /** Start vertex. */
    private Vertex start;
    /** End vertex. */
    private Vertex end;
    /** Map of distance from start to vertex.*/
    private final HashMap<Vertex, Double> distTo;
    /** Map of vertex to its parent vertex tracked to start. */
    private final HashMap<Vertex, Vertex> edgeTo;
    /** Priority queue in relaxing order. */
    private final ExtrinsicMinPQ<Vertex> pq;
    /** The vertices have been fixed. */
    private final HashSet<Vertex> fixedVertices;
    /** The shortest path, from start to end(including). */
    private final LinkedList<Vertex> shortestPath;
    /** The weight of the shortest path. */
    private double shortestPathWeight;
    /** Consequence of finding the shortest path. */
    private SolverOutcome consequence;
    /** Number of vertices in the process of finding the shortest path. */
    private int traversedVerticesCount;
    /** Time in finding the shortest path in seconds. */
    private final double explorationTime;

    /** Constructor which finds the solution, computing everything necessary for all other
     * methods to return their results in constant time. Note that timeout passed in is in
     * seconds. */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        try {
            consequence = SolverOutcome.UNSOLVABLE;
            distTo = new HashMap<>();
            edgeTo = new HashMap<>();
            pq = new ArrayHeapMinPQ<>();
            fixedVertices = new HashSet<>();
            shortestPath = new LinkedList<>();
            if (input == null) {
                return;
            }
            G = input;
            if (start == null) {
                return;
            }
            this.start = start;
            if (end == null) {
                return;
            }
            this.end = end;
            if (timeout < 0) {
                consequence = SolverOutcome.TIMEOUT;
                return;
            }
            distTo.put(start, 0.0);
            edgeTo.put(start, null);
            pq.add(start, 0.0);
            while (pq.size() > 0) {
                Vertex p = pq.removeSmallest();
                traversedVerticesCount++;
                if (p.equals(end)) {
                    consequence = SolverOutcome.SOLVED;
                    break;
                } else if (sw.elapsedTime() > timeout){
                    consequence = SolverOutcome.TIMEOUT;
                    break;
                } else {
                    relax(p);
                }
            }

            if (consequence != SolverOutcome.SOLVED) {
                return;
            }
            for (Vertex v = end; !v.equals(start); v = edgeTo.get(v)) {
                shortestPath.addFirst(v);
            }
            shortestPath.addFirst(start);
            shortestPathWeight = distTo.get(end);
        } finally {
            explorationTime = sw.elapsedTime();
        }
    }

    private void relax(Vertex v) {
        if (v == null) {
            return;
        }
        fixedVertices.add(v);
        for (WeightedEdge<Vertex> outEdge: G.neighbors(v)) {
            Vertex neighbor = outEdge.to();
            if (outEdge.to().equals(v)) {
                neighbor = outEdge.from();
            }
            if (fixedVertices.contains(neighbor)) {
                continue;
            }
            double fromSourceDist = distTo.get(v) + outEdge.weight();
            if (!distTo.containsKey(neighbor) || distTo.get(neighbor) < fromSourceDist) {
                distTo.put(neighbor, fromSourceDist);
                edgeTo.put(neighbor, v);
                double potentialDist = fromSourceDist + G.estimatedDistanceToGoal(neighbor, end);
                if (pq.contains(neighbor)) {
                    pq.changePriority(neighbor, potentialDist);
                } else {
                    pq.add(neighbor, potentialDist);
                }
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return consequence;
    }

    @Override
    public List<Vertex> solution() {
        return shortestPath;
    }

    @Override
    public double solutionWeight() {
        return shortestPathWeight;
    }

    @Override
    public int numStatesExplored() {
        return traversedVerticesCount;
    }

    @Override
    public double explorationTime() {
        return explorationTime;
    }
}
