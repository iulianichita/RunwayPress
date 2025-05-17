package model;

public abstract class Person {
    protected String name;
    protected String email;
    protected Integer experince;

    public Person(String name, String email, Integer experince){
        this.name = name;
        this.email = email;
        this.experince = experince;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getExperince() {
        return experince;
    }

    protected abstract Integer getSalary();

    @Override
    public abstract String toString();
}
