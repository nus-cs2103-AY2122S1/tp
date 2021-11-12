package teletubbies.logic.parser;

import org.junit.jupiter.api.Test;

import teletubbies.commons.core.Messages;
import teletubbies.commons.core.UserProfile;
import teletubbies.logic.commands.ProfileCommand;

class ProfileCommandParserTest {
    private ProfileCommandParser parser = new ProfileCommandParser();

    @Test
    public void parse_validTelemarketerRole_success() {
        String userInput = " -n Telemarketer Name -role Telemarketer";
        UserProfile userProfile = new UserProfile("Telemarketer Name", UserProfile.Role.TELEMARKETER);
        ProfileCommand expectedCommand = new ProfileCommand(userProfile);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validSupervisorRole_success() {
        String userInput = " -n Supervisor Name -role Supervisor";
        UserProfile userProfile = new UserProfile("Supervisor Name", UserProfile.Role.SUPERVISOR);
        ProfileCommand expectedCommand = new ProfileCommand(userProfile);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidRole_failure() {
        String userInput = " -n Name -role Invalid";
        CommandParserTestUtil.assertParseFailure(parser, userInput, ProfileCommand.MESSAGE_INVALID_ROLE);
    }

    @Test
    public void parse_noName_failure() {
        String userInput = " -n Name";
        CommandParserTestUtil.assertParseFailure(parser, userInput,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ProfileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noRole_failure() {
        String userInput = " -role Invalid";
        CommandParserTestUtil.assertParseFailure(parser, userInput,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ProfileCommand.MESSAGE_USAGE));
    }
}
