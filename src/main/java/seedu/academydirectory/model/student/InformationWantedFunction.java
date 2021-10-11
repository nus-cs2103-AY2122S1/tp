package seedu.academydirectory.model.student;

import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.function.Function;

import seedu.academydirectory.logic.parser.Prefix;

public class InformationWantedFunction implements Function<Student, Information> {
    private final Prefix prefix;

    public InformationWantedFunction(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public Information apply(Student student) {
        if (PREFIX_EMAIL.equals(prefix)) {
            return student.getEmail();
        } else if (PREFIX_TELEGRAM.equals(prefix)) {
            return student.getTelegram();
        } else if (PREFIX_PHONE.equals(prefix)) {
            return student.getPhone();
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
