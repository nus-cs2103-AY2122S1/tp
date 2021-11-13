package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.Client.EditClientDescriptor;
import seedu.address.model.client.ClientId;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;
import seedu.address.ui.ThemeType;

public class AddCommandTest {

    @Test
    public void constructor_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_clientAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        EditClientDescriptor validClientFunction = new ClientBuilder().buildFunction();
        Client validClient = new ClientBuilder().build();

        CommandResult commandResult = new AddCommand(validClientFunction).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(List.of(validClient), modelStub.clientsAdded);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        EditClientDescriptor validClientFunction = new ClientBuilder().buildFunction();
        Client validClient = new ClientBuilder().build();
        AddCommand addCommand = new AddCommand(validClientFunction);
        ModelStub modelStub = new ModelStubWithClient(validClient);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_CLIENT, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        EditClientDescriptor alice = new ClientBuilder().withName("Alice").buildFunction();
        EditClientDescriptor bob = new ClientBuilder().withName("Bob").buildFunction();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different client -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);


    }

    /**
     * A default model stub that have all of the methods failing.
     */
    public static class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Path> getAddressBookList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<ThemeType> getThemeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ThemeType getTheme() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTheme(ThemeType theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableValue<Path> getAddressBookFilePathObject() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookDirectory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAddressBookList(Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAddressBookList(Path filePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getAddressBookListString() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClientId(ClientId clientId) {
            return true;
        }

        @Override
        public List<Client> removeAllClients(List<ClientId> clientIds) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Client createClient(EditClientDescriptor client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Client> setAllClients(List<ClientId> clientIds, EditClientDescriptor editedClientDescriptor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Client getClient(ClientId clientId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTagName(String tagName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Tag getTag(String tagName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getSortedNextMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterSortedNextMeetingList(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void filterFilteredClientList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortFilteredClientList(Comparator<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getClientToView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isClientExistToView(ClientId clientId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getNameOfClientToView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Client> retrieveSchedule(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateClientToView(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single client.
     */
    private static class ModelStubWithClient extends ModelStub {
        private final Client client;

        ModelStubWithClient(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public Client createClient(EditClientDescriptor clientDescriptor) {
            Client client = clientDescriptor.createClient(new ClientId("0"));
            if (client.isSameClient(client)) {
                return null;
            }
            return client;
        }
    }

    /**
     * A Model stub that always accept the client being added.
     */
    private static class ModelStubAcceptingClientAdded extends ModelStub {
        final ArrayList<Client> clientsAdded = new ArrayList<>();

        private int count = 0;

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public Client createClient(EditClientDescriptor clientDescriptor) {
            requireNonNull(clientDescriptor);
            Client client = clientDescriptor.createClient(new ClientId(Integer.toString(count++)));
            clientsAdded.add(client);
            return client;
        }
    }
}
