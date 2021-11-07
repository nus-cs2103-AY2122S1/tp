package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MessagesTest {

    @Test
    public void messageUnknownCommand() {
        String expectedMessage = "Unknown command";
        assertEquals(expectedMessage, new Messages().MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void messageInvalidCommandFormat() {
        String expectedMessage = "Invalid command format! \n%1$s";
        assertEquals(expectedMessage, Messages.MESSAGE_INVALID_COMMAND_FORMAT);
    }

    @Test
    public void messageInvalidPersonDisplayedIndex() {
        String expectedMessage = "The person index provided is invalid";
        assertEquals(expectedMessage, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void messagePersonListedOverview() {
        String expectedMessage = "%1$d persons listed!";
        assertEquals(expectedMessage, Messages.MESSAGE_PERSONS_LISTED_OVERVIEW);
    }

    @Test
    public void messageInvalidName() {
        String expectedMessage = "Invalid name! Names should contain only"
                + " alphabetical characters.";
        assertEquals(expectedMessage, Messages.MESSAGE_INVALID_NAME);
    }

    @Test
    public void messageTelegramFieldCannotBeEmpty() {
        String expectedMessage = "Invalid format! Telegram handle field cannot "
                + "be empty.";
        assertEquals(expectedMessage, Messages.MESSAGE_TELEGRAM_FIELD_CANNOT_BE_EMPTY);
    }

    @Test
    public void messageTagFieldCannotBeEmpty() {
        String expectedMessage = "Invalid format! Tag field cannot "
                + "be empty.";
        assertEquals(expectedMessage, Messages.MESSAGE_TAG_FIELD_CANNOT_BE_EMPTY);
    }

    @Test
    public void messageGithubFieldCannotBeEmpty() {
        String expectedMessage = "Invalid format! Github field cannot "
                + "be empty.";
        assertEquals(expectedMessage, Messages.MESSAGE_GITHUB_FIELD_CANNOT_BE_EMPTY);
    }

    @Test
    public void messageCommandDescriptionCannotBeEmpty() {
        String expectedMessage = " command description cannot be empty!";
        assertEquals(expectedMessage, Messages.MESSAGE_COMMAND_DESCRIPTION_CANNOT_BE_EMPTY);
    }

    @Test
    public void messageCommandDoesNotTakeParameters() {
        String expectedMessage = " command doesn't take parameters!";
        assertEquals(expectedMessage, Messages.MESSAGE_COMMAND_DOES_NOT_TAKE_PARAMETERS);
    }

    @Test
    public void messageParseCommandError() {
        String expectedMessage = "Error encountered while parsing command!";
        assertEquals(expectedMessage, Messages.MESSAGE_PARSE_COMMAND_ERROR);
    }
}
