package seedu.address.logic.descriptors;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.application.Application;


/**
 * Stores the details to edit the applicant with. Each non-empty field value will replace the
 * corresponding field value of the position.
 */
public class EditApplicantDescriptor {

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Application application;

    public EditApplicantDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditApplicantDescriptor(EditApplicantDescriptor toCopy) {
        setName(toCopy.name);
        setPhone(toCopy.phone);
        setEmail(toCopy.email);
        setAddress(toCopy.address);
        setApplication(toCopy.application);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, phone, email, address, application);
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

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Optional<Application> getApplication() {
        return Optional.ofNullable(application);
    }

    /**
     * Creates and returns a {@code Applicant} with the details of {@code applicantToEdit}
     * edited with {@code editApplicantDescriptor}.
     */
    public Applicant createEditedApplicant(Applicant applicantToEdit) {
        assert applicantToEdit != null;
        Name updatedName = getName().orElse(applicantToEdit.getName());
        Phone updatedPhone = getPhone().orElse(applicantToEdit.getPhone());
        Email updatedEmail = getEmail().orElse(applicantToEdit.getEmail());
        Address updatedAddress = getAddress().orElse(applicantToEdit.getAddress());
        Application updatedApplication = getApplication().orElse(applicantToEdit.getApplication());

        return new Applicant(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedApplication);
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
                && getAddress().equals(e.getAddress())
                && getApplication().equals(e.getApplication());
    }
}
