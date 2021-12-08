import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class Company {
    @Size(min = 1, message = "count of workers must be more 0")
    private int workersCount;
    @Size(min = 10000, message = "count of workers must be more 9999")
    private long capitalization;
    @NotNull
    @Size(min = 3)
    @Pattern(regexp = "[A-Z][a-z][0-9]+", message = "Name contain not valid letters")
    private String name;
    @NotNull
    @Size(min = 5)
    @Pattern(regexp = "[A-Z][a-z][0-9]+", message = "Location contain not valid letters")
    private String location;

    public static class Builder{
        private Company company;

        public Builder(){
            company = new Company();
        }
        /**
         * add workers count. count>0
         * @param count
         * @return
         */
        public Builder addWorkersCount(int count){
           company.workersCount = count;
            return this;
        }

        /**
         * add capitalization. price>0
         * @param price
         * @return
         */
        public Builder addCapitalization(long price){
            company.capitalization=price;
            return this;
        }

        /**
         * add name company
         * @param name
         * @return
         */
        public Builder addName(String name){
            company.name = name;
            return this;
        }

        /**
         * add location company
         * @param location
         * @return
         */
        public Builder addLocation(String location){
            company.location = location;
            return this;
        }

        /**
         * get Company
         * @return
         */
        public Company build() throws IllegalArgumentException{
            validate(company);
            return company;
        }

    }
    private static void validate(Company company) throws IllegalArgumentException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Company>> check =  validator.validate(company);
        StringBuilder stringBuilder = new StringBuilder();

        for (ConstraintViolation<Company> c : check) {
            stringBuilder.append("Error").append(c.getInvalidValue()).append(" because ").append(c.getMessage()).append("\n");
        }
        if(stringBuilder.length()>0){
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    public int getWorkersCount() {
        return workersCount;
    }

    public void setWorkersCount(int workersCount) {
        this.workersCount = workersCount;
    }

    public long getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(long capitalization) {
        this.capitalization = capitalization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return workersCount == company.workersCount && capitalization == company.capitalization && Objects.equals(name, company.name) && Objects.equals(location, company.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workersCount, capitalization, name, location);
    }

    @Override
    public String toString() {
        return "Company{" +
                "workersCount=" + workersCount +
                ", capitalization=" + capitalization +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
