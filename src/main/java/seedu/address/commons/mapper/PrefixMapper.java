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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Client.EditClientDescriptor;
import seedu.address.model.client.ClientId;
import seedu.address.model.client.CurrentPlan;
import seedu.address.model.client.DisposableIncome;
import seedu.address.model.client.Email;
import seedu.address.model.client.IgnoreNullComparable;
import seedu.address.model.client.LastMet;
import seedu.address.model.client.Name;
import seedu.address.model.client.NextMeeting;
import seedu.address.model.client.Phone;
import seedu.address.model.client.RiskAppetite;
import seedu.address.model.client.SortDirection;
import seedu.address.model.tag.Tag;

/**
 * Contains {@code Map} mapping {@code Prefix} to relevant properties
 */
public class PrefixMapper {
    // Client getter methods
    private static final Function<Client, ClientId> GET_CLIENTID = Client::getClientId;
    private static final Function<Client, Name> GET_NAME = Client::getName;
    private static final Function<Client, Phone> GET_PHONE = Client::getPhone;
    private static final Function<Client, Email> GET_EMAIL = Client::getEmail;
    private static final Function<Client, Address> GET_ADDRESS = Client::getAddress;
    private static final Function<Client, RiskAppetite> GET_RISKAPPETITE = Client::getRiskAppetite;
    private static final Function<Client, DisposableIncome> GET_DISPOSABLEINCOME = Client::getDisposableIncome;
    private static final Function<Client, LastMet> GET_LASTMET = Client::getLastMet;
    private static final Function<Client, NextMeeting> GET_NEXTMEETING = Client::getNextMeeting;
    private static final Function<Client, CurrentPlan> GET_CURRENTPLAN = Client::getCurrentPlan;

    // EditClientDescriptor setter methods
    private static final BiConsumer<EditClientDescriptor, Name> EDIT_SET_NAME = EditClientDescriptor::setName;
    private static final BiConsumer<EditClientDescriptor, Phone> EDIT_SET_PHONE = EditClientDescriptor::setPhone;
    private static final BiConsumer<EditClientDescriptor, Email> EDIT_SET_EMAIL = EditClientDescriptor::setEmail;
    private static final BiConsumer<EditClientDescriptor, Address> EDIT_SET_ADDRESS = EditClientDescriptor::setAddress;
    private static final BiConsumer<EditClientDescriptor, RiskAppetite> EDIT_SET_RISKAPPETITE =
            EditClientDescriptor::setRiskAppetite;
    private static final BiConsumer<EditClientDescriptor, DisposableIncome> EDIT_SET_DISPOSABLEINCOME =
            EditClientDescriptor::setDisposableIncome;
    private static final BiConsumer<EditClientDescriptor, LastMet> EDIT_SET_LASTMET =
            EditClientDescriptor::setLastMet;
    private static final BiConsumer<EditClientDescriptor, NextMeeting> EDIT_SET_NEXTMEETING =
            EditClientDescriptor::setNextMeeting;
    private static final BiConsumer<EditClientDescriptor, CurrentPlan> EDIT_SET_CURRENTPLAN =
            EditClientDescriptor::setCurrentPlan;

