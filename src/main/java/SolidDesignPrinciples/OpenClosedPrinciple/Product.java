package SolidDesignPrinciples.OpenClosedPrinciple;

import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE
}

public class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

/*
    Bad
 */
// using a isolated product filter so that we fulfill the single responsibility principle (SRP)
class ProductFilter {

    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }


    public Stream<Product> filterBySize(List<Product> products, Size size) {
        return products.stream().filter(p -> p.size == size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products, Color color, Size size) {
        return products.stream().filter(p -> p.color == color && p.size == size);
    }
}


/*
    Good
 */
interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T> {
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product> {
    private final Color color;

    ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == this.color;
    }
}

class SizeSpecification implements Specification<Product> {
    private final Size size;

    SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == this.size;
    }
}

class AndSpecification<T> implements Specification<T> {
    private Specification<T> t1, t2;

    public AndSpecification(Specification<T> t1, Specification<T> t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isSatisfied(T item) {
        return t1.isSatisfied(item) && t2.isSatisfied(item);
    }
}

class BetterProductFilter<T> implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(spec::isSatisfied);
    }

}

class Demo {
    public static void main(String[] args) {
        Product apple = new Product("Apple", Color.RED, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);

        ProductFilter pf = new ProductFilter();
        System.out.println("Green Products (old): ");
        pf.filterByColor(products, Color.GREEN)
                .forEach(p -> System.out.println("- " + p.name + " is green."));
        pf.filterBySize(products, Size.LARGE)
                .forEach(p -> System.out.println("-" + p.name + " is large"));
        pf.filterByColorAndSize(products, Color.RED, Size.LARGE)
                .forEach(System.out::println);


        BetterProductFilter<Product> bf = new BetterProductFilter<>();
        bf.filter(products, new ColorSpecification(Color.GREEN)).forEach(System.out::println);
        bf.filter(products, new SizeSpecification(Size.LARGE)).forEach(System.out::println);
        bf.filter(products, new AndSpecification<>(new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE))).forEach(System.out::println);
    }
}