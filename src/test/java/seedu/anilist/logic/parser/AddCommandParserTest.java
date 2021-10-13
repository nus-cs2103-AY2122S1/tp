package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.anilist.logic.commands.CommandTestUtil.INVALID_GENRE_DESC;
import static seedu.anilist.logic.commands.CommandTestUtil.NAME_DESC_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.NAME_DESC_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.anilist.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.anilist.logic.commands.CommandTestUtil.GENRE_DESC_SHOUNEN;
import static seedu.anilist.logic.commands.CommandTestUtil.GENRE_DESC_SUPERHERO;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SUPERHERO;
import static seedu.anilist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.anilist.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.anilist.testutil.TypicalAnimes.AKIRA;
import static seedu.anilist.testutil.TypicalAnimes.BNHA;

import org.junit.jupiter.api.Test;

import seedu.anilist.logic.commands.AddCommand;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.testutil.AnimeBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Anime expectedAnime = new AnimeBuilder(BNHA).withGenres(VALID_GENRE_SUPERHERO).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BNHA
                + GENRE_DESC_SUPERHERO, new AddCommand(expectedAnime));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AKIRA + NAME_DESC_BNHA
                + GENRE_DESC_SUPERHERO, new AddCommand(expectedAnime));


        // multiple genress - all accepted
        Anime expectedAnimeMultipleGenres = new AnimeBuilder(BNHA).withGenres(VALID_GENRE_SUPERHERO, VALID_GENRE_SHOUNEN)
                .build();
        assertParseSuccess(parser, NAME_DESC_BNHA
                + GENRE_DESC_SHOUNEN + GENRE_DESC_SUPERHERO, new AddCommand(expectedAnimeMultipleGenres));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero genress
        Anime expectedAnime = new AnimeBuilder(AKIRA).withGenres().build();
        assertParseSuccess(parser, NAME_DESC_AKIRA,
                new AddCommand(expectedAnime));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BNHA,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BNHA,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC
                + GENRE_DESC_SHOUNEN + GENRE_DESC_SUPERHERO, Name.MESSAGE_CONSTRAINTS);

        // invalid genres
        assertParseFailure(parser, NAME_DESC_BNHA
                + INVALID_GENRE_DESC + VALID_GENRE_SUPERHERO, Genre.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BNHA
                + GENRE_DESC_SHOUNEN + GENRE_DESC_SUPERHERO,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
