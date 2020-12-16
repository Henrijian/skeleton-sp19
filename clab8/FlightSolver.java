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
        Comparator<Flight> startTimeComparator = (Flight flightA, Flight flightB) -> {
            int diff = flightA.startTime - flightB.startTime;
            return diff;
        };
        Comparator<Flight> endTimeComparator = (Flight flightA, Flight flightB) -> {
            int diff = flightA.endTime - flightB.endTime;
            return diff;
        };
        PriorityQueue<Flight> startTimeQueue = new PriorityQueue<>(startTimeComparator);
        PriorityQueue<Flight> endTimeQueue = new PriorityQueue<>(endTimeComparator);
        for (Flight flight: flights) {
            startTimeQueue.add(flight);
            endTimeQueue.add(flight);
        }
        int maxPassengers = 0;
        int curPassengers = 0;
        do {
            Flight startFlight = startTimeQueue.peek();
            Flight endFlight = endTimeQueue.peek();
            if (startFlight == null || endFlight.endTime < startFlight.startTime) {
                curPassengers -= endFlight.passengers;
                endTimeQueue.poll();
            } else if (startFlight.startTime < endFlight.endTime) {
                curPassengers += startFlight.passengers;
                startTimeQueue.poll();
                if (curPassengers > maxPassengers) {
                    maxPassengers = curPassengers;
                }
            }
        } while (startTimeQueue.size() != 0 || endTimeQueue.size() != 0);
        return maxPassengers;
    }

}
