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

    public void clearModules() {
        modules.clear();
        moduleCount = 0;
        Storage.writeModuleListToFile(this); // Reflect changes in storage
    }

    public void removeModule(int index) {
        if (index >= 0 && index < modules.size()) {
            modules.remove(index);
            moduleCount--;
            Storage.writeModuleListToFile(this); // Reflect changes in storage
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
