package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.testutil.TypicalStudents.ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.parser.CliSyntax;
import tutoraid.model.LessonBook;
import tutoraid.model.Model;
import tutoraid.model.StudentBook;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonNameContainsKeywordsPredicate;
import tutoraid.model.student.NameContainsKeywordsPredicate;
import tutoraid.model.student.Student;
import tutoraid.testutil.Assert;
import tutoraid.testutil.EditLessonDescriptorBuilder;
import tutoraid.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_ADD_STUDENT_COMMAND = "add -s ";
    public static final String VALID_ADD_LESSON_COMMAND = "add -l ";

    public static final String VALID_STUDENT_NAME_AMY = "Amy Bee";
    public static final String VALID_STUDENT_NAME_BOB = "Bob Choo";
    public static final String VALID_STUDENT_PHONE_AMY = "11111111";
    public static final String VALID_STUDENT_PHONE_BOB = "22222222";

    public static final String VALID_PARENT_NAME_AMY = "Mr Bee";
    public static final String VALID_PARENT_NAME_BOB = "Mrs Clara Choo";
    public static final String VALID_PARENT_PHONE_AMY = "33333333";
    public static final String VALID_PARENT_PHONE_BOB = "44444444";

    public static final ArrayList<String> VALID_PROGRESS_LIST_BOB = new ArrayList<>();
    public static final ArrayList<String> VALID_PROGRESS_LIST_AMY = new ArrayList<>();
    public static final boolean VALID_PAYMENT_STATUS_AMY = false;
    public static final boolean VALID_PAYMENT_STATUS_BOB = false;

    public static final String VALID_LESSON_NAME_MATHS_TWO = "Maths 2";
    public static final String VALID_LESSON_NAME_SCIENCE_TWO = "Science 2";

    public static final String VALID_CAPACITY_MATHS_TWO = "40";
    public static final String VALID_CAPACITY_SCIENCE_TWO = "45";

    public static final String VALID_PRICE_MATHS_TWO = "130";
    public static final String VALID_PRICE_SCIENCE_TWO = "145";

    public static final String VALID_TIMING_MATHS_TWO = "1000-1200";
    public static final String VALID_TIMING_SCIENCE_TWO = "1400-1600";

    public static final ArrayList<Student> VALID_STUDENTS_MATHS_TWO = new ArrayList<>(List.of(ALICE));
    public static final ArrayList<Student> VALID_STUDENTS_SCIENCE_TWO = new ArrayList<>(List.of(ALICE));

    public static final String STUDENT_NAME_DESC_AMY = " " + CliSyntax.PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_AMY;
    public static final String STUDENT_NAME_DESC_BOB = " " + CliSyntax.PREFIX_STUDENT_NAME + VALID_STUDENT_NAME_BOB;
    public static final String STUDENT_PHONE_DESC_AMY = " " + CliSyntax.PREFIX_STUDENT_PHONE + VALID_STUDENT_PHONE_AMY;
    public static final String STUDENT_PHONE_DESC_BOB = " " + CliSyntax.PREFIX_STUDENT_PHONE + VALID_STUDENT_PHONE_BOB;
    public static final String PARENT_NAME_DESC_AMY = " " + CliSyntax.PREFIX_PARENT_NAME + VALID_PARENT_NAME_AMY;
    public static final String PARENT_NAME_DESC_BOB = " " + CliSyntax.PREFIX_PARENT_NAME + VALID_PARENT_NAME_BOB;
    public static final String PARENT_PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_AMY;
    public static final String PARENT_PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PARENT_PHONE + VALID_PARENT_PHONE_BOB;

    public static final String LESSON_NAME_DESC_MATH =
            " " + CliSyntax.PREFIX_LESSON_NAME + VALID_LESSON_NAME_MATHS_TWO;
    public static final String LESSON_NAME_DESC_SCIENCE =
            " " + CliSyntax.PREFIX_LESSON_NAME + VALID_LESSON_NAME_SCIENCE_TWO;
    public static final String CAPACITY_DESC_MATH =
            " " + CliSyntax.PREFIX_LESSON_CAPACITY + VALID_CAPACITY_MATHS_TWO;
    public static final String CAPACITY_DESC_SCIENCE =
            " " + CliSyntax.PREFIX_LESSON_CAPACITY + VALID_CAPACITY_SCIENCE_TWO;
    public static final String PRICE_DESC_MATH =
            " " + CliSyntax.PREFIX_LESSON_PRICE + VALID_PRICE_MATHS_TWO;
    public static final String PRICE_DESC_SCIENCE =
            " " + CliSyntax.PREFIX_LESSON_PRICE + VALID_PRICE_SCIENCE_TWO;
    public static final String TIMING_DESC_MATH =
            " " + CliSyntax.PREFIX_LESSON_TIMING + VALID_TIMING_MATHS_TWO;
    public static final String TIMING_DESC_SCIENCE =
            " " + CliSyntax.PREFIX_LESSON_TIMING + VALID_TIMING_SCIENCE_TWO;

    public static final String INVALID_STUDENT_NAME_DESC =
            " " + CliSyntax.PREFIX_STUDENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_PHONE_DESC =
            " " + CliSyntax.PREFIX_STUDENT_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_PARENT_NAME_DESC =
            " " + CliSyntax.PREFIX_PARENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PARENT_PHONE_DESC =
            " " + CliSyntax.PREFIX_PARENT_PHONE + "911a"; // 'a' not allowed in phones

    public static final String INVALID_LESSON_NAME_DESC =
            " " + CliSyntax.PREFIX_LESSON_NAME + "Maths&"; // alphanumeric chars only
    public static final String INVALID_CAPACITY_DESC =
            " " + CliSyntax.PREFIX_LESSON_CAPACITY + "911.1"; // must be integer
    public static final String INVALID_PRICE_DESC =
            " " + CliSyntax.PREFIX_LESSON_PRICE + "911.123"; // too many decimal places

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditStudentDescriptor DESC_AMY;
    public static final EditStudentCommand.EditStudentDescriptor DESC_BOB;
    public static final EditLessonCommand.EditLessonDescriptor DESC_MATH;
    public static final EditLessonCommand.EditLessonDescriptor DESC_SCIENCE;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_AMY)
                .withStudentPhone(VALID_STUDENT_PHONE_AMY)
                .withParentPhone(VALID_PARENT_PHONE_AMY)
                .withParentName(VALID_PARENT_NAME_AMY)
                .build();
        DESC_BOB = new EditStudentDescriptorBuilder()
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .withParentPhone(VALID_PARENT_PHONE_BOB)
                .withParentName(VALID_PARENT_NAME_BOB)
                .build();
        DESC_MATH = new EditLessonDescriptorBuilder()
                .withLessonName(VALID_LESSON_NAME_MATHS_TWO)
                .withCapacity(VALID_CAPACITY_MATHS_TWO)
                .withPrice(VALID_PRICE_MATHS_TWO)
                .withTiming(VALID_TIMING_MATHS_TWO)
                .build();
        DESC_SCIENCE = new EditLessonDescriptorBuilder()
                .withLessonName(VALID_LESSON_NAME_SCIENCE_TWO)
                .withCapacity(VALID_CAPACITY_SCIENCE_TWO)
                .withPrice(VALID_PRICE_SCIENCE_TWO)
                .withTiming(VALID_TIMING_SCIENCE_TWO)
                .build();
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
     * - the student book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudentBook expectedStudentBook = new StudentBook(actualModel.getStudentBook());
        List<Student> expectedFilteredStudentList = new ArrayList<>(actualModel.getFilteredStudentList());

        LessonBook expectedLessonBook = new LessonBook(actualModel.getLessonBook());
        List<Lesson> expectedFilteredLessonList = new ArrayList<>(actualModel.getFilteredLessonList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudentBook, actualModel.getStudentBook());
        assertEquals(expectedFilteredStudentList, actualModel.getFilteredStudentList());
        assertEquals(expectedLessonBook, actualModel.getLessonBook());
        assertEquals(expectedFilteredLessonList, actualModel.getFilteredLessonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s student book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getStudentName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the lesson at the given {@code targetIndex} in the
     * {@code model}'s lesson book.
     */
    public static void showLessonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLessonList().size());
        Lesson lesson = model.getFilteredLessonList().get(targetIndex.getZeroBased());
        final String[] splitName = lesson.getLessonName().lessonName.split("\\s+");
        model.updateFilteredLessonList(new LessonNameContainsKeywordsPredicate(List.of(splitName[0])));

        assertEquals(1, model.getFilteredLessonList().size());
    }

}
