package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;
import static seedu.address.testutil.TypicalMembers.getTypicalEzFoodie;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.model.member.Point;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RedeemCommand}.
 */
public class RedeemCommandTest {

    private Model model = new ModelManager(new Account(), getTypicalEzFoodie(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws ParseException {
        Member memberToEdit = model.getUpdatedMemberList().get(INDEX_FIRST_MEMBER.getZeroBased());
        Member editedMember = new MemberBuilder(memberToEdit)
                .withPoint(String.valueOf(memberToEdit.getPoint().getIntValue() - 100)).build();

        List<Point> points = new ArrayList<>();
        points.add(ParserUtil.parsePoint("100"));
        RedeemCommand redeemCommand = new RedeemCommand(points, INDEX_FIRST_MEMBER);

        String expectedMessage = String.format(RedeemCommand.MESSAGE_SUCCESS_REDEMPTION, editedMember);

        Model expectedModel = new ModelManager(new Account(), new EzFoodie(model.getEzFoodie()), new UserPrefs());
        expectedModel.setMember(memberToEdit, editedMember);

        assertCommandSuccess(redeemCommand, model, expectedMessage, expectedModel);
    }
}
