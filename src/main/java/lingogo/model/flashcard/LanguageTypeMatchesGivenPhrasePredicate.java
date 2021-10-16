package lingogo.model.flashcard;

import java.util.function.Predicate;

/**
 * A {@code Predicate} which tests whether a given {@code Flashcard}'s Language type matches the given phrase.
 */
public class LanguageTypeMatchesGivenPhrasePredicate implements Predicate<Flashcard> {
    private final Phrase givenPhrase;

    public LanguageTypeMatchesGivenPhrasePredicate(Phrase givenPhrase) {
        this.givenPhrase = givenPhrase;
    }

    @Override
    public boolean test(Flashcard flashcard) {
        return flashcard.getLanguageType().value.equalsIgnoreCase(givenPhrase.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LanguageTypeMatchesGivenPhrasePredicate
                && givenPhrase.equals(((LanguageTypeMatchesGivenPhrasePredicate) other).givenPhrase));
    }
}
