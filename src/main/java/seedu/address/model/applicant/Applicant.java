package seedu.address.model.applicant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.applicant.applicantparticulars.ApplicantParticulars;
import seedu.address.model.application.Application;
import seedu.address.model.application.Application.ApplicationStatus;
import seedu.address.model.position.Position;

/**
 * Represents an Applicant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Application application;

    /**
     * Every field must be present and not null.
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Position position) {
        this(name, phone, email, address, new Application(position));
    }


    /**
     * Constructor for an applicant given the applicant's particulars.
     */
    public Applicant(ApplicantParticulars applicantParticulars, Position position) {
        this(
                applicantParticulars.getName(),
                applicantParticulars.getPhone(),
                applicantParticulars.getEmail(),
                applicantParticulars.getAddress(),
                new Application(position)
        );
    }

    /**
     * Internal constructor for a new Applicant object.
     */
    public Applicant(Name name, Phone phone, Email email, Address address, Application application) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.application = application;
    }

    /**
     * Marks the application with the specified application status.
     */
    public Applicant markAs(ApplicationStatus applicationStatus) {
        return new Applicant(name, phone, email, address, application.markAs(applicationStatus));
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Application getApplication() {
        return application;
    }

    /**
     * Returns true if this applicant is applying to the given position.
     */
    public boolean isApplyingTo(Position position) {
        return application.getPosition().equals(position);
    }

    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && name.equals(otherApplicant.name);
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return name.equals(otherApplicant.name)
                && phone.equals(otherApplicant.phone)
                && email.equals(otherApplicant.email)
                && address.equals(otherApplicant.address)
                && application.equals(otherApplicant.application);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, application);
    }

    @Override
    public String toString() {
        return name
                + "; Phone: "
                + phone
                + "; Email: "
                + email
                + "; Address: "
                + address
                + "; Application: "
                + application;
    }

    public String getApplicationSummary() {
        return "Applied for: " + application.getDescription();
    }
}
