package Builder.examples;

public abstract class Vehicle<E extends Vehicle<E>> implements Comparable<E> {
    private String name;
    private double length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public int compareTo(E o) {
        if (this.getLength() > o.getLength()) {
            return 1;
        } else if (this.getLength() < o.getLength()) {
            return -1;
        } else {
            return 0;
        }
    }
}

