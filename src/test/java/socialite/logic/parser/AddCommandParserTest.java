package socialite.logic.parser;

import org.junit.jupiter.api.Test;

import socialite.commons.core.Messages;
import socialite.logic.commands.AddCommand;
import socialite.logic.commands.CommandTestUtil;
import socialite.model.person.Name;
import socialite.model.person.Person;
import socialite.model.person.Phone;
import socialite.model.tag.Tag;
import socialite.testutil.PersonBuilder;
import socialite.testutil.TypicalPersons;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson =
                new PersonBuilder(TypicalPersons.BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.REMARK_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.FACEBOOK_DESC_BOB + CommandTestUtil.INSTAGRAM_DESC_BOB
                + CommandTestUtil.TELEGRAM_DESC_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                + CommandTestUtil.TWITTER_DESC_BOB + CommandTestUtil.DATE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.REMARK_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.FACEBOOK_DESC_BOB + CommandTestUtil.INSTAGRAM_DESC_BOB
                + CommandTestUtil.TELEGRAM_DESC_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                + CommandTestUtil.TWITTER_DESC_BOB + CommandTestUtil.DATE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.REMARK_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.FACEBOOK_DESC_BOB + CommandTestUtil.INSTAGRAM_DESC_BOB
                + CommandTestUtil.TELEGRAM_DESC_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                + CommandTestUtil.TWITTER_DESC_BOB + CommandTestUtil.DATE_DESC_BOB,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(TypicalPersons.BOB)
                .withTags(CommandTestUtil.VALID_TAG_FRIEND, CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.REMARK_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.FACEBOOK_DESC_BOB + CommandTestUtil.INSTAGRAM_DESC_BOB
                + CommandTestUtil.TELEGRAM_DESC_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                + CommandTestUtil.TWITTER_DESC_BOB + CommandTestUtil.DATE_DESC_BOB,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(TypicalPersons.AMY).withTags().build();
        CommandParserTestUtil.assertParseSuccess(parser, CommandTestUtil.NAME_DESC_AMY
                        + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.REMARK_DESC_AMY
                        + CommandTestUtil.FACEBOOK_DESC_AMY + CommandTestUtil.INSTAGRAM_DESC_AMY
                        + CommandTestUtil.TELEGRAM_DESC_AMY + CommandTestUtil.TIKTOK_DESC_AMY
                        + CommandTestUtil.TWITTER_DESC_AMY + CommandTestUtil.DATE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_HELP_GUIDE);

        // missing name prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                        + CommandTestUtil.TWITTER_DESC_BOB , expectedMessage);

        // missing phone prefix
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.VALID_PHONE_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                        + CommandTestUtil.TWITTER_DESC_BOB, expectedMessage);

        // all prefixes missing
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.VALID_PHONE_BOB + CommandTestUtil.TIKTOK_DESC_BOB
                        + CommandTestUtil.TWITTER_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TIKTOK_DESC_BOB
                + CommandTestUtil.TWITTER_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TIKTOK_DESC_BOB
                + CommandTestUtil.TWITTER_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid tag
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.REMARK_DESC_BOB
                + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.VALID_TAG_FRIEND
                + CommandTestUtil.TIKTOK_DESC_BOB + CommandTestUtil.TWITTER_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.INVALID_NAME_DESC
                        + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.TIKTOK_DESC_BOB
                        + CommandTestUtil.TWITTER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        CommandParserTestUtil.assertParseFailure(parser, CommandTestUtil.PREAMBLE_NON_EMPTY
                        + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND
                        + CommandTestUtil.TIKTOK_DESC_BOB + CommandTestUtil.TWITTER_DESC_BOB,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_HELP_GUIDE));
    }
}
