package gpa;

import java.util.ArrayList;

public class ModuleList {
    private ArrayList<Module> modules;

    public ModuleList() {
        this.modules = new ArrayList<>();
    }

    // Adds a new module to the list with the module name included.
    public void addModule(String moduleName, int modularCredit, String expectedGrade) {
        Module newModule = new Module(moduleName, modularCredit, expectedGrade);
        modules.add(newModule);
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void clearModules() {
        modules.clear();
    }

    public void removeModule(int index) {
        if (index >= 0 && index < modules.size()) {
            modules.remove(index);
        } else {
            System.out.println("Invalid module index.");
        }
    }

    public int getTotalModularCredits() {
        int totalCredits = 0;
        for (Module module : modules) {
            totalCredits += module.getModularCredit();
        }
        return totalCredits;
    }
}
