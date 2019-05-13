package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence {

    private List<SentenceElement> sentence = new ArrayList<>();

    public List<SentenceElement> getSentence() {
        return sentence;
    }

    @Override
    public String toString() {
        return sentence + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sentence sentence1 = (Sentence) o;
        return Objects.equals(sentence, sentence1.sentence);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sentence);
    }
}
