package seedu.address.commons.mapper;

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
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.CurrentPlan;
import seedu.address.model.person.DisposableIncome;
import seedu.address.model.person.Email;
import seedu.address.model.person.LastMet;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RiskAppetite;
import seedu.address.model.tag.Tag;

/**
 * Contains {@code Map} mapping {@code Prefix} to relevant properties
 */
public class PrefixMapper {
    // Person getter methods
    public static final Function<Person, ClientId> GET_CLIENTID = Person::getClientId;
    public static final Function<Person, Name> GET_NAME = Person::getName;
    public static final Function<Person, Phone> GET_PHONE = Person::getPhone;
    public static final Function<Person, Email> GET_EMAIL = Person::getEmail;
    public static final Function<Person, Address> GET_ADDRESS = Person::getAddress;
    public static final Function<Person, RiskAppetite> GET_RISKAPPETITE = Person::getRiskAppetite;
    public static final Function<Person, DisposableIncome> GET_DISPOSABLEINCOME = Person::getDisposableIncome;
    public static final Function<Person, LastMet> GET_LASTMET = Person::getLastMet;
    public static final Function<Person, CurrentPlan> GET_CURRENTPLAN = Person::getCurrentPlan;
    public static final Function<Person, Set<Tag>> GET_TAGS = Person::getTags;

    // EditPersonDescriptor setter methods
    public static final BiConsumer<EditPersonDescriptor, Name> EDIT_SET_NAME = EditPersonDescriptor::setName;
    public static final BiConsumer<EditPersonDescriptor, Phone> EDIT_SET_PHONE = EditPersonDescriptor::setPhone;
    public static final BiConsumer<EditPersonDescriptor, Email> EDIT_SET_EMAIL = EditPersonDescriptor::setEmail;
    public static final BiConsumer<EditPersonDescriptor, Address> EDIT_SET_ADDRESS = EditPersonDescriptor::setAddress;
    public static final BiConsumer<EditPersonDescriptor, RiskAppetite> EDIT_SET_RISKAPPETITE =
            EditPersonDescriptor::setRiskAppetite;
    public static final BiConsumer<EditPersonDescriptor, DisposableIncome> EDIT_SET_DISPOSABLEINCOME =
            EditPersonDescriptor::setDisposableIncome;
    public static final BiConsumer<EditPersonDescriptor, LastMet> EDIT_SET_LASTMET =
            EditPersonDescriptor::setLastMet;
    public static final BiConsumer<EditPersonDescriptor, CurrentPlan> EDIT_SET_CURRENTPLAN =
            EditPersonDescriptor::setCurrentPlan;
    public static final BiConsumer<EditPersonDescriptor, Set<Tag>> EDIT_SET_TAGS = EditPersonDescriptor::setTags;

    // EditPersonDescriptor getter methods
    public static final Function<EditPersonDescriptor, Optional<Name>> EDIT_GET_NAME =
            EditPersonDescriptor::getName;
    public static final Function<EditPersonDescriptor, Optional<Phone>> EDIT_GET_PHONE =
            EditPersonDescriptor::getPhone;
    public static final Function<EditPersonDescriptor, Optional<Email>> EDIT_GET_EMAIL =
            EditPersonDescriptor::getEmail;
    public static final Function<EditPersonDescriptor, Optional<Address>> EDIT_GET_ADDRESS =
            EditPersonDescriptor::getAddress;
    public static final Function<EditPersonDescriptor, Optional<RiskAppetite>> EDIT_GET_RISKAPPETITE =
            EditPersonDescriptor::getRiskAppetite;
    public static final Function<EditPersonDescriptor, Optional<DisposableIncome>> EDIT_GET_DISPOSABLEINCOME =
            EditPersonDescriptor::getDisposableIncome;
    public static final Function<EditPersonDescriptor, Optional<LastMet>> EDIT_GET_LASTMET =
            EditPersonDescriptor::getLastMet;
    public static final Function<EditPersonDescriptor, Optional<CurrentPlan>> EDIT_GET_CURRENTPLAN =
            EditPersonDescriptor::getCurrentPlan;
    public static final Function<EditPersonDescriptor, Optional<Set<Tag>>> EDIT_GET_TAGS =
            EditPersonDescriptor::getTags;

