//package seedu.address.model.tag;
//
//import java.util.List;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//import seedu.address.model.guest.Guest;
//
///**
// * Tests that a {@code Person}'s {@code Identifier} matches any of the keywords given.
// */
//public class TagContainsKeywordsPredicate implements Predicate<Person> {
//    private final List<Tag> tags;
//
//    public TagContainsKeywordsPredicate(List<Tag> tags) {
//        this.tags = tags;
//    }
//
//    @Override
//    public boolean test(Person person) {
//        if (tags.isEmpty()) {
//            return false;
//        }
//
//        if (person instanceof Staff) {
//            Staff staff = (Staff) person;
//            return tags.stream()
//                    .filter(tag -> staff.getTags().contains(tag)).collect(Collectors.toList()).size() == tags.size();
//        } else {
//            Guest guest = (Guest) person;
//            return tags.stream()
//                    .filter(tag -> guest.getTags().contains(tag)).collect(Collectors.toList()).size() == tags.size();
//        }
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
//                && tags.equals(((TagContainsKeywordsPredicate) other).tags)); // state check
//    }
//
//    public List<Tag> getTags() {
//        return tags;
//    }
//
//}
