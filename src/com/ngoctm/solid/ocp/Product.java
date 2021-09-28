package com.ngoctm.solid.ocp;

//ocp + specification

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return products.stream().filter(p -> p.color == color);
    }

    //manager want adding filter by size copy-paste? modifiy color -> size

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(p -> p.size == size);
    }

    //manager want adding filter by size and color copy-paste? modifiy code

    public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color){
        return products.stream().filter(p -> (p.color == color && p.size == size));
    }
}

//solution - when adding filter don't need modify class available
interface Specification<T>{
    boolean isSatisfied(T item);
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class BetterFilter implements Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(product -> spec.isSatisfied(product));
    }
}

class ColorSpecification implements Specification<Product>{
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class SizeSpecification implements Specification<Product>{
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

//combine spec
class AndSpecification implements Specification<Product>{
    private List<Specification> specifications;

    public AndSpecification(List<Specification> specifications) {
        this.specifications = specifications;
    }

    @Override
    public boolean isSatisfied(Product item) {
        for (Specification spec : specifications){
            if(!spec.isSatisfied(item)){
                return false;
            }
        }
        return true;
    }
}



class Demo{
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("apple", Color.BLUE, Size.MEDIUM));
        products.add(new Product("samsung", Color.BLUE, Size.LARGE));
        products.add(new Product("nokia", Color.GREEN, Size.LARGE));
        products.add(new Product("sony", Color.GREEN, Size.LARGE));

        ProductFilter productFilter = new ProductFilter();
        System.out.println("Filter by color: ");
        productFilter.filterByColor(products, Color.BLUE).forEach(product -> System.out.println(product.name));
        System.out.println("Filter by size: ");
        productFilter.filterBySize(products, Size.LARGE).forEach(product -> System.out.println(product.name));

        System.out.println("New filter ---------");
        BetterFilter betterFilter = new BetterFilter();
        List<Specification> specifications = new ArrayList<>();
        specifications.add(new ColorSpecification(Color.BLUE));
        specifications.add(new SizeSpecification(Size.LARGE));
        AndSpecification andSpecification = new AndSpecification(specifications);
        betterFilter.filter(products,andSpecification).forEach(product -> System.out.println(product.name));
    }
}

enum Color{
    RED, BLUE, GREEN
}

enum Size {
    SMALL, MEDIUM, LARGE, YUGE
}

