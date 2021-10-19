package seedu.address.commons.mapper;

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
import seedu.address.model.person.NextMeeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RiskAppetite;
import seedu.address.model.tag.Tag;

/**
 * Contains {@code Map} mapping {@code Prefix} to relevant properties
 */
public class PrefixMapper {
    // Person getter methods
    private static final Function<Person, ClientId> GET_CLIENTID = Person::getClientId;
    private static final Function<Person, Name> GET_NAME = Person::getName;
    private static final Function<Person, Phone> GET_PHONE = Person::getPhone;
    private static final Function<Person, Email> GET_EMAIL = Person::getEmail;
    private static final Function<Person, Address> GET_ADDRESS = Person::getAddress;
    private static final Function<Person, RiskAppetite> GET_RISKAPPETITE = Person::getRiskAppetite;
    private static final Function<Person, DisposableIncome> GET_DISPOSABLEINCOME = Person::getDisposableIncome;
    private static final Function<Person, LastMet> GET_LASTMET = Person::getLastMet;
    private static final Function<Person, NextMeeting> GET_NEXTMEETING = Person::getNextMeeting;
    private static final Function<Person, CurrentPlan> GET_CURRENTPLAN = Person::getCurrentPlan;
    private static final Function<Person, Set<Tag>> GET_TAGS = Person::getTags;

    // EditPersonDescriptor setter methods
    private static final BiConsumer<EditPersonDescriptor, Name> EDIT_SET_NAME = EditPersonDescriptor::setName;
    private static final BiConsumer<EditPersonDescriptor, Phone> EDIT_SET_PHONE = EditPersonDescriptor::setPhone;
    private static final BiConsumer<EditPersonDescriptor, Email> EDIT_SET_EMAIL = EditPersonDescriptor::setEmail;
    private static final BiConsumer<EditPersonDescriptor, Address> EDIT_SET_ADDRESS = EditPersonDescriptor::setAddress;
    private static final BiConsumer<EditPersonDescriptor, RiskAppetite> EDIT_SET_RISKAPPETITE =
            EditPersonDescriptor::setRiskAppetite;
    private static final BiConsumer<EditPersonDescriptor, DisposableIncome> EDIT_SET_DISPOSABLEINCOME =
            EditPersonDescriptor::setDisposableIncome;
    private static final BiConsumer<EditPersonDescriptor, LastMet> EDIT_SET_LASTMET =
            EditPersonDescriptor::setLastMet;
    private static final BiConsumer<EditPersonDescriptor, NextMeeting> EDIT_SET_NEXTMEETING =
        EditPersonDescriptor::setNextMeeting;
    private static final BiConsumer<EditPersonDescriptor, CurrentPlan> EDIT_SET_CURRENTPLAN =
            EditPersonDescriptor::setCurrentPlan;
    private static final BiConsumer<EditPersonDescriptor, Set<Tag>> EDIT_SET_TAGS = EditPersonDescriptor::setTags;

    // EditPersonDescriptor getter methods
    private static final Function<EditPersonDescriptor, Optional<Name>> EDIT_GET_NAME =
            EditPersonDescriptor::getName;
    private static final Function<EditPersonDescriptor, Optional<Phone>> EDIT_GET_PHONE =
            EditPersonDescriptor::getPhone;
    private static final Function<EditPersonDescriptor, Optional<Email>> EDIT_GET_EMAIL =
            EditPersonDescriptor::getEmail;
    private static final Function<EditPersonDescriptor, Optional<Address>> EDIT_GET_ADDRESS =
            EditPersonDescriptor::getAddress;
    private static final Function<EditPersonDescriptor, Optional<RiskAppetite>> EDIT_GET_RISKAPPETITE =
            EditPersonDescriptor::getRiskAppetite;
    private static final Function<EditPersonDescriptor, Optional<DisposableIncome>> EDIT_GET_DISPOSABLEINCOME =
            EditPersonDescriptor::getDisposableIncome;
    private static final Function<EditPersonDescriptor, Optional<LastMet>> EDIT_GET_LASTMET =
            EditPersonDescriptor::getLastMet;
    private static final Function<EditPersonDescriptor, Optional<NextMeeting>> EDIT_GET_NEXTMEETING =
        EditPersonDescriptor::getNextMeeting;
    private static final Function<EditPersonDescriptor, Optional<CurrentPlan>> EDIT_GET_CURRENTPLAN =
            EditPersonDescriptor::getCurrentPlan;
    private static final Function<EditPersonDescriptor, Optional<Set<Tag>>> EDIT_GET_TAGS =
            EditPersonDescriptor::getTags;

