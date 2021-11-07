package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE_REQUIREMENTS;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SetRoleReqCommandParser implements Parser<SetRoleReqCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the SetRoleReqCommand
     * and returns a SetRoleReqCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SetRoleReqCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROLE_REQUIREMENTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE_REQUIREMENTS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.getHelpMessage()));
        }

        Set<String> roleReqList = ParserUtil.parseRoleRequirements(argMultimap.getAllValues(PREFIX_ROLE_REQUIREMENTS));
        return new SetRoleReqCommand(roleReqList);
    }


}
