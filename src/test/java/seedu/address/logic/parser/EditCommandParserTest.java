package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEW_MODULE_NAME_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TASK_DEADLINE_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TASK_ID_DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.TASK_NAME_DESC_0;
import static seedu.address.logic.commands.CommandTestUtil.TELE_HANDLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_0;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELE_HANDLE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditModuleCommand;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();
    private ModuleName moduleName = new ModuleName(MODULE_NAME_0);

    @Test
    public void parse_editModule_returnsEditModuleCommand() {
        String userInput = "module" + MODULE_NAME_DESC_0 + NEW_MODULE_NAME_DESC_1;
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(new ModuleName(MODULE_NAME_1));
        EditModuleCommand expectedCommand = new EditModuleCommand(moduleName, editModuleDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_editTask_returnsEditTaskCommand() {
        String userInput = "task" + MODULE_NAME_DESC_0 + TASK_ID_DESC_1 + TASK_NAME_DESC_0
                + TASK_DEADLINE_DESC_0;
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_0)
                .withTaskId(VALID_TASK_ID_1).withTaskDeadline(VALID_TASK_DEADLINE_0).build();
        EditTaskCommand expectedCommand = new EditTaskCommand(moduleName, editTaskDescriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_editStudent_returnsEditStudentCommand() {
        String userInput = "student" + MODULE_NAME_DESC_0 + STUDENT_ID_DESC_BOB
                + EMAIL_DESC_AMY + TELE_HANDLE_DESC_AMY + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentId(VALID_STUDENT_ID_BOB).withEmail(VALID_EMAIL_AMY).withTeleHandle(VALID_TELE_HANDLE_AMY)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(moduleName, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidCommand_throwsParseException() {
        // any user input that is not module, task or student
        String userInput = "(*#@";
        assertParseFailure(parser, userInput, MESSAGE_UNKNOWN_COMMAND);
    }
}
