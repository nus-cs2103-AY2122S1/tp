package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsFieldsPredicate;
import seedu.address.stubs.model.ModelStub;
import seedu.address.testutil.TypicalPersons;

public class ViewCommandTest {

    private Model model;


    @Test
    public void execute_success() {
        model = new ReadOnlyModel();
        ViewCommand command = new ViewCommand(new PersonContainsFieldsPredicate());
        Model expectedModel = new ReadOnlyModel();
        assertCommandSuccess(command, model, new CommandResult(String.format(ViewCommand.DEFAULT_COMMAND,
                expectedModel.getFilteredPersonList().toString())), expectedModel);
    }


    private class ReadOnlyModel extends ModelStub {
        private ObservableList<Person> testList;


        public ReadOnlyModel() {
            this.testList = FXCollections.observableArrayList(TypicalPersons.ALICE,
                    TypicalPersons.BENSON, TypicalPersons.CARL);

        }


        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            this.testList = this.testList.filtered(predicate);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return this.testList;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ReadOnlyModel)) {
                return false;
            }
            ReadOnlyModel readOnlyModel = (ReadOnlyModel) obj;
            return readOnlyModel.testList.equals(this.testList);
        }
    }


}
