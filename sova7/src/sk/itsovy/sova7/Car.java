package sk.itsovy.sova7;

public class Car {

    private String brand;
    private String color;
    private char fuel;
    private String spz;
    private int price;

    public Car(String brand, String color, char fuel, String spz, int price) {
        this.brand = brand;
        this.color = color;
        this.fuel = fuel;
        this.spz = spz;
        this.price = price;
    }
    public Car(){
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public char getFuel() {
        return fuel;
    }

    public String getSpz() {
        return spz;
    }

    public int getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFuel(char fuel) {
        this.fuel = fuel;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
