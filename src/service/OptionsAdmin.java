package service;

public enum OptionsAdmin {
    // Magazines options
    showAllMagazines("Show all magazines in alphabetical order."),
    showAllFashionHouses("Show all fashion houses in alphabetical order."),
    showAllRubrics("Show all rubrics."),
    showRubricsHouse("Show rubrics for a specified fashion house."),
    showCritics("Show all critics"),
    showEditors("Show all editors"),
    editEditor("Edit the contract for a specified editor"),
    delEditors("Remove an editor from the team"),
    exitEditorMode("Exit admin priviledges."),
    EXIT("Exit.");


    private final String description;

    OptionsAdmin(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
