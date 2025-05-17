package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public final class MagazinesService {
    private static MagazinesService singleInstance = new MagazinesService();

    private Set<FashionHouse> fashionHouses;
    private Set<Magazine> magazines;
    private List<Rubric> rubrics;
    private Set<Critic> critics;
    private Set<Editor> editors;
    private Set<FashionDesigner> fashionDesigners;

    //{ critic : { [magazine : impression for magazine in magazines] } }
    private Map<Critic, Map<String, Integer>> criticReviews;
    private Map<Editor, List<String>> editorMagazine; // fiecare editor la ce reviste a lucrat

    List<Optional<Rubric>> archive;


    private MagazinesService() {
        fashionHouses = new TreeSet<>();
        magazines = new HashSet<>();
        rubrics = new ArrayList<>();
        critics = new HashSet<>();
        editors = new HashSet<>();
        fashionDesigners = new HashSet<>();
        criticReviews = new HashMap<>();
        editorMagazine = new HashMap<>();
        archive = new ArrayList<>();

        // MAGAZINES
        Magazine vogue = new Magazine("Vogue", 3, Arrays.asList("SUA", "Canada", "Mexico", "France"));
        Magazine elle = new Magazine("Elle", 2, Arrays.asList("SUA", "Argentina", "Italy", "France", "Germany"));
        Magazine gq = new Magazine("GQ", 4, Arrays.asList("SUA", "Canada"));
        Magazine cosmopolitan = new Magazine("COSMOPOLITAN", 2, Arrays.asList("Spain", "Portugal", "Italy", "France", "UK"));

        magazines.addAll(List.of(vogue, elle, gq, cosmopolitan));

        // FASHION HOUSES
        FashionHouse dior = new FashionHouse("Dior", 1947, "$10 billion");
        FashionHouse gucci = new FashionHouse("Gucci", 1921, "$23.8 billion");
        FashionHouse prada = new FashionHouse("Prada", 1913, "$8.3 billion");
        FashionHouse burberry = new FashionHouse("Burberry", 1856, "$7 billion");
        FashionHouse chanel = new FashionHouse("Chanel", 1910, "$15 billion");
        FashionHouse fendi = new FashionHouse("Fendi", 1925, "$3.6 billion");

        fashionHouses.addAll(List.of(dior, gucci, prada, burberry, chanel, fendi));

        // FASHION DESIGNERS
        FashionDesigner designer1 = new FashionDesigner("Anna Wintour", "anna@vogue.com", 30, List.of(chanel, prada));
        FashionDesigner designer2 = new FashionDesigner("Tom Ford", "tom@gq.com", 25, List.of(gucci, fendi, burberry, prada));
        FashionDesigner designer3 = new FashionDesigner("Donatella Versace", "donatella@elle.com", 28, List.of(dior, burberry));

        fashionDesigners.addAll(List.of(designer1, designer2, designer3));

        // EDITORS
        Editor editor1 = new Editor("Lucia Rossi", "lucia@elle.com", 10);
        Editor editor2 = new Editor("James Smith", "james@gq.com", 12);

        editors.addAll(List.of(editor1, editor2));

        // CRITICS
        Critic critic1 = new Critic("Marie Popescu", "marie.p@review.com", 8, 2);
        Critic critic2 = new Critic("Alex Johnson", "alex@style.com", 6, 4);

        critics.addAll(List.of(critic1, critic2));

        // RUBRICS
        rubrics.add(new Rubric("Haute Couture", "Dior reinvents the classic silhouette.", vogue, dior, designer1, "Women"));
        rubrics.add(new Rubric("Luxury Trends", "Gucci's new season shocker.", elle, gucci, designer3, "Men"));
        rubrics.add(new Rubric("Timeless Elegance", "Chanel's influence on minimalism.", vogue, chanel, designer1, "ages 18-24"));
        rubrics.add(new Rubric("Street Style", "Burberry’s trench coat gets a bold refresh.", gq, burberry, designer2, "General"));
        rubrics.add(new Rubric("Editorial Picks", "Prada and the power of storytelling.", elle, prada, designer3, "Women"));
        rubrics.add(new Rubric("New Classics", "Fendi’s futuristic take.", cosmopolitan, fendi, designer2, "Women"));
        rubrics.add(new Rubric("Modern Romance", "Vogue explores romanticism in high fashion.", vogue, chanel, designer1, "General"));
        rubrics.add(new Rubric("GQ Insider", "Gucci’s masculine tailoring leads the charts.", gq, gucci, designer2, "Men"));
        rubrics.add(new Rubric("Beauty Beyond Borders", "COSMOPOLITAN brings in global trends.", cosmopolitan, dior, designer1, "all ages"));
        rubrics.add(new Rubric("Bold Statements", "GQ pushes boundaries with futuristic fashion.", gq, prada, designer3, "ages 25-40"));
        rubrics.add(new Rubric("Next Gen Style", "Fendi sets the tone for Gen Z fashion.", gq, fendi, designer2, "Gen Z"));

        archive.add(Optional.of(new Rubric("Sustainable Fashion", "Chanel goes green.", vogue, chanel, designer1, "Eco-friendly")));
        archive.add(Optional.of(new Rubric("Retro Revival", "Prada revisits the 90s.", elle, prada, designer3, "Nostalgia")));


        // CRITIC REVIEWS
        Map<String, Integer> reviewsCritic1 = new HashMap<>();
        reviewsCritic1.put("Vogue", 9);
        reviewsCritic1.put("GQ", 10);
        reviewsCritic1.put("Elle", 10);
        criticReviews.put(critic1, reviewsCritic1);

        Map<String, Integer> reviewsCritic2 = new HashMap<>();
        reviewsCritic2.put("Elle", 9);
        reviewsCritic2.put("GQ", 9);
        reviewsCritic2.put("COSMOPOLITAN", 6);
        criticReviews.put(critic2, reviewsCritic2);


        // EDITOR PER FASHION MAGAZINE
        editorMagazine.put(editor1, Arrays.asList("Vogue", "Elle"));
        editorMagazine.put(editor2, Arrays.asList("COSMOPOLITAN", "GQ"));

    }

    public static MagazinesService getInstance() {
        return singleInstance;
    }

    public String getFashionHouses() {
        return this.fashionHouses.toString();
    }

    public String getMagazines() {
        List<Magazine> magazineSorted = new ArrayList<>(this.magazines);
        magazineSorted.sort(Comparator.comparing(Magazine::getName));
        return new LinkedHashSet<>(magazineSorted).toString();
    }

    public String getMagazinesName() {
        if (magazines.isEmpty()) return "No magazines available.";

        Iterator<Magazine> iterator = magazines.iterator();
        Magazine first = iterator.next();

        StringBuilder result = new StringBuilder(first.getName());

        while (iterator.hasNext()) {
            Magazine magazine = iterator.next();
            result.append(", ").append(magazine.getName());
        }

        return result.toString();
    }

    public String getFashionHousesName() {
        if (fashionHouses.isEmpty()) return "No fashion houses available.";

        Iterator<FashionHouse> iterator = fashionHouses.iterator();
        FashionHouse first = iterator.next();

        StringBuilder result = new StringBuilder(first.getName());

        while (iterator.hasNext()) {
            FashionHouse fashionHouse = iterator.next();
            result.append(", ").append(fashionHouse.getName());
        }

        return result.toString();
    }

    public boolean verifyMagazineName(String title) {
        Optional<Magazine> magazineFound = magazines.stream()
                .filter(r -> r.getName().equalsIgnoreCase(title))
                .findFirst();
        return magazineFound.isPresent();
    }

    public void addEditor(String name, String email, Integer experience) {
        editors.add(new Editor(name, email, experience));
    }

    public void getEditors() {
        System.out.println(editors.toString());
    }

    public void getEditorsName() {
        if (editors.stream().findFirst().isPresent()) {
            StringBuilder result = new StringBuilder(editors.stream().findFirst().get().getName());
            for (Editor editor : editors)
                if (!editor.equals(editors.stream().findFirst().get()))
                    result.append(", ").append(editor.getName());
            System.out.println(result);
        } else {
            System.out.println("None registered.");
        }
    }

    public void getEditor(String editorName){
        Editor currentEditor = editors.stream().filter(
                e -> Objects.equals(e.getName().toUpperCase(), editorName.toUpperCase())
        ).findFirst().orElse(null);

        if (currentEditor != null)
            System.out.println(currentEditor.toString());
        else System.out.println("Editor not recognized");
    }

    public void editEditor(String editorName, Integer amount, boolean increase){
        Editor currentEditor = editors.stream().filter(
                e -> Objects.equals(e.getName().toUpperCase(), editorName.toUpperCase())
        ).findFirst().orElseThrow(() -> {
            throw new RuntimeException("Editor not recognized");
        });

        if (increase)
            currentEditor.increaseSalary(amount);
        else
            currentEditor.decreaseSalary(amount);

    }

    public void delEditor(String editorName){

        Editor currentEditor = editors.stream().filter(
                e -> Objects.equals(e.getName().toUpperCase(), editorName.toUpperCase())
        ).findFirst().orElseThrow(() -> {
            throw new RuntimeException("Editor not recognized");
        });

        editors.remove(currentEditor);
        editorMagazine.remove(currentEditor);
    }

    public void getCritics() {
        System.out.println(critics.toString());
    }

    public String getAllRubrics() {
        return rubrics.toString();
    }

    public String getRubricsMagazine(String magazineName) {
        List<Rubric> filteredRubrics = DashboardUtils.filterOnRubricsMagazine(rubrics, magazineName);
        return filteredRubrics.toString();
    }

    public String getRubricsHouse(String fashionHouseName) {
        List<Rubric> filteredRubrics = DashboardUtils.filterOnRubricsHouse(rubrics, fashionHouseName);
        return filteredRubrics.toString();
    }

    public void addRubric(String editorName, String title, String article, String magazineName, String fashionHouseName, String fashionDesignerName, String audience) {
        Magazine magazine = magazines.stream().filter(
                        m -> Objects.equals(m.getName().toUpperCase(), magazineName.toUpperCase()))
                .findFirst().orElseThrow(() -> {
                    throw new RuntimeException("Operation failed! Magazine not found.");
                });

        FashionHouse fashionHouse = fashionHouses.stream().filter(
                        f -> Objects.equals(f.getName().toUpperCase(), fashionHouseName.toUpperCase()))
                .findFirst().orElseThrow(() -> {
                    throw new RuntimeException("Operation failed! Fashion House not found.");
                });

        FashionDesigner fashionDesigner = fashionDesigners.stream().filter(
                        f -> Objects.equals(f.getName().toUpperCase(), fashionDesignerName.toUpperCase()))
                .findFirst().orElseThrow(() -> {
                    throw new RuntimeException("Operation failed! Fashion Designer not found.");
                });

        Rubric rubric = new Rubric(title, article, magazine, fashionHouse, fashionDesigner, audience);
        rubrics.add(rubric);

        Editor currentEditor = editors.stream().filter(
                e -> Objects.equals(e.getName().toUpperCase(), editorName.toUpperCase())
        ).findFirst().orElse(null);

        if (editorMagazine.containsKey(currentEditor)) { // daca editorul curent se afla deja in map
            if (!editorMagazine.get(currentEditor).contains(magazineName))
                editorMagazine.get(currentEditor).add(magazineName);
        } else {
            editorMagazine.put(currentEditor, List.of(magazineName));
        }
    }

    public void addFashionDesigner(String name, String email, Integer experience, List<String> affiliates) {
        List<FashionHouse> affiliatesList = fashionHouses.stream()
                .filter(f -> affiliates.contains(f.getName()))
                .toList();
        FashionDesigner fashionDesigner = new FashionDesigner(name, email, experience, affiliatesList);
        fashionDesigners.add(fashionDesigner);
    }

    public void delRubric(String title) {
        Optional<Rubric> rubricToRemove = rubrics.stream()
                .filter(r -> r.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (rubricToRemove.isPresent()) {
            archive.add(rubricToRemove);
            rubrics.remove(rubricToRemove.get());
        }
    }

    public boolean verifyRubricTitle(String title) {
        Optional<Rubric> rubricToRemove = rubrics.stream()
                .filter(r -> r.getTitle().equalsIgnoreCase(title))
                .findFirst();
        return rubricToRemove.isPresent();
    }

    public String getArchive() {
        StringBuilder rubricsToShow = new StringBuilder();
        for (Optional<Rubric> rubric : archive)
            rubric.ifPresent(value -> rubricsToShow.append(value.toString()));

        return rubricsToShow.toString();
    }

    public void getTop3BestSellersMagazines() {
        System.out.println(DashboardUtils.getTop3BestSellers(magazines));
    }

    public void getTop3MoreRubricsMagazines() {
        System.out.println(DashboardUtils.getTop3MoreRubrics(magazines));
    }

    public String magazineDetails(String name) {
        Optional<Magazine> magazineFound = magazines.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst();

        StringBuilder aux = new StringBuilder();
        if (magazineFound.isPresent())
            aux.append(magazineFound.get().getName());
        String magazineName = String.valueOf(aux);

        StringBuilder result = new StringBuilder();
        result.append("\n\nDetails for magazine: ").append(magazineName).append("\n\n");

        // Fashion Designers
        Set<String> designers = rubrics.stream()
                .filter(r -> r.getPublisher().getName().equalsIgnoreCase(magazineName))
                .map(r -> r.getFashionDesigner().getName())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        result.append("Fashion Designers:\n");
        if (designers.isEmpty()) {
            result.append(" - None\n");
        } else {
            designers.forEach(d -> result.append(" - ").append(d).append("\n"));
        }

        // Editors
        Set<String> editors = new HashSet<>();
        for (Editor editor : editorMagazine.keySet()) {
            List<String> magazines = editorMagazine.get(editor);
            boolean hasMagazine = magazines.stream()
                    .anyMatch(m -> m.equalsIgnoreCase(magazineName));
            if (hasMagazine) {
                editors.add(editor.getName());
            }
        }

        result.append("\nEditors:\n");
        if (editors.isEmpty()) {
            result.append(" - None\n");
        } else {
            editors.forEach(d -> result.append(" - ").append(d).append("\n"));
        }

        // Critics
        Map<String, Integer> critics = new HashMap<>();
        for (Critic critic : criticReviews.keySet()) {
            Map<String, Integer> magazines = criticReviews.get(critic);
            boolean hasMagazine = magazines.keySet().stream()
                    .anyMatch(m -> m.equalsIgnoreCase(magazineName));
            if (hasMagazine) {
                critics.put(critic.getName(), magazines.get(magazineName));
            }
        }

        result.append("\nCritic reviews:\n");
        if (critics.isEmpty()) {
            result.append(" - None\n");
        } else {
            for (String criticName : critics.keySet()) {
                result.append(" - ").append(criticName).append("(").append(critics.get(criticName)).append(")\n");
            }
        }

        return result.toString();
    }


}

