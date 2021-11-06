package seedu.edrecord.model.module;

import java.util.HashMap;
import java.util.Map;

import seedu.edrecord.model.group.Group;

public class ModuleGroupMap {

    public static final String MESSAGE_CONSTRAINTS = "Module and Group map must be separated by a colon.";

    private final HashMap<Module, Group> mapping = new HashMap<>();

    /**
     * Creates a new ModuleGroupMap
     */
    public ModuleGroupMap() {}

    public void add(Module mod, Group group) {
        mapping.put(mod, group);
    }

    public void addAll(ModuleGroupMap mods) {
        mapping.putAll(mods.getMapping());
    }

    /**
     * Removes the specified module from the map.
     * @param mod The module to be removed.
     * @return The group of the removed module.
     */
    public Group removeMod(Module mod) {
        for (Map.Entry<Module, Group> modGroupMapping : mapping.entrySet()) {
            Module m = modGroupMapping.getKey();
            if (m.isSameModule(mod)) {
                return mapping.remove(m);
            }
        }
        return null;
    }

    public boolean removeGroup(Module mod, Group grp) {
        return mapping.remove(mod, grp);
    }

    /**
     * @return A HashMap of Module and their Group mapping.
     */
    public HashMap<Module, Group> getMapping() {
        return mapping;
    }

    /**
     * @return boolean to show if the mapping contains the given module.
     */
    public boolean containsModule(Module mod) {
        for (Map.Entry<Module, Group> modGroupMapping : mapping.entrySet()) {
            if (modGroupMapping.getKey().isSameModule(mod)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean to show if the mapping contains the given group in given module.
     */
    public boolean containsGroup(Module mod, Group grp) {
        for (Map.Entry<Module, Group> modGroupMapping : mapping.entrySet()) {
            if (modGroupMapping.getKey().isSameModule(mod) && modGroupMapping.getValue().isSameGroup(grp)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ModuleGroupMap)) {
            return false;
        }

        ModuleGroupMap otherModuleGroupMap = (ModuleGroupMap) other;
        return this.mapping.equals(otherModuleGroupMap.getMapping());
    }

    @Override
    public int hashCode() {
        return mapping.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Module, Group> modGroupPair : mapping.entrySet()) {
            Module mod = modGroupPair.getKey();
            Group group = modGroupPair.getValue();
            sb.append(mod + ":" + group + " ");
        }
        return sb.toString().trim();
    }
}

