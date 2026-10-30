package Practice_1;

public class Main {
    public static void main(String[] args) {

        Shape shape = new Shape(10, 20);
        Rectangle rectangle = new Rectangle(10, 20);
        Circle circle = new Circle(5);

        System.out.println("=== SHAPE ===");
        shape.displayInfo();

        System.out.println("\n=== RECTANGLE ===");
        rectangle.displayInfo();
        System.out.println("Area: " + rectangle.getArea());
        System.out.println("Perimeter: " + rectangle.getPerimeter());

        System.out.println("\n=== CIRCLE ===");
        circle.displayInfo();
        System.out.println("Area: " + circle.getArea());
        System.out.println("Circumference: " + circle.getCircumference());
    }
}