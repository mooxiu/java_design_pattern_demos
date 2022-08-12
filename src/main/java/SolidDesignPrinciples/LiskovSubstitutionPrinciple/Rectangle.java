package SolidDesignPrinciples.LiskovSubstitutionPrinciple;

public class Rectangle {
    protected int height, width;

    public Rectangle() {
    }

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getArea() {
        return this.height * this.width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "height=" + height +
                ", width=" + width +
                '}';
    }
}


// we should avoid using a subclass like this!
class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        this.height = this.width = size;
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }
}

class Demo {
    public static void UseRectangle(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("the area should be: " + (10 * width) + " , while we get: " + r.getArea());
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(2, 3);
        UseRectangle(r);

        Rectangle s = new Square(3);
        UseRectangle(s);
    }

}