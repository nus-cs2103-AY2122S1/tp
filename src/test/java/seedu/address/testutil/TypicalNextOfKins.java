package seedu.address.testutil;

import seedu.address.model.participant.Name;
import seedu.address.model.participant.NextOfKin;
import seedu.address.model.participant.Phone;
import seedu.address.model.tag.Tag;

public class TypicalNextOfKins {

    public static final NextOfKin SARAH = new NextOfKin(new Name("Sarah Starlet"),
            new Phone("08203212"), new Tag("Spouse"));

    public static final NextOfKin JACK = new NextOfKin(new Name("Jack Beanstalk"),
            new Phone("08203212"), new Tag("Son"));

    public static final NextOfKin KAN = new NextOfKin(new Name("Kan Doe"),
            new Phone("68009698"), new Tag("Brother"));

    public static final NextOfKin KEN = new NextOfKin(new Name("Ken Lee Tulibudibudouchoo"),
            new Phone("2012017"), new Tag("Unknown"));
}
