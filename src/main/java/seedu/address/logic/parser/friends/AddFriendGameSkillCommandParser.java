package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD_GAME_SKILL;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_VALUE;

import seedu.address.logic.commands.friends.AddFriendGameSkillCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.SkillValue;

public class AddFriendGameSkillCommandParser implements Parser<AddFriendGameSkillCommand> {

    @Override
    public AddFriendGameSkillCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, FLAG_ADD_GAME_SKILL,
                FLAG_GAME, FLAG_VALUE);

        if (!ParserUtil.areFlagsPresent(argumentMultimap, FLAG_ADD_GAME_SKILL, FLAG_GAME, FLAG_VALUE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddFriendGameSkillCommand.MESSAGE_USAGE));
        }
        FriendId friendId = ParserUtil.parseFriendId(argumentMultimap.getValue(FLAG_ADD_GAME_SKILL).get());
        GameId gameId = ParserUtil.parseGameId(argumentMultimap.getValue(FLAG_GAME).get());
        SkillValue skillValue = ParserUtil.parseSkillValue(argumentMultimap.getValue(FLAG_VALUE).get());

        return new AddFriendGameSkillCommand(friendId, gameId, skillValue);
    }
}