    // ParserUtil parser method
    private static final Function<String, ClientId> PARSE_CLIENTID =
            throwableFunctionWrapper(ParserUtil::parseClientId);
    private static final Function<String, Name> PARSE_NAME =
            throwableFunctionWrapper(ParserUtil::parseName);
    private static final Function<String, Phone> PARSE_PHONE =
            throwableFunctionWrapper(ParserUtil::parsePhone);
    private static final Function<String, Email> PARSE_EMAIL =
            throwableFunctionWrapper(ParserUtil::parseEmail);
    private static final Function<String, Address> PARSE_ADDRESS =
            throwableFunctionWrapper(ParserUtil::parseAddress);
    private static final Function<String, RiskAppetite> PARSE_RISKAPPETITE =
            throwableFunctionWrapper(ParserUtil::parseRiskAppetite);
    private static final Function<String, DisposableIncome> PARSE_DISPOSABLEINCOME =
            throwableFunctionWrapper(ParserUtil::parseDisposableIncome);
    private static final Function<String, LastMet> PARSE_LASTMET =
            throwableFunctionWrapper(ParserUtil::parseLastMet);
    private static final Function<String, NextMeeting> PARSE_NEXTMEETING =
        throwableFunctionWrapper(ParserUtil::parseNextMeeting);
    private static final Function<String, CurrentPlan> PARSE_CURRENTPLAN =
            throwableFunctionWrapper(ParserUtil::parseCurrentPlan);

    // PrefixMapperElement wrapping the different function together
    private static final PrefixMapperElement<ClientId> PME_CLIENTID = new PrefixMapperElement<>(GET_CLIENTID,
            null, null, PARSE_CLIENTID, "Client Id");
    private static final PrefixMapperElement<Name> PME_NAME = new PrefixMapperElement<>(GET_NAME,
            EDIT_SET_NAME, EDIT_GET_NAME, PARSE_NAME, "Name");
    private static final PrefixMapperElement<Phone> PME_PHONE = new PrefixMapperElement<>(GET_PHONE,
            EDIT_SET_PHONE, EDIT_GET_PHONE, PARSE_PHONE, "Phone");
    private static final PrefixMapperElement<Email> PME_EMAIL = new PrefixMapperElement<>(GET_EMAIL,
            EDIT_SET_EMAIL, EDIT_GET_EMAIL, PARSE_EMAIL, "Email");
    private static final PrefixMapperElement<Address> PME_ADDRESS = new PrefixMapperElement<>(GET_ADDRESS,
            EDIT_SET_ADDRESS, EDIT_GET_ADDRESS, PARSE_ADDRESS, "Address");
    private static final PrefixMapperElement<RiskAppetite> PME_RISKAPPETITE = new PrefixMapperElement<>(
            GET_RISKAPPETITE, EDIT_SET_RISKAPPETITE, EDIT_GET_RISKAPPETITE, PARSE_RISKAPPETITE, "Risk Appetite");
    private static final PrefixMapperElement<DisposableIncome> PME_DISPOSABLEINCOME = new PrefixMapperElement<>(
            GET_DISPOSABLEINCOME, EDIT_SET_DISPOSABLEINCOME, EDIT_GET_DISPOSABLEINCOME,
            PARSE_DISPOSABLEINCOME, "Disposable Income");
    private static final PrefixMapperElement<LastMet> PME_LASTMET = new PrefixMapperElement<>(GET_LASTMET,
            EDIT_SET_LASTMET, EDIT_GET_LASTMET, PARSE_LASTMET, "Last Met");
    private static final PrefixMapperElement<NextMeeting> PME_NEXTMEETING = new PrefixMapperElement<>(GET_NEXTMEETING,
            EDIT_SET_NEXTMEETING, EDIT_GET_NEXTMEETING, PARSE_NEXTMEETING, "Next Meeting");
    private static final PrefixMapperElement<CurrentPlan> PME_CURRENTPLAN = new PrefixMapperElement<>(GET_CURRENTPLAN,
            EDIT_SET_CURRENTPLAN, EDIT_GET_CURRENTPLAN, PARSE_CURRENTPLAN, "Current Plan");
    private static final PrefixMapperElement<Set<Tag>> PME_TAG = new PrefixMapperElement<>(GET_TAGS, EDIT_SET_TAGS,
            EDIT_GET_TAGS, null, "Tag");

