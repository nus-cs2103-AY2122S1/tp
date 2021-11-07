package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.logic.parser.Prefix;
import seedu.academydirectory.model.AdditionalInfo;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.NameContainsKeywordsPredicate;
import seedu.academydirectory.model.student.PersonalDetail;
import seedu.academydirectory.model.student.Student;

/**
 * Get personal detail of a student or students.
 */
public class GetCommand extends Command {
    public static final String COMMAND_WORD = "get";

    public static final String HELP_MESSAGE = "#### Getting personal detail of students': `get`\n"
            + "\n"
            + "Gets personal detail of student/s. Personal detail refers to:\n"
            + "- Telegram Handle\n"
            + "- Email address\n"
            + "- Telephone number, if any\n\n"
            + "Format: `get [p/] [e/] [te/] [n/NAME]`\n\n"
            + "* At least one of the supported prefix i.e. `p/`, `e/` or `te/` must be passed in.\n"
            + "* If `NAME` is provided, only personal detail of the student/s whose name contain the given `NAME` "
            + "will be shown.\n"
            + "The behavior here is similar to the filter command\n"
            + "* If `NAME` is not provided, the personal detail of all students in AcademyDirectory will be shown\n"
            + "* Multiple prefixes can be supplied at the same time, and personal detail corresponding to all the"
            + " given prefixes will be shown\n"
            + "* The order in which personal details will be shown is as follows, regardless of the order of the\n"
            + "prefixes passed to `get` command:\n"
            + "  * Phone Number (p/)\n"
            + "  * Email (e/)\n"
            + "  * Telegram Handle (te/)\n\nExamples:\n"
            + "* `get p/` shows the phone number of all students in Academy Directory\n"
            + "* `get p/ e/ te/` shows the phone number, email,"
            + " and telegram handle of all students in Academy Directory\n"
            + "* `get p/ n/alex` shows the phone number of all students in Academy Directory whose name has `alex`\n"
            + "* `get p/ e/ te/ n/alex` show the phone number, email, "
            + "and telegram handle of all students in Academy Directory\n"
            + "whose name contains `alex`";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Get and display the personal details of students.\n"
            + "Parameters: " + PREFIX_EMAIL + " | "
            + PREFIX_TELEGRAM + " | " + PREFIX_PHONE + "\n"
            + "Type in `help get` for more details.\n";
    public static final String MESSAGE_SUCCESS = "Personal details retrieval is successful";
    public static final String MESSAGE_FAILED = "Failed to receive one or more personal details. Showing what I can...";
    public static final String MESSAGE_NOTHING_TO_SHOW = "Nothing to show...";
    public static final List<Prefix> SUPPORTED_PREFIX = List.of(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);

    private final List<PersonalDetailRetriever> functionList;

    private final NameContainsKeywordsPredicate nameContainsKeywordsPredicate;

    /**
     * Constructs a GetCommand to retrieve PersonalDetail of students or a student. For GetCommand
     * to operate on all students, pass an empty list to keywordList. Otherwise, GetCommand will operate only on
     * Student objects whose Name contains the keyword in keywordList. This behavior is similar to {@code FilterCommand}
     * @param prefixList list of Prefixes for the PersonalDetail to be retrieved
     * @param keywordList list of keywords to match with names of students in the model
     */
    public GetCommand(List<Prefix> prefixList, List<String> keywordList) {
        requireNonNull(prefixList);
        requireNonNull(keywordList);

        // Checks done at GetCommandParser
        assert !prefixList.isEmpty();

        this.nameContainsKeywordsPredicate = keywordList.isEmpty()
                || (keywordList.size() == 1 && keywordList.contains(""))
                ? null
                : new NameContainsKeywordsPredicate(keywordList);

        this.functionList = prefixList
                .stream()
                .filter(SUPPORTED_PREFIX::contains) // only accepts prefixes that are supported
                .map(PersonalDetailRetriever::new)
                .collect(Collectors.toList());
    }

    private String executeFilter(VersionedModel model, PersonalDetailRetriever personalDetailRetriever) {
        ObservableList<PersonalDetail> view = model.getFilteredStudentList()
                .stream().map(personalDetailRetriever)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        return view.size() == 0
                ? null
                : view.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public CommandResult execute(VersionedModel model) {
        requireNonNull(model);
        model.updateFilteredStudentList(nameContainsKeywordsPredicate == null
                ? VersionedModel.PREDICATE_SHOW_ALL_STUDENTS
                : nameContainsKeywordsPredicate);
        List<String> result = functionList.stream().map(x -> executeFilter(model, x))
                .collect(Collectors.toList());
        String feedbackMessage = result.contains(null) ? MESSAGE_FAILED : MESSAGE_SUCCESS;
        String resultString = result.stream().allMatch(Objects::isNull)
                ? MESSAGE_NOTHING_TO_SHOW
                : result.stream().filter(x -> !Objects.isNull(x)).collect(Collectors.joining("\n"));
        model.updateFilteredStudentList(VersionedModel.PREDICATE_SHOW_ALL_STUDENTS);

        model.setAdditionalViewType(AdditionalViewType.TEXT);
        model.setAdditionalInfo(AdditionalInfo.of(resultString));
        return new CommandResult(feedbackMessage);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof GetCommand) {
            List<PersonalDetailRetriever> otherList = ((GetCommand) other).functionList;
            List<PersonalDetailRetriever> thisList = this.functionList;

            return thisList.stream().map(otherList::contains).reduce(true, (x, y) -> x && y)
                    &&
                    otherList.stream().map(thisList::contains).reduce(true, (x, y) -> x && y);
        }
        return false;
    }

    /**
     * Obtains desired PersonalDetail among a list of Students
     */
    private static final class PersonalDetailRetriever implements Function<Student, Optional<PersonalDetail>> {
        private final Prefix prefix;

        /**
         * Returns a Function that can be used to obtain the desired {@code PersonalDetail} among a list of Students.
         * @param prefix Prefix of the PersonalDetail that is desired
         */
        public PersonalDetailRetriever(Prefix prefix) {
            requireNonNull(prefix);

            // Guaranteed since PersonalDetailRetriever is only used by GetCommand
            assert SUPPORTED_PREFIX.contains(prefix);
            this.prefix = prefix;
        }

        @Override
        public Optional<PersonalDetail> apply(Student student) {
            if (PREFIX_EMAIL.equals(prefix)) {
                return Optional.of(student.getEmail());
            } else if (PREFIX_TELEGRAM.equals(prefix)) {
                return Optional.of(student.getTelegram());
            } else if (PREFIX_PHONE.equals(prefix)) {
                return student.getPhone().value.isEmpty() ? Optional.empty() : Optional.of(student.getPhone());
            } else {
                return Optional.empty();
            }
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof PersonalDetailRetriever // instanceof handles nulls
                    && prefix.equals(((PersonalDetailRetriever) other).prefix)); // state check
        }
    }
}
