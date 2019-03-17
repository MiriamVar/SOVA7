package sk.itsovy.sova7;

import java.util.List;

public interface carmethods {

    void addCar( Car car);
    List<Car> getCarsByPrice(int maxPrice);
    List<Car> getCarsByBrand(String brand);
    List<Car> getCarsByFuel(char fuel);
    List<Car> getCarsByRegion(String spz);
    void changeSPZ(String oldSPZ, String newSPZ);
    void generateXML();
}
