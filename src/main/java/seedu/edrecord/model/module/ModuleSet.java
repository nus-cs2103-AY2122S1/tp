package seedu.edrecord.model.module;

import java.util.HashSet;
import java.util.Set;

import seedu.edrecord.model.group.Group;

public class ModuleSet {

    public static final String MESSAGE_CONSTRAINTS = "Module and Group map must be separated by a colon.";
    public static final String MESSAGE_GROUP_CONSTRAINTS = "Module's group must be surround by square brackets.";
    public static final String MESSAGE_INVALID_MODULE = "Module %1$s does not exist in this set!";
    public static final String MESSAGE_DUPLICATE_GROUP = "Module %1$s for %2$s already contains group %3$s!";

    private final Set<Module> modules = new HashSet<>();

    /**
     * Creates a new ModuleSet, which holds a student's modules and classes.
     */
    public ModuleSet() {}

    /**
     * Adds a module to the person's module set.
     * The module should not have any classes
     */
    public void add(Module mod, Group group) {
        if (!containsModule(mod)) {
            modules.add(mod);
        }

        if (!containsGroupInModule(mod, group)) {
            for (Module module : modules) {
                if (module.equals(mod)) {
                    module.addGroup(group);
                }
            }
        }
    }
    /**
     * Adds a module without adding to the module's group system.
     * Used for testing purposes only.
     */
    public void addToSet(Module mod) {
        modules.add(mod);
    }

    public void addAll(ModuleSet mods) {
        modules.addAll(mods.getModules());
    }

    /**
     * Removes the given mod based on correct code. The module does not need to have the same classes.
     * containsModule should be called together with this method.
     */
    public void removeMod(Module mod) {
        for (Module module : this.getModules()) {
            if (module.getCode().equals(mod.getCode())) {
                modules.remove(module);
                return;
            }
        }
    }

    /**
     * Removes if the module and group is within this module set.
     * containsModule and containsGroup should be called together with this method.
     */
    public void removeGroup(Module mod, Group grp) {
        for (Module module : this.getModules()) {
            if (module.getCode().equals(mod.getCode())) {
                if (module.hasGroup(grp)) {
                    module.deleteGroup(grp);
                    return;
                }
            }
        }
    }

    /**
     * @return A Set of Modules.
     */
    public Set<Module> getModules() {
        return modules;
    }

    /**
     * Checks if the given module is in the set based on correct code.
     * The module does not need to have the same classes.
     * @return boolean to show if the set contains the given module.
     */
    public boolean containsModule(Module mod) {
        for (Module module : this.getModules()) {
            if (module.getCode().equals(mod.getCode())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Checks if the module and group is within this module set.
     * containsModule should be called together with this method.
     * @return boolean to show if the set contains the given group in given module.
     */
    public boolean containsGroupInModule(Module mod, Group grp) {
        for (Module module : this.getModules()) {
            if (module.getCode().equals(mod.getCode())) {
                if (module.hasGroup(grp)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleSet)) {
            return false;
        }

        ModuleSet otherModuleSet = (ModuleSet) other;
        return this.getModules().equals(otherModuleSet.getModules());
    }

    @Override
    public int hashCode() {
        return modules.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Module module : modules) {
            sb.append(module.getCode())
                    .append(":")
                    .append(module.getGroupSystem())
                    .append(" ");
        }
        return sb.toString().trim();
    }

    public static String parseGroups(String groups) {
        return groups.substring(1, groups.length() - 1);
    }
}

