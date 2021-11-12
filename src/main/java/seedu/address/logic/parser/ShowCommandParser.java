package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @param args to be parsed.
     * @return ShowCommand containing the target index to be shown.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        String trimmedArgs = " " + args.replaceAll("\\s+", " ").strip();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_TELEGRAM, PREFIX_GITHUB);

        String argMain = argMultimap.getPreamble();
        List<String> telegram = argMultimap.getAllValues(PREFIX_TELEGRAM);
        List<String> github = argMultimap.getAllValues(PREFIX_GITHUB);


        int checkArg1 = (argMain.isBlank()) ? 0 : 1;
        if (checkArg1 + telegram.size() + github.size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        if (!telegram.isEmpty()) {
            return new ShowCommand(p -> p.getTelegram().value.toLowerCase(Locale.ROOT)
                    .contains(telegram.get(0).toLowerCase(Locale.ROOT)), "Telegram Id");
        }

        if (!github.isEmpty()) {
            return new ShowCommand(p -> p.getGithub().value.toLowerCase(Locale.ROOT)
                    .contains(github.get(0).toLowerCase(Locale.ROOT)), "GitHub Username");
        }

        if (argMain.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }

        if (StringUtil.isNonZeroUnsignedInteger(argMain)) {
            Index index = ParserUtil.parseIndex(argMain);
            return new ShowCommand(index);
        }

        Integer ind = StringUtil.getInt(argMain);
        if (ind != null && ind <= 0) {
            throw new ParseException(ShowCommand.MESSAGE_INVALID_INDEX);
        }


        if (!Name.isValidName(argMain)) {
            throw new ParseException(ShowCommand.MESSAGE_INVALID_NAME);
        }

        return new ShowCommand(new NameContainsKeywordsPredicate(
                new ArrayList<>(Collections.singleton(argMain))), "Name");
    }
}
