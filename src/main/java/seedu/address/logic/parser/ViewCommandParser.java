package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.function.Supplier;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Class representing the parser for the view command.
 *
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    private static final Supplier<Boolean> ALWAYS_TRUE = () -> true;

    /**
     * Parser for the view command.
     *
     * @param args The input search fields
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        //when no argument is given to the argMultiMap
        if (argMultimap.isEmpty()) {
            throw new ParseException(ViewCommand.HELP_MESSAGE);
        }
        return new ViewCommand(person -> {
            Optional<Boolean> isNameEqual = argMultimap.getValue(PREFIX_NAME).map(Name::new)
                    .map(nameArg -> person.getName().equals(nameArg));
            Optional<Boolean> isPhoneEqual = argMultimap.getValue(PREFIX_PHONE).map(Phone::new)
                    .map(phoneArg -> person.getPhone().equals(phoneArg));
            Optional<Boolean> isEmailEqual = argMultimap.getValue(PREFIX_EMAIL).map(Email::new)
                    .map(emailArg -> person.getEmail().equals(emailArg));
            Optional<Boolean> isAddressEqual = argMultimap.getValue(PREFIX_ADDRESS).map(Address::new)
                    .map(addressArg -> person.getAddress().equals(addressArg));
            Optional<Boolean> isTagEqual = argMultimap.getValue(PREFIX_TAG).map(Tag::new)
                    .map(tagArg -> person.getTags().contains(tagArg));
            return isNameEqual.orElseGet(ALWAYS_TRUE)
                    && isPhoneEqual.orElseGet(ALWAYS_TRUE)
                    && isEmailEqual.orElseGet(ALWAYS_TRUE)
                    && isAddressEqual.orElseGet(ALWAYS_TRUE)
                    && isTagEqual.orElseGet(ALWAYS_TRUE);
        });
    }


}
