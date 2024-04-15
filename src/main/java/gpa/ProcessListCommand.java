package gpa;

import storage.Storage;
import static storage.Storage.readGPAFile;
import java.util.ArrayList;
import cantvasui.UI;


import static seedu.duke.CantVasMain.ui;

public class ProcessListCommand {

    /**
     * Processes user commands related to managing a list of academic modules and calculating GPA.
     * This method acts as an interface for adding, deleting, and viewing modules, as well as calculating
     * an updated GPA based on the current list of modules. It supports various commands and handles
     * user interactions in a loop until the user decides to exit.
     *
     * Supported commands:
     * - 'add': Prompts the user to enter the number of modules and their details to be added to the list.
     * - 'del': Allows the user to delete specific modules from the list or clear all modules.
     * - 'viewlist': Displays all the modules currently in the list.
     * - 'gpa': Calculates and displays the updated GPA based on the current modules and user's input of current GPA
     * and total credits.
     * - 'exit': Exits the module list manager and returns to the main menu.
     * - 'help': Displays help information related to the available commands.
     *
     * Each command guides the user through the required inputs and provides feedback on the success or
     * failure of the operation. The method ensures that all inputs are validated and handles errors appropriately,
     * including incorrect formats and out-of-range values.
     *
     * @return true if the user exits the module list manager; false if an operation does not lead to an exit.
     */
    public boolean processModuleListCommand() {
        System.out.println("Welcome to the advanced gpa calculator page!");
        UI.gpaListHelpMessage();

        command_loop:
        while (true) {
            System.out.println("Please enter a command (or type 'exit' to return):");
            String command = ui.getUserCommand().trim().toLowerCase();
            switch (command) {
            case "add":
                int numOfModules = 0;
                boolean validNumOfModules = false;
                while (!validNumOfModules) {
                    System.out.println("Enter the number of mods you want to add (format: NUMBER_OF_MODS):");
                    String modsInput = ui.getUserCommand();
                    if ("exit".equalsIgnoreCase(modsInput.trim())) {
                        System.out.println("Exiting Module Entry. Going back to command menu....");
                        continue command_loop; // Use return to exit the method immediately
                    }

                    try {
                        numOfModules = Integer.parseInt(modsInput.trim());
                        if (numOfModules < 0) {
                            System.out.println("Invalid input: Number of modules cannot be negative.");
                        } else if (numOfModules >=999) {
                            System.out.println("You have input too many modules!");
                        } else {
                            validNumOfModules = true; // Input is valid, proceed to the next step
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input detected!!!! \n" +
                                "Please enter a valid integer for the number of modules!");
                    }
                }
                assert numOfModules <= 999 && numOfModules >= 0 :"" +
                        "Numbers of Modules input should be between 0 and 999";

                int[] moduleCredits = new int[numOfModules];
                String[] moduleGrades = new String[numOfModules];
                ModuleList moduleList = readGPAFile();


                for (int i = 0; i < numOfModules; i++) {
                    // For each module, request details in the new format
                    while (true) {
                        System.out.println("Enter details for module " + (i + 1) +
                                " in the format: n/MODULE_NAME mc/MODULAR_CREDIT gr/EXPECTED_GRADE. \n" +
                                "Example: n/CS1010 mc/4 gr/A+");
                        String moduleInput = ui.getUserCommand().trim();

                        //enabling the following 4 lines allows user to exit from the gpa calculator anytime
                        if ("exit".equalsIgnoreCase(moduleInput)) {
                            System.out.println("Exiting module entry!");
                            continue command_loop;
                        }

                        // Splitting the input by spaces to get each part of the input
                        String[] parts = moduleInput.split("\\s+");
                        if (parts.length != 3) {
                            System.out.println("Invalid input: Please ensure your input matches the format.");
                            continue;
                        }

                        String moduleName = "";
                        int modularCredit = -1;
                        String expectedGrade = "";

                        try {
                            // Extracting module name, credits, and grade from the input
                            for (String part : parts) {
                                if (part.startsWith("n/")) {
                                    moduleName = part.substring(2);
                                } else if (part.startsWith("mc/")) {
                                    modularCredit = Integer.parseInt(part.substring(3));
                                } else if (part.startsWith("gr/")) {
                                    expectedGrade = part.substring(3).toUpperCase();
                                }
                            }

                            // Check if all parts were correctly parsed
                            if (moduleName.isEmpty() || modularCredit == -1 || expectedGrade.isEmpty()) {
                                throw new IllegalArgumentException("Missing or incorrect details." +
                                        " Please ensure all parts are correctly entered.");
                            }

                            // Add the new module to the module list
                            moduleList.addModule(moduleName, modularCredit, expectedGrade);
                            break; // Valid input, proceed to the next module
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input: Please ensure the modular credit is an integer.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid input: " + e.getMessage());
                        }
                    }
                }
                break;

            case "del":
                ModuleList modules = readGPAFile();
                if (modules.getModules().isEmpty()) {
                    System.out.println("The module list is currently empty.");
                } else {
                    ArrayList<Module> currentModules = modules.getModules();
                    UI.printLineBreak();
                    System.out.println("Here is the list of modules in the list.");
                    for (int i = 0; i < currentModules.size(); i++) {
                        Module module = currentModules.get(i);
                        System.out.printf("%d. Module Name: %s, Credits: %d, Grade: %s\n",
                                i + 1, module.getModuleName(),
                                module.getModularCredit(), module.getExpectedGrade());
                    }
                    System.out.println("Please input the index of the module that you want to delete" +
                            "\n " +
                            "or input 'clear' to delete everything \n" +
                            "or 'exit' to cancel.");

                    String delCommand = ui.getUserCommand();

                    if ("exit".equalsIgnoreCase(delCommand)) {
                        System.out.println("Exiting delete function." +
                                "Going back to the command menu....");
                        continue command_loop;
                    } else if ("clear".equalsIgnoreCase(delCommand)) {
                        modules.clearModules(); // Assuming ModuleList has a clearModules() method to clear the list
                        System.out.println("All modules have been deleted.");
                    } else {
                        try {
                            int index = Integer.parseInt(delCommand) - 1; // Convert to zero-based index
                            if (index >= 0 && index < currentModules.size()) {
                                Module removedModule = currentModules.remove(index);
                                System.out.printf("Module '%s' deleted successfully.\n", removedModule.getModuleName());
                            } else {
                                System.out.println("Invalid index. Please try again.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number, 'clear', or 'exit'.");
                        }
                    }

                    Storage.writeModuleListToFile(modules); // Persist the updated list to storage
                }
                break;

            case "viewlist":
                ModuleList modulesA = readGPAFile();
                if (modulesA.getModules().isEmpty()) {
                    System.out.println("The module list is currently empty.");
                } else {
                    System.out.println("Current modules in the list:");
                    ArrayList<Module> currentModulesA = modulesA.getModules();
                    for (int i = 0; i < currentModulesA.size(); i++) {
                        Module module = currentModulesA.get(i);
                        System.out.printf("%d. Module Name: %s, Credits: %d, Grade: %s\n",
                                i + 1, module.getModuleName(), module.getModularCredit(), module.getExpectedGrade());
                    }
                }
                break;
            case "exit":
                System.out.println("Roger! Brining you back to the main window now. \n" +
                        "You can type Help again to view commands available!");
                return true;

            case "help":
                UI.gpaListHelpMessage();
                break;

            case "gpa":
                UI.printLineBreak();
                System.out.println("\nMake sure you have add modules first " +
                        "before you do the calculation.\n" +
                        "Alternatively, you can use the step by step gpa calculator via 'gpa' " +
                        "command at the main window\n ");
                double currentGPA;
                int totalCredits;
                // Prompt for current GPA
                UI.printLineBreak();
                System.out.println("Please enter your current GPA:");
                String currentGPAInput = ui.getUserCommand().trim();
                try {
                    currentGPA = Double.parseDouble(currentGPAInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid GPA input. Please enter a valid number.");
                    break;
                }

                // Prompt for total credits
                System.out.println("Please enter the total number of credits taken so far:");
                String totalCreditsInput = ui.getUserCommand().trim();
                try {
                    totalCredits = Integer.parseInt(totalCreditsInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid credits input. Please enter a valid number.");
                    break;
                }

                ModuleList modulesB = readGPAFile(); // Read the current modules
                double updatedGPA = GPAMain.calculateUpdatedGPA(currentGPA, totalCredits, modulesB);
                System.out.printf("Your GPA is: %.2f\n", updatedGPA);
                break;


            default:
                System.out.println("Unknown function: '" + command + "'. \n" +
                        "Please try again or type 'exit' to return to the main menu. \n" +
                        "or type 'help' to view available commands");
                //command = ui.getUserCommand(); // Re-prompt the user for command
                //if ("exit".equalsIgnoreCase(command.trim())) {
                //    System.out.println("Exiting the GPA calculator. Thank you for using it!");
                //    continue command_loop;
                //}
                break;
            }

        }
    }
}
