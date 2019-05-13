package dto.impl;

import dto.SentenceDto;
import entity.Sentence;

import java.util.List;

public class SentencesDtoImpl implements SentenceDto {

    private List<Sentence> sentences;

    public SentencesDtoImpl(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public List<Sentence> getAllSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        return sentences.toString().
                replaceAll(", ", "")
                .replaceAll("]", "")
                .replaceAll("\\[", "");
    }
}
