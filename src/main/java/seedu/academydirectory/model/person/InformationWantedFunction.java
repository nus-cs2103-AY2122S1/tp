package seedu.academydirectory.model.person;

import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Function;

import seedu.academydirectory.logic.parser.Prefix;

public class InformationWantedFunction implements Function<Person, Information> {
    private final Prefix prefix;

    public InformationWantedFunction(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public Information apply(Person person) {
        if (PREFIX_EMAIL.equals(prefix)) {
            return person.getEmail();
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return person.getAddress();
        } else if (PREFIX_PHONE.equals(prefix)) {
            return person.getPhone();
        }
        return null;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InformationWantedFunction // instanceof handles nulls
                && prefix.equals(((InformationWantedFunction) other).prefix)); // state check
    }
}
