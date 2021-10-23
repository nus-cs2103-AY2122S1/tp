package seedu.address.logic.parser.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.person.EditPersonCommand;
import seedu.address.logic.commands.person.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ModuleCode;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class EditPersonCommandParser implements Parser<EditPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_EMAIL, PREFIX_MODULE_CODE, PREFIX_PHONE,
                        PREFIX_TELE_HANDLE, PREFIX_REMARK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_TELE_HANDLE).isPresent()) {
            editPersonDescriptor
                    .setTeleHandle(ParserUtil.parseTeleHandle(argMultimap.getValue(PREFIX_TELE_HANDLE).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editPersonDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }
        parseModuleCodesForEdit(argMultimap.getAllValues(PREFIX_MODULE_CODE))
                .ifPresent(editPersonDescriptor::setModuleCodes);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(index, editPersonDescriptor);
    }

    private Optional<Set<ModuleCode>> parseModuleCodesForEdit(Collection<String> moduleCodes) throws ParseException {
        assert moduleCodes != null;

        if (moduleCodes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseModuleCodes(moduleCodes));
    }
}
