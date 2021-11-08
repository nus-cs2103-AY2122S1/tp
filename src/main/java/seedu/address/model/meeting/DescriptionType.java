package seedu.address.model.meeting;

/**
 * Represents the different types of Description in NewAddressBook
 */
public enum DescriptionType {
    TITLE() {
        @Override
        public String toString() {
            return "Title";
        }
    },
    VENUE() {
        @Override
        public String toString() {
            return "Venue";
        }
    };
}
