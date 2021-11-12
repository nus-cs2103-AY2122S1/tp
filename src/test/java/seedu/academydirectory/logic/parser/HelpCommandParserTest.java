package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.logic.commands.AddCommand;
import seedu.academydirectory.logic.commands.AttendanceCommand;
import seedu.academydirectory.logic.commands.ClearCommand;
import seedu.academydirectory.logic.commands.DeleteCommand;
import seedu.academydirectory.logic.commands.EditCommand;
import seedu.academydirectory.logic.commands.ExitCommand;
import seedu.academydirectory.logic.commands.FilterCommand;
import seedu.academydirectory.logic.commands.GetCommand;
import seedu.academydirectory.logic.commands.GradeCommand;
import seedu.academydirectory.logic.commands.HelpCommand;
import seedu.academydirectory.logic.commands.HistoryCommand;
import seedu.academydirectory.logic.commands.ListCommand;
import seedu.academydirectory.logic.commands.ParticipationCommand;
import seedu.academydirectory.logic.commands.RedoCommand;
import seedu.academydirectory.logic.commands.RevertCommand;
import seedu.academydirectory.logic.commands.ShowCommand;
import seedu.academydirectory.logic.commands.SortCommand;
import seedu.academydirectory.logic.commands.TagCommand;
import seedu.academydirectory.logic.commands.UndoCommand;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualizeCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    private final String[] validInputs = {
        "add", "attendance", "clear", "delete",
        "edit", "exit", "filter", "get",
        "grade", "list", "participation", "view",
        "show", "sort", "tag", "visualize",
        "revert", "history", "undo", "redo"
    };

    private final HashMap<String, String> mapInputsToCorrectHelpMessage = new HashMap<>();

    private final String[] invalidInputs = {
        "CS2103T", "my life sucks", "NUS",
        "Computer Science", "02hf40hg240",
        "[;.;..;[", "23rglbt;mbw", "-3429"
    };

    @Test
    public void parse_validArgument_success() {
        assertParseSuccess(parser, "", new HelpCommand());

        mapInputsToCorrectHelpMessage.put("add", AddCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("attendance", AttendanceCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("clear", ClearCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("delete", DeleteCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("edit", EditCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("exit", ExitCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("filter", FilterCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("get", GetCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("grade", GradeCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("list", ListCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("participation", ParticipationCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("show", ShowCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("sort", SortCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("tag", TagCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("visualize", VisualizeCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("view", ViewCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("revert", RevertCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("history", HistoryCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("undo", UndoCommand.HELP_MESSAGE);
        mapInputsToCorrectHelpMessage.put("redo", RedoCommand.HELP_MESSAGE);

        // help works for all valid input command
        for (String keyword : validInputs) {
            assertParseSuccess(parser, keyword, new HelpCommand(keyword, mapInputsToCorrectHelpMessage.get(keyword)));
        }
    }

    @Test
    public void parse_invalidArgument_failure() {
        for (String keyword : invalidInputs) {
            assertParseFailure(parser, keyword, String.format(Messages.MESSAGE_HELP_NOT_EXIST, keyword));
        }
    }
}
