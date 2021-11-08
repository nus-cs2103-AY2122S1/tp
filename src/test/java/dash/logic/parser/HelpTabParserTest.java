package dash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.logic.commands.ExitCommand;
import dash.logic.commands.SwitchTabContactsCommand;
import dash.logic.commands.SwitchTabHelpCommand;
import dash.logic.commands.SwitchTabTasksCommand;

class HelpTabParserTest {

    private final HelpTabParser parser = new HelpTabParser();

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_switchTabContacts() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabContactsCommand.COMMAND_WORD)
                instanceof SwitchTabContactsCommand);
        assertTrue(parser.parseCommand(SwitchTabContactsCommand.COMMAND_WORD + " 3")
                instanceof SwitchTabContactsCommand);
    }

    @Test
    public void parseCommand_switchTabTasks() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabTasksCommand.COMMAND_WORD) instanceof SwitchTabTasksCommand);
        assertTrue(parser.parseCommand(SwitchTabTasksCommand.COMMAND_WORD + " 3")
                instanceof SwitchTabTasksCommand);
    }

    @Test
    public void parseCommand_switchTabHelp() throws Exception {
        assertTrue(parser.parseCommand(SwitchTabHelpCommand.COMMAND_WORD) instanceof SwitchTabHelpCommand);
        assertTrue(parser.parseCommand(SwitchTabHelpCommand.COMMAND_WORD + " 3")
                instanceof SwitchTabHelpCommand);
    }

}
