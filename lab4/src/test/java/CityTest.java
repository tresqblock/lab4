import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CityTest {
    Company company = new Company.Builder()
            .addName("aaaa")
            .addWorkersCount(2)
            .addCapitalization(15000)
            .addLocation("bulgrstr")
            .build();
    Company company1 = new Company.Builder()
            .addName("aaaa")
            .addWorkersCount(2)
            .addCapitalization(15000)
            .addLocation("bulgrstr")
            .build();
    Company company2 = new Company.Builder()
            .addName("aaaa")
            .addWorkersCount(2)
            .addCapitalization(15000)
            .addLocation("bulgrstr")
            .build();
    @Test
    public void goodTest(){
        City city = new City.Builder()
                .addName("aaaa").addCompany(company).addCompany(company1).addCompany(company2).build();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badTest(){
        City city = new City.Builder().build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badTest1(){
        City city = new City.Builder().addName("aaaa").build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badTest2(){
        City city = new City.Builder().addName("aa").build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badTest3(){
        City city = new City.Builder().addCompany(company).build();
    }

}