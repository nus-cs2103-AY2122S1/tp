package seedu.address.model.modulelesson;

import java.util.function.Predicate;

import seedu.address.model.person.ModuleCode;

/**
 * Tests that a {@code ModuleLesson}'s {@code ModuleCode}s matches the keyword given.
 */
public class ModuleCodeContainsKeywordsPredicate implements Predicate<ModuleLesson> {

    private final String keyword;

    public ModuleCodeContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(ModuleLesson moduleLesson) {
        return moduleLesson.getModuleCodes().toArray(ModuleCode[]::new)[0]
                .getModuleCodeName().equalsIgnoreCase(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCodeContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((ModuleCodeContainsKeywordsPredicate) other).keyword)); // state check
    }
}
