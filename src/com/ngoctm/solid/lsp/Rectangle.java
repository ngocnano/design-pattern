package com.ngoctm.solid.lsp;
// Nguyen tac thay the: Lop con thay the lop cha ma k anh hyong
public class Rectangle {
    protected int width, high;

    public Rectangle() {
    }

    public Rectangle(int width, int high) {
        this.width = width;
        this.high = high;
    }

    public int getArea(){
        return width * high;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", high=" + high +
                '}';
    }
}

class Square extends Rectangle {
    public Square(){

    }

    public Square(int size){
        this.width = this.high = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHigh(width);
    }

    @Override
    public void setHigh(int high) {
        super.setHigh(high);
        super.setWidth(high);
    }
}

class Demo{

    static void useIt(Rectangle rectangle){
        int w = rectangle.getWidth();
        rectangle.setHigh(10);
        System.out.println("E " + (w * 10) + " A " + rectangle.getArea());
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2,3);
        useIt(rectangle);
        Rectangle rectangle1 = new Square(5);
        useIt(rectangle1);
    }
}
