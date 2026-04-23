package autofix;

import java.util.*;

public class RecordViolations {

    record Point(int x, int y) {}
    record Line(Point start, Point end) {}
    record Circle(double radius, Point center) {}

    // SelfAssignment inside record method context
    void updatePoint(int x) {
        x = x;
    }

    // ConcreteCollectionType with records
    public ArrayList<Point> getPoints() {
        return new ArrayList<>();
    }

    // BadArrayToString with record array
    void printLines(Line[] lines) {
        System.out.println(lines.toString());
    }

    // CompareToShouldntBeNegated
    void compareCircles(Circle a, Circle b) {
        Comparator<Circle> comp = Comparator.comparingDouble(Circle::radius);
        int x = -comp.compare(a, b);
    }

    // NonstaticStaticAccess
    static int MAX_POINTS = 100;
    void accessViaInstance() {
        RecordViolations inst = new RecordViolations();
        System.out.println(inst.MAX_POINTS);
    }

    // StaticAccessThruChild
    static class ParentConfig {
        static final String DEFAULT = "default";
    }
    static class ChildConfig extends ParentConfig {}

    void accessParentViaChild() {
        String val = ChildConfig.DEFAULT;
    }
}
