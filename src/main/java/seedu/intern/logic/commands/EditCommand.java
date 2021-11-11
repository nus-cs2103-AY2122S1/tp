package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.intern.commons.core.Messages;
import seedu.intern.commons.core.selection.Selection;
import seedu.intern.commons.util.CollectionUtil;
import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;
import seedu.intern.model.applicant.ApplicationStatus;
import seedu.intern.model.applicant.Course;
import seedu.intern.model.applicant.Email;
import seedu.intern.model.applicant.Grade;
import seedu.intern.model.applicant.GraduationYearMonth;
import seedu.intern.model.applicant.Institution;
import seedu.intern.model.applicant.Job;
import seedu.intern.model.applicant.Name;
import seedu.intern.model.applicant.Phone;
import seedu.intern.model.skills.Skill;

/**
 * Edits the details of an existing applicant in Intern Watcher.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the applicant identified "
            + "by the index number in the currently displayed list, or all currently displayed applicants. "
            + "Existing values will be overwritten by the input values.\n"
            + "Index Parameters: INDEX (must be a positive integer)"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_GRADE + "GRADE] "
            + "[" + PREFIX_INSTITUTION + "INSTITUTION] "
            + "[" + PREFIX_COURSE + "COURSE] "
            + "[" + PREFIX_GRADUATIONYEARMONTH + "GRADUATION_YEAR_MONTH] "
            + "[" + PREFIX_JOB + "APPLIED JOB] "
            + "[" + PREFIX_STATUS + "APPLICATION_STATUS] "
            + "[" + PREFIX_SKILL + "SKILL]...\n"
            + "Example index: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com\n"
            + "ALL Parameters: ALL (must be uppercase) "
            + "" + PREFIX_STATUS + "APPLICATION_STATUS \n"
            + "Example all: " + COMMAND_WORD + " ALL "
            + PREFIX_STATUS + "APPLIED";

    public static final String MESSAGE_EDIT_APPLICANT_SUCCESS = "Edited Applicant: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in Intern Watcher.";
    public static final String MESSAGE_EDIT_ALL_SUCCESS = "Successfully edited %d of %d applicants.";
    public static final String MESSAGE_COMMIT_EDIT = "Edit Applicant: %1$s";
    public static final String MESSAGE_COMMIT_EDIT_ALL = "Edit %d applicants.";

    private final Selection selection;
    private final EditApplicantDescriptor editApplicantDescriptor;

    /**
     * Public constructor for {@code EditCommand}.
     * @param selection of the applicant(s) in the filtered applicant list to edit
     * @param editApplicantDescriptor details to edit the applicant with
     */
    public EditCommand(Selection selection, EditApplicantDescriptor editApplicantDescriptor) {
        requireNonNull(selection);
        requireNonNull(editApplicantDescriptor);

        // editApplicant Descriptor should only contain ApplicationStatus if All flag is present
        assert(!selection.hasAllSelectFlag()
                || selection.checkAllSelected()
                && editApplicantDescriptor.getName().isEmpty()
                && editApplicantDescriptor.getCourse().isEmpty()
                && editApplicantDescriptor.getEmail().isEmpty()
                && editApplicantDescriptor.getGrade().isEmpty()
                && editApplicantDescriptor.getGraduationYearMonth().isEmpty()
                && editApplicantDescriptor.getInstitution().isEmpty()
                && editApplicantDescriptor.getSkills().isEmpty()
                && editApplicantDescriptor.getPhone().isEmpty());

        this.selection = selection;
        this.editApplicantDescriptor = new EditApplicantDescriptor(editApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (selection.hasIndex()) {
            if (selection.getIndexZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
            }

            Applicant applicantToEdit = lastShownList.get(selection.getIndexZeroBased());
            Applicant editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

            if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
                throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
            }

            model.setApplicant(applicantToEdit, editedApplicant);
            model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
            model.commitInternWatcher(String.format(MESSAGE_COMMIT_EDIT, editedApplicant));
            return new CommandResult(String.format(MESSAGE_EDIT_APPLICANT_SUCCESS, editedApplicant));
        } else {
            if (!selection.checkAllSelected()) {
                throw new CommandException(Messages.MESSAGE_UNEXPECTED_FLAG);
            }

            int totalApplicants = lastShownList.size();
            int addSuccesses = 0;
            // Create copy of list
            ArrayList<Applicant> targetApplicants = new ArrayList<>(lastShownList);

            for (Applicant applicantToEdit : targetApplicants) {
                Applicant editedApplicant = createEditedApplicant(applicantToEdit, editApplicantDescriptor);

                if (!applicantToEdit.isSameApplicant(editedApplicant) && model.hasApplicant(editedApplicant)) {
                    continue;
                }

                model.setApplicant(applicantToEdit, editedApplicant);
                addSuccesses++;
            }

            model.commitInternWatcher(String.format(MESSAGE_COMMIT_EDIT_ALL, addSuccesses));
            return new CommandResult(String.format(MESSAGE_EDIT_ALL_SUCCESS, addSuccesses, totalApplicants));
        }
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    private static Applicant createEditedApplicant(Applicant applicantToEdit,
                                                   EditApplicantDescriptor editApplicantDescriptor) {
        assert applicantToEdit != null;

        Name updatedName = editApplicantDescriptor.getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = editApplicantDescriptor.getPhone().orElse(applicantToEdit.getPhone());
        Email updatedEmail = editApplicantDescriptor.getEmail().orElse(applicantToEdit.getEmail());
        Grade updatedGrade = editApplicantDescriptor.getGrade().orElse(applicantToEdit.getGrade());
        Institution updatedInstitution = editApplicantDescriptor.getInstitution()
                .orElse(applicantToEdit.getInstitution());
        GraduationYearMonth updatedGraduationYearMonth = editApplicantDescriptor.getGraduationYearMonth()
                .orElse(applicantToEdit.getGraduationYearMonth());
        Course updatedCourse = editApplicantDescriptor.getCourse().orElse(applicantToEdit.getCourse());
        Job updatedJob = editApplicantDescriptor.getJob().orElse(applicantToEdit.getJob());
        ApplicationStatus updatedStatus = editApplicantDescriptor.getApplicationStatus()
                .orElse(applicantToEdit.getApplicationStatus());
        Set<Skill> updatedSkills = editApplicantDescriptor.getSkills().orElse(applicantToEdit.getSkills());


        return new Applicant(updatedName, updatedPhone, updatedEmail,
                updatedGrade, updatedInstitution, updatedCourse,
                updatedGraduationYearMonth, updatedJob, updatedStatus, updatedSkills);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return selection.equals(e.selection)
                && editApplicantDescriptor.equals(e.editApplicantDescriptor);
    }

    public String toString() {
        return editApplicantDescriptor.toString();
    }

    /**
     * Stores the details to edit the applicant with. Each non-empty field value will replace the
     * corresponding field value of the applicant.
     */
    public static class EditApplicantDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Grade grade;
        private Institution institution;
        private GraduationYearMonth graduationYearMonth;
        private Course course;
        private Job job;
        private ApplicationStatus status;
        private Set<Skill> skills;

        public EditApplicantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skill} is used internally.
         */
        public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setGrade(toCopy.grade);
            setInstitution(toCopy.institution);
            setGraduationYearMonth(toCopy.graduationYearMonth);
            setCourse(toCopy.course);
            setJob(toCopy.job);
            setApplicationStatus(toCopy.status);
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(
                    name, phone, email, grade, institution, graduationYearMonth, course, job, status, skills);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        public void setGraduationYearMonth(GraduationYearMonth graduationYearMonth) {
            this.graduationYearMonth = graduationYearMonth;
        }

        public Optional<GraduationYearMonth> getGraduationYearMonth() {
            return Optional.ofNullable(graduationYearMonth);
        }

        public void setInstitution(Institution institution) {
            this.institution = institution;
        }

        public Optional<Institution> getInstitution() {
            return Optional.ofNullable(institution);
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Optional<Course> getCourse() {
            return Optional.ofNullable(course);
        }

        public void setJob(Job job) {
            this.job = job;
        }

        public Optional<Job> getJob() {
            return Optional.ofNullable(job);
        }

        public void setApplicationStatus(ApplicationStatus status) {
            this.status = status;
        }

        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code skill} to this object's {@code skill}.
         * A defensive copy of {@code skill} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skill} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditApplicantDescriptor)) {
                return false;
            }

            // state check
            EditApplicantDescriptor e = (EditApplicantDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getGrade().equals(e.getGrade())
                    && getInstitution().equals(e.getInstitution())
                    && getGraduationYearMonth().equals(e.getGraduationYearMonth())
                    && getCourse().equals(e.getCourse())
                    && getJob().equals(e.getJob())
                    && getApplicationStatus().equals(e.getApplicationStatus())
                    && getSkills().equals(e.getSkills());
        }

        @Override
        public String toString() {
            return "EditApplicantDescriptor{"
                    + "name=" + name
                    + ", phone=" + phone
                    + ", email=" + email
                    + ", grade=" + grade
                    + ", institution=" + institution
                    + ", graduation year month=" + graduationYearMonth
                    + ", course=" + course
                    + ", applied job=" + job
                    + ", application status=" + status
                    + ", skill=" + skills + '}';
        }
    }
}
