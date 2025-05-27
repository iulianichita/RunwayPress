package model;

import genercTypes.GenericDatabaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Magazine{
    private String name;
    private Integer noRubrics;
    private List<String> countries = new ArrayList<>();

    public Magazine(String name, Integer noRubrics, List<String> countries){
        this.name = name;
        this.noRubrics = noRubrics;
        this.countries = countries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoRubrics() {
        return noRubrics;
    }

    public void setNoRubrics(Integer noRubrics) {
        this.noRubrics = noRubrics;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "\n\nName: " + name +
                "\nNoRubrics: " + noRubrics +
                "\nCountries: " + countries;
    }


}
