import java.util.HashMap;
import java.util.Map;

// Abstract Shape Prototype
abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    abstract void draw();

    // Method to be overridden for setting attributes specific to shapes
    void setAttribute(String attributeName, Object attributeValue) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    // Implements cloning support
    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

// Concrete Circle class
class Circle extends Shape {
    private int radius;

    public Circle() {
        type = "Circle";
    }

    // Allows setting the radius attribute for Circle
    @Override
    public void setAttribute(String attributeName, Object attributeValue) {
        if ("radius".equals(attributeName)) {
            this.radius = (Integer) attributeValue;
        }
    }

    @Override
    void draw() {
        System.out.println("Drawing Circle with radius: " + this.radius);
    }
}

// Concrete Rectangle class
class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    void draw() {
        System.out.println("Drawing Rectangle");
    }
}

// Prototype Registry to manage shape prototypes
class ShapeRegistry {

    private static Map<String, Shape> shapeMap = new HashMap<>();

    // Returns a clone of the shape identified by shapeId
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    // Loads initial shape prototypes into the registry
    public static void loadShapes() {
        Circle circle = new Circle();
        circle.setId("1");
        circle.setAttribute("radius", 5); // Predefined radius for the prototype circle
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("2");
        shapeMap.put(rectangle.getId(), rectangle);
    }
}

// Demonstrates the Prototype Pattern
public class PrototypePattern {
    public static void main(String[] args) {
        ShapeRegistry.loadShapes();

        System.out.println("Cloning Circle with predefined radius");
        Shape clonedCircle = ShapeRegistry.getShape("1");
        System.out.println("Shape: " + clonedCircle.getType() + " (before setting new radius)");
        clonedCircle.draw();

        System.out.println("\nSetting new radius for cloned Circle");
        clonedCircle.setAttribute("radius", 10);
        System.out.println("Shape: " + clonedCircle.getType() + " (after setting new radius)");
        clonedCircle.draw();

        System.out.println("\nCloning Rectangle...");
        Shape clonedRectangle = ShapeRegistry.getShape("2");
        System.out.println("Shape: " + clonedRectangle.getType());
        clonedRectangle.draw();
    }
}
