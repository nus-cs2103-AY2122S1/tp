package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Predicate to check if the person contains the input fields.
 */
public class PersonContainsFieldsPredicate implements Predicate<Person> {

    private ArrayList<Field> fields = new ArrayList<>();

    /**
     * Constructor for a predicate to check if a person has a
     * group of fields.
     * @param fields The fields to check.
     */
    public PersonContainsFieldsPredicate(Field... fields) {
        for (Field field : fields) {
            this.fields.add(field);
        }
    }

    /**
     * Add a field to the predicate to test.
     * @param field The field to test.
     */
    public void addFieldToTest(Optional<? extends Field> field) {
        if (!field.isPresent()) {
            return;
        }
        this.fields.add(field.get());
    }

    @Override
    public boolean test(Person person) {
        return person.containsFields(this.fields);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
                && obj instanceof PersonContainsFieldsPredicate
                && this.fields.containsAll(((PersonContainsFieldsPredicate) obj).fields)
                && ((PersonContainsFieldsPredicate) obj).fields.containsAll(this.fields); //check equality
    }
}
