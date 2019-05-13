package service;

import dto.impl.SentencesDtoImpl;
import dto.impl.SymbolsDtoImpl;
import entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import parser.SentenceParser;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SentenceServiceTest {


    @Mock
    private SentencesDtoImpl sentencesDtoImpl;
    @InjectMocks
    private SentenceService sentenceService;

    private static final Symbol LOWER_CASE_LETTER = new Symbol('j');
    private static final Symbol UPPER_CASE_LETTER = new Symbol('J');
    private static final Symbol DOT = new Symbol('.');
    private static final Symbol DASH = new Symbol('-');
    private static final List<Character> WORD_MAIN_SEPARATORS =
            Arrays.asList('?', '!', ' ', ',', ':', ';', '\n', '—', '"', '“', '”');

    private static final SentenceParser SENTENCE_PARSER = new SentenceParser();

    private static final List<Symbol> SYMBOLS = new ArrayList<>();

    static {
        SYMBOLS.add(new Symbol('P'));
        SYMBOLS.add(new Symbol('r'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('-'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('f'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('c'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('?'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('J'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('v'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('?'));
        SYMBOLS.add(new Symbol(' '));
        SYMBOLS.add(new Symbol('1'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('2'));
        SYMBOLS.add(new Symbol('—'));
        SYMBOLS.add(new Symbol('s'));
        SYMBOLS.add(new Symbol('o'));
        SYMBOLS.add(new Symbol('m'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('?'));
        SYMBOLS.add(new Symbol(' '));
        SYMBOLS.add(new Symbol('w'));
        SYMBOLS.add(new Symbol('o'));
        SYMBOLS.add(new Symbol('r'));
        SYMBOLS.add(new Symbol('d'));
        SYMBOLS.add(new Symbol('.'));
    }

    private static SymbolsDtoImpl SYMBOLS_DTO = new SymbolsDtoImpl(SYMBOLS);

    private static List<Word> WORDS = new ArrayList<>();

    static {
        Word word1 = new Word();
        word1.getWord().add(SYMBOLS.get(0));
        word1.getWord().add(SYMBOLS.get(1));
        word1.getWord().add(SYMBOLS.get(2));
        word1.getWord().add(SYMBOLS.get(5));
        word1.getWord().add(SYMBOLS.get(6));
        word1.getWord().add(SYMBOLS.get(7));
        word1.getWord().add(SYMBOLS.get(8));

        Word word2 = new Word();
        word2.getWord().add(SYMBOLS.get(13));
        word2.getWord().add(SYMBOLS.get(14));
        word2.getWord().add(SYMBOLS.get(15));
        word2.getWord().add(SYMBOLS.get(16));

        Word word3 = new Word();
        word3.getWord().add(SYMBOLS.get(19));
        word3.getWord().add(SYMBOLS.get(20));
        word3.getWord().add(SYMBOLS.get(21));

        Word word4 = new Word();
        word4.getWord().add(SYMBOLS.get(23));
        word4.getWord().add(SYMBOLS.get(24));
        word4.getWord().add(SYMBOLS.get(25));
        word4.getWord().add(SYMBOLS.get(26));

        Word word5 = new Word();
        word5.getWord().add(SYMBOLS.get(29));
        word5.getWord().add(SYMBOLS.get(30));
        word5.getWord().add(SYMBOLS.get(31));
        word5.getWord().add(SYMBOLS.get(32));


        WORDS.add(word1);
        WORDS.add(word2);
        WORDS.add(word3);
        WORDS.add(word4);
        WORDS.add(word5);
    }

    private static List<Sentence> SENTENCES = new ArrayList<>();

    static {
        Sentence sentence1 = new Sentence();
        sentence1.getSentence().add(WORDS.get(0));
        sentence1.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(9)));
        sentence1.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(10)));
        sentence1.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(11)));
        sentence1.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(12)));

        Sentence sentence2 = new Sentence();
        sentence2.getSentence().add(WORDS.get(1));
        sentence2.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(17)));
        sentence2.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(18)));

        Sentence sentence3 = new Sentence();
        sentence3.getSentence().add(WORDS.get(2));
        sentence3.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(22)));
        sentence3.getSentence().add(WORDS.get(3));
        sentence3.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(27)));
        sentence3.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(28)));

        Sentence sentence4 = new Sentence();
        sentence4.getSentence().add(WORDS.get(4));
        sentence4.getSentence().add(new SentenceSymbol(SYMBOLS_DTO.getSymbolByIndex(33)));

        SENTENCES.add(sentence1);
        SENTENCES.add(sentence2);
        SENTENCES.add(sentence3);
        SENTENCES.add(sentence4);
    }

    private static final Set<SentenceElement> EXPECTED_WORDS = new HashSet<>();

    {
        EXPECTED_WORDS.add(WORDS.get(1));
        EXPECTED_WORDS.add(WORDS.get(3));
    }


    @Test
    public void shouldReturnExpectedWordsGivenLength() {
        when(sentencesDtoImpl.getAllSentences()).thenReturn(SENTENCES);
        Set<SentenceElement> actual = sentenceService.wordOfInterrogativeSentencesGivenLength(4);
        assertEquals(EXPECTED_WORDS, actual);

    }
}