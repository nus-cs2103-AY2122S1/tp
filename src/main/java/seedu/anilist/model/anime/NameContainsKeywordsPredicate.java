package seedu.anilist.model.anime;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.anilist.commons.util.StringUtil;
import seedu.anilist.logic.parser.ParserUtil;
import seedu.anilist.logic.parser.exceptions.ParseException;

/**
 * Tests that an {@code Anime}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Anime> {
    private final List<Name> names;

    /**
     * Constructs a {@code NameContainsKeywordsPredicate}.
     *
     * @param keywords Keywords to search within the anime's name.
     * @throws ParseException if the keywords given are not valid.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) throws ParseException {
        names = new ArrayList<>();
        for (String keyword: keywords) {
            names.add(ParserUtil.parseName(keyword));
        }
    }

    @Override
    public boolean test(Anime anime) {
        return names.stream()
                .anyMatch(name -> StringUtil.containsPhraseIgnoreCase(anime.getName().fullName, name.fullName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && names.equals(((NameContainsKeywordsPredicate) other).names)); // state check
    }

}
