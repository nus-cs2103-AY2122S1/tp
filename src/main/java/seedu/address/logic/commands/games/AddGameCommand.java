//package seedu.address.logic.commands.games;
//
//import static java.util.Objects.requireNonNull;
//import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
//
//import seedu.address.logic.commands.Command;
//import seedu.address.logic.commands.CommandResult;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.logic.parser.CliSyntax;
//import seedu.address.model.Model;
//import seedu.address.model.friend.Friend;
//import seedu.address.model.friend.FriendName;
//
//public class AddGameCommand extends Command {
//    public static final String COMMAND_WORD = "--add";
//    public static final FriendName DEFAULT_FRIEND_NAME = new FriendName("No name assigned");
//    // add command messages
//    public static final String MESSAGE_SUCCESS_ADD_GAME = "New friend added - %1$s";
//    public static final String MESSAGE_DUPLICATE_GAME = "This friend already exists in the gitGud friends list.";
//    public static final String MESSAGE_USAGE = "To add a friend: \n"
//            + COMMAND_WORD
//            + " FRIEND_ID [--name NAME]: "
//            + "Adds a friend to the gitGud friends list. \n"
//            + "Parameters: "
//            + "FRIEND_ID ["
//            + CliSyntax.FLAG_FRIEND_NAME
//            + " NAME]\n"
//            + "Example: "
//            + COMMAND_WORD + " "
//            + "myfeely923 "
//            + FLAG_FRIEND_NAME
//            + "Yu Zher ";
//    private final Friend toAdd;
//
//    /**
//     * Constructor for AddFriendCommand that takes in the friend to add as the argument.
//     * @param friend The friend to be added.
//     */
//    public AddGameCommand(Friend friend) {
//        requireNonNull(friend);
//        this.toAdd = friend;
//    }
//
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//
//        if (model.hasFriendId(toAdd.getFriendId())) {
//            throw new CommandException(MESSAGE_DUPLICATE_GAME);
//        }
//
//        model.addFriend(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS_ADD_GAME, toAdd));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof seedu.address.logic.commands.friends.AddFriendCommand // instanceof handles nulls
//                && toAdd.equals(((seedu.address.logic.commands.friends.AddFriendCommand) other).toAdd));
//    }
//}
