package model;

import java.util.Comparator;

public final class FashionHouse implements Comparable<FashionHouse> {
    private String name;
    private Integer debut;
    private String worth;

    public FashionHouse(String name, Integer debut, String worth){
        this.name = name;
        this.debut = debut;
        this.worth = worth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDebut() {
        return debut;
    }

    public void setDebut(Integer debut) {
        this.debut = debut;
    }

    public String getWorth() {
        return worth;
    }

    public void setWorth(String worth) {
        this.worth = worth;
    }

    @Override
    public String toString(){
        return "\n\nName: " + name +
                "\nDebut: " + debut +
                "\nWorth: " + worth;
    }

    @Override
    public int compareTo(FashionHouse f) {
        return this.name.compareTo(f.name);
    }
}
