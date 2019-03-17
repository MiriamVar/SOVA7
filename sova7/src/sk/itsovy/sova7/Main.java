package sk.itsovy.sova7;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Database database = new Database();

        Car auticko = new Car("seat","cierne",'F',"BA467KL",9700);
        database.addCar(auticko);

        List<Car> auto = database.getCarsByPrice(11000);
        for (int i = 0; i < auto.size(); i++) {
            System.out.println(auto.get(i).getBrand() + " " + auto.get(i).getColor() + " " + auto.get(i).getFuel() + " " + auto.get(i).getSpz()+" "+auto.get(i).getPrice());
        }

        List<Car> auto1 = database.getCarsByBrand("hyundai");
        for (int i = 0; i < auto1.size(); i++) {
            System.out.println(auto1.get(i).getBrand() + " " + auto1.get(i).getColor() + " " + auto1.get(i).getFuel() + " " + auto1.get(i).getSpz()+" "+auto1.get(i).getPrice());
        }

        List<Car> auto2 = database.getCarsByFuel('F');
        for (int i = 0; i < auto2.size(); i++) {
            System.out.println(auto2.get(i).getBrand() + " " + auto2.get(i).getColor() + " " + auto2.get(i).getFuel() + " " + auto2.get(i).getSpz()+" "+auto2.get(i).getPrice());
        }

        List<Car> auto3 = database.getCarsByRegion("KE");
        for (int i = 0; i < auto3.size(); i++) {
            System.out.println(auto3.get(i).getBrand() + " " + auto3.get(i).getColor() + " " + auto3.get(i).getFuel() + " " + auto3.get(i).getSpz()+" "+auto3.get(i).getPrice());
        }

        database.changeSPZ("BA467KL","BL467KL");


    }
}
