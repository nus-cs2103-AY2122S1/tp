package dash.logic.parser;

import dash.logic.commands.Command;
import dash.logic.parser.exceptions.ParseException;
import dash.model.person.Person;
import javafx.collections.ObservableList;

public interface ParserRequiringPersonList<T extends Command> extends Parser<T> {
    /**
     * Parses {@code userInput} into a command and returns it.
     * For commands that require list of people
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput, ObservableList<Person> personList) throws ParseException;
}
