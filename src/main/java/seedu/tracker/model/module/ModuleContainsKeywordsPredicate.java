package seedu.tracker.model.module;

import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_MC;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.tracker.commons.util.StringUtil;
import seedu.tracker.logic.parser.Prefix;

/**
 * Tests that a {@code Module} contains any of the keywords given.
 */
public class ModuleContainsKeywordsPredicate implements Predicate<Module> {
    private final List<String> keywords;
    private final List<Prefix> prefixList = new ArrayList<>(
        Arrays.asList(PREFIX_CODE, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_MC, PREFIX_TAG, PREFIX_ACADEMIC_YEAR,
            PREFIX_SEMESTER));

    /**
     * Constructs a ModuleContainsKeywordsPredicate class.
     *
     * @param keywords for finding the respective modules
     */
    public ModuleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the module's specified components contain any of the keywords provided by the user.
     *
     * @param module that is being searched
     * @return true if the module contains the keyword(s)
     */
    @Override
    public boolean test(Module module) {
        List<String> prefixesPresent = findPrefixesPresent(prefixList);
        List<String> keywordsPresent = findKeywordsPresent(prefixesPresent);
        assert keywordsPresent.size() != 0;
        if (prefixesPresent.isEmpty()) {
            if (module.hasAcademicCalendar()) {
                return (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getCode().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTitle().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getDescription().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getMc().toString(), keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTags().toString(), keyword))
                    || keywordsPresent.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                    module.getAcademicCalendar().getAcademicYear().toString(), keyword))
                    || keywordsPresent.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                    module.getAcademicCalendar().getSemester().toString(), keyword)));
            } else {
                return (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getCode().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTitle().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getDescription().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getMc().toString(), keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTags().toString(), keyword)));
            }
        } else {
            return doesModuleContainKeywords(prefixesPresent, keywordsPresent, module);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ModuleContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Constructs a list of prefix(es) that the user has inputted.
     *
     * @param prefixes the list of prefixes allowed for FindCommand
     * @return a list of String(s) containing prefix(es) that the user has inputted
     */
    private List<String> findPrefixesPresent(List<Prefix> prefixes) {
        ArrayList<String> prefixesPresent = new ArrayList<>();
        for (Prefix prefix : prefixes) {
            if (keywords.contains(prefix.getPrefix())) {
                prefixesPresent.add(prefix.getPrefix());
            }
        }
        return prefixesPresent;
    }

    /**
     * Creates a new list of keywords that has the prefix(es) removed.
     *
     * @param prefixesPresent the list of prefix(es) the user has inputted
     * @return a list of Strings containing the keyword(s) to be searched for
     */
    private List<String> findKeywordsPresent(List<String> prefixesPresent) {
        ArrayList<String> keywordsPresent = new ArrayList<>();
        for (String keyword : keywords) {
            if (!prefixesPresent.contains(keyword)) {
                keywordsPresent.add(keyword);
            }
        }
        return keywordsPresent;
    }

    /**
     * Checks the specific component in the {@code Module} for the given keywords.
     *
     * @param prefixesPresent the list of prefixes to specify which component to check
     * @param keywordsPresent the list of keywords to check the module with
     * @param module to be checked
     * @return true if the keyword is found
     */
    private boolean doesModuleContainKeywords(List<String> prefixesPresent,
                                              List<String> keywordsPresent, Module module) {
        boolean moduleContainsKeywords = false;
        for (String prefix : prefixesPresent) {
            switch (prefix) {

            case "c/":
                moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getCode().value, keyword)));
                break;

            case "t/":
                moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTitle().value, keyword)));
                break;

            case "d/":
                moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getDescription().value, keyword)));
                break;

            case "m/":
                moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getMc().toString(), keyword)));
                break;

            case "tag/":
                moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTags().toString(), keyword)));
                break;

            case "y/":
                if (module.hasAcademicCalendar()) {
                    moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getAcademicCalendar()
                            .getAcademicYear().toString(), keyword)));
                }
                break;

            case "s/":
                if (module.hasAcademicCalendar()) {
                    moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getAcademicCalendar()
                            .getSemester().toString(), keyword)));
                }
                break;

            default:
                moduleContainsKeywords = moduleContainsKeywords || (keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getCode().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTitle().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getDescription().value, keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getMc().toString(), keyword))
                    || keywordsPresent.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(module.getTags().toString(), keyword))
                    || (module.hasAcademicCalendar())
                    && keywordsPresent.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                    module.getAcademicCalendar().getAcademicYear().toString(), keyword))
                    || keywordsPresent.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                    module.getAcademicCalendar().getSemester().toString(), keyword)));
            }
        }
        return moduleContainsKeywords;
    }


}
