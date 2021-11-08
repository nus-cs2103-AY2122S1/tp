package seedu.address.model.interview;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindInterviewCommandPredicate implements Predicate<Interview> {

    private List<String> candidateKeywords = new ArrayList<String>();
    private List<String> dateKeywords = new ArrayList<String>();
    private List<String> interviewStatusKeywords = new ArrayList<String>();
    private List<String> positionKeywords = new ArrayList<String>();
    private List<LocalTime> timeKeywords = new ArrayList<LocalTime>();

    public FindInterviewCommandPredicate() {}

    /**
     * Alternative Constructor with keyword field.
     * @param candidateKeywords
     * @param dateKeywords
     * @param interviewStatusKeywords
     * @param positionKeywords
     * @param timeKeywords
     */
    public FindInterviewCommandPredicate(List<String> candidateKeywords, List<String> dateKeywords,
                                         List<String> interviewStatusKeywords, List<String> positionKeywords,
                                         List<LocalTime> timeKeywords) {
        this.candidateKeywords = candidateKeywords;
        this.dateKeywords = dateKeywords;
        this.interviewStatusKeywords = interviewStatusKeywords;
        this.positionKeywords = positionKeywords;
        this.timeKeywords = timeKeywords;
    }

    @Override
    public boolean test(Interview interview) {
        boolean candidateCheck = candidateKeywords.isEmpty() || candidateKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(interview.getCandidateNameString(), keyword));

        boolean dateCheck = dateKeywords.isEmpty() || dateKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(interview.getDateInFormattedString(), keyword));

        boolean interviewStatusCheck = interviewStatusKeywords.isEmpty() || interviewStatusKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(interview.getStatusInString(), keyword));

        boolean positionCheck = positionKeywords.isEmpty() || positionKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        interview.getPositionTitle().fullTitle, keyword));


        boolean timeCheck = timeKeywords.isEmpty() || timeKeywords.stream()
                .anyMatch(time -> (interview.getStartTime().isBefore(time) || interview.getStartTime().equals(time))
                        && (interview.getEndTime().isAfter(time) || interview.getEndTime().equals(time)));


        return candidateCheck && dateCheck && interviewStatusCheck && positionCheck && timeCheck;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindInterviewCommandPredicate // instanceof handles nulls
                && candidateKeywords.equals(((FindInterviewCommandPredicate) other).candidateKeywords) // state check
                && dateKeywords.equals(((FindInterviewCommandPredicate) other).dateKeywords)
                && interviewStatusKeywords.equals(((FindInterviewCommandPredicate) other).interviewStatusKeywords)
                && positionKeywords.equals(((FindInterviewCommandPredicate) other).positionKeywords)
                && timeKeywords.equals(((FindInterviewCommandPredicate) other).timeKeywords));

    }

    public void setCandidateKeywords(List<String> candidateKeywords) {
        this.candidateKeywords = candidateKeywords;
    }

    public void setDateKeywords(List<String> dateKeywords) {
        this.dateKeywords = dateKeywords;
    }

    public void setPositionKeywords(List<String> positionKeywords) {
        this.positionKeywords = positionKeywords;
    }

    public void setTimeKeywords(List<LocalTime> timeKeywords) {
        this.timeKeywords = timeKeywords;
    }

    public void setInterviewStatusKeywords(List<String> interviewStatusKeywords) {
        this.interviewStatusKeywords = interviewStatusKeywords;
    }


    /**
     * If any field is provided.
     * @return if any field is provided.
     */
    public boolean isAnyField() {
        return !Stream.of(candidateKeywords, dateKeywords, interviewStatusKeywords,
                positionKeywords, timeKeywords)
                .allMatch(List::isEmpty);
    }

}
