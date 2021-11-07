package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSportsPa.getTypicalSportsPa;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SportsPa;
import seedu.address.model.UserPrefs;
import seedu.address.model.member.Member;
import seedu.address.testutil.MemberBuilder;


class ClearAttendanceCommandTest {

    @Test
    public void execute_someMembersPresent_success() {
        Model model = new ModelManager(new SportsPa(getTypicalSportsPa()), new UserPrefs());
        Member member = new MemberBuilder().withTodayAttendance(true).build();
        model.addMember(member);

        ClearAttendanceCommand command = new ClearAttendanceCommand();
        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.resetTodayAttendance();

        assertCommandSuccess(command, model, ClearAttendanceCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_allMembersAbsent_success() {
        Model model = new ModelManager(new SportsPa(getTypicalSportsPa()), new UserPrefs());

        ClearAttendanceCommand command = new ClearAttendanceCommand();
        Model expectedModel = new ModelManager(model.getSportsPa(), new UserPrefs());
        expectedModel.resetTodayAttendance();

        assertCommandSuccess(command, model, ClearAttendanceCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
