package autofix;

import java.util.*;

public class RecordViolations {

    record Point(int x, int y) {
        // Violation INSIDE record method
        public ArrayList<Point> neighbors() {
            return new ArrayList<>();
        }

        // Self-assign inside record
        void normalize(int scale) {
            scale = scale;
        }
    }

    record Circle(double radius, Point center) {
        // Array toString inside record
        void debugPrint(int[] data) {
            System.out.println(data.toString());
        }
    }

    sealed interface Shape permits Rect, Oval {}
    record Rect(double w, double h) implements Shape {}
    record Oval(double rx, double ry) implements Shape {}

    // Violation inside switch expression with patterns
    String describe(Shape shape) {
        return switch (shape) {
            case Rect r when r.w() > 0 -> {
                int x = 5;
                x = x;
                yield "rect: " + r.w();
            }
            case Rect r -> "small rect";
            case Oval o -> {
                int[] arr = {1, 2};
                System.out.println(arr.toString());
                yield "oval";
            }
        };
    }

    // Violation inside instanceof pattern block
    void process(Object obj) {
        if (obj instanceof Rect(double w, double h)) {
            int[] debug = {1};
            System.out.println(debug.toString());
        }
        if (obj instanceof Shape s) {
            int val = 10;
            val = val;
        }
    }

    // Concrete collection with records
    public HashMap<String, Shape> getShapeMap() {
        return new HashMap<>();
    }

    // Static access via instance
    static int MAX = 100;
    void accessBug() {
        RecordViolations inst = new RecordViolations();
        System.out.println(inst.MAX);
    }
}
