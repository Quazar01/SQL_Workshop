package se.lexicon.DAO;

import com.mysql.cj.protocol.Resultset;
import se.lexicon.Model.City;
import se.lexicon.db.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {

    @Override
    public City findById(int id) {
        // Create a connection to the database
        // Create a statement
        // Execute a query to find a city by id
        // Extract the result from the ResultSet
        // Create a new City object and return it
        try ( Connection connection = MySQLConnection.getConnection();
              PreparedStatement preparedStatement =
                      connection.prepareStatement("select * from city where id = ?")
        ){
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ) {
                if (resultSet.next()) {
                    // Extract the result from the ResultSet and create a new City object.
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int population = resultSet.getInt("Population");

                    return new City(cityId, cityName, countryCode, district, population);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        try (Connection connection = MySQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from city where CountryCode = ?")
        ){
            List<City> cityList = new ArrayList<>();
            preparedStatement.setString(1, code);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {

                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int population = resultSet.getInt("Population");

                    cityList.add(new City(cityId, cityName, countryCode, district, population));
                }
            }
            return cityList;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<City> findByName(String name) {
        try ( Connection connection = MySQLConnection.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement("select * from city where name = ?")
        ){
            List<City> cityList = new ArrayList<>();
            preparedStatement.setString(1, name);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {

                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int population = resultSet.getInt("Population");

                    cityList.add(new City(cityId, cityName, countryCode, district, population));
                }
            }
            return cityList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
