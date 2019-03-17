package sk.itsovy.sova7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Database implements carmethods{

    private final String userName = "user1";
    private final String password = "heslo";
    private final String url = "jdbc:mysql://localhost:3308/sova7";

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Is connection");
            connection = DriverManager.getConnection(url,userName,password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }


    public void addCar(Car car){
        Connection con= getConnection();
        String query = "insert into cars(brand,color,fuel,spz,price) values(?,?,?,?,?)";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,car.getBrand());
            statement.setString(2,car.getColor());
            statement.setString(3,String.valueOf(car.getFuel()));
            statement.setString(4,car.getSpz());
            statement.setInt(5,car.getPrice());
            int result = statement.executeUpdate();

            con.close();

        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Car> getCarsByPrice(int maxPrice){
        Connection con= getConnection();
        String query = "select * from cars where price <= ?";
        List<Car> auta = new ArrayList<>();
         String carBrand="";
         String carColor="";
         char carFuel=' ';
         String carSpz="";
         int carPrice=0;
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, String.valueOf(maxPrice));
            ResultSet res = statement.executeQuery();
                while (res.next()) {
                    carBrand = res.getString("brand");
                    carColor = res.getString("color");
                    carFuel = res.getString("fuel").charAt(0);
                    carSpz = res.getString("spz");
                    carPrice = res.getInt("price");
                    Car auto = new Car(carBrand,carColor,carFuel,carSpz,carPrice);
                    auta.add(auto);
                }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auta;
    }

    public List<Car> getCarsByBrand(String brand){
        Connection con= getConnection();
        String query = "select * from cars where brand = ?";
        List<Car> auta = new ArrayList<>();
        String carBrand="";
        String carColor="";
        char carFuel=' ';
        String carSpz="";
        int carPrice=0;
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,brand);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                carBrand = res.getString("brand");
                carColor = res.getString("color");
                carFuel = res.getString("fuel").charAt(0);
                carSpz = res.getString("spz");
                carPrice = res.getInt("price");
                Car auto = new Car(carBrand,carColor,carFuel,carSpz,carPrice);
                auta.add(auto);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auta;

    }

    public List<Car> getCarsByFuel(char fuel){
        Connection con= getConnection();
        String query = "select * from cars where fuel = ?";
        List<Car> auta = new ArrayList<>();
        String carBrand="";
        String carColor="";
        char carFuel=' ';
        String carSpz="";
        int carPrice=0;
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, String.valueOf(fuel));
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                carBrand = res.getString("brand");
                carColor = res.getString("color");
                carFuel = res.getString("fuel").charAt(0);
                carSpz = res.getString("spz");
                carPrice = res.getInt("price");
                Car auto = new Car(carBrand,carColor,carFuel,carSpz,carPrice);
                auta.add(auto);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auta;

    }

    public List<Car> getCarsByRegion(String spz){
        Connection con= getConnection();
        String okres = spz.substring(0,2);
        String query = "select * from cars where spz like ?";
        List<Car> auta = new ArrayList<>();
        String carBrand="";
        String carColor="";
        char carFuel=' ';
        String carSpz="";
        int carPrice=0;
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, okres+'%');
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                carBrand = res.getString("brand");
                carColor = res.getString("color");
                carFuel = res.getString("fuel").charAt(0);
                carSpz = res.getString("spz");
                carPrice = res.getInt("price");
                Car auto = new Car(carBrand,carColor,carFuel,carSpz,carPrice);
                auta.add(auto);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auta;
    }

    public void changeSPZ(String oldSPZ, String newSPZ){
        Connection con= getConnection();
        String query = "update cars set spz=?  where spz like ?";
        try{
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,newSPZ);
            statement.setString(2,oldSPZ);
            int result = statement.executeUpdate();

            con.close();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void generateXML(){
        Connection con = getConnection();
        String query = "select * from cars";

        ArrayList<Car> auta = new ArrayList<>();
        ResultSet res;

        String output="";
        output += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        output += "<cars >";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            res = statement.executeQuery();
            while (res.next()) {
                Car swap_auto = new Car();
                swap_auto.setBrand(res.getString("brand"));
                swap_auto.setColor(res.getString("color"));
                swap_auto.setSpz(res.getString("spz"));
                swap_auto.setFuel(res.getString("fuel").charAt(0));
                swap_auto.setPrice(res.getInt("price"));

                auta.add(swap_auto);

                for (Car element: auta) {
                    output += "\t<car>\n";
                    output += "\t\t<brand>"+element.getBrand()+"</brand>\n";
                    output += "\t\t<color>"+element.getColor()+"</color>\n";
                    output += "\t\t<fuel>"+element.getFuel()+"</fuel>\n";
                    output += "\t\t<spz>"+element.getSpz()+"</spz>\n";
                    output += "\t\t<price>"+element.getPrice()+"</price>\n";
                    output += "\t</car>\n";
                }

            }
            output += "</cars >";
            File subor = new File("C:\\Users\\user\\IdeaProjects\\sova7\\src\\sk\\itsovy\\sova7\\XMLfile.xml");
            FileWriter writer = new FileWriter(subor);
            writer.write(output);
            writer.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
