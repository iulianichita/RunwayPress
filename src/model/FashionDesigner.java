package model;

import java.util.ArrayList;
import java.util.List;

public class FashionDesigner extends Person{
    private static final Integer salary = 1200;
    private List<FashionHouse> affiliates = new ArrayList<>();

    public FashionDesigner(String name, String email, Integer experience, List<FashionHouse> affiliates){
        super(name, email, experience);
        this.affiliates = affiliates;
    }

    //every fashion designer have a 15% add to the salary per 2 fashion houses that worked with him in the past
    public Integer getSalary(){
        Integer addSalary = 15 * this.salary / 100;
        if (affiliates.size() >= 2){
            return salary + ( addSalary * affiliates.size() / 2 );
        } else {
            return salary;
        }
    }

    public List<FashionHouse> getAffiliates() {
        return affiliates;
    }

    @Override
    public String toString(){
        return "\n\nName:" + name +
                "\nEmail: " + email +
                "\nExperince: " + experince + " years" +
                "\nSalary: " + this.getSalary() +
                "\nAffiliates: " + affiliates;
    }
}
