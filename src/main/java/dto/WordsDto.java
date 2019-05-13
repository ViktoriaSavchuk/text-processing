package dto;

import entity.Word;

import java.util.List;

public interface WordsDto {

    Word getWordByIndex(int index);

    List<Word> getWords();
}
