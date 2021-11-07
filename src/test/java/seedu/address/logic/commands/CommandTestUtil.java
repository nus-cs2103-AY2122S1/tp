package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELE_HANDLE;
import static seedu.address.model.util.SampleDataUtil.parseModuleCode;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand;
import seedu.address.logic.commands.person.EditPersonCommand;
import seedu.address.model.Conthacks;
import seedu.address.model.Model;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.modulelesson.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Person
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_TELE_HANDLE_AMY = "@amytang";
    public static final String VALID_TELE_HANDLE_BOB = "@bobgoh";
    public static final String VALID_MODULE_CODE_CS2103 = "CS2103";
    public static final String VALID_LESSON_DAY_TUES = "2";
    public static final String VALID_LESSON_DAY_WED = "3";
    public static final String VALID_LESSON_TIME_09 = "09:00";
    public static final String VALID_LESSON_TIME_11 = "11:00";
    public static final String VALID_LESSON_TIME_12 = "12:00";
    public static final String VALID_LESSON_TIME_15 = "15:00";
    public static final String VALID_LESSON_TIME_11_12 = "11:00 12:00";
    public static final String VALID_LESSON_TIME_12_13 = "12:00 13:00";
    public static final String VALID_MODULE_LESSON_REMARK = "COM1-130";
    public static final String VALID_REMARK_ONLINE = "Online";
    // Both
    public static final String VALID_MODULE_CODE_CS2030S_T12 = "CS2030S T12";
    public static final String VALID_MODULE_CODE_CS2040 = "CS2040";
    public static final String VALID_MODULE_CODE_CS2100 = "CS2100";
    public static final String VALID_MODULE_CODE_CS2106 = "CS2106";
    public static final String VALID_MODULE_CODE_CS2040S_B05 = "CS2040S B05";
    // Person description
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELE_HANDLE_DESC_AMY = " " + PREFIX_TELE_HANDLE + VALID_TELE_HANDLE_AMY;
    public static final String TELE_HANDLE_DESC_BOB = " " + PREFIX_TELE_HANDLE + VALID_TELE_HANDLE_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    // Module lesson description
    public static final String LESSON_DAY_DESC_TUES = " " + PREFIX_LESSON_DAY + VALID_LESSON_DAY_TUES;
    public static final String LESSON_DAY_DESC_WED = " " + PREFIX_LESSON_DAY + VALID_LESSON_DAY_WED;
    public static final String LESSON_TIME_DESC_11_12 = " " + PREFIX_LESSON_TIME + VALID_LESSON_TIME_11_12;
    public static final String LESSON_TIME_DESC_12_13 = " " + PREFIX_LESSON_TIME + VALID_LESSON_TIME_12_13;
    public static final String REMARK_DESC_MODULES = " " + PREFIX_REMARK + VALID_MODULE_LESSON_REMARK;
    public static final String REMARK_DESC_ONLINE = " " + PREFIX_REMARK + VALID_REMARK_ONLINE;
    // Both
    public static final String MODULE_CODE_DESC_CS2030S_T12 = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_CS2030S_T12;
    public static final String MODULE_CODE_DESC_CS2040S_B05 = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_CS2040S_B05;
    public static final String MODULE_CODE_DESC_CS2040 = " " + PREFIX_MODULE_CODE + VALID_MODULE_CODE_CS2040;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TELE_HANDLE_DESC = " " + PREFIX_TELE_HANDLE + "yoz"; // missing '@' symbol
    public static final String INVALID_MODULE_CODE_DESC = " " + PREFIX_MODULE_CODE + "CS 50"; // space not allowed
    public static final String INVALID_REMARK_DESC = " " + PREFIX_REMARK + "Ãgent"; // non-ASCII character

    public static final String INVALID_LESSON_DAY_DESC = " " + PREFIX_LESSON_DAY + "9"; // not a day of a week
    public static final String INVALID_LESSON_TIME_DESC = " " + PREFIX_LESSON_TIME + "99:00 99:00"; // not a valid time
    public static final String MISSING_LESSON_TIME_DESC = " " + PREFIX_LESSON_TIME + "12:00"; // missing end time
    public static final String INVALID_LESSON_DURATION_DESC = " " + PREFIX_LESSON_TIME
            + VALID_LESSON_TIME_12 + " " + VALID_LESSON_TIME_11; // Start time is after end time

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    public static final EditModuleLessonCommand.EditLessonDescriptor DESC_CS2040S;
    public static final EditModuleLessonCommand.EditLessonDescriptor DESC_CS2030S;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        DESC_CS2040S = new EditLessonDescriptorBuilder().withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2040S_B05))
                .withLessonDay(new LessonDay("2")).withLessonStartTime(new LessonTime("10:00")).build();
        DESC_CS2030S = new EditLessonDescriptorBuilder().withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12))
                .withLessonDay(new LessonDay("5")).withLessonStartTime(new LessonTime("13:30")).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Conthacks expectedConthacks = new Conthacks(actualModel.getConthacks());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedConthacks, actualModel.getConthacks());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered person list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered person list to show only the persons that have the {@moduleCode} in the
     * {@code model}'s address book.
     */
    public static void showPersonsWithModuleCode(Model model, ModuleCode moduleCode) {
        List<String> moduleCodeToShow = new ArrayList<>();
        moduleCodeToShow.add(moduleCode.getModuleCodeName());
        ModuleCodesContainsKeywordsPredicate predicate = new ModuleCodesContainsKeywordsPredicate(moduleCodeToShow);

        model.updateFilteredPersonList(predicate);
    }

    /**
     * Updates {@code model}'s filtered lesson list to show only the lesson at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showLessonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleLessonList().size());

        ModuleLesson lesson = model.getFilteredModuleLessonList().get(targetIndex.getZeroBased());
        final String moduleCode = lesson.getModuleCode().getModuleCodeName();
        model.updateFilteredModuleLessonList(
                new ModuleCodeContainsKeywordsPredicate(Collections.singletonList(moduleCode))
        );
        System.out.println(model.getFilteredModuleLessonList());

        assertEquals(1, model.getFilteredModuleLessonList().size());
    }
}
