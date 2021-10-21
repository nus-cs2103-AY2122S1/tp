package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUPNAME_DESC_G1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REPONAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_YEAR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REPONAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.REPONAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_G1;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_G1;
import static seedu.address.logic.commands.CommandTestUtil.YEAR_DESC_G2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GROUP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddGithubGroupCommand;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.LinkYear;

public class AddGithubGroupCommandParserTest {
    private AddGithubGroupCommandParser parser = new AddGithubGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        LinkYear expectedYear = new LinkYear(VALID_YEAR_G1);
        RepoName expectedRepoName = new RepoName(VALID_REPONAME_AMY);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST_GROUP.getOneBased() + YEAR_DESC_G1
                + REPONAME_DESC_AMY,
                new AddGithubGroupCommand(INDEX_FIRST_GROUP, expectedYear, expectedRepoName));

        // multiple years - last year accepted
        assertParseSuccess(parser, INDEX_FIRST_GROUP.getOneBased() + YEAR_DESC_G2 + YEAR_DESC_G1
                        + REPONAME_DESC_AMY,
                new AddGithubGroupCommand(INDEX_FIRST_GROUP, expectedYear, expectedRepoName));

        // multiple reponames - last name accepted
        assertParseSuccess(parser, INDEX_FIRST_GROUP.getOneBased() + YEAR_DESC_G1 + REPONAME_DESC_BOB
                        + REPONAME_DESC_AMY,
                new AddGithubGroupCommand(INDEX_FIRST_GROUP, expectedYear, expectedRepoName));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGithubGroupCommand.MESSAGE_USAGE);

        // missing year prefix
        assertParseFailure(parser, INDEX_FIRST_GROUP.getOneBased() + VALID_YEAR_G1 + REPONAME_DESC_AMY,
                expectedMessage);

        // missing reponame prefix
        assertParseFailure(parser, INDEX_FIRST_GROUP.getOneBased() + YEAR_DESC_G1 + VALID_REPONAME_AMY,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid year name
        assertParseFailure(parser, INDEX_FIRST_GROUP.getOneBased() + INVALID_YEAR_DESC + REPONAME_DESC_AMY,
                LinkYear.MESSAGE_CONSTRAINTS);

        // invalid repo name
        assertParseFailure(parser, INDEX_FIRST_GROUP.getOneBased() + YEAR_DESC_G1 + INVALID_REPONAME_DESC,
                RepoName.MESSAGE_CONSTRAINTS);

        // invalid index
        assertParseFailure(parser, "a" + VALID_YEAR_G1 + VALID_REPONAME_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGithubGroupCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + INDEX_FIRST_STUDENT.getOneBased() + GROUPNAME_DESC_G1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGithubGroupCommand.MESSAGE_USAGE));
    }
}
