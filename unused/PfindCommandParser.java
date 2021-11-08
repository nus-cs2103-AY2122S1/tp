//@@author Samuel-bit-prog-unused
//not used due to freexing of features. Cannot tweak command format. Naming of command is off, wanted to change
//to mpfind instead of pfind.
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//
//import java.util.Arrays;
//
//import seedu.address.logic.commands.member.PfindCommand;
//import seedu.address.logic.parser.Parser;
//import seedu.address.logic.parser.exceptions.ParseException;
//
///**
// * Parses input arguments and creates a new FindCommand object
// */
//public class PfindCommandParser implements Parser<PfindCommand> {
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the PfindCommand
//     * and returns a PfindCommand object for execution.
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public PfindCommand parse(String args) throws ParseException {
//        String trimmedArgs = args.trim();
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, PfindCommand.MESSAGE_USAGE));
//        }
//
//        String[] nameKeywords = trimmedArgs.split("\\s+");
//
//        return new PfindCommand(Arrays.asList(nameKeywords));
//    }
//}
