package service;

public enum OptionsMenu {
    showAllMagazines("Show all magazines in alphabetical order."),
    showAllFashionHouses("Show all fashion houses in alphabetical order."),
    showAllRubrics("Show all rubrics."),
    showRubricsMagazine("Show rubrics for a specified magazine."),
    showRubricsHouse("Show rubrics for a specified fashion house."),
    addRubric("Add a new rubric."),
    delRubric("Delete a rubric."),
    showOldRubrics("Show rubrics archive."),
    top3BestSellers("Top3 best sellers magazines."),
    top3MoreRubrics("Top3 magazines with most rubrics"),
    showDetails("Show details of a specified magazine\n    (critic reviews, all editors, all fashion designer that work on it)"),
    EXIT("Exit.");

    private final String description;

    OptionsMenu(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