    // ParserUtil parser method
    public static final Function<String, ClientId> PARSE_CLIENTID =
            throwableFunctionWrapper(ParserUtil::parseClientId);
    public static final Function<String, Name> PARSE_NAME =
            throwableFunctionWrapper(ParserUtil::parseName);
    public static final Function<String, Phone> PARSE_PHONE =
            throwableFunctionWrapper(ParserUtil::parsePhone);
    public static final Function<String, Email> PARSE_EMAIL =
            throwableFunctionWrapper(ParserUtil::parseEmail);
    public static final Function<String, Address> PARSE_ADDRESS =
            throwableFunctionWrapper(ParserUtil::parseAddress);
    public static final Function<String, RiskAppetite> PARSE_RISKAPPETITE =
            throwableFunctionWrapper(ParserUtil::parseRiskAppetite);
    public static final Function<String, DisposableIncome> PARSE_DISPOSABLEINCOME =
            throwableFunctionWrapper(ParserUtil::parseDisposableIncome);
    public static final Function<String, LastMet> PARSE_LASTMET =
            throwableFunctionWrapper(ParserUtil::parseLastMet);
    public static final Function<String, CurrentPlan> PARSE_CURRENTPLAN =
            throwableFunctionWrapper(ParserUtil::parseCurrentPlan);

    public static final Map<Prefix, Function<Person, ?>> PREFIX_GET_MAP = Map.of(
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

    public static final Map<Prefix, BiConsumer<EditPersonDescriptor, ?>> PREFIX_EDIT_SET_MAP = Map.of(
            PREFIX_NAME, EDIT_SET_NAME,
            PREFIX_PHONE, EDIT_SET_PHONE,
            PREFIX_EMAIL, EDIT_SET_EMAIL,
            PREFIX_ADDRESS, EDIT_SET_ADDRESS,
            PREFIX_RISKAPPETITE, EDIT_SET_RISKAPPETITE,
            PREFIX_DISPOSABLEINCOME, EDIT_SET_DISPOSABLEINCOME,
            PREFIX_LASTMET, EDIT_SET_LASTMET,
            PREFIX_CURRENTPLAN, EDIT_SET_CURRENTPLAN,
            PREFIX_TAG, EDIT_SET_TAGS
    );

    public static final Map<Prefix, Function<EditPersonDescriptor, ? extends Optional<?>>> PREFIX_EDIT_GET_MAP = Map.of(
            PREFIX_NAME, EDIT_GET_NAME,
            PREFIX_PHONE, EDIT_GET_PHONE,
            PREFIX_EMAIL, EDIT_GET_EMAIL,
            PREFIX_ADDRESS, EDIT_GET_ADDRESS,
            PREFIX_RISKAPPETITE, EDIT_GET_RISKAPPETITE,
            PREFIX_DISPOSABLEINCOME, EDIT_GET_DISPOSABLEINCOME,
            PREFIX_LASTMET, EDIT_GET_LASTMET,
            PREFIX_CURRENTPLAN, EDIT_GET_CURRENTPLAN,
            PREFIX_TAG, EDIT_GET_TAGS
    );

    public static final Map<Prefix, Function<String, ?>> PREFIX_PARSE_MAP = Map.of(
            PREFIX_CLIENTID, PARSE_CLIENTID,
            PREFIX_NAME, PARSE_NAME,
            PREFIX_PHONE, PARSE_PHONE,
            PREFIX_EMAIL, PARSE_EMAIL,
            PREFIX_ADDRESS, PARSE_ADDRESS,
            PREFIX_RISKAPPETITE, PARSE_RISKAPPETITE,
            PREFIX_DISPOSABLEINCOME, PARSE_DISPOSABLEINCOME,
            PREFIX_LASTMET, PARSE_LASTMET,
            PREFIX_CURRENTPLAN, PARSE_CURRENTPLAN
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

    private static <T, R> Function<T, R> throwableFunctionWrapper(
            ThrowableFunction<T, R, ? extends Exception> throwableFunction) {
        return t -> {
            try {
                return throwableFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        };
    }
}
