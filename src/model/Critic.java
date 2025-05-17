package model;

import java.util.ArrayList;
import java.util.List;

public class Critic extends Person{
    private Integer ranking;

    public Critic(String name, String email, Integer experience, Integer ranking){
        super(name, email, experience);

        if (ranking < 1 | ranking > 5)
            throw new IllegalArgumentException("Ranking should be between 1 and 5.");
        else {
            this.ranking = ranking;
        }
    }

    public Integer getSalary(){
        return switch (this.ranking) {
            case 1 -> 750;
            case 2 -> 800;
            case 3 -> 1000;
            case 4 -> 1200;
            default -> 1650;
        };

    }

    @Override
    public String toString(){
        return "\n\nName:" + name +
                "\nEmail: " + email +
                "\nExperince: " + experince + " years" +
                "\nSalary: " + this.getSalary();
    }
}

