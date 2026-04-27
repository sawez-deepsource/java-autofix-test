package autofix;

import java.util.*;

public class UnnamedVarViolations {

    record Item(String name, int qty) {}

    // Violations right next to unnamed variable usage
    void processItems(List<Item> items) {
        for (Item _ : items) {
            int[] arr = new int[3];
            System.out.println(arr.toString());
        }
    }

    void processMap(Map<String, Integer> map) {
        map.forEach((_, v) -> {
            int x = v;
            x = x;
        });
    }

    int safeParse(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException _) {
            int[] err = {-1};
            System.out.println(err.toString());
            return -1;
        }
    }

    // Concrete collection
    public LinkedList<Item> getItems() {
        return new LinkedList<>();
    }
}
