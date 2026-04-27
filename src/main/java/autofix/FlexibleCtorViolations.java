package autofix;

import java.util.*;

public class FlexibleCtorViolations {

    static class Base {
        String name;
        Base(String name) { this.name = name; }
        public void doWork() { System.out.println(name); }
    }

    // Violations inside class with flexible constructor
    static class Child extends Base {
        Child(String name) {
            if (name == null) throw new IllegalArgumentException();
            String trimmed = name.trim();
            super(trimmed);
        }

        // Useless override
        @Override
        public void doWork() {
            super.doWork();
        }

        // Array toString inside flexible ctor class
        void debug(int[] data) {
            System.out.println(data.toString());
        }

        // Concrete collection
        public ArrayList<String> getNames() {
            return new ArrayList<>();
        }
    }

    // ThrowsThrowable
    void risky() throws Exception {
        System.out.println("risky");
    }
}
