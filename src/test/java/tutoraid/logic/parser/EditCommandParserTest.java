package tutoraid.logic.parser;

import static tutoraid.testutil.LessonUtil.getEditCommand;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.EditLessonCommand;
import tutoraid.logic.commands.EditLessonCommand.EditLessonDescriptor;
import tutoraid.model.lesson.Lesson;
import tutoraid.testutil.EditLessonDescriptorBuilder;
import tutoraid.testutil.LessonBuilder;

public class EditCommandParserTest {
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void arseCommand_editLesson_correctCommandReturned() throws Exception {
        int index = 1;

        Lesson lesson = new LessonBuilder().build();
        EditLessonDescriptor e = new EditLessonDescriptorBuilder(lesson).build();

        EditLessonCommand commandFromInput = (EditLessonCommand) parser.parse(getEditCommand(index, lesson));
        EditLessonCommand commandFromDescriptor = new EditLessonCommand(Index.fromOneBased(index), e);
        assert (commandFromDescriptor.equals(commandFromInput));
    }
}
