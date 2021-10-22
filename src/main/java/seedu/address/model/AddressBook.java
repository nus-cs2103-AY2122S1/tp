package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    /* Singleton design, but only accessible internally */
    private static AddressBook addressBook;

    private final UniquePersonList persons;
    private final TaskList tasks;
    private final OrderList orders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tasks = new TaskList();
        orders = new OrderList();
    }

    public AddressBook() {
        addressBook = this;
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
        addressBook = this;
    }

    /**
     * For each person, finds orders associated with the person, and adds up the amount.
     * Creates a ClientTotalOrder for each person.
     *
     * @return an ObservableList of {@code ClientTotalOrder}.
     */
    public static ObservableList<ClientTotalOrder> getClientTotalOrders() {
        ObservableList<ClientTotalOrder> clientTotalOrders = FXCollections.observableArrayList();
        for (Person client : addressBook.persons) {
            clientTotalOrders.add(getClientTotalOrder(client));
        }
        return clientTotalOrders;
    }

    private static ClientTotalOrder getClientTotalOrder(Person client) {
        String clientName = client.getName().toString();
        Predicate<Order> correctClient = (order) -> order.getCustomer().toString().equals(clientName);
        double totalOrder = addressBook.orders.asUnmodifiableObservableList().stream()
                .filter(correctClient)
                .mapToDouble(Order::getAmountAsDouble)
                .sum();
        return new ClientTotalOrder(clientName, totalOrder);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the tasks list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTasks(newData.getTaskList());

    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }


    //// task-level operations

    public void addTask(Task toAdd) {
        tasks.add(toAdd);
    }

    public void deleteTask(Task toDelete) {
        tasks.remove(toDelete);
    }

    public void markDone(Task task) {
        tasks.markDone(task);
    }

    public void setTask(Task target, Task editedtask) {
        tasks.setTask(target, editedtask);
    }

    public boolean hasTask(Task task) {
        return tasks.hasTask(task);
    }

    /// order-level operations

    public void addOrder(Order toAdd) {
        orders.add(toAdd);
    }

    public void deleteOrder(Order toDelete) {
        orders.remove(toDelete);
    }

    public void markOrder(Order order) {
        orders.markComplete(order);
    }

    public void setOrder(Order target, Order editedOrder) {
        orders.setOrder(target, editedOrder);
    }

    public boolean hasOrder(Order order) {
        return orders.hasOrder(order);
    }


    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons; "
                + tasks.asUnmodifiableObservableList().size() + " tasks; "
                + orders.asUnmodifiableObservableList().size() + " orders.";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Order> getOrderList() {
        return orders.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
