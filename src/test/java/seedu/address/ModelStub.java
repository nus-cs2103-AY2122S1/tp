package seedu.address;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ClientTotalOrder;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * A default model stub that has all of the methods failing. Classed out for tests to reuse
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
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
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getTaskBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setTaskBook(ReadOnlyTaskBook addressBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTask(Task toDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean markTask(Task task) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getOrderBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOrderBookFilePath(Path orderBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOrderBook(ReadOnlyOrderBook orderBook) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyOrderBook getOrderBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean markOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<ClientTotalOrder> getClientTotalOrders() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Order> getFilteredOrderList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOrder(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasOrder(long id) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteOrder(Order toDelete) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void sortOrderList(Comparator<Order> sortDescriptor) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void resetOrderView() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteTaskIf(Predicate<Task> pred) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteOrderIf(Predicate<Order> pred) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void checkClientAndOrderRelation() throws DataConversionException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void checkTaskAndOrderRelation() throws DataConversionException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRelatedTasks(Order order) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPersonWithName(String name) {
        throw new AssertionError("This method should not be called.");
    }
}
