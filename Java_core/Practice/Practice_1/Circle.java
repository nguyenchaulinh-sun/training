package Practice_1;
public class Circle extends Shape {

    public Circle(double radius) {
        super(radius * 2, radius * 2);
    }

    public double getArea() {
        double radius = width / 2;
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return width * 3.14;
    }
}