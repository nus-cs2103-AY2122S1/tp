package seedu.tracker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_MC;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.tracker.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.model.Model;
import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.NameContainsKeywordsPredicate;
import seedu.tracker.testutil.EditModuleDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_CODE_CS2103T = "CS2103T";
    public static final String VALID_CODE_GEQ1000 = "GEQ1000";
    public static final String VALID_CODE_CS1101S = "CS1101S";
    public static final String VALID_CODE_CS2100 = "CS2100";
    public static final String VALID_CODE_CP3108A = "CP3108A";

    public static final String VALID_TITLE_CS2103T = "Software Engineering";
    public static final String VALID_TITLE_GEQ1000 = "Asking Questions";
    public static final String VALID_TITLE_CS1101S = "Programming Methodology";
    public static final String VALID_TITLE_CP3108A = "Independent work";
    public static final String VALID_DESCRIPTION_CS1101S = "Introduces the concepts of programming and "
            + "computational problem solving";
    public static final String VALID_DESCRIPTION_CP3108A = "Independent work";
    public static final String VALID_DESCRIPTION_CS2103T = "Covers the main areas of software development";
    public static final String VALID_DESCRIPTION_GEQ1000 = "introduces six dominant modes of questioning";
    public static final Integer VALID_MC_CS2103T = 4;
    public static final Integer VALID_MC_GEQ1000 = 4;
    public static final Integer VALID_MC_CP3108A = 2;
    public static final String VALID_TAG_CORE = "core";
    public static final String VALID_TAG_GE = "ge";
    public static final String VALID_TAG_UE = "ue";
    public static final Integer VALID_ACADEMIC_YEAR = 2;
    public static final Integer VALID_SEMESTER = 1;


    public static final String CODE_DESC_CS2103T = " " + PREFIX_CODE + VALID_CODE_CS2103T;
    public static final String CODE_DESC_GEQ1000 = " " + PREFIX_CODE + VALID_CODE_GEQ1000;
    public static final String CODE_DESC_CP3108A = " " + PREFIX_CODE + VALID_CODE_CP3108A;
    public static final String TITLE_DESC_CP3108A = " " + PREFIX_TITLE + VALID_TITLE_CP3108A;
    public static final String TITLE_DESC_CS2103T = " " + PREFIX_TITLE + VALID_TITLE_CS2103T;
    public static final String TITLE_DESC_GEQ1000 = " " + PREFIX_TITLE + VALID_TITLE_GEQ1000;
    public static final String DESCRIPTION_DESC_CS2103T = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS2103T;
    public static final String DESCRIPTION_DESC_GEQ1000 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_GEQ1000;
    public static final String MC_DESC_CS2103T = " " + PREFIX_MC + VALID_MC_CS2103T;
    public static final String MC_DESC_GEQ1000 = " " + PREFIX_MC + VALID_MC_GEQ1000;
    public static final String MC_DESC_CP3108A = " " + PREFIX_MC + VALID_MC_CP3108A;
    public static final String TAG_DESC_CORE = " " + PREFIX_TAG + VALID_TAG_CORE;
    public static final String TAG_DESC_GE = " " + PREFIX_TAG + VALID_TAG_GE;
    public static final String ACADEMIC_YEAR_DESC = " " + PREFIX_ACADEMIC_YEAR + VALID_ACADEMIC_YEAR;
    public static final String SEMESTER_DESC = " " + PREFIX_SEMESTER + VALID_SEMESTER;

    public static final String INVALID_CODE_DESC = " " + PREFIX_CODE + "&&CS2103T"; // '&' not allowed in names
    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE
            + "$$This is a invalid title"; // first char should be alphanumeric
    public static final String INVALID_MC_DESC = " " + PREFIX_MC + "Z"; // Z is not a valid integer
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "core*"; // '*' not allowed in tags
    public static final String INVALID_ACADEMIC_YEAR_DESC =
            " " + PREFIX_ACADEMIC_YEAR + "10"; // 10 is not a valid academic year
    public static final String INVALID_SEMESTER_DESC = " " + PREFIX_SEMESTER + "6"; // 6 is not a valid semester
    public static final String INVALID_ACADEMIC_YEAR_SIGNED_DESC =
            " " + PREFIX_ACADEMIC_YEAR + "+1"; // +1 is not a valid academic year
    public static final String INVALID_SEMESTER_SIGNED_DESC = " "
            + PREFIX_SEMESTER + "-6"; // -6 is not a valid semester

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditModuleDescriptor DESC_CS2103T;
    public static final EditCommand.EditModuleDescriptor DESC_GEQ1000;

    static {
        DESC_CS2103T = new EditModuleDescriptorBuilder().withCode(VALID_CODE_CS2103T)
                .withTitle(VALID_TITLE_CS2103T).withDescription(VALID_DESCRIPTION_CS2103T).withMc(VALID_MC_CS2103T)
                .withTags(VALID_TAG_CORE).build();
        DESC_GEQ1000 = new EditModuleDescriptorBuilder().withCode(VALID_CODE_GEQ1000)
                .withTitle(VALID_TITLE_GEQ1000).withDescription(VALID_DESCRIPTION_GEQ1000).withMc(VALID_MC_GEQ1000)
                .withTags(VALID_TAG_GE).build();
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
     * - the module tracker, filtered module list and selected module in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ModuleTracker expectedModuleTracker = new ModuleTracker(actualModel.getModuleTracker());
        List<Module> expectedFilteredList = new ArrayList<>(actualModel.getFilteredModuleList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedModuleTracker, actualModel.getModuleTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredModuleList());
    }


    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s module tracker.
     */
    public static void showModuleAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());

        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
        final String[] splitName = module.getCode().value.split("\\s+");
        model.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredModuleList().size());
    }
}
