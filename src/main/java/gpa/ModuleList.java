package gpa;

import java.util.ArrayList;
import storage.Storage; // Import statement assuming Storage class is accessible

public class ModuleList {
    private static int moduleCount;
    private ArrayList<Module> modules;

    public ModuleList() {
        this.modules = new ArrayList<>();
        moduleCount = 0;
    }

    public void addModule(String moduleName, int modularCredit, String expectedGrade) {
        Module newModule = new Module(moduleName, modularCredit, expectedGrade);
        modules.add(newModule);
        moduleCount++;
        Storage.writeModuleListToFile(this); // Save every time a module is added
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public static int getModuleCount() {
        return moduleCount;
    }

    /**
     * Clears all modules from the module list and resets the module count.
     *
     * This method removes all entries from the modules list, sets the module count to zero, and updates the storage
     * to ensure that the changes are persisted. It is typically used to reset the state of the module list, for example,
     * at the start of a new term or when clearing user data.
     *
     * After clearing the modules, it calls {@link Storage#writeModuleListToFile} to save the now empty module list
     * to the designated storage, ensuring that the system's persistent state matches the in-memory state.
     */
    public void clearModules() {
        modules.clear();
        moduleCount = 0;
        Storage.writeModuleListToFile(this); // Reflect changes in storage
    }

    /**
     * Removes a module from the list at the specified index and updates the storage.
     *
     * This method checks if the provided index is within the valid range of the module list. If the index is valid,
     * it removes the module at that index, decrements the module count, and updates the persistent storage via
     * {@link Storage#writeModuleListToFile}. If the index is invalid, it prints an error message to the console.
     *
     * @param index the index of the module to be removed, where the index must be non-negative and less
     *              than the size of the module list.
     */
    public void removeModule(int index) {
        if (index >= 0 && index < modules.size()) {
            modules.remove(index);
            moduleCount--;
            Storage.writeModuleListToFile(this); // Reflect changes in storage
        } else {
            System.out.println("Invalid module index.");
        }
    }

    /**
     * Calculates and returns the total number of modular credits for all modules currently in the list.
     *
     * This method iterates over each module in the list, summing up the modular credits associated with each module.
     * It's used to obtain a total count of credits that could be used for calculating GPAs, scheduling, or other
     * academic planning purposes.
     *
     * @return the total number of modular credits for all modules in the list.
     */
    public int getTotalModularCredits() {
        int totalCredits = 0;
        for (Module module : modules) {
            totalCredits += module.getModularCredit();
        }
        return totalCredits;
    }
}
