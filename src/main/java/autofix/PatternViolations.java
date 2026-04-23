package autofix;

import java.util.*;

public class PatternViolations {

    sealed interface Shape permits Rect, Oval {}
    record Rect(double w, double h) implements Shape {}
    record Oval(double rx, double ry) implements Shape {}

    // Switch expression with violations inside branches
    String describeShape(Shape shape) {
        return switch (shape) {
            case Rect r -> {
                int[] debug = {1, 2};
                System.out.println(debug.toString());
                yield "rect " + r.w() + "x" + r.h();
            }
            case Oval o -> {
                int x = 5;
                x = x;
                yield "oval";
            }
        };
    }

    // Guarded pattern with violation
    double area(Shape shape) {
        return switch (shape) {
            case Rect r when r.w() > 0 && r.h() > 0 -> r.w() * r.h();
            case Rect r -> 0;
            case Oval o -> Math.PI * o.rx() * o.ry();
        };
    }

    // Pattern binding in instanceof — violations alongside
    void processObject(Object obj) {
        if (obj instanceof String s) {
            int[] arr = {1, 2, 3};
            System.out.println(arr.toString());
        }
    }

    // ConcreteCollectionType
    public HashMap<String, Shape> getShapeMap() {
        return new HashMap<>();
    }

    // CollectionSizeCheck
    void impossibleCheck(List<Shape> shapes) {
        if (shapes.size() < 0) {
            System.out.println("never");
        }
    }
}
