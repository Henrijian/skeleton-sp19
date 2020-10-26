package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.*;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    private static final double MOVE_ENERGY_CONSUMED = 0.15;

    private static final double STAY_ENERGY_GAIN = 0.2;

    private static final double MAX_ENERGY = 2;

    private static final double MIN_ENERGY = 0;

    private static final double REP_ENERGY_RETAINED = 0.5;

    private static final double REP_ENERGY_GIVEN = 0.5;

    private static final double RPE_MIN_ENERGY = 1.0;

    private static final double MOVE_PROBABILITY = 0.5;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        r = 99;
        g = (int)energy * 96 + 63;
        b = 76;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= MOVE_ENERGY_CONSUMED;
        if (energy < MIN_ENERGY) {
            energy = MIN_ENERGY;
        }
    }

    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += STAY_ENERGY_GAIN;
        if (energy > MAX_ENERGY) {
            energy = MAX_ENERGY;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip r = new Plip(energy * REP_ENERGY_GIVEN);
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
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        List<Direction> emptyDirections = new ArrayList<>();
        boolean anyClorus = false;
        boolean anyEmpty = false;
        for (Map.Entry<Direction, Occupant> neighbor: neighbors.entrySet()) {
            Direction dir = neighbor.getKey();
            Occupant occ = neighbor.getValue();
            if (occ.name().equals("empty")) {
                anyEmpty = true;
                emptyDirections.add(dir);
            }
            if (occ.name().equals("clorus")) {
                anyClorus = true;
            }
        }
        if (!anyEmpty) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (energy >= RPE_MIN_ENERGY) {
            Direction emptyDir = randomDirection(emptyDirections);
            return new Action(Action.ActionType.REPLICATE, emptyDir);
        }

        // Rule 3
        if (anyClorus) {
            Direction emptyDir;
            do {
                emptyDir = randomDirection(emptyDirections);
            } while (Math.random() < MOVE_PROBABILITY);
            return new Action(Action.ActionType.MOVE, emptyDir);
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
