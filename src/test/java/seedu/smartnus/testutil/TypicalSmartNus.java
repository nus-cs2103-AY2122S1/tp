package seedu.smartnus.testutil;

import seedu.smartnus.model.SmartNus;

public class TypicalSmartNus {

    public static SmartNus getTypicalSmartNus() {
        SmartNus smartNusQuestions = TypicalQuestions.getTypicalSmartNusQuestions();
        SmartNus smartNusNotes = TypicalNotes.getTypicalSmartNusNotes();
        return new SmartNus(smartNusQuestions.getQuestionsAsList(), smartNusNotes.getNotesAsList());
    }
}
