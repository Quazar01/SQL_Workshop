package se.lexicon;

import se.lexicon.DAO.CityDaoJDBC;
import se.lexicon.Model.City;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Test CityDaoJDBC
        CityDaoJDBC cityDaoJDBC = new CityDaoJDBC();
        System.out.println(cityDaoJDBC.findById(1));
        System.out.println(cityDaoJDBC.findByCode("SWE"));
        System.out.println(cityDaoJDBC.findByName("Stockholm"));
//        System.out.println(cityDaoJDBC.findAll());
        System.out.println(cityDaoJDBC.add(new City(4080, "Test", "SWE", "Test", 1000)));
        System.out.println(cityDaoJDBC.update(new City(4080, "Test", "SWE", "Test", 10000)));
//        System.out.println(cityDaoJDBC.delete(new City(4080, "Test", "SWE", "Test", 1000)));
        System.out.println(cityDaoJDBC.findById(4080));
        System.out.println(cityDaoJDBC.delete(new City(4080, "Test", "SWE", "Test", 10000)));
        System.out.println(cityDaoJDBC.findById(4080));


    }
}
