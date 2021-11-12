package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.logic.commands.ClearCommand;
import seedu.academydirectory.logic.commands.Command;
import seedu.academydirectory.logic.commands.ExitCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ListCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;

public class SingularCommandParserTest {

    private final String[] validInputs = {
        "clear", "exit", "list", "redo",
        "undo", "history", "visualize"
    };

    private final HashMap<String, Command> mapToValidCommand = new HashMap<>();

    private void processHashMap() {
        mapToValidCommand.put("clear", new ClearCommand());
        mapToValidCommand.put("exit", new ExitCommand());
        mapToValidCommand.put("list", new ListCommand());
        mapToValidCommand.put("redo", new RedoCommand());
        mapToValidCommand.put("undo", new UndoCommand());
        mapToValidCommand.put("history", new HistoryCommand());
        mapToValidCommand.put("visualize", new VisualizeCommand());
    }

    @Test
    public void testValidSingularCommand() throws Exception {
        processHashMap();
        for (String input : validInputs) {
            SingularCommandParser parser = new SingularCommandParser(input);
            assertParseSuccess(parser, "", mapToValidCommand.get(input));
        }
    }

    @Test
    public void testInvalidSingularCommand() {
        processHashMap();
        for (String input : validInputs) {
            SingularCommandParser parser = new SingularCommandParser(input);
            String userInput1 = "2";
            assertParseFailure(parser, userInput1,
                    String.format(SingularCommandParser.MESSAGE_NO_ARGUMENT_SHOULD_FOLLOW, input));
            String userInput2 = "random arg";
            assertParseFailure(parser, userInput2,
                    String.format(SingularCommandParser.MESSAGE_NO_ARGUMENT_SHOULD_FOLLOW, input));
        }
    }
}
