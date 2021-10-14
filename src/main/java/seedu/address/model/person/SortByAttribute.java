package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.person.PrefixMapper.PREFIX_FUNCTION_MAP;
import static seedu.address.model.person.PrefixMapper.PREFIX_NAME_MAP;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Function;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

/**
 * Compare two {@code Person} based on {@code Prefix} and {@code SortDirection}
 * to determine their ordering
 */
public class SortByAttribute implements Comparator<Person> {


    private final SortDirection direction;
    private final Prefix prefix;

    /**
     * @param direction to sort by. Either asc or dsc.
     */
    public SortByAttribute(Prefix prefix, SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
        this.prefix = prefix;
    }

    public String getPrefixName() {
        return PREFIX_NAME_MAP.get(prefix);
    }

    @Override
    public int compare(Person a, Person b) {
        Function<Person, ?> getAttribute = PREFIX_FUNCTION_MAP.get(prefix);
        Function<Person, String> getString;

        // For tags, to sort and concatenate the resulting string to compare
        if (prefix.equals(PREFIX_TAG)) {
            @SuppressWarnings("unchecked")
            Function<Person, Set<Tag>> getTags = (Function<Person, Set<Tag>>) getAttribute;
            getString = getTags.andThen(this::concatenateTagString);
        } else {
            getString = getAttribute.andThen(Object::toString);
        }

        String sa = getString.apply(a);
        String sb = getString.apply(b);

        int result = compareString(sa, sb);
        if (!direction.isAscending()) {
            result = Math.negateExact(result);
        }

        return result;
    }

    /**
     * Compare two string lexicographically similar to {@code String::compareToIgnoreCase} except
     * that an empty string will be ordered last e.g. "" and "abc" will be ordered as "abc" ""
     *
     * @param a first string to be compared
     * @param b second string to be compared
     * @return an integer representing the relative order of a and b
     */
    private int compareString(String a, String b) {
        if (a.isEmpty()) {
            return 1;
        }

        if (b.isEmpty()) {
            return 0;
        }

        return a.compareToIgnoreCase(b);
    }

    private String concatenateTagString(Set<Tag> tags) {
        return tags.stream()
                .map(Tag::toString)
                .sorted(String::compareToIgnoreCase)
                .reduce("", String::concat);
    }
}
