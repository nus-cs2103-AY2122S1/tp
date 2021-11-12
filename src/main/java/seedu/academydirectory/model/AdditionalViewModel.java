package seedu.academydirectory.model;

import seedu.academydirectory.logic.AdditionalViewType;


public class AdditionalViewModel {
    private AdditionalViewType additionalViewType;
    private AdditionalInfo<? extends Object> additionalInfo;

    /**
     * @param additionalViewType the AdditionalViewType of the last command
     * @param additionalInfo the Additional Information that the last command presents
     */
    public AdditionalViewModel(AdditionalViewType additionalViewType, AdditionalInfo<? extends Object> additionalInfo) {
        this.additionalViewType = additionalViewType;
        this.additionalInfo = additionalInfo;
    }

    public void setAdditionalViewType(AdditionalViewType additionalViewType) {
        this.additionalViewType = additionalViewType;

        //Reset AdditionalViewType if it's default command
        if (this.additionalViewType.equals(AdditionalViewType.DEFAULT)) {
            this.additionalInfo = AdditionalInfo.empty();
        }
    }

    public void setAdditionalInfo(AdditionalInfo<? extends Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public AdditionalInfo<? extends Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public AdditionalViewType getAdditionalViewType() {
        return additionalViewType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AdditionalViewModel)) {
            return false;
        }

        AdditionalViewModel otherAdditionalViewModel = (AdditionalViewModel) obj;
        return additionalInfo.equals(otherAdditionalViewModel.additionalInfo)
                && additionalViewType.equals(otherAdditionalViewModel.additionalViewType);
    }
}
