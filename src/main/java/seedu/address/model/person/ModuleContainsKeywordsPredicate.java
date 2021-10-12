package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Mod;
import seedu.address.model.tag.Status;

/**
 * Tests that a {@code Person}'s {@code Module} and optionally {@code Status} matches any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Person> {

    private final Set<Mod> mods;
    private final Status status;

    /**
     * Constructor for the ModuleContainsKeywordsPredicate class.
     *
     * @param mods Set of modules.
     * @param status Group status of the module.
     */
    public ModuleContainsKeywordsPredicate(Set<Mod> mods, Status status) {
        requireNonNull(mods);
        this.mods = mods;
        this.status = status;
    }

    @Override
    public boolean test(Person person) {
        boolean doesMatchMod = false;
        for (Mod mod : person.getMods()) {
            for (Mod module : mods) {
                if (module.modName.equalsIgnoreCase(mod.modName)) {
                    if (this.status != null) {
                        if (mod.status.equals(this.status)) {
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
