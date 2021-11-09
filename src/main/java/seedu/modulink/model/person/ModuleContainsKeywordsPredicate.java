package seedu.modulink.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.modulink.model.tag.Mod;
import seedu.modulink.model.tag.Status;

/**
 * Tests that a {@code Person}'s {@code Module} and optionally {@code Status} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Person> {

    private final Set<Mod> mods;

    /**
     * Constructor for the ModuleContainsKeywordsPredicate class.
     *
     * @param mods Set of modules.
     */
    public ModuleContainsKeywordsPredicate(Set<Mod> mods) {
        requireNonNull(mods);
        this.mods = mods;
    }

    @Override
    public boolean test(Person person) {
        boolean doesMatchMod = false;
        if (person.getIsMyProfile()) {
            return false;
        }
        for (Mod mod : person.getMods()) {
            for (Mod module : mods) {
                if (module.modName.equalsIgnoreCase(mod.modName)) {
                    if (!module.status.equals(Status.NONE)) {
                        if (mod.status.equals(module.status)) {
                            doesMatchMod = true;
                            break;
                        }
                    } else {
                        doesMatchMod = true;
                        break;
                    }
                }
            }
        }
        return doesMatchMod;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && mods.equals(((ModuleContainsKeywordsPredicate) other).mods)); // state check
    }
}
