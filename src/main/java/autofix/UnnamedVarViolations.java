package autofix;

import java.util.*;

public class UnnamedVarViolations {

    record Item(String name, int qty) {}

    // Unnamed in for-each with violation after
    void countItems(List<Item> items) {
        int count = 0;
        for (Item _ : items) {
            count++;
        }
        int[] arr = new int[count];
        System.out.println(arr.toString());
    }

    // Unnamed in catch with violation
    int safeParse(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException _) {
            int x = -1;
            x = x;
            return x;
        }
    }

    // Unnamed lambda param with violation
    void processMap(Map<String, Integer> map) {
        map.forEach((_, v) -> System.out.println(v));
        int result = -"hello".compareTo("world");
    }

    // ConcreteCollectionType
    public LinkedList<Item> getItems() {
        return new LinkedList<>();
    }
}
