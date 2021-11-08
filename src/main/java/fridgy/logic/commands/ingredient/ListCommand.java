package fridgy.logic.commands.ingredient;

import static java.util.Objects.requireNonNull;

import fridgy.logic.commands.Command;
import fridgy.logic.commands.CommandResult;
import fridgy.model.IngredientModel;
import fridgy.model.Model;
import fridgy.model.ingredient.IngredientDefaultComparator;


/**
 * Lists all ingredients in the inventory to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String INGREDIENT_KEYWORD = "ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + INGREDIENT_KEYWORD + ": Lists all ingredients.\n";

    public static final String MESSAGE_SUCCESS = "Listed all ingredients!";


    @Override
    public CommandResult execute(IngredientModel model) {
        requireNonNull(model);
        model.sortIngredient(new IngredientDefaultComparator());
        model.updateFilteredIngredientList(Model.PREDICATE_SHOW_ALL_INGREDIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
