//package seedu.address.logic.parser.games;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
//import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
//
//import seedu.address.logic.commands.friends.AddFriendCommand;
//import seedu.address.logic.commands.games.AddGameCommand;
//import seedu.address.logic.parser.ArgumentMultimap;
//import seedu.address.logic.parser.ArgumentTokenizer;
//import seedu.address.logic.parser.Parser;
//import seedu.address.logic.parser.ParserUtil;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.friend.Friend;
//import seedu.address.model.friend.FriendId;
//import seedu.address.model.friend.FriendName;
//import seedu.address.model.game.Game;
//
//public class AddGameCommandParser implements Parser<AddGameCommand> {
//        /**
//         * Parses the given {@code String} of arguments in the context of the AddFriendCommand
//         * and returns an AddFriendCommand object for execution.
//         *
//         * @throws ParseException if the user input does not conform the expected format
//         */
//        @Override
//        public AddGameCommand parse(String args) throws ParseException {
//            // assign friend name
//            ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, FLAG_ADD, FLAG_FRIEND_NAME);
//
//            if (!ParserUtil.areFlagsPresent(argumentMultimap, FLAG_ADD)) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFriendCommand.MESSAGE_USAGE));
//            }
//            FriendName friendName = argumentMultimap.getValue(FLAG_FRIEND_NAME).isPresent()
//                    ? ParserUtil.parseFriendName(argumentMultimap.getValue(FLAG_FRIEND_NAME).get())
//                    : FriendName.DEFAULT_FRIEND_NAME;
//            FriendId friendId = ParserUtil.parseFriendId(argumentMultimap.getValue(FLAG_ADD).get());
//            return new AddGameCommand(new Friend(friendId, friendName));
//        }
//}
