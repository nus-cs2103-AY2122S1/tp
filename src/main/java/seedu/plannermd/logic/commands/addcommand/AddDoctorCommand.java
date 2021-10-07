package seedu.plannermd.logic.commands.addcommand;

import seedu.plannermd.logic.commands.CommandResult;
import seedu.plannermd.logic.commands.exceptions.CommandException;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.doctor.Doctor;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_BIRTH_DATE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_RISK;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_TAG;

public class AddDoctorCommand extends AddCommand{

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a doctor to PlannerMD "
            + "Parameters: " + PREFIX_NAME + "NAME " + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS " + "[" + PREFIX_TAG + "TAG]... " + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe " + PREFIX_PHONE + "98765432 " + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 " + PREFIX_BIRTH_DATE + "31/09/2021 " + PREFIX_TAG
            + "friends " + PREFIX_TAG + "owesMoney " + PREFIX_RISK + "LOW";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in PlannerMD";

    private final Doctor toAdd;

    /**
     * Creates an AddPatientCommand to add the specified {@code Patient}.
     */
    public AddDoctorCommand(Doctor doctor) {
        requireNonNull(doctor);
        toAdd = doctor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

//        if (model.hasDoctor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
//        }
//
//        model.addDoctor(toAdd);
//        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDoctorCommand // instanceof handles nulls
                && toAdd.equals(((AddDoctorCommand) other).toAdd));
    }
}

