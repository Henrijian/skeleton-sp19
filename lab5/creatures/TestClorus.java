package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    private final double TOLERANCE = 0.01;
    @Test
    public void testBasics() {
        Clorus c = new Clorus(5);
        /* Test initialization. */
        assertEquals(5, c.energy(), TOLERANCE);
        /* Test MOVE action. */
        c.move();
        assertEquals(4.97, c.energy(), TOLERANCE);
        /* Test STAY action. */
        c.stay();
        assertEquals(4.97, c.energy(), TOLERANCE);
        /* Test color. */
        assertEquals(new Color(34, 0, 231), c.color());
        /* Test ATTACK action. */
        Clorus prey = new Clorus(3.03);
        c.attack(prey);
        assertEquals(8.0, c.energy(), TOLERANCE);
        /* Test REPLICATE action. */
        Clorus offspring = c.replicate();
        assertNotSame(c, offspring);
        assertEquals(4.0, c.energy(), TOLERANCE);
        assertEquals(4.0, offspring.energy(), TOLERANCE);
        /* Test DIE action. */
    }

    @Test
    public void testChooseAction() {
        /* Test If there are no empty squares, the Clorus will STAY
           (even if there are Plips nearby they could attack since plip squares do not count as empty squares). */
        Clorus c = new Clorus(5);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        c = new Clorus(5);
        HashMap<Direction, Occupant> surroundedPlips = new HashMap<Direction, Occupant>();
        surroundedPlips.put(Direction.TOP, new Plip());
        surroundedPlips.put(Direction.BOTTOM, new Plip());
        surroundedPlips.put(Direction.LEFT, new Plip());
        surroundedPlips.put(Direction.RIGHT, new Plip());
        actual = c.chooseAction(surroundedPlips);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        /* Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly. */
        c = new Clorus(5);
        HashMap<Direction, Occupant> topEmptyRightPlip = new HashMap<Direction, Occupant>();
        topEmptyRightPlip.put(Direction.TOP, new Empty());
        topEmptyRightPlip.put(Direction.BOTTOM, new Impassible());
        topEmptyRightPlip.put(Direction.LEFT, new Impassible());
        topEmptyRightPlip.put(Direction.RIGHT, new Plip(5));
        actual = c.chooseAction(topEmptyRightPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.RIGHT);
        assertEquals(expected, actual);

        /* Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square. */
        c = new Clorus(1);
        HashMap<Direction, Occupant> bottomEmpty = new HashMap<Direction, Occupant>();
        bottomEmpty.put(Direction.TOP, new Impassible());
        bottomEmpty.put(Direction.BOTTOM, new Empty());
        bottomEmpty.put(Direction.LEFT, new Impassible());
        bottomEmpty.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(bottomEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected, actual);

        /* Otherwise, the Clorus will MOVE to a random empty square. */
        c = new Clorus(0.9);
        actual = c.chooseAction(bottomEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        assertEquals(expected, actual);
    }
}
