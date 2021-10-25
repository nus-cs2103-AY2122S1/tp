package seedu.academydirectory.model;

import java.util.Optional;

import seedu.academydirectory.logic.AdditionalViewType;
import seedu.academydirectory.logic.commands.ViewCommand;
import seedu.academydirectory.logic.commands.VisualiseCommand;


public class AdditionalViewModel {
    private Optional<String> lastCommandWord;
    private AdditionalInfo<? extends Object> additionalInfo;

    /**
     * @param lastCommandWord the Command Word of the last command
     * @param additionalInfo the Additional Information that the last command presents
     */
    public AdditionalViewModel(Optional<String> lastCommandWord, AdditionalInfo<? extends Object> additionalInfo) {
        this.lastCommandWord = lastCommandWord;
        this.additionalInfo = additionalInfo;
    }

    public Optional<String> getLastCommand() {
        return lastCommandWord;
    }

    public void setLastCommand(Optional<String> lastCommandWord) {
        this.lastCommandWord = lastCommandWord;
    }

    public AdditionalInfo<? extends Object> getAdditionalInfo() {
        //Ugly hack
        if (this.lastCommandWord.isEmpty()) {
            this.additionalInfo = AdditionalInfo.empty();
        }
        switch (lastCommandWord.get()) {
        case ViewCommand.COMMAND_WORD:
            return this.additionalInfo;
        case VisualiseCommand.COMMAND_WORD:
            return this.additionalInfo;
        default:
            return AdditionalInfo.empty();
        }
    }

    public void setAdditionalInfo(AdditionalInfo<? extends Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public AdditionalViewType getAdditionalViewType() {
        if (this.lastCommandWord.isEmpty()) {
            return AdditionalViewType.DEFAULT;
        }
        switch (lastCommandWord.get()) {
        case ViewCommand.COMMAND_WORD:
            return AdditionalViewType.VIEW;
        case VisualiseCommand.COMMAND_WORD:
            return AdditionalViewType.VISUALISE;
        default:
            return AdditionalViewType.DEFAULT;
        }
    }
}
