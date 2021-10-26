package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Alias;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

public class AliasCommandTest {
    private final Model model = new ModelManager();
    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void execute_success() {
        AliasCommand command = new AliasCommand(new Alias("aliasWord", "commandWord"), parser);
        Model expectedModel = new ModelManager();
        expectedModel.addAlias(new Alias("aliasWord", "commandWord"));
        assertCommandSuccess(command, model,
                String.format(AliasCommand.MESSAGE_ADD_SUCCESS, "aliasWord"), expectedModel);

        AddressBookParser expectedParser = new AddressBookParser();
        expectedParser.addAlias(new Alias("aliasWord", "commandWord"));
        assertEquals(parser, expectedParser);

        command = new AliasCommand(new Alias("aliasWord", "aliasWord"), parser);
        expectedModel = new ModelManager();
        assertCommandSuccess(command, model,
                String.format(AliasCommand.MESSAGE_REMOVE_SUCCESS, "aliasWord"), expectedModel);

        expectedParser = new AddressBookParser();
        assertEquals(parser, expectedParser);
    }
}
