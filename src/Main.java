/*
 *   In this app, you are an editor or an admin, you can add new rubrics or
 *   see the stored data (Magazines, Fashion Houses, known critics, fashion designers, other editors)
*/

import service.MagazinesService;
import service.OptionsAdmin;
import service.OptionsMenu;

import java.io.Console;
import java.util.*;
import java.util.stream.Collectors;

public class Main{

    static String capitalLetter(String text) {
        StringBuilder result = new StringBuilder();
        if (text != null && !text.isEmpty()) {
            char firstChar = Character.toUpperCase(text.charAt(0));
            String rest = text.substring(1).toLowerCase();

            result.append(firstChar).append(rest);
        }

        if (!result.isEmpty())
            return result.toString();
        else return "";

    }

    public static String capitalizeEachWord(String text) {
        if (text == null || text.isEmpty()) return text;

        return Arrays.stream(text.split("\\s+"))
                .map(Main::capitalLetter)
                .collect(Collectors.joining(" "));
    }


    public static void main(String[] args) throws InterruptedException{

        System.out.println("\nWelcome to your studio!\n");

        Scanner scanner = new Scanner(System.in);
        MagazinesService database = MagazinesService.getInstance();

        while(true) {
            Integer value = 0;
            try {
                System.out.println("Please enter 1 for **editor**, 2 for **admin** privileges:");
                value = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException error) {
                System.out.println("You have to enter **1** or **2**.");
            }


            if (value == 1) {

                System.out.println("Please enter your name: ");
                String name = scanner.nextLine();
                String email;

                while (true) {
                    try {
                        System.out.println("\nPlease enter your email address: ");
                        email = scanner.nextLine();
                        if (!email.contains("@") | !email.contains(".com") | email.contains(" ")) {
                            throw new IllegalArgumentException("Email invalid " + email + ". It should contain '@' and '.com' and no spaces.");
                        }
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e);
                    }
                }
                System.out.println("\nEnter your experience in this position(no years): ");
                Integer experience = scanner.nextInt();
                database.addEditor(capitalizeEachWord(name), email, experience);

                System.out.println("\nYour data uploaded successfully!");
                System.out.print("Loading");
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(500); // 0.5s
                    System.out.print(".");
                }
                System.out.println();
                System.out.println("\n\nWelcome, " + capitalizeEachWord(name) + "!");

                while (true) {

                    Integer i = 1;
                    System.out.println();
                    for (OptionsMenu option : OptionsMenu.values())
                        System.out.println(i++ + ". " + option.getDescription());

                    Integer option;
                    while (true) {
                        try {
                            System.out.println("\nEnter value: ");
                            option = scanner.nextInt();
                            if (option < 1 | option > OptionsMenu.values().length)
                                throw new IllegalArgumentException("Invalid option");
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("You must enter a **number** between 1 and " + OptionsMenu.values().length + ".");
                            scanner.nextLine();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid option. Your option should be a number between 1 and " + OptionsMenu.values().length + ".");
                        }
                    }

                    switch (option) {
                        case 1 -> System.out.println(database.getMagazines());
                        case 2 -> System.out.println(database.getFashionHouses());
                        case 3 -> System.out.println(database.getAllRubrics());
                        case 4 -> {
                            System.out.println("These are the magazines: ");
                            System.out.println(database.getMagazinesName());

                            if (!database.getMagazinesName().equals("No magazines available.")) {
                                while (true) {
                                    System.out.println("\nEnter the name of the magazine: ");
                                    Scanner scanner4 = new Scanner(System.in);
                                    String magazineName = scanner4.nextLine();
                                    if (database.getMagazinesName().toUpperCase().contains(magazineName.toUpperCase())) {
                                        System.out.println(database.getRubricsMagazine(magazineName));
                                        break;
                                    } else {
                                        System.out.println("Sorry. Magazine name not recognized.");
                                    }
                                }

                            }
                        }
                        case 5 -> {
                            System.out.println("These are the fashion houses: ");
                            System.out.println(database.getFashionHousesName());

                            if (!database.getFashionHousesName().equals("No fashion houses available.")) {
                                while (true) {
                                    System.out.println("\nEnter the name of the fashion house: ");
                                    Scanner scanner5 = new Scanner(System.in);
                                    String houseName = scanner5.nextLine();
                                    if (database.getFashionHousesName().toUpperCase().contains(houseName.toUpperCase())) {
                                        System.out.println(database.getRubricsHouse(houseName));
                                        break;
                                    } else {
                                        System.out.println("Sorry. House name not recognized.");
                                    }
                                }

                            }

                        }
                        case 6 -> {
                            Scanner scanner6 = new Scanner(System.in);

                            String title = null, article = null, magazineName = null, fashionHouseName = null, fashionDesignerName = null, audience = null;
                            while (true) {

                                try {
                                    System.out.println("Enter the title: ");
                                    title = scanner6.nextLine();
                                    System.out.println("Enter the magazine: ");
                                    System.out.println("(Available magazines: " + database.getMagazinesName() + ")");
                                    magazineName = scanner6.nextLine();
                                    System.out.println("Enter the Fashion House that is reffering to: ");
                                    System.out.println("(Available Fashion Houses: " + database.getFashionHousesName() + ")");
                                    fashionHouseName = scanner6.nextLine();
                                    System.out.println("Enter the name of the Fashion Designer that you worked with: ");
                                    fashionDesignerName = scanner6.nextLine();
                                    System.out.println("Enter the text of the article: ");
                                    article = scanner6.nextLine();
                                    System.out.println("Enter the target audience(ex: ages 18-24): ");
                                    audience = scanner6.nextLine();

                                    database.addRubric(name, title, article, magazineName, fashionHouseName, fashionDesignerName, audience);
                                    break;

                                } catch (RuntimeException error) {
                                    if (error.toString().contains("Fashion Designer not found.")) {
                                        System.out.println("\nSorry, the designer name is not stored in our db.\nPlease enter the name of the Fashion Designer that you worked with: ");
                                        String nameDes = scanner6.nextLine();
                                        fashionDesignerName = capitalizeEachWord(nameDes);
                                        String emailDes;
                                        while (true) {
                                            try {
                                                System.out.println("\nPlease enter email address: ");
                                                emailDes = scanner6.nextLine();
                                                if (!emailDes.contains("@") | !emailDes.contains(".com") | emailDes.contains(" ")) {
                                                    throw new IllegalArgumentException("Email invalid '" + emailDes + "'. It should contain '@' and '.com' and no spaces.");
                                                }
                                                break;
                                            } catch (IllegalArgumentException error2) {
                                                System.out.println(error2);
                                            }
                                        }
                                        System.out.println("\nEnter his no years of experience: ");
                                        Integer experienceDes = scanner6.nextInt();
                                        scanner6.nextLine();
                                        System.out.println("\nEnter the fashion houses that he worked with(comma separated): ");
                                        System.out.println("(Available Fashion Houses: " + database.getFashionHousesName() + ")");
                                        String input = scanner6.nextLine();
                                        String[] fashionHouses = input.split("\\s*,\\s*");
                                        List<String> housesList = Arrays.stream(fashionHouses)
                                                .map(f -> capitalLetter(f))
                                                .collect(Collectors.toList());

                                        database.addFashionDesigner(fashionDesignerName, emailDes, experienceDes, housesList);
                                        break;

                                    } else if (error.toString().contains("Magazine not found.")) {
                                        System.out.println("Magazine not found!");
                                        System.out.println("Enter again the magazine name: ");
                                        System.out.println("(Available magazines: " + database.getMagazinesName() + ")");
                                        magazineName = scanner6.nextLine();
                                        break;

                                    } else if (error.toString().contains("Fashion House not found.")) {
                                        System.out.println("Fashion House not found!");
                                        System.out.println("Enter again the Fashion House name: ");
                                        System.out.println("(Available Fashion Houses: " + database.getFashionHousesName() + ")");
                                        fashionHouseName = scanner6.nextLine();
                                        break;

                                    } else {
                                        System.out.println(error);
                                        break;

                                    }
                                }

                            }

                            database.addRubric(name, capitalLetter(title), capitalLetter(article), magazineName, fashionHouseName, fashionDesignerName, audience);
                            System.out.println("\nRubric added successfully!");
                        }
                        case 7 -> {
                            while (true) {
                                Scanner scanner7 = new Scanner(System.in);
                                System.out.println("\nEnter the rubric title: ");
                                String title = scanner7.nextLine();

                                if (database.verifyRubricTitle(title)) {
                                    database.delRubric(title);
                                    System.out.println("Rubric deleted successfully!");
                                    break;
                                } else {
                                    System.out.println("No rubric found with the title: " + title + ". Please try again.");
                                }
                            }
                        }
                        case 8 -> {
                            System.out.println("\n-- Our archive: ");
                            System.out.println(database.getArchive());
                            System.out.println("\n---");
                        }
                        case 9 -> {
                            System.out.println("\nTOP 3 BEST SELLERS");
                            database.getTop3BestSellersMagazines();
                            System.out.println();
                        }
                        case 10 -> {
                            System.out.println("\nTOP 3 MOST RUBRICS");
                            database.getTop3MoreRubricsMagazines();
                            System.out.println();
                        }
                        case 11 -> {
                            System.out.println("These are the magazines: ");
                            System.out.println(database.getMagazinesName());

                            if (!database.getMagazinesName().toString().equals("No magazines available.")) {
                                while (true) {
                                    try {
                                        System.out.println("\nEnter the magazine name: ");
                                        Scanner scanner11 = new Scanner(System.in);
                                        String magazineName = scanner11.nextLine();
                                        if (database.verifyMagazineName(magazineName)) {
                                            System.out.println(database.magazineDetails(magazineName));
                                            break;
                                        } else {
                                            throw new RuntimeException("Magazine not found. Please enter a valid name.");
                                        }

                                    } catch (RuntimeException e) {
                                        System.out.println(e);
                                    }

                                }
                            }

                        }
                        case 12 -> System.exit(0);
                    }

                }
            } else {
                // case when the person is the admin
                Integer count = 0;
                Console console = System.console();
                if (console == null) {
                    System.out.println("No console available. Please run this in a terminal (not an IDE).");
                    System.exit(1);
                }
                while (true) {
                    if (count == 3){
                        System.out.println("\nToo many failed attempts. Unauthorized access!");
                        System.exit(0);
                    }

                    char[] passwordChars = console.readPassword("Please enter the password: ");
                    String pass = new String(passwordChars);
                    if (!pass.equals("admin")) {
                        count++;
                        System.out.println("Incorrect password.");
                    } else {
                        System.out.println("Welcome, admin! You are now in privilege mode.");
                        break;
                    }
                }

                if (count != 3){
                    while (true){

                        Integer i = 1;
                        System.out.println();
                        for (OptionsAdmin option : OptionsAdmin.values())
                            System.out.println(i++ + ". " + option.getDescription());

                        Integer input;
                        while (true) {
                            try {
                                System.out.println("\nEnter value: ");
                                input = scanner.nextInt();
                                if (input < 1 | input >= i)
                                    throw new IllegalArgumentException("Invalid option");
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("You must enter a **number** between 1 and " + i + ".");
                                scanner.nextLine();
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid option. Your option should be a number between 1 and " + OptionsAdmin.values().length + ".");
                            }
                        }

                        switch (input) {
                            case 1 -> System.out.println(database.getMagazines());
                            case 2 -> System.out.println(database.getFashionHouses());
                            case 3 -> System.out.println(database.getAllRubrics());
                            case 4 -> {
                                System.out.println("These are the fashion houses: ");
                                System.out.println(database.getFashionHousesName());

                                if (!database.getFashionHousesName().equals("No fashion houses available.")) {
                                    while (true) {
                                        System.out.println("\nEnter the name of the fashion house: ");
                                        Scanner scanner5 = new Scanner(System.in);
                                        String houseName = scanner5.nextLine();
                                        if (database.getFashionHousesName().toUpperCase().contains(houseName.toUpperCase())) {
                                            System.out.println(database.getRubricsHouse(houseName));
                                            break;
                                        } else {
                                            System.out.println("Sorry. House name not recognized.");
                                        }
                                    }

                                }

                            }
                            case 5 -> database.getCritics();
                            case 6 ->  database.getEditors();
                            case 7 -> {
                                System.out.println("These are team's editors: ");
                                database.getEditorsName();
                                System.out.println();

                                while (true) {
                                    try {
                                        System.out.println("Enter the editor name that you want to increase his salary: ");
                                        Scanner scanner8 = new Scanner(System.in);
                                        String editorName = scanner8.nextLine();
                                        database.getEditor(editorName);
                                        System.out.println("Would you like to increase(1) or decrease(2) his salary? Enter your option:");
                                        Boolean increase = false;
                                        while (true) {
                                            Integer opt = scanner8.nextInt();
                                            if (opt == 1) {
                                                increase = true; break;
                                            }
                                            else if (opt == 2){
                                                increase = false; break; }
                                            else
                                                System.out.println("Please enter a valid option (1 or 2)");
                                        }
                                        System.out.println("Enter the amount: ");
                                        Integer amount = scanner8.nextInt();
                                        database.editEditor(editorName, amount, increase);
                                        System.out.println("Success!");
                                        break;
                                    } catch (RuntimeException e) {
                                        System.out.println("Please enter a valid editor name.");
                                    }
                                }
                            }
                            case 8 -> {
                                System.out.println("These are team's editors: ");
                                database.getEditorsName();
                                System.out.println();

                                while (true) {
                                    try {
                                        System.out.println("Enter the editor name that you want to fire: ");
                                        Scanner scanner8 = new Scanner(System.in);
                                        String editorName = scanner8.nextLine();
                                        database.delEditor(editorName);
                                        System.out.println("Success! This member is no longer in our team.");
                                        break;
                                    } catch (RuntimeException e) {
                                        System.out.println("Please enter a valid editor name.");
                                    }
                                }

                            }
                            case 9 -> System.exit(0);
                        }

                    }
                }

            }

        }

    }
}