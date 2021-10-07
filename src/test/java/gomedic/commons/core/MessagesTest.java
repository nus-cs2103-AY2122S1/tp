package gomedic.commons.core;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MessagesTest {
    @Test
    public void getSuggestions_misspelledCommand_correctSuggestionsReturned() {
        String wrongCommand = "clara";
        List<String> closestWords = List.of("clear\n");
        String reply = String.format("Sorry, %s is an invalid command. "
                + "You can choose from these commands instead: \n", wrongCommand);
        for (String s : closestWords) {
            reply += s;
        }
        assertEquals(reply, Messages.getSuggestions(wrongCommand));
    }

    @Test
    public void getSuggestions_garbageCommand_noSuggestionsReturned() {
        String wrongCommand = "ajadsjfksdkj";
        String reply = String.format("Sorry, %s is an invalid command.", wrongCommand);
        assertEquals(reply, Messages.getSuggestions(wrongCommand));
    }
}
