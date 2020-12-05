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
    public static final int NODE_COUNT_BOUND = 5000;
    public static final Random random = new Random();

    public static void experiment1() {
        BST<Integer> bst = new BST<>();
        MyBST<Integer> myBst = new MyBST<>();
        List<Integer> nodeCountValues = new ArrayList<>();
        List<Double> optimalAvgDepthValues = new ArrayList<>();
        List<Double> bstAvgDepthValues = new ArrayList<>();
        List<Double> myBstAvgDepthValues = new ArrayList<>();
        for (int nodeCount = 1; nodeCount <= NODE_COUNT_BOUND; nodeCount++) {
            nodeCountValues.add(nodeCount);
            double optimalAvgDepth = ExperimentHelper.optimalAverageDepth(nodeCount);
            optimalAvgDepthValues.add(optimalAvgDepth);

            int key;
            do {
                key = random.nextInt(Integer.MAX_VALUE);
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
        BST<Integer> bst = new BST<>();
        for (int nodeCount = 1; nodeCount <= NODE_COUNT_BOUND; nodeCount++) {
            int key;
            do {
                key = random.nextInt(Integer.MAX_VALUE);
            } while (bst.contains(key));
            bst.add(key);
        }

        List<Integer> operationCountValues = new ArrayList<>();
        operationCountValues.add(0);
        List<Double> avgDepthValues = new ArrayList<>();
        avgDepthValues.add(bst.averageDepth());

        final int OPERATION_BOUND = 5000;
        for (int operationCount = 1; operationCount <= OPERATION_BOUND; operationCount++) {
            operationCountValues.add(operationCount);
            ExperimentHelper.randomlyDeleteAndInsert(bst, operationCount, false);
            double avgDepth = bst.averageDepth();
            avgDepthValues.add(avgDepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).
                xAxisTitle("Operation Count").yAxisTitle("Average Depth").build();
        chart.addSeries("experiment2", operationCountValues, avgDepthValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        BST<Integer> bst = new BST<>();
        for (int nodeCount = 1; nodeCount <= NODE_COUNT_BOUND; nodeCount++) {
            int key;
            do {
                key = random.nextInt(Integer.MAX_VALUE);
            } while (bst.contains(key));
            bst.add(key);
        }

        List<Integer> operationCountValues = new ArrayList<>();
        operationCountValues.add(0);
        List<Double> avgDepthValues = new ArrayList<>();
        avgDepthValues.add(bst.averageDepth());

        final int OPERATION_BOUND = 5000;
        for (int operationCount = 1; operationCount <= OPERATION_BOUND; operationCount++) {
            operationCountValues.add(operationCount);
            ExperimentHelper.randomlyDeleteAndInsert(bst, operationCount, true);
            double avgDepth = bst.averageDepth();
            avgDepthValues.add(avgDepth);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).
                xAxisTitle("Operation Count").yAxisTitle("Average Depth").build();
        chart.addSeries("experiment3", operationCountValues, avgDepthValues);

        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();
    }
}
