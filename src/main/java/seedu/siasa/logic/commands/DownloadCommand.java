package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.PolicyIsOwnedByPredicate;

public class DownloadCommand extends Command {

    public static final String COMMAND_WORD = "download";

    public static final String CSV_FILEPATH = "data\\stats.csv";
    public static final String MESSAGE_DOWNLOAD_SUCCESS = "File has been downloaded, you can view it at "
            + CSV_FILEPATH;
    public static final String MESSAGE_ERROR_WRITING_FILE = "There was an error saving the file.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        int totalCommission = model.getTotalCommission();

        List<Person> modifiablePersonList = new ArrayList<>(model.getFilteredPersonList());

        // TODO: Abstract out when comparators for sorting policies are finished.
        modifiablePersonList.sort(new Comparator<Person>() {
            @Override
            public int compare(Person personA, Person personB) {
                model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(personA));
                ObservableList<Policy> personAPolicies = model.getFilteredPolicyList();
                int commissionFromPersonA = getCommissionFromPolicyList(personAPolicies);

                model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(personB));
                ObservableList<Policy> personBPolicies = model.getFilteredPolicyList();
                int commissionFromPersonB = getCommissionFromPolicyList(personBPolicies);

                model.updateFilteredPolicyList(x -> true);

                return (commissionFromPersonB - commissionFromPersonA);
            }
        });

        List<String> listStringForCsv = stringListBuilderForCsv(totalCommission, modifiablePersonList);

        try {
            writeToCsv(listStringForCsv);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_ERROR_WRITING_FILE);
        }

        return new CommandResult(MESSAGE_DOWNLOAD_SUCCESS);
    }

    private int getCommissionFromPolicyList(List<Policy> policyList) {
        float total = 0;
        for (Policy policy : policyList) {
            total = total + ((float) policy.getCommission().commissionPercentage / 100)
                    * policy.getPrice().priceInCents;
        }
        return (int) total;
    }

    private List<String> stringListBuilderForCsv(int totalCommission, List<Person> sortedPersons) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Most premium customers:");

        for (Person person : sortedPersons) {
            stringList.add(person.toString());
        }

        stringList.add("Total Commission: " + centsToDollars(totalCommission));
        return stringList;
    }

    private void writeToCsv(List<String> stringList) throws IOException {
        FileWriter fileWriter = new FileWriter(CSV_FILEPATH);
        for (String string : stringList) {
            fileWriter.append(string);
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private String centsToDollars(int priceInCents) {
        int cents = priceInCents % 100;
        int dollars = (priceInCents - cents) / 100;

        String centsStr;
        if (cents <= 9) {
            centsStr = 0 + "" + cents;
        } else {
            centsStr = Integer.toString(cents);
        }

        return "$" + dollars + "." + centsStr;
    }

}
