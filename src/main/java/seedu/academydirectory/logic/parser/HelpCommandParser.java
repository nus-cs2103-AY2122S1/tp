package seedu.academydirectory.logic.parser;

import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {
    private static final String TERMINATED_CHAR = "`";

    @Override
    public HelpCommand parse(String userInput) throws ParseException {
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            return new HelpCommand();
        } else {
            String keywordNormalized = normalizeInput(userInput);
            return new HelpCommand(keywordNormalized);
        }
    }

    private String normalizeInput(String keyword) {
        return TERMINATED_CHAR + keyword + TERMINATED_CHAR;
    }
}

