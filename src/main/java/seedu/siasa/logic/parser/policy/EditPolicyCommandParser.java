package seedu.siasa.logic.parser.policy;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.policy.EditPolicyCommand;
import seedu.siasa.logic.parser.ArgumentMultimap;
import seedu.siasa.logic.parser.ArgumentTokenizer;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditPolicyCommandParser implements Parser<EditPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args,
                        PREFIX_TITLE,
                        PREFIX_EXPIRY,
                        PREFIX_PAYMENT,
                        PREFIX_COMMISSION,
                        PREFIX_CLIENT_INDEX);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPolicyCommand.MESSAGE_USAGE), pe);
        }

        EditPolicyCommand.EditPolicyDescriptor editPolicyDescriptor = new EditPolicyCommand.EditPolicyDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editPolicyDescriptor.setTitle(ParserUtil.parseTitle(
                    argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_EXPIRY).isPresent()) {
            editPolicyDescriptor.setCoverageExpiryDate(ParserUtil.parseCoverageExpiryDate(
                    argMultimap.getValue(PREFIX_EXPIRY).get()));
        }
        if (argMultimap.getValue(PREFIX_PAYMENT).isPresent()) {
            editPolicyDescriptor.setPaymentStructure(ParserUtil.parsePaymentStructure(
                    argMultimap.getValue(PREFIX_PAYMENT).get()));
        }
        if (argMultimap.getValue(PREFIX_COMMISSION).isPresent()) {
            if (editPolicyDescriptor.getPaymentStructure().isPresent()) {
                editPolicyDescriptor.setCommission(ParserUtil.parseCommission(
                        argMultimap.getValue(PREFIX_COMMISSION).get(),
                        editPolicyDescriptor.getPaymentStructure().get()));
            } else {
                editPolicyDescriptor.setCommission(ParserUtil.parseCommission(
                        argMultimap.getValue(PREFIX_COMMISSION).get()));
            }
        }
        if (argMultimap.getValue(PREFIX_CLIENT_INDEX).isPresent()) {
            editPolicyDescriptor.setOwnerIndex(ParserUtil.parseIndex(
                    argMultimap.getValue(PREFIX_CLIENT_INDEX).get()));
        }

        if (!editPolicyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPolicyCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPolicyCommand(index, editPolicyDescriptor);
    }

}
