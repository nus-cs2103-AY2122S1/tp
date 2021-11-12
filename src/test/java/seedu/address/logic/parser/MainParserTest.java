package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_VALID_VALORANT_VALID_HOUR_ZERO_MONDAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.RecommendCommand;
import seedu.address.logic.commands.friends.AddFriendCommand;
import seedu.address.logic.commands.games.AddGameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.friends.FriendCommandParser;
import seedu.address.logic.parser.games.GameCommandParser;

public class MainParserTest {

    private final MainParser parser = new MainParser();

    @Test
    public void parseCommand_friendCommands() throws Exception {
        String addFriendCommandString = FriendCommandParser.COMMAND_WORD + FLAG_POSTFIX.getFlag()
                + FLAG_ADD.getFlag() + VALID_FRIEND_ID_AMY;
        assertTrue(parser.parseCommand(addFriendCommandString) instanceof AddFriendCommand);
    }

    @Test
    public void parseCommand_gameCommands() throws Exception {
        String addGameCommandString = GameCommandParser.COMMAND_WORD + FLAG_POSTFIX.getFlag()
                + FLAG_ADD.getFlag() + VALID_GAME_ID_CSGO;
        assertTrue(parser.parseCommand(addGameCommandString) instanceof AddGameCommand);
    }

    @Test
    public void parseCommand_recommendFriends() throws ParseException {
        String recommendFriendsCommandString = RecommendCommand.COMMAND_WORD + " "
                + RECOMMEND_VALID_VALORANT_VALID_HOUR_ZERO_MONDAY_DESC;
        assertTrue(parser.parseCommand(recommendFriendsCommandString) instanceof RecommendCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
