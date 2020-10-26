import java.util.LinkedList;
import java.util.List;

public class Stack<Item> {
    private LinkedList<Item> list;

    public Stack() {
        list = new LinkedList<>();
    }

    public void push(Item x) {
        list.push(x);
    }
}