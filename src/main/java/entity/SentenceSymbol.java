package entity;

import java.util.Objects;

public class SentenceSymbol implements SentenceElement {

    private Symbol sentenceSymbol;

    public SentenceSymbol(Symbol sentenceSymbol) {
        this.sentenceSymbol = sentenceSymbol;
    }

    public Symbol getSentenceSymbol() {
        return sentenceSymbol;
    }

    public SentenceSymbol setSentenceSymbol(Symbol sentenceSymbol) {
        this.sentenceSymbol = sentenceSymbol;
        return this;
    }
//todo benefits of this type of setter
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SentenceSymbol that = (SentenceSymbol) o;
        return Objects.equals(sentenceSymbol, that.sentenceSymbol);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sentenceSymbol);
    }
}
