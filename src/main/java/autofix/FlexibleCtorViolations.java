package autofix;

import java.util.*;

public class FlexibleCtorViolations {

    static class Base {
        String name;
        Base(String name) { this.name = name; }
        public void doWork() { System.out.println(name); }
    }

    // JEP 513: statements before super()
    static class Child extends Base {
        Child(String name) {
            if (name == null) throw new IllegalArgumentException("null name");
            String trimmed = name.trim();
            super(trimmed);
        }

        // UselessMethod
        @Override
        public void doWork() {
            super.doWork();
        }
    }

    // ThrowsThrowable
    void process() throws Exception {
        System.out.println("processing");
    }

    // SelfAssignment
    void bug(int x) {
        x = x;
    }

    // BadArrayToString
    void printData(int[] data) {
        System.out.println(data.toString());
    }

    // ConcreteCollectionType
    public ArrayList<String> getNames() {
        return new ArrayList<>();
    }
}
