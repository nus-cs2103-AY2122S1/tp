package seedu.smartnus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_LIMIT_ARG;
import static seedu.smartnus.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.smartnus.logic.commands.CommandUtil.NOTE_KEYWORD;
import static seedu.smartnus.logic.commands.CommandUtil.QUESTION_KEYWORD;
import static seedu.smartnus.logic.commands.CommandUtil.TAG_KEYWORD;
import static seedu.smartnus.logic.commands.ThemeCommand.DARK_KEYWORD;
import static seedu.smartnus.logic.commands.ThemeCommand.LIGHT_KEYWORD;
import static seedu.smartnus.logic.parser.AddTfqCommandParser.ANSWER_FALSE;
import static seedu.smartnus.logic.parser.AddTfqCommandParser.ANSWER_TRUE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.smartnus.model.choice.Choice.MESSAGE_KEYWORD_CONSTRAINTS;
import static seedu.smartnus.model.question.MultipleChoiceQuestion.NUMBER_OF_INCORRECT_CHOICES;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.commons.util.StringUtil;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.note.NoteName;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is invalid, "
            + "index should be between 1-2147483647 inclusive.";

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static NoteName parseNoteName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(NoteName.MESSAGE_CONSTRAINTS);
        }
        return new NoteName(trimmedName);
    }

    /**
     * Parses a {@code String importance} into a {@code Importance}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code importance} is invalid.
     */
    public static Importance parseImportance(String importance) throws ParseException {
        requireNonNull(importance);
        String trimmedImportance = importance.trim();
        if (!Importance.isValidImportance(trimmedImportance)) {
            throw new ParseException(Importance.MESSAGE_CONSTRAINTS);
        }
        return new Importance(trimmedImportance);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> indexes} into a {@code Set<Index>}.
     */
    public static Set<Index> parseQuizIndexes(Collection<String> indexCollection) throws ParseException {
        requireNonNull(indexCollection);
        final Set<Index> indexSet = new HashSet<>();
        for (String indexCol: indexCollection) {
            String[] indexes = indexCol.split("\\s+");
            for (String index : indexes) {
                indexSet.add(parseIndex(index));
            }
        }
        return indexSet;
    }

    /**
     * Parses {@code String limit} into a {@code int} limit.
     */
    public static int parseQuizLimit(String limit) throws ParseException {
        requireNonNull(limit);
        String trimmedLimit = limit.trim();
        int res;
        try {
            res = Integer.parseInt(trimmedLimit);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_LIMIT_ARG);
        }
        if (res <= 0) {
            throw new ParseException(MESSAGE_INVALID_LIMIT_ARG);
        }
        return res;
    }

    /**
     * Parses {@code String choice} into a {@code Choice choice}.
     */
    public static Choice parseChoice(String choice, boolean isCorrect) throws ParseException {
        requireNonNull(choice);
        String trimmedChoice = choice.trim();
        if (!Choice.isValidChoiceTitle(trimmedChoice)) {
            throw new ParseException(Choice.MESSAGE_CONSTRAINTS);
        }
        return new Choice(trimmedChoice, isCorrect);
    }

    /**
     * Parses {@code List<String> choices, String answer} into a {@code Set<Choice> choice}.
     */
    public static Set<Choice> parseChoices(List<String> choices, String answer) throws ParseException {
        requireAllNonNull(choices, answer);
        if (choices.size() != NUMBER_OF_INCORRECT_CHOICES) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_INCORRECT_NUMBER_OF_CHOICES);
        }
        Set<Choice> choiceSet = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_INCORRECT_CHOICES; i++) {
            String choice = choices.get(i);
            if (choice.equals(answer)) {
                throw new ParseException(MultipleChoiceQuestion.MESSAGE_DUPLICATE_CHOICES);
            }
            choiceSet.add(parseChoice(choice, false));
        }
        if (choiceSet.size() != NUMBER_OF_INCORRECT_CHOICES) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_DUPLICATE_CHOICES);
        }
        choiceSet.add(parseChoice(answer, true));
        return choiceSet;
    }

    /**
     * Parses  {@code choices} into a set of Choices.
     *
     * @param choices List of Strings representing titles of choices.
     * @return Set of Choices where all Choices have isCorrect set to false.
     * @throws ParseException If any choice has an invalid title.
     */
    public static Set<Choice> parseWrongChoicesForEdit(List<String> choices) throws ParseException {
        requireAllNonNull(choices);
        Set<Choice> choiceSet = new HashSet<>();
        for (String choice : choices) {
            choiceSet.add(parseChoice(choice, false));
        }
        if (choiceSet.size() != choices.size()) {
            throw new ParseException(MultipleChoiceQuestion.MESSAGE_DUPLICATE_CHOICES);
        }
        return choiceSet;
    }

    /**
     * Parses {@code answer} into a correct Choice.
     *
     * @param answer Title of the correct Choice.
     * @return Choice with title set to answer and isCorrect set to true.
     * @throws ParseException If answer is not a valid choice title.
     */
    public static Choice parseAnswerForEdit(String answer) throws ParseException {
        requireNonNull(answer);
        return parseChoice(answer, true);
    }

    /**
     * Parses {@code String answer} (T or F) into a {@code Set<Choice> choice} of 2 (T and F) choices.
     */
    public static Set<Choice> parseTrueFalseAnswer(String answer) throws ParseException {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim().toUpperCase();
        boolean isAnswerTrue = trimmedAnswer.equals(ANSWER_TRUE);
        boolean isAnswerFalse = trimmedAnswer.equals(ANSWER_FALSE);
        if (!isAnswerTrue && !isAnswerFalse) {
            throw new ParseException(TrueFalseQuestion.MESSAGE_ANSWER_INVALID);
        }
        Set<Choice> choices = new HashSet<>();
        choices.add(new Choice(Choice.TRUE_CHOICE_TITLE, isAnswerTrue));
        choices.add(new Choice(Choice.FALSE_CHOICE_TITLE, isAnswerFalse));
        return choices;
    }

    /**
     * Parses {@code String answer} into a {@code Set<Choice> choice} of two Choices (with titles True and False).
     * {@code answer} may not be T or F, as the question to be edited may not be a TrueFalseQuestion.
     * This method is used to prepare the choices for EditCommand in the event the question to be edited is
     * a TrueFalseQuestion.
     *
     * @param answer The answer specified by the user.
     * @return A set of two Choices (with titles True and False).
     */
    public static Set<Choice> parseTrueFalseAnswerForEdit(String answer) {
        requireNonNull(answer);
        String trimmedAnswer = answer.trim();
        boolean isAnswerTrue = trimmedAnswer.equalsIgnoreCase(ANSWER_TRUE);
        boolean isAnswerFalse = trimmedAnswer.equalsIgnoreCase(ANSWER_FALSE);
        Set<Choice> choices = new HashSet<>();
        choices.add(new Choice(Choice.TRUE_CHOICE_TITLE, isAnswerTrue));
        choices.add(new Choice(Choice.FALSE_CHOICE_TITLE, isAnswerFalse));
        return choices;
    }

    /**
     * Returns true if there are no choices with duplicate titles in {@code choices}, false otherwise.
     * {@code choices} should not contain null.
     *
     * @param choices A set of choices.
     * @return True if there are no choices with duplicate titles, false otherwise.
     */
    public static boolean isValidChoiceTitles(Set<Choice> choices) {
        requireAllNonNull(choices);
        ArrayList<Choice> arr = new ArrayList<>(choices);
        for (int i = 0; i < arr.size(); i++) {
            Choice currentChoice = arr.get(i);
            for (int j = i + 1; j < arr.size(); j++) {
                Choice nextChoice = arr.get(j);
                if (currentChoice.hasSameTitle(nextChoice)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns SAQ Choice with parsed title that does not contain keyword prefixes.
     *
     * @param answer Choice that contains raw user input as the title.
     * @return SAQ Choice with parsed title that does not contain keyword prefixes.
     */
    public static Set<Choice> parseEditSaqAnswer(Choice answer) {
        String answerString = answer.getTitle();
        ArgumentMultimap keywordsMultimap = ArgumentTokenizer.tokenize(" " + answerString, PREFIX_KEYWORD);
        try {
            answer = ParserUtil.getChoiceWithKeywords(answerString, keywordsMultimap);
        } catch (ParseException e) {
            // empty catch block as question to be edited may not be an SAQ and may not require keywords
        }
        Set<Choice> choices = new HashSet<>();
        choices.add(answer);
        return choices;
    }

    /**
     * Returns Choice with formatted title without keyword prefixes and set of keywords specified in user input.
     *
     * @param answerString Raw user input that includes keyword prefixes (k/).
     * @param keywordsMultimap Multimap of keyword prefixes to their arguments.
     * @return Choice that contains formatted title without keyword prefix and set of keywords from user input.
     * @throws ParseException If answerString is blank or empty keywords are specified.
     */
    public static Choice getChoiceWithKeywords(String answerString, ArgumentMultimap keywordsMultimap)
            throws ParseException {
        if (answerString.isBlank()) {
            throw new ParseException(Choice.MESSAGE_CONSTRAINTS);
        }
        String answerTitleWithoutPrefix = answerString.replaceAll(PREFIX_KEYWORD.toString(), "");
        Set<String> parsedKeywords = new HashSet<>();
        List<String> keywordsList = keywordsMultimap.getAllValues(PREFIX_KEYWORD);
        checkEmptyKeywords(keywordsList);

        for (String inputAfterKeywordPrefix : keywordsList) {
            boolean validKeywordAdded = false;
            String[] possibleKeywords = inputAfterKeywordPrefix.split("[^a-zA-Z0-9]+");
            for (String word : possibleKeywords) {
                if (word.isBlank()) {
                    continue;
                }
                parsedKeywords.add(word.toLowerCase());
                validKeywordAdded = true;
                break; // only the first non-empty, alphanumeric part specified after k/ is a keyword
            }
            if (!validKeywordAdded) {
                throw new ParseException(MESSAGE_KEYWORD_CONSTRAINTS);
            }
        }
        return new Choice(answerTitleWithoutPrefix, true, parsedKeywords);
    }

    private static void checkEmptyKeywords(List<String> keywords) throws ParseException {
        for (String word : keywords) {
            if (word.isBlank()) {
                throw new ParseException(MESSAGE_KEYWORD_CONSTRAINTS);
            }
        }
    }

    /**
     * Returns True if the given theme is valid.
     * @param theme The given theme.
     * @return True if theme is valid, false otherwise.
     */
    public static boolean isValidTheme(String theme) {
        return theme.trim().equals(LIGHT_KEYWORD) || theme.trim().equals(DARK_KEYWORD);
    }

    /**
     * Returns True if the given listArgument is valid.
     * @param listArgument The given argument
     * @return True if listArgument is valid, false otherwise.
     */
    public static boolean isValidListArgument(String listArgument) {
        return listArgument.trim().equals(NOTE_KEYWORD)
                || listArgument.trim().equals(QUESTION_KEYWORD)
                || listArgument.trim().equals(TAG_KEYWORD);
    }
}
