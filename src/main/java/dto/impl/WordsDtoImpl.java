package dto.impl;

import dto.WordsDto;
import entity.Word;

import java.util.List;

public class WordsDtoImpl implements WordsDto {

    private List<Word> words;

    public WordsDtoImpl(List<Word> words) {
        this.words = words;
    }

    @Override
    public Word getWordByIndex(int index) {
        return index > 0 ? words.get(index) : words.get(0);
    }

    @Override
    public List<Word> getWords() {
        return words;
    }

    @Override
    public String toString() {
        return words.toString()
                .replaceAll(",", "")
                .replaceAll("]", "")
                .replaceAll("\\[", "");
    }
}
