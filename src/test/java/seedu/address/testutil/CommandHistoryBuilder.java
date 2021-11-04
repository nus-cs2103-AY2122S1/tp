package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_STUDENT;
import static seedu.address.model.CommandHistory.addCommand;
import static seedu.address.model.CommandHistory.clear;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.TagCommand;

public class CommandHistoryBuilder {

    public static final String SAMPLE_COMMAND_1 = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY
            + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + BIRTHDAY_DESC_AMY;
    public static final String SAMPLE_COMMAND_2 = EditCommand.COMMAND_WORD + INDEX_FIRST_PERSON.getZeroBased()
            + EMAIL_DESC_BOB;
    public static final String SAMPLE_COMMAND_3 = TagCommand.COMMAND_WORD + INDEX_THIRD_PERSON.getZeroBased()
            + TAG_DESC_FRIEND + TAG_DESC_STUDENT;
    public static final String SAMPLE_COMMAND_4 = FindCommand.COMMAND_WORD + NAME_DESC_BOB;

    /**
     * Creates an empty {@code CommandHistory}.
     */
    public static void emptyHistory() {
        clear();
    }

    /**
     * Creates a valid {@code CommandHistory} with sample commands.
     */
    public static void validHistory() {
        clear();
        addCommand(SAMPLE_COMMAND_1);
        addCommand(SAMPLE_COMMAND_2);
        addCommand(SAMPLE_COMMAND_3);
        addCommand(SAMPLE_COMMAND_4);
    }
}
