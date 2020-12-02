import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        Random r = new Random();

        BST<Integer> bst = new BST<>();
        MyBST<Integer> myBst = new MyBST<>();
        List<Integer> nodeCountValues = new ArrayList<>();
        List<Double> optimalAvgDepthValues = new ArrayList<>();
        List<Double> bstAvgDepthValues = new ArrayList<>();
        List<Double> myBstAvgDepthValues = new ArrayList<>();
        for (int nodeCount = 1; nodeCount <= 5000; nodeCount++) {
            nodeCountValues.add(nodeCount);
            double optimalAvgDepth = ExperimentHelper.optimalAverageDepth(nodeCount);
            optimalAvgDepthValues.add(optimalAvgDepth);

            int key;
            do {
                key = r.nextInt(Integer.MAX_VALUE);
            } while (bst.contains(key));

            bst.add(key);
            double bstAvgDepth = bst.averageDepth();
            bstAvgDepthValues.add(bstAvgDepth);
            myBst.add(key);
            double myBstAvgDepth = myBst.averageDepth();
            myBstAvgDepthValues.add(myBstAvgDepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).
            xAxisTitle("Node Count").yAxisTitle("Average Depth").build();
        chart.addSeries("Optimal", nodeCountValues, optimalAvgDepthValues);
        chart.addSeries("BST", nodeCountValues, bstAvgDepthValues);
        chart.addSeries("MyBST", nodeCountValues, myBstAvgDepthValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
        experiment1();
    }
}
