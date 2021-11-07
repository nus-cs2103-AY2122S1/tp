package seedu.address.logic.parser.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.vendor.FilterVendorCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.Cost;
import seedu.address.model.vendor.OperatingHours;
import seedu.address.model.vendor.VendorPredicate;

/**
 * Parses input arguments and creates a new FilterVendorCommand object
 */
public class FilterVendorCommandParser implements Parser<FilterVendorCommand> {

    private static final String VALIDATION_OPERATING_HOURS_REGEX =
            "^[1-7]+([\\s][0-2][0-9][0-5][0-9])?([-][0-2][0-9][0-5][0-9])?$";

    private static final String VALIDATION_COST_REGEX = "^[<,>]{0,1}[0-9]+(.[0-9]+)?$";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterVendorCommand
     * and returns a FilterVendorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterVendorCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                        PREFIX_VENDOR_ID, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_SERVICE_NAME, PREFIX_COST,
                        PREFIX_OPERATING_HOURS);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_VENDOR_ID, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_SERVICE_NAME, PREFIX_COST,
                PREFIX_OPERATING_HOURS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterVendorCommand.MESSAGE_USAGE));
        }

        try {
            Optional<String> nameOptional = argMultimap.getValue(PREFIX_NAME);

            Optional<String> emailOptional = argMultimap.getValue(PREFIX_EMAIL);

            Optional<String> vendorIdOptional = argMultimap.getValue(PREFIX_VENDOR_ID);

            Optional<String> phoneOptional = argMultimap.getValue(PREFIX_PHONE);

            Optional<String> addressOptional = argMultimap.getValue(PREFIX_ADDRESS);

            Optional<String> serviceNameOptional = argMultimap.getValue(PREFIX_SERVICE_NAME);

            Optional<String> costOptional = argMultimap.getValue(PREFIX_COST);
            if (costOptional.isPresent() && !costOptional.get().matches(VALIDATION_COST_REGEX)) {
                throw new ParseException(Cost.MESSAGE_FILTER_CONSTRAINTS);
            }

            Optional<String> operatingHoursOptional = argMultimap.getValue(PREFIX_OPERATING_HOURS);
            if (operatingHoursOptional.isPresent()
                    && (!operatingHoursOptional.get().matches(VALIDATION_OPERATING_HOURS_REGEX)
                    && !operatingHoursOptional.get().trim().equals("now"))) {
                throw new ParseException(OperatingHours.MESSAGE_FILTER_CONSTRAINTS);
            }

            List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
            Optional<Set<Tag>> tagsOptional =
                    tags.isEmpty() ? Optional.empty() : Optional.of(ParserUtil.parseTags(tags));

            return new FilterVendorCommand(
                    new VendorPredicate(vendorIdOptional, phoneOptional, nameOptional, emailOptional,
                            tagsOptional, addressOptional, serviceNameOptional, costOptional, operatingHoursOptional));
        } catch (ParseException pe) {
            throw pe;
        }

    }

    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
