package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.parser.ParserCheckedFunction;
import seedu.address.logic.parser.exceptions.ParseException;

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
     * Add a field to the predicate to test from a string.
     * @param fieldString The field to test.
     * @param func The function to create the field.
     */
    public void addFieldToTest(Optional<? extends String> fieldString,
                               ParserCheckedFunction<String, Field> func) throws ParseException {
        if (!fieldString.isPresent()) {
            return;
        }
        this.fields.add(func.apply(fieldString.get()));
    }

    /**
     * Adds the {@code field} to the predicate test conditions.
     */
    public void addFieldToTest(Field field) {
        requireNonNull(field);
        this.fields.add(field);
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
