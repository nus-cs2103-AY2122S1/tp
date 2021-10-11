package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import safeforhall.commons.core.Messages;
import safeforhall.commons.util.CollectionUtil;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.Name;
import safeforhall.model.person.NameContainsKeywordsPredicate;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

/**
 * Finds and lists all persons in address book whose parameters matched any of the provided argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all residents whose parameters match any of "
            + "the provided keywords for different options (case-insensitive) and displays them as a "
            + "list with index numbers.\n"
            + "Parameters: "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_ROOM + "ROOM] "
            + "[" + CliSyntax.PREFIX_PHONE + "PHONE] "
            + "[" + CliSyntax.PREFIX_EMAIL + "EMAIL] "
            + "[" + CliSyntax.PREFIX_VACCSTATUS + "VACCINATION STATUS] "
            + "[" + CliSyntax.PREFIX_FACULTY + "FACULTY] \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "david li "
            + CliSyntax.PREFIX_VACCSTATUS + "T "
            + CliSyntax.PREFIX_FACULTY + "SoC";

    public static final String MESSAGE_SUCCESS = "Matching residents listed.";
    public static final String MESSAGE_NOT_FILTERED = "At least one field to filter be must be provided.";

    private final FindCompositePredicate predicate;

    public FindCommand(FindCompositePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    /**
     * Stores the predicates to search the address book with. Each non-empty field value will be used for filtering.
     */
    public static class FindCompositePredicate implements Predicate<Person> {
        private Predicate<Person> name;
        private Predicate<Room> room;
        private Predicate<Phone> phone;
        private Predicate<Email> email;
        private Predicate<VaccStatus> vaccStatus;
        private Predicate<Faculty> faculty;

        // For equality checks
        private Name ename;
        private Room eroom;
        private Phone ephone;
        private Email eemail;
        private VaccStatus evaccStatus;
        private Faculty efaculty;

        public FindCompositePredicate() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public FindCompositePredicate(FindCompositePredicate toCopy) {
            this.name = toCopy.name;
            this.room = toCopy.room;
            this.phone = toCopy.phone;
            this.email = toCopy.email;
            this.vaccStatus = toCopy.vaccStatus;
            this.faculty = toCopy.faculty;
            this.ename = toCopy.ename;
            this.eroom = toCopy.eroom;
            this.ephone = toCopy.ephone;
            this.eemail = toCopy.eemail;
            this.evaccStatus = toCopy.evaccStatus;
            this.efaculty = toCopy.efaculty;
        }

        /**
         * Returns true if at least one field is to be filtered with.
         */
        public boolean isAnyFieldFiltered() {
            return CollectionUtil.isAnyNonNull(name, room, phone, email, vaccStatus, faculty);
        }

        public void setName(Name name) {
            this.ename = new Name(String.join(" ", name.fullName.split("\\s+")));
            this.name = new NameContainsKeywordsPredicate(Arrays.asList(name.fullName.split("\\s+")));
        }

        public void setRoom(Room room) {
            this.eroom = room;
            this.room = room::equals;
        }

        public void setPhone(Phone phone) {
            this.ephone = phone;
            this.phone = phone::equals;
        }

        public void setEmail(Email email) {
            this.eemail = email;
            this.email = email::equals;
        }

        public void setVaccStatus(VaccStatus vaccStatus) {
            this.evaccStatus = vaccStatus;
            this.vaccStatus = vaccStatus::equals;
        }

        public void setFaculty(Faculty faculty) {
            this.efaculty = faculty;
            this.faculty = faculty::equals;
        }

        public Optional<Predicate<Person>> getName() {
            return Optional.ofNullable(name);
        }

        private Optional<Predicate<Room>> getRoom() {
            return Optional.ofNullable(room);
        }

        public Optional<Predicate<Phone>> getPhone() {
            return Optional.ofNullable(phone);
        }

        public Optional<Predicate<Email>> getEmail() {
            return Optional.ofNullable(email);
        }

        private Optional<Predicate<VaccStatus>> getVaccStatus() {
            return Optional.ofNullable(vaccStatus);
        }

        private Optional<Predicate<Faculty>> getFaculty() {
            return Optional.ofNullable(faculty);
        }

        @Override
        public boolean test(Person person) {
            List<Predicate<Person>> allPredicates = Arrays.asList(
                p -> getName().orElse(x -> true).test(p),
                p -> getRoom().orElse(x -> true).test(p.getRoom()),
                p -> getPhone().orElse(x -> true).test(p.getPhone()),
                p -> getEmail().orElse(x -> true).test(p.getEmail()),
                p -> getVaccStatus().orElse(x -> true).test(p.getVaccStatus()),
                p -> getFaculty().orElse(x -> true).test(p.getFaculty()));

            return allPredicates
                    .stream()
                    .reduce(p -> true, Predicate::and)
                    .test(person);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindCompositePredicate)) {
                return false;
            }

            // state check
            FindCompositePredicate e = (FindCompositePredicate) other;

            return Objects.equals(ename, e.ename)
                    && Objects.equals(eroom, e.eroom)
                    && Objects.equals(ephone, e.ephone)
                    && Objects.equals(eemail, e.eemail)
                    && Objects.equals(evaccStatus, e.evaccStatus)
                    && Objects.equals(efaculty, e.efaculty);
        }
    }
}
