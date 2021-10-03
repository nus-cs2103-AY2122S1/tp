package seedu.tracker.logic.commands;

//import seedu.tracker.logic.commands.exceptions.CommandException;
//import seedu.tracker.model.Model;
//import seedu.tracker.model.ModuleTracker;
//import seedu.tracker.model.module.Module;
//
//import java.util.ArrayList;
//import java.util.List;


import static seedu.tracker.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_MC;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TITLE;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_CODE_CS1101S = "CS1101S";
    public static final String VALID_TITLE_CS1101S = "Programming Methodology";
    public static final String VALID_DESCRIPTION_CS1101S = "This module introduces the concepts of programming and "
            + "computational problem solving, and is the first and foremost introductory module to computing. Starting "
            + "from a small core of fundamental abstractions, the module introduces programming as a method for "
            + "communicating computational processes. The module begins with purely functional programming based on "
            + "a simple substitution-based execution model, and ends with a powerful modern imperative language based "
            + "on a realistic environment-based execution model. Topics covered include: functional abstraction, "
            + "recursion, higher-order functions, data abstraction, algorithmic strategies, state mutation, "
            + "loops and arrays, evaluation strategies, sorting and searching, debugging and testing.";
    public static final int VALID_MC_CS1101S = 4;


    public static final String VALID_CODE_CP3108A = "CP3108A";
    public static final String VALID_TITLE_CP3108A = "Independent Work";
    public static final String VALID_DESCRIPTION_CP3108A = "";
    public static final int VALID_MC_CP3108A = 2;

    public static final String VALID_CODE_CS2100 = "CS2100";
    public static final String VALID_TITLE_CS2100 = "Computer Organisation";
    public static final String VALID_DESCRIPTION_CS2100 = "";
    public static final int VALID_MC_CS2100 = 4;

    public static final String VALID_TAG_CORE = "core";
    public static final String VALID_TAG_UE = "ue";

    public static final String TITLE_DESC_CP3108A = " " + PREFIX_TITLE + VALID_TITLE_CP3108A;
    public static final String TITLE_DESC_CS1101S = " " + PREFIX_TITLE + VALID_TITLE_CS1101S;
    public static final String CODE_DESC_CP3108A = " " + PREFIX_CODE + VALID_CODE_CP3108A;
    public static final String CODE_DESC_CS1101S = " " + PREFIX_CODE + VALID_CODE_CS1101S;
    public static final String DESCRIPTION_DESC_CP3108A = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CP3108A;
    public static final String DESCRIPTION_DESC_CS1101S = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_CS1101S;
    public static final String MC_DESC_CP3108A = " " + PREFIX_MC + VALID_MC_CP3108A;
    public static final String MC_DESC_CS1101S = " " + PREFIX_MC + VALID_MC_CS1101S;
    public static final String TAG_DESC_CORE = " " + PREFIX_TAG + VALID_TAG_CORE;
    public static final String TAG_DESC_UE = " " + PREFIX_TAG + VALID_TAG_UE;

    public static final String INVALID_CODE_DESC = " " + PREFIX_CODE + "CS2100&"; // '&' not allowed in codes
    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + " "; // titles not allowed be blank
    public static final String INVALID_MC_DESC = " " + PREFIX_MC + "-4"; // mcs not allowed be negative
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "ge*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    //    static {
    //        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
    //                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
    //                .withTags(VALID_TAG_FRIEND).build();
    //        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
    //                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    //    }
    //
    //    /**
    //     * Executes the given {@code command}, confirms that <br>
    //     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
    //     * - the {@code actualModel} matches {@code expectedModel}
    //     */
    //    public static void assertCommandSuccess(Command command, Model actualModel,
    //    CommandResult expectedCommandResult,Model expectedModel) {
    //        try {
    //            CommandResult result = command.execute(actualModel);
    //            assertEquals(expectedCommandResult, result);
    //            assertEquals(expectedModel, actualModel);
    //        } catch (CommandException ce) {
    //            throw new AssertionError("Execution of command should not fail.", ce);
    //        }
    //    }
    //
    //    /**
    //     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
    //     * that takes a string {@code expectedMessage}.
    //     */
    //    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
    //                                            Model expectedModel) {
    //        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
    //        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    //    }
    //
    //    /**
    //     * Executes the given {@code command}, confirms that <br>
    //     * - a {@code CommandException} is thrown <br>
    //     * - the CommandException message matches {@code expectedMessage} <br>
    //     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
    //     */
    //    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
    //        // we are unable to defensively copy the model for comparison later, so we can
    //        // only do so by copying its components.
    //        ModuleTracker expectedModuleTracker = new ModuleTracker(actualModel.getModuleTracker());
    //        List<Module> expectedFilteredList = new ArrayList<>(actualModel.getFilteredModuleList());
    //
    //        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
    //        assertEquals(expectedModuleTracker, actualModel.getModuleTracker());
    //        assertEquals(expectedFilteredList, actualModel.getFilteredModuleList());
    //    }
    //    /**
    //     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
    //     * {@code model}'s module tracker.
    //     */
    //    public static void showModuleAtIndex(Model model, Index targetIndex) {
    //        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());
    //
    //        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
    //        final String[] splitTitle = module.getTitle().fullName.split("\\s+");
    //        model.updateFilteredModuleList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
    //
    //        assertEquals(1, model.getFilteredModuleList().size());
    //    }

}