    // Maps prefix with their respective functions
    private static final Map<Prefix, PrefixMapperElement<?>> PREFIX_MAP = Map.ofEntries(
            Map.entry(PREFIX_CLIENTID, PME_CLIENTID),
            Map.entry(PREFIX_NAME, PME_NAME),
            Map.entry(PREFIX_PHONE, PME_PHONE),
            Map.entry(PREFIX_EMAIL, PME_EMAIL),
            Map.entry(PREFIX_ADDRESS, PME_ADDRESS),
            Map.entry(PREFIX_RISKAPPETITE, PME_RISKAPPETITE),
            Map.entry(PREFIX_DISPOSABLEINCOME, PME_DISPOSABLEINCOME),
            Map.entry(PREFIX_LASTMET, PME_LASTMET),
            Map.entry(PREFIX_NEXTMEETING, PME_NEXTMEETING),
            Map.entry(PREFIX_CURRENTPLAN, PME_CURRENTPLAN),
            Map.entry(PREFIX_TAG, PME_TAG)
    );

    public static Function<Person, ?> getAttributeFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).getAttributeFunction;
    }

    public static BiConsumer<EditPersonDescriptor, ?> getEditSetFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).editSetFunction;
    }

    public static Function<EditPersonDescriptor, ? extends Optional<?>> getEditGetFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).editGetFunction;
    }

    public static Function<String, ?> getParseFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).parseFunction;
    }

    public static String getName(Prefix prefix) {
        return PREFIX_MAP.get(prefix).name;
    }

    public static BiConsumer<EditPersonDescriptor, String> parseAndEditSet(Prefix prefix) {
        return PREFIX_MAP.get(prefix).parseAndEditSet();
    }

    /**
     * Wrapper class to wrap the different function related to an attribute
     */
    private static class PrefixMapperElement<T> {
        private final Function<Person, T> getAttributeFunction;

        private final BiConsumer<EditPersonDescriptor, T> editSetFunction;
        private final Function<EditPersonDescriptor, Optional<T>> editGetFunction;
        private final Function<String, T> parseFunction;
        private final String name;
        private PrefixMapperElement(Function<Person, T> getAttributeFunction,
            BiConsumer<EditPersonDescriptor, T> editSetFunction, Function<EditPersonDescriptor,
                Optional<T>> editGetFunction, Function<String, T> parseFunction, String name) {
            this.getAttributeFunction = getAttributeFunction;
            this.editSetFunction = editSetFunction;
            this.editGetFunction = editGetFunction;
            this.parseFunction = parseFunction;
            this.name = name;
        }

        /**
         * Returns a {@code BiConsumer<EditPersonDescriptor, String} that takes a {@code EditPersonDescriptor}
         * to set with the result parsed from {@code parse_attribute} with the given {@code String}
         */
        private BiConsumer<EditPersonDescriptor, String> parseAndEditSet() {
            return (editPersonDescriptor, s) ->
                    editSetFunction.accept(editPersonDescriptor, parseFunction.apply(s));
        }

    }

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
