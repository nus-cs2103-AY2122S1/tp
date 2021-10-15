package seedu.anilist.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.anilist.testutil.Assert.assertThrows;
import static seedu.anilist.testutil.TypicalAnimes.BNHA;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_FIRST_ANIME;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.anilist.logic.commands.AddCommand;
import seedu.anilist.logic.commands.ClearCommand;
import seedu.anilist.logic.commands.DeleteCommand;
import seedu.anilist.logic.commands.EditCommand;
import seedu.anilist.logic.commands.ExitCommand;
import seedu.anilist.logic.commands.FindCommand;
import seedu.anilist.logic.commands.GenreAddCommand;
import seedu.anilist.logic.commands.GenreCommand;
import seedu.anilist.logic.commands.GenreDeleteCommand;
import seedu.anilist.logic.commands.HelpCommand;
import seedu.anilist.logic.commands.ListCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.NameContainsKeywordsPredicate;
import seedu.anilist.testutil.AnimeBuilder;
import seedu.anilist.testutil.AnimeUtil;
import seedu.anilist.testutil.EditAnimeDescriptorBuilder;
import seedu.anilist.testutil.GenresDescriptorBuilder;

public class AnimeListParserTest {

    private final AnimeListParser parser = new AnimeListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Anime anime = new AnimeBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(AnimeUtil.getAddCommand(anime));
        assertEquals(new AddCommand(anime), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_ANIME.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_ANIME), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Anime anime = new AnimeBuilder().build();
        EditCommand.EditAnimeDescriptor descriptor = new EditAnimeDescriptorBuilder(anime).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ANIME.getOneBased() + " " + AnimeUtil.getEditAnimeDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_ANIME, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_genreAdd() throws Exception {
        GenreCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder().withGenre(BNHA.getGenres()).build();
        GenreCommand command = (GenreCommand) parser.parseCommand(GenreCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ANIME.getOneBased() + " " + "c/add " + AnimeUtil.getGenreDescriptorDetails(descriptor));

        GenreCommand expectedCommand = new GenreAddCommand(INDEX_FIRST_ANIME, descriptor);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_genreDelete() throws Exception {
        GenreCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder().withGenre(BNHA.getGenres()).build();
        GenreCommand command = (GenreCommand) parser.parseCommand(GenreCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ANIME.getOneBased() + " " + "c/delete "
                + AnimeUtil.getGenreDescriptorDetails(descriptor));

        assertEquals(new GenreDeleteCommand(INDEX_FIRST_ANIME, descriptor), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
