package seedu.address.model.person.comparators;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

public class SortByAttribute implements Comparator<Person> {
    private static final Function<Person, ?> getClientId = Person::getClientId;
    private static final Function<Person, ?> getName = Person::getName;
    private static final Function<Person, ?> getPhone = Person::getPhone;
    private static final Function<Person, ?> getEmail = Person::getEmail;
    private static final Function<Person, ?> getAddress = Person::getAddress;
    private static final Function<Person, ?> getRiskAppetite = Person::getRiskAppetite;
    private static final Function<Person, ?> getDisposableIncome = Person::getDisposableIncome;
    private static final Function<Person, ?> getLastMet = Person::getLastMet;
    private static final Function<Person, ?> getCurrentPlan = Person::getCurrentPlan;
    private static final Function<Person, ?> getTags = Person::getTags;

    private static final Map<Prefix, Function<Person, ?>> personFunctionMapping = Map.of(
            PREFIX_CLIENTID, getClientId,
            PREFIX_NAME, getName,
            PREFIX_PHONE, getPhone,
            PREFIX_EMAIL, getEmail,
            PREFIX_ADDRESS, getAddress,
            PREFIX_RISKAPPETITE, getRiskAppetite,
            PREFIX_DISPOSABLEINCOME, getDisposableIncome,
            PREFIX_LASTMET, getLastMet,
            PREFIX_CURRENTPLAN, getCurrentPlan,
            PREFIX_TAG, getTags
    );

    private static final Map<Prefix, String> prefixNameMapping = Map.of(
            PREFIX_CLIENTID, "Client Id",
            PREFIX_NAME, "Name",
            PREFIX_PHONE, "Phone",
            PREFIX_EMAIL, "Email",
            PREFIX_ADDRESS, "Address",
            PREFIX_RISKAPPETITE, "Risk Appetite",
            PREFIX_DISPOSABLEINCOME, "Disposable Income",
            PREFIX_LASTMET, "Last Met",
            PREFIX_CURRENTPLAN, "Current Plan",
            PREFIX_TAG, "Tags"
    );

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
        return prefixNameMapping.get(prefix);
    }

    @Override
    public int compare(Person a, Person b) {
        Function<Person, String> getAttribute = personFunctionMapping.get(prefix).andThen(Object::toString);
        String sa = getAttribute.apply(a);
        String sb = getAttribute.apply(b);

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
}
