package donnafin.ui.testutil;

import donnafin.commons.core.GuiSettings;
import donnafin.logic.Logic;
import donnafin.logic.commands.AddCommand;
import donnafin.logic.commands.CommandResult;
import donnafin.logic.commands.exceptions.CommandException;
import donnafin.logic.parser.ParserStrategy;
import donnafin.logic.parser.exceptions.ParseException;
import donnafin.model.AddressBook;
import donnafin.model.ReadOnlyAddressBook;
import donnafin.model.person.Person;
import donnafin.testutil.TypicalPersons;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LogicManagerStub implements Logic {

    private Person alice = TypicalPersons.ALICE;
    private AddCommand addCommand = new AddCommand(alice);

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        return new CommandResult(AddCommand.MESSAGE_SUCCESS);
    }

    @Override
    //Should return an empty addressbook
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return null;
    }

    @Override
    public Path getAddressBookFilePath() {
        Path addressBookFilePath = Paths.get("data" , "donnafin.json");
        return addressBookFilePath;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return new GuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        assert guiSettings instanceof GuiSettings: "input parameters wrong";
    }

    @Override
    public void setParserStrategy(ParserStrategy strategyParser) {
        assert strategyParser instanceof ParserStrategy: "input parameters of wrong type";
    }
}
