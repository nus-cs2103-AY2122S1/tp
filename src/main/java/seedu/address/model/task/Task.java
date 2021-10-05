package seedu.address.model.task;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

public class Task {
    private final Description desc;
    private final DateTime dateTime;
    private final boolean isDone;
    private final Set<Name> relatedNames = new HashSet<>();

    /**
     * Creates a Task object.
     *
     * @param desc                      the description of the task
     * @param dt                        the date & time of the task
     * @param names                     the names of people associated with the task
     * @return                          task created
     */
    public Task(Description desc, DateTime dt, Set<Name> names) {
        this.desc = desc;
        this.dateTime = dt;
        this.isDone = false;
        this.relatedNames.addAll(names);
    }


    /**
     * Returns task description of this task.
     *
     * @return task description
     */
    public Description getDesc() {
        return desc;
    }

    /**
     * Returns set date of this task.
     *
     * @return task description
     */
    public DateTime getDateTime() {
        return dateTime;
    }

    public Set<Name> getRelatedNames() {
        return relatedNames;
    }

//    /**
//     * Returns set of person objects related to this task.
//     *
//     * @param book                      address book that stores this task
//     * @return                          task description
//     */
//    public Set<Person> getRelatedPeople(AddressBook book) {
//        Set<Person> relatedPeople = new HashSet<>();
//        for (Name name: relatedNames) {
//            relatedPeople.add(book.getPerson(name));
//        }
//        return relatedPeople;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return other.getDesc().equals(this.desc)
                    && other.getDateTime().equals(this.dateTime)
                    && other.getRelatedNames().equals(this.relatedNames)
                    && other.isDone == this.isDone;
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDesc())
                .append("; When: ")
                .append(getDateTime());
        if (!relatedNames.isEmpty()) {
            builder.append("; People: ");
            relatedNames.forEach(builder::append);
        }
        return builder.toString();
    }
}
