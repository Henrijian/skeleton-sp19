import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private ArrayList<Flight> flights;

    public FlightSolver(ArrayList<Flight> flights) {
        if (flights == null) {
            throw new IllegalArgumentException();
        }
        this.flights = flights;
    }

    public int solve() {
        Comparator<Integer> timeStampComparator = (Integer timeStampA, Integer timeStampB) -> {
            int diff = timeStampA - timeStampB;
            return diff;
        };
        PriorityQueue<Integer> timeStampQueue = new PriorityQueue<>(timeStampComparator);
        for (Flight flight: flights) {
            timeStampQueue.add(flight.startTime);
            timeStampQueue.add(flight.endTime);
        }
        int maxPassengers = 0;
        int startTime = 0;
        do {
            Integer endTime = timeStampQueue.poll();
            int curPassengers = 0;
            for (Flight flight: flights) {
                if (flight.startTime <= startTime && endTime <= flight.endTime) {
                    curPassengers += flight.passengers;
                }
            }
            startTime = endTime;
            if (curPassengers > maxPassengers) {
                maxPassengers = curPassengers;
            }
        } while (timeStampQueue.size() != 0);
        return maxPassengers;
    }

}
