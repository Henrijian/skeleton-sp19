package creatures;
import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    /* Color of red. */
    private int r;
    /* Color of green. */
    private int g;
    /* Color of blue. */
    private int b;

    private static final double MOVE_ENERGY_CONSUMED = 0.03;

    private static final double STAY_ENERGY_CONSUMED = 0.01;

    private static final double REP_ENERGY_RETAINED = 0.5;

    private static final double REP_ENERGY_GIVEN = 0.5;

    private static final double RPE_MIN_ENERGY = 1.0;


    /* Initiate Clorus with energy e. */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    /* Clorus always stay on color in rgb color, where red = 34, green = 0, and blue = 231. */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return new Color(r, g, b);
    }

    /* Get energy from attacked creature. */
    public void attack(Creature other) {
        energy += other.energy();
    }

    /* Whenever move action happened, lose 0.3 point of energy after action finished. */
    public void move() {
        energy -= MOVE_ENERGY_CONSUMED;
    }

    /* Whenever stay action happened, lose 0.1 point of energy after action complete. */
    public void stay() {
        energy -= STAY_ENERGY_CONSUMED;
    }

    /* Consume 50% of energy to replicate offspring, and offspring get 50% energy from mother. */
    public Clorus replicate() {
        Clorus r = new Clorus(energy * REP_ENERGY_GIVEN);
        energy *= REP_ENERGY_RETAINED;
        return r;
    }

    /**
     * Randomly choose the direction in directions.
     */
    private Direction randomDirection(List<Direction> directions) {
        int randomIdx = (int)Math.floor(Math.random() * directions.size());
        return directions.get(randomIdx);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        List<Direction> emptyDirections = new ArrayList<>();
        List<Direction> plipDirections = new ArrayList<>();
        for (Map.Entry<Direction, Occupant> neighbor: neighbors.entrySet()) {
            Direction dir = neighbor.getKey();
            Occupant occ = neighbor.getValue();
            if (occ.name().equals("empty")) {
                emptyDirections.add(dir);
            }
            if (occ.name().equals("plip")) {
                plipDirections.add(dir);
            }
        }
        if (emptyDirections.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule2
        if (plipDirections.size() > 0) {
            Direction plipDir = randomDirection(plipDirections);
            return new Action(Action.ActionType.ATTACK, plipDir);
        }

        // Rule3
        if (energy >= RPE_MIN_ENERGY) {
            Direction repDir = randomDirection(emptyDirections);
            return new Action(Action.ActionType.REPLICATE, repDir);
        }

        Direction moveDir = randomDirection(emptyDirections);
        return new Action(Action.ActionType.MOVE, moveDir);
    }
}
