package service;

import dto.impl.SentencesDtoImpl;
import entity.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SentenceService {

    private SentencesDtoImpl sentencesDtoImpl;

    public SentenceService(SentencesDtoImpl sentencesDtoImpl) {
        this.sentencesDtoImpl = sentencesDtoImpl;
    }

    public Set<SentenceElement> wordOfInterrogativeSentencesGivenLength(int length) {

        List<Sentence> interrogativeSentence = sentencesDtoImpl.getAllSentences().stream()
                .filter(sentence -> sentence.getSentence().stream()
                        .anyMatch(sentenceElement -> sentenceElement instanceof SentenceSymbol
                                && sentenceElement.equals(new SentenceSymbol(new Symbol('?')))))
                .collect(toList());

        return interrogativeSentence.stream()
                .flatMap(sentence -> sentence.getSentence().stream())
                .filter(sentenceElement -> sentenceElement instanceof Word && ((Word) sentenceElement)
                        .getWord().size() == length).collect(Collectors.toSet());
    }

}


