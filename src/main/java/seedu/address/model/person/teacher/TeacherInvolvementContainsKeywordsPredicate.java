package seedu.address.model.person.teacher;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Teacher}'s {@code Name} matches any of the keywords given.
 */
public class TeacherInvolvementContainsKeywordsPredicate implements Predicate<Teacher> {
    private final List<String> keywords;

    public TeacherInvolvementContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Teacher teacher) {
        boolean inInvolvement = false;
        boolean inTags = false;
        boolean isTagsPresent = false;
        boolean isInvolvementPresent = true;

        for (int i = 0; i < keywords.size(); i++) {

            if (keywords.get(i).startsWith("t/")) {
                inTags = inTagsChecker(i, teacher);
                isTagsPresent = true;
                if (i == 0) {
                    isInvolvementPresent = false;
                }
                break;
            }
        }
        inInvolvement = inInvolvementChecker(teacher);

        if (isTagsPresent && !isInvolvementPresent) {
            return inTags;
        } else if (isTagsPresent && isInvolvementPresent) {
            return (inInvolvement && inTags);
        }

        return (inInvolvement);
    }

    private boolean inTagsChecker(int t, Teacher teacher) {
        String allTags = "";
        for (Tag s : teacher.getTags()) {
            String tagNameLowerCase = s.tagName.toLowerCase();
            allTags = allTags + tagNameLowerCase;
        }

        for (int i = t; i < keywords.size(); i++) {
            String keywordCurrent = keywords.get(i).toLowerCase();
            if (keywordCurrent.startsWith("t/") && i == t) {
                keywordCurrent = keywordCurrent.substring(2);
            }

            if (allTags.contains(keywordCurrent)) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean inInvolvementChecker(Teacher teacher) {
        boolean toReturn = true;
        String teacherInvolvement = teacher.getInvolvement().value.toLowerCase();
        for (int i = 0; i < keywords.size(); i++) {
            String keywordCurrent = keywords.get(i).toLowerCase();
            if (keywordCurrent.startsWith("t/")) {
                return toReturn;
            }

            if (!teacherInvolvement.contains(keywordCurrent)) {
                toReturn = false;
            }
        }

        if (keywords.size() < 1) {
            return false;
        }
        return toReturn;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeacherInvolvementContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TeacherInvolvementContainsKeywordsPredicate) other).keywords)); // state check
    }

}
