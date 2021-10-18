package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTPLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISPOSABLEINCOME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LASTMET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEXTMEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKAPPETITE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Map;
import java.util.function.Function;

import seedu.address.logic.parser.Prefix;

/**
 * Contains {@code Map} mapping {@code Prefix} to relevant properties
 */
public class PrefixMapper {
    public static final Function<Person, ?> GET_CLIENTID = Person::getClientId;
    public static final Function<Person, ?> GET_NAME = Person::getName;
    public static final Function<Person, ?> GET_PHONE = Person::getPhone;
    public static final Function<Person, ?> GET_EMAIL = Person::getEmail;
    public static final Function<Person, ?> GET_ADDRESS = Person::getAddress;
    public static final Function<Person, ?> GET_RISKAPPETITE = Person::getRiskAppetite;
    public static final Function<Person, ?> GET_DISPOSABLEINCOME = Person::getDisposableIncome;
    public static final Function<Person, ?> GET_LASTMET = Person::getLastMet;
    public static final Function<Person, ?> GET_NEXTMEETING = Person::getNextMeeting;
    public static final Function<Person, ?> GET_CURRENTPLAN = Person::getCurrentPlan;
    public static final Function<Person, ?> GET_TAGS = Person::getTags;

    public static final Map<Prefix, Function<Person, ?>> PREFIX_FUNCTION_MAP = Map.ofEntries(
            Map.entry(PREFIX_CLIENTID, GET_CLIENTID),
            Map.entry(PREFIX_NAME, GET_NAME),
            Map.entry(PREFIX_PHONE, GET_PHONE),
            Map.entry(PREFIX_EMAIL, GET_EMAIL),
            Map.entry(PREFIX_ADDRESS, GET_ADDRESS),
            Map.entry(PREFIX_RISKAPPETITE, GET_RISKAPPETITE),
            Map.entry(PREFIX_DISPOSABLEINCOME, GET_DISPOSABLEINCOME),
            Map.entry(PREFIX_LASTMET, GET_LASTMET),
            Map.entry(PREFIX_NEXTMEETING, GET_NEXTMEETING),
            Map.entry(PREFIX_CURRENTPLAN, GET_CURRENTPLAN),
            Map.entry(PREFIX_TAG, GET_TAGS)
    );

    public static final Map<Prefix, String> PREFIX_NAME_MAP = Map.ofEntries(
            Map.entry(PREFIX_CLIENTID, "Client Id"),
            Map.entry(PREFIX_NAME, "Name"),
            Map.entry(PREFIX_PHONE, "Phone"),
            Map.entry(PREFIX_EMAIL, "Email"),
            Map.entry(PREFIX_ADDRESS, "Address"),
            Map.entry(PREFIX_RISKAPPETITE, "Risk Appetite"),
            Map.entry(PREFIX_DISPOSABLEINCOME, "Disposable Income"),
            Map.entry(PREFIX_LASTMET, "Last Met"),
            Map.entry(PREFIX_NEXTMEETING, "Next Meeting"),
            Map.entry(PREFIX_CURRENTPLAN, "Current Plan"),
            Map.entry(PREFIX_TAG, "Tags")
    );
}
