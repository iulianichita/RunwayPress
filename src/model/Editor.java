package model;

import java.util.ArrayList;
import java.util.List;

public class Editor extends Person{
    private Integer salary = 900;

    public Editor(String name, String email, Integer experience){
        super(name, email, experience);
    }

    public Integer getSalary(){
        if (experince > 5){
            return salary + experince / 5 * 400;
        } else {
            return salary;
        }
    }

    public void increaseSalary(int amount){
        this.salary += amount;
    }

    public void decreaseSalary(int amount){
        this.salary -= amount;
    }

    @Override
    public String toString(){
        return "\n\nName:" + name +
                "\nEmail: " + email +
                "\nExperince: " + experince + " years" +
                "\nSalary: " + this.getSalary();
    }
}

