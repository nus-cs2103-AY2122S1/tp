package ay2122s1_cs2103t_w16_2.btbb.logic.parser;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.Command;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.AddClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.DeleteClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.EditClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.FindClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.ListClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.ExitCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.HelpCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.general.TabCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.AddIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.DeleteIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.EditIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.FindIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.ingredient.ListIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.AddOrderIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DeleteOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DeleteOrderIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.DoneOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.EditOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.FindOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.ListOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.order.UndoneOrderCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.AddRecipeIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.DeleteRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.DeleteRecipeIngredientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.EditRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.FindRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.recipe.ListRecipeCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.client.AddClientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.client.DeleteClientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.client.EditClientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.client.FindClientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.general.TabCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient.AddIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient.DeleteIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient.EditIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.ingredient.FindIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.AddOrderCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.AddOrderIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.DeleteOrderCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.DeleteOrderIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.DoneOrderCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.EditOrderCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.FindOrderCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.order.UndoneOrderCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe.AddRecipeCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe.AddRecipeIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe.DeleteRecipeCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe.DeleteRecipeIngredientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe.EditRecipeCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.recipe.FindRecipeCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddClientCommand.COMMAND_WORD:
            return new AddClientCommandParser().parse(arguments);

        case AddIngredientCommand.COMMAND_WORD:
            return new AddIngredientCommandParser().parse(arguments);

        case AddOrderCommand.COMMAND_WORD:
            return new AddOrderCommandParser().parse(arguments);

        case AddOrderIngredientCommand.COMMAND_WORD:
            return new AddOrderIngredientCommandParser().parse(arguments);

        case AddRecipeCommand.COMMAND_WORD:
            return new AddRecipeCommandParser().parse(arguments);

        case AddRecipeIngredientCommand.COMMAND_WORD:
            return new AddRecipeIngredientCommandParser().parse(arguments);

        case DeleteClientCommand.COMMAND_WORD:
            return new DeleteClientCommandParser().parse(arguments);

        case DeleteIngredientCommand.COMMAND_WORD:
            return new DeleteIngredientCommandParser().parse(arguments);

        case DeleteOrderCommand.COMMAND_WORD:
            return new DeleteOrderCommandParser().parse(arguments);

        case DeleteOrderIngredientCommand.COMMAND_WORD:
            return new DeleteOrderIngredientCommandParser().parse(arguments);

        case DeleteRecipeCommand.COMMAND_WORD:
            return new DeleteRecipeCommandParser().parse(arguments);

        case DeleteRecipeIngredientCommand.COMMAND_WORD:
            return new DeleteRecipeIngredientCommandParser().parse(arguments);

        case DoneOrderCommand.COMMAND_WORD:
            return new DoneOrderCommandParser().parse(arguments);

        case EditClientCommand.COMMAND_WORD:
            return new EditClientCommandParser().parse(arguments);

        case EditIngredientCommand.COMMAND_WORD:
            return new EditIngredientCommandParser().parse(arguments);

        case EditOrderCommand.COMMAND_WORD:
            return new EditOrderCommandParser().parse(arguments);

        case EditRecipeCommand.COMMAND_WORD:
            return new EditRecipeCommandParser().parse(arguments);

        case FindClientCommand.COMMAND_WORD:
            return new FindClientCommandParser().parse(arguments);

        case FindIngredientCommand.COMMAND_WORD:
            return new FindIngredientCommandParser().parse(arguments);

        case FindOrderCommand.COMMAND_WORD:
            return new FindOrderCommandParser().parse(arguments);

        case FindRecipeCommand.COMMAND_WORD:
            return new FindRecipeCommandParser().parse(arguments);

        case ListClientCommand.COMMAND_WORD:
            return new ListClientCommand();

        case ListIngredientCommand.COMMAND_WORD:
            return new ListIngredientCommand();

        case ListOrderCommand.COMMAND_WORD:
            return new ListOrderCommand();

        case ListRecipeCommand.COMMAND_WORD:
            return new ListRecipeCommand();

        case UndoneOrderCommand.COMMAND_WORD:
            return new UndoneOrderCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TabCommand.COMMAND_WORD:
            return new TabCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
