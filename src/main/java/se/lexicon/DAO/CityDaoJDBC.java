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
        try ( Connection connection = MySQLConnection.getConnection();
              Statement statement = connection.createStatement()
        ){
            List<City> cityList = new ArrayList<>();
            try(
                    ResultSet resultSet = statement.executeQuery("select * from city");
            ) {

                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int population = resultSet.getInt("Population");

                    cityList.add(new City(cityId, cityName, countryCode, district, population));
                }
                connection.close();
                statement.close();
            }
            return cityList;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public City add(City city) {
        try ( Connection connection = MySQLConnection.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement("insert into city (name, CountryCode, District, Population) values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                try (
                        ResultSet resultSet = preparedStatement.getGeneratedKeys();
                ) {
                    if (resultSet.next()) {
                        int cityId = resultSet.getInt(1);
                        city.setId(cityId);
                    }
                }
            }
            return city;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public City update(City city) {
        try ( Connection connection = MySQLConnection.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement("update city set name = ?, CountryCode = ?, District = ?, Population = ? where id = ?")
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                return city;
            }
            return null;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(City city) {
        try ( Connection connection = MySQLConnection.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement("delete from city where id = ?")
        ) {
            preparedStatement.setInt(1, city.getId());
            int result = preparedStatement.executeUpdate();
            return result;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
