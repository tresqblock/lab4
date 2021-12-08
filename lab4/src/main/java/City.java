import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

public class City implements Comparable<City> {
    @Size(min = 1)
    private Collection<Company> companies = new ArrayList<>();
    @NotNull
    @Pattern(regexp = "[A-Z][a-z][0-9]+", message = "Name contain not valid letters")
    private String name;

    @Override
    public int compareTo(City o) {
        return name.compareTo(o.getName());
    }

    public static class Builder{
        private City city = new City();

        /**
         * add 1 company
         * @param company
         * @return
         */
        public Builder addCompany(Company company){
            city.companies.add(company);
            return this;
        }

        /**
         * add array company
         * @param companies
         * @return
         */
        public Builder addCompanies(Collection<Company> companies){
            city.companies = companies;
            return this;
        }

        /**
         * add name city
         * @param name
         * @return
         */
        public Builder addName(String name){
            city.name = name;
            return this;
        }

        /**
         * get city class
         * @return
         */
        public City build(){
            validate(city);
            return city;
        }
    }
    private static void validate(City city) throws IllegalArgumentException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<City>> check =  validator.validate(city);
        StringBuilder stringBuilder = new StringBuilder();

        for (var c : check) {
            stringBuilder.append("Error").append(c.getInvalidValue()).append(" because ").append(c.getMessage()).append("\n");
        }
        if(stringBuilder.length()>0){
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public Collection<Company> filterByCompanyName(ArrayList<Company> companies, String name){
        ArrayList<Company> res = new ArrayList<>();

        for (var c: companies) {
            if(c.getName()==name){
                res.add(c);
            }
        }
        return res;
    }

    public Collection<Company> selectAllWhereWorkersCountMoreValue(ArrayList<Company> companies,int value){
        ArrayList<Company> res = new ArrayList<>();

        for (var c: companies) {
            if(c.getWorkersCount()>=value){
                res.add(c);
            }
        }
        return res;
    }

    public Collection<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Collection<Company> companies) {
        this.companies = companies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(companies, city.companies) && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies, name);
    }

    @Override
    public String toString() {
        return "City{" +
                "companies=" + companies +
                ", name='" + name + '\'' +
                '}';
    }
}
