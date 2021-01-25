package bearmaps.proj2c;

import bearmaps.hw4.WeightedEdge;
import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private Map<Point, Node> pointNodeMap;
    private KDTree pointTree;
    private TrieMap<Set<Node>> locationsTrieMap;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        this.pointNodeMap = new HashMap<>();
        this.pointTree = new KDTree();
        this.locationsTrieMap = new TrieMap<>();
        List<Node> nodes = this.getNodes();
        for (Node node : nodes) {
            Point nodePoint = new Point(node.lon(), node.lat());
            pointNodeMap.put(nodePoint, node);
            List<WeightedEdge<Long>> neighbors = this.neighbors(node.id());
            if (neighbors.size() > 0) {
                pointTree.addPoint(nodePoint);
            }
            String nodeName = node.name();
            if (nodeName == null) {
                continue;
            }
            String nodeKey = cleanString(nodeName);
            if (locationsTrieMap.contains(nodeKey)) {
                Set<Node> nodeSet = locationsTrieMap.get(nodeKey);
                nodeSet.add(node);
            } else {
                Set<Node> nodeSet = new HashSet<>();
                nodeSet.add(node);
                locationsTrieMap.put(nodeKey, nodeSet);
            }
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point nearestPoint = pointTree.nearest(lon, lat);
        Node nearestNode = pointNodeMap.get(nearestPoint);
        return nearestNode.id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        HashSet<String> resultSet = new HashSet<>();
        Set<String> keysSet = locationsTrieMap.keysWithPrefix(prefix);
        if (keysSet != null) {
            for (String key : keysSet) {
                Set<Node> nodeSet = locationsTrieMap.get(key);
                for (Node node : nodeSet) {
                    resultSet.add(node.name());
                }
            }
        }
        ArrayList<String> result = new ArrayList<>(resultSet);
        result.sort(null);
        return result;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        if (locationName == null) {
            return result;
        }
        locationName = cleanString(locationName);
        Set<Node> nodes = locationsTrieMap.get(locationName);
        if (nodes == null) {
            return result;
        }
        for (Node node : nodes) {
            HashMap<String, Object> nodeMap = new HashMap<>();
            nodeMap.put("lat", node.lat());
            nodeMap.put("lon", node.lon());
            nodeMap.put("name", node.name());
            nodeMap.put("id", node.id());
            result.add(nodeMap);
        }
        return result;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
