package seedu.address.model.person;

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
    public static final Function<Person, ?> GET_CURRENTPLAN = Person::getCurrentPlan;
    public static final Function<Person, ?> GET_TAGS = Person::getTags;

    public static final Map<Prefix, Function<Person, ?>> PREFIX_FUNCTION_MAP = Map.of(
            PREFIX_CLIENTID, GET_CLIENTID,
            PREFIX_NAME, GET_NAME,
            PREFIX_PHONE, GET_PHONE,
            PREFIX_EMAIL, GET_EMAIL,
            PREFIX_ADDRESS, GET_ADDRESS,
            PREFIX_RISKAPPETITE, GET_RISKAPPETITE,
            PREFIX_DISPOSABLEINCOME, GET_DISPOSABLEINCOME,
            PREFIX_LASTMET, GET_LASTMET,
            PREFIX_CURRENTPLAN, GET_CURRENTPLAN,
            PREFIX_TAG, GET_TAGS
    );

    public static final Map<Prefix, String> PREFIX_NAME_MAP = Map.of(
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
}
