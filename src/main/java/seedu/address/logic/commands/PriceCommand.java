package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Price;
import seedu.address.model.person.predicates.StatusEqualsConfirmedPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class PriceCommand extends Command {

    public static final String COMMAND_WORD = "price";

    public static final String MESSAGE_ARGUMENT_USAGE = COMMAND_WORD
            + ": Checks the total price under specified {@code targetTag} in the displayed person list.\n"
            + "Parameters: Either empty or t/TAG\n"
            + "Example 1: " + COMMAND_WORD + "\n"
            + "Example 2: " + COMMAND_WORD + " t/photographer";

    public static final String MESSAGE_TAG_USAGE = COMMAND_WORD
            + ": Checks the total price under specified {@code targetTag} in the displayed person list.\n"
            + "Parameters: TAG (must be a non-empty and non-blank string)\n"
            + "Example: " + COMMAND_WORD + " t/photographer";

    public static final String MESSAGE_TAG_NOT_FOUND_FAILURE = "The following tag(s) you specified is not found:\n %s";
    public static final String MESSAGE_PRICE_SUM_SUCCESS = "Your wedding overall cost is $%.2f";
    public static final String MESSAGE_PRICE_SUM_UNDER_TAG_SUCCESS = "Total price for %s is $%.2f";

    private static final Predicate<Person> PREDICATE_STATUS_IS_CONFIRMED = new StatusEqualsConfirmedPredicate();

    private final List<String> targetTagKeywords;

    /**
     * Creates a PriceCommand to calculate the price sum of persons with confirmed status
     */
    public PriceCommand() {
        this.targetTagKeywords = null;
    }

    /**
     * Creates a PriceCommand to calculate the price sum of persons with confirmed status and {@code targetTagKeyword}
     */
    public PriceCommand(String targetTagKeyWord) {
        this.targetTagKeywords = new ArrayList<>();
        this.targetTagKeywords.add(targetTagKeyWord);
    }

    /**
     * Creates a PriceCommand to calculate the price sum of persons with confirmed status and {@code targetTagKeywords}
     */
    public PriceCommand(List<String> targetTagKeywords) {
        this.targetTagKeywords = targetTagKeywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (targetTagKeywords == null) {
            return executeTotalSum(model);
        } else {
            return executeSumByTag(model);
        }
    }

    private List<Person> getConfirmedPersonList(Model model) {
        model.updateFilteredPersonList(PREDICATE_STATUS_IS_CONFIRMED);
        return model.getFilteredPersonList();
    }

    private CommandResult executeTotalSum(Model model) {
        List<Person> confirmedPersonList = getConfirmedPersonList(model);
        double totalSum = sumPriceInTheList(confirmedPersonList);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_PRICE_SUM_SUCCESS, totalSum));
    }

    private void searchPersonsWithSameTags(Model model) throws CommandException {
        assert targetTagKeywords != null : "Tag keywords should not be null";
        for (String targetTagKeyword : targetTagKeywords) {
            Tag tagToCheck = new Tag(targetTagKeyword);
            Predicate<Person> containSameTag = (person) -> person.getTags().contains(tagToCheck);
            model.updateFilteredPersonList(containSameTag);
            if (model.getFilteredPersonList().isEmpty()) {
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND_FAILURE, tagToCheck));
            }
        }
    }

    private List<Person> getPersonWithSameTagsList(Model model) throws CommandException {
        searchPersonsWithSameTags(model);
        Predicate<Person> containTagsPredicate = new TagContainsKeywordsPredicate(targetTagKeywords);
        Predicate<Person> containTagsAndConfirmed = PREDICATE_STATUS_IS_CONFIRMED.and(containTagsPredicate);
        model.updateFilteredPersonList(containTagsAndConfirmed);
        return model.getFilteredPersonList();
    }

    private CommandResult executeSumByTag(Model model) throws CommandException {
        assert targetTagKeywords != null : "Tag keywords should not be null";
        List<Person> personWithSameTagsList = getPersonWithSameTagsList(model);
        double totalSumByTag = sumPriceInTheList(personWithSameTagsList);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_PRICE_SUM_UNDER_TAG_SUCCESS, targetTagKeywords, totalSumByTag));
    }

    private double sumPriceInTheList(List<Person> listToSum) {
        double totalPrice = 0.00;
        for (int i = 0; i < listToSum.size(); i++) {
            Price priceToAdd = listToSum.get(i).getPrice();
            double amountToAdd = priceToAdd.toDouble();
            totalPrice += amountToAdd;
        }

        return totalPrice;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriceCommand // instanceof handles nulls
                && PREDICATE_STATUS_IS_CONFIRMED.equals(((PriceCommand) other).PREDICATE_STATUS_IS_CONFIRMED)
                && targetTagKeywords.equals(((PriceCommand) other).targetTagKeywords)); // state check
    }

}
