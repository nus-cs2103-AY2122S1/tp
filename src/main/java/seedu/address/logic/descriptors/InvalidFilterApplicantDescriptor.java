package seedu.address.logic.descriptors;

import static seedu.address.model.applicant.ApplicantMatchesFiltersPredicate.passesFilter;

import seedu.address.model.Model;

/**
 * A descriptor used to verify the validity of a FilterApplicantDescriptor.
 */
public class InvalidFilterApplicantDescriptor extends FilterApplicantDescriptor {

    /**
     * Constructor for a InvalidFilterApplicantDescriptor.
     * Verifies the validity of each filter in the specified {@code descriptor} against the given {@code model}.
     */
    public InvalidFilterApplicantDescriptor(Model model, FilterApplicantDescriptor descriptor) {
        super();

        if (!passesFilter(descriptor::getPositionTitle, model::hasPositionWithTitle)) {
            setPositionTitle(descriptor.getPositionTitle().get());
        }
    }

}
