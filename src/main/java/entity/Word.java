package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Word implements SentenceElement {

    private List<Symbol> word = new ArrayList<>();

    public List<Symbol> getWord() {
        return word;
    }

    @Override
    public String toString() {
        return word + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {return false;}
        Word word1 = (Word) o;
        return Objects.equals(word, word1.word);
    }

    @Override
    public int hashCode() {

        return Objects.hash(word);
    }
}