    // EditClientDescriptor getter methods
    private static final Function<EditClientDescriptor, Optional<Name>> EDIT_GET_NAME =
            EditClientDescriptor::getName;
    private static final Function<EditClientDescriptor, Optional<Phone>> EDIT_GET_PHONE =
            EditClientDescriptor::getPhone;
    private static final Function<EditClientDescriptor, Optional<Email>> EDIT_GET_EMAIL =
            EditClientDescriptor::getEmail;
    private static final Function<EditClientDescriptor, Optional<Address>> EDIT_GET_ADDRESS =
            EditClientDescriptor::getAddress;
    private static final Function<EditClientDescriptor, Optional<RiskAppetite>> EDIT_GET_RISKAPPETITE =
            EditClientDescriptor::getRiskAppetite;
    private static final Function<EditClientDescriptor, Optional<DisposableIncome>> EDIT_GET_DISPOSABLEINCOME =
            EditClientDescriptor::getDisposableIncome;
    private static final Function<EditClientDescriptor, Optional<LastMet>> EDIT_GET_LASTMET =
            EditClientDescriptor::getLastMet;
    private static final Function<EditClientDescriptor, Optional<NextMeeting>> EDIT_GET_NEXTMEETING =
            EditClientDescriptor::getNextMeeting;
    private static final Function<EditClientDescriptor, Optional<CurrentPlan>> EDIT_GET_CURRENTPLAN =
            EditClientDescriptor::getCurrentPlan;

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
            null, null, PARSE_CLIENTID, "Client Id", null);
    private static final PrefixMapperElement<Name> PME_NAME = new PrefixMapperElement<>(GET_NAME,
            EDIT_SET_NAME, EDIT_GET_NAME, PARSE_NAME, "Name", Name.DEFAULT_VALUE);
    private static final PrefixMapperElement<Phone> PME_PHONE = new PrefixMapperElement<>(GET_PHONE,
            EDIT_SET_PHONE, EDIT_GET_PHONE, PARSE_PHONE, "Phone", Phone.DEFAULT_VALUE);
    private static final PrefixMapperElement<Email> PME_EMAIL = new PrefixMapperElement<>(GET_EMAIL,
            EDIT_SET_EMAIL, EDIT_GET_EMAIL, PARSE_EMAIL, "Email", Email.DEFAULT_VALUE);
    private static final PrefixMapperElement<Address> PME_ADDRESS = new PrefixMapperElement<>(GET_ADDRESS,
            EDIT_SET_ADDRESS, EDIT_GET_ADDRESS, PARSE_ADDRESS, "Address", Address.DEFAULT_VALUE);
    private static final PrefixMapperElement<RiskAppetite> PME_RISKAPPETITE = new PrefixMapperElement<>(
            GET_RISKAPPETITE, EDIT_SET_RISKAPPETITE, EDIT_GET_RISKAPPETITE,
            PARSE_RISKAPPETITE, "Risk Appetite", RiskAppetite.DEFAULT_VALUE);
    private static final PrefixMapperElement<DisposableIncome> PME_DISPOSABLEINCOME = new PrefixMapperElement<>(
            GET_DISPOSABLEINCOME, EDIT_SET_DISPOSABLEINCOME, EDIT_GET_DISPOSABLEINCOME, PARSE_DISPOSABLEINCOME,
            "Disposable Income", DisposableIncome.DEFAULT_VALUE);
    private static final PrefixMapperElement<LastMet> PME_LASTMET = new PrefixMapperElement<>(GET_LASTMET,
            EDIT_SET_LASTMET, EDIT_GET_LASTMET, PARSE_LASTMET, "Last Met", LastMet.DEFAULT_VALUE);
    private static final PrefixMapperElement<NextMeeting> PME_NEXTMEETING = new PrefixMapperElement<>(GET_NEXTMEETING,
            EDIT_SET_NEXTMEETING, EDIT_GET_NEXTMEETING, PARSE_NEXTMEETING, "Next Meeting", NextMeeting.NO_NEXT_MEETING);
    private static final PrefixMapperElement<CurrentPlan> PME_CURRENTPLAN = new PrefixMapperElement<>(GET_CURRENTPLAN,
            EDIT_SET_CURRENTPLAN, EDIT_GET_CURRENTPLAN, PARSE_CURRENTPLAN, "Current Plan", CurrentPlan.DEFAULT_VALUE);
    private static final PrefixMapperElement<Tag> PME_TAG = new PrefixMapperElement<>(null, null, null,
            null, "Tag", null);

    // Maps prefix with their respective functions
    private static final Map<Prefix, PrefixMapperElement<? extends IgnoreNullComparable<?>>> PREFIX_MAP = Map.ofEntries(
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

    public static String getName(Prefix prefix) {
        return PREFIX_MAP.get(prefix).name;
    }

    /**
     * @see PrefixMapperElement#getAttributeFunction
     */
    public static Function<Client, ? extends IgnoreNullComparable<?>> getAttributeFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).getAttributeFunction;
    }

    /**
     * @see PrefixMapperElement#getAttributeAndSet()
     */
    public static BiConsumer<EditClientDescriptor, Client> getAttributeAndSetFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).getAttributeAndSet();
    }

    /**
     * @see PrefixMapperElement#getEditAndSet()
     */
    public static BiConsumer<EditClientDescriptor, EditClientDescriptor> getEditAndSetFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).getEditAndSet();
    }

    /**
     * @see PrefixMapperElement#parseAndEditSet()
     */
    public static BiConsumer<EditClientDescriptor, String> parseAndEditSetFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).parseAndEditSet();
    }

    /**
     * @see PrefixMapperElement#parseAndEditSetDefault()
     */
    public static BiConsumer<EditClientDescriptor, Optional<String>> parseAndEditSetDefaultFunction(Prefix prefix) {
        return PREFIX_MAP.get(prefix).parseAndEditSetDefault();
    }

    /**
     * @see PrefixMapperElement#compareFunction(SortDirection)
     */
    public static BiFunction<Client, Client, Integer> compareFunction(Prefix prefix, SortDirection sortDirection) {
        return PREFIX_MAP.get(prefix).compareFunction(sortDirection);
    }


    /**
     * Wrapper class to wrap the different function related to an attribute
     */
    private static class PrefixMapperElement<T extends IgnoreNullComparable<T>> {
        private final Function<Client, T> getAttributeFunction;
        private final BiConsumer<EditClientDescriptor, T> editSetFunction;
        private final Function<EditClientDescriptor, Optional<T>> editGetFunction;
        private final Function<String, T> parseFunction;
        private final String name;
        private final String defaultValue;

        private PrefixMapperElement(Function<Client, T> getAttributeFunction,
                                    BiConsumer<EditClientDescriptor, T> editSetFunction,
                                    Function<EditClientDescriptor, Optional<T>> editGetFunction,
                                    Function<String, T> parseFunction,
                                    String name,
                                    String defaultValue) {
            this.getAttributeFunction = getAttributeFunction;
            this.editSetFunction = editSetFunction;
            this.editGetFunction = editGetFunction;
            this.parseFunction = parseFunction;
            this.name = name;
            this.defaultValue = defaultValue;
        }

        /**
         * Returns a {@code BiConsumer<EditClientDescriptor, Client>} that takes a {@code EditClientDescriptor}
         * to set with the {@code Client}'s attribute.
         */
        private BiConsumer<EditClientDescriptor, Client> getAttributeAndSet() {
            return (editClientDescriptor, client) -> editSetFunction.accept(editClientDescriptor,
                    getAttributeFunction.apply(client));
        }

        /**
         * Returns a {@code BiConsumer<EditClientDescriptor, EditClientDescriptor>} that takes {@code getEdit}'s
         * attribute if present to set {@code setEdit}'s attribute.
         */
        private BiConsumer<EditClientDescriptor, EditClientDescriptor> getEditAndSet() {
            return (getEdit, setEdit) -> editGetFunction.apply(getEdit)
                    .ifPresent(s -> editSetFunction.accept(setEdit, s));
        }

        /**
         * Returns a {@code BiConsumer<EditClientDescriptor, String>} that takes a {@code EditClientDescriptor}
         * to set with the result parsed from {@code parse_attribute} with the given {@code String}
         */
        private BiConsumer<EditClientDescriptor, String> parseAndEditSet() {
            return (editClientDescriptor, s) ->
                    editSetFunction.accept(editClientDescriptor, parseFunction.apply(s));
        }

        /**
         * Returns a {@code BiConsumer<EditClientDescriptor, Optional<String>>} that takes a
         * {@code EditClientDescriptor} to set with the result parsed from {@code parse_attribute}
         * with the given {@code String}. If {@code String} is not present then default value is used.
         */
        private BiConsumer<EditClientDescriptor, Optional<String>> parseAndEditSetDefault() {
            return (editClientDescriptor, s) -> parseAndEditSet().accept(editClientDescriptor, s.orElse(defaultValue));
        }

        /**
         * Returns an integer determined by the compareTo method based on the attribute of the two {@code Client}
         */
        private BiFunction<Client, Client, Integer> compareFunction(SortDirection direction) {
            return (x, y) -> getAttributeFunction.apply(x)
                    .compareWithDirection(getAttributeFunction.apply(y), direction);
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
