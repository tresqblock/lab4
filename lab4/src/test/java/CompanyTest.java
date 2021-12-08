import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompanyTest {
    @Test
    public void goodTest(){
        Company company = new Company.Builder()
                .addName("aaaa")
                .addWorkersCount(2)
                .addCapitalization(15000)
                .addLocation("bulgrstr")
                .build();

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badcreationTest(){
        Company company  = new Company.Builder().addName("aa").build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badcreationTest1(){
        Company company  = new Company.Builder().addName("aaaa").build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badcreationTest2(){
        Company company  = new Company.Builder().addName("aaaa").addCapitalization(1).build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badcreationTest3(){
        Company company  = new Company.Builder().addName("aaaa").addCapitalization(10000).addLocation("a").build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badcreationTest4(){
        Company company  = new Company.Builder().addName("aaaa").addCapitalization(10000).addLocation("aaaa").build();
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void badcreationTest5(){
        Company company  = new Company.Builder().addCapitalization(10000).addWorkersCount(1).build();
    }

}