package service;

import model.Magazine;
import model.Rubric;

import java.util.*;

public final class DashboardUtils {
    private DashboardUtils(){};

    public static String getTop3BestSellers(Set<Magazine> magazines){
        List<Magazine> magazinesList = new ArrayList<>(magazines);
        magazinesList.sort(Comparator.comparingInt((Magazine m) -> m.getCountries().size()).reversed());

        return "\n" + magazinesList.getFirst() + " " +
                magazinesList.get(1) + " " +
                magazinesList.get(2);
    }

    public static String getTop3MoreRubrics(Set<Magazine> magazines){
        List<Magazine> magazinesList = new ArrayList<>(magazines);
        magazinesList.sort(Comparator.comparingInt(Magazine::getNoRubrics).reversed());

        return "\n" + magazinesList.getFirst() + " " +
                magazinesList.get(1) + " " +
                magazinesList.get(2);
    }

    public static List<Rubric> filterOnRubricsMagazine(List<Rubric> rubrics, String magazineName){
        List<Rubric> filteredRubrics = new ArrayList<>();

        for(Rubric rubric : rubrics){
            if (Objects.equals(rubric.getPublisher().getName().toUpperCase(), magazineName.toUpperCase())){
                filteredRubrics.add(rubric);
            }
        }

        return filteredRubrics;
    }


    public static List<Rubric> filterOnRubricsHouse(List<Rubric> rubrics, String fashionHouseName){
        List<Rubric> filteredRubrics = new ArrayList<>();

        for(Rubric rubric : rubrics){
            if (Objects.equals(rubric.getReference().getName().toUpperCase(), fashionHouseName.toUpperCase())){
                filteredRubrics.add(rubric);
            }
        }

        return filteredRubrics;
    }

}
