package autofix;

import java.util.*;

public class UnnamedVarViolations {

    record Item(String name, int qty) {}

    // Violation on same line as unnamed var
    void processItems(List<Item> items) {
        for (Item _ : items) {
            int[] arr = new int[3];
            System.out.println(arr.toString());
        }
    }

    // Self-assign inside unnamed lambda
    void processMap(Map<String, Integer> map) {
        map.forEach((_, v) -> {
            int x = v;
            x = x;
        });
    }

    // Unnamed in catch with violation
    int safeParse(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException _) {
            int[] err = {-1};
            System.out.println(err.toString());
            return -1;
        }
    }

    // Multiple unnamed - concrete collection return
    void drain(Queue<String> q) {
        var _ = q.remove();
        var _ = q.remove();
        System.out.println(q.remove());
    }

    // Unnamed in record deconstruction with violation on same line
    record Line(Item start, Item end) {}
    void deconstructLine(Object obj) {
        if (obj instanceof Line(Item(String name, int _), Item(String _, int qty))) {
            int[] debug = {1};
            System.out.println(debug.toString());
        }
    }

    // Concrete collection
    public LinkedList<Item> getItems() {
        return new LinkedList<>();
    }
}
