package parser;

import dto.impl.SymbolsDtoImpl;
import dto.impl.WordsDtoImpl;
import entity.Sentence;
import entity.SentenceSymbol;
import entity.Symbol;
import entity.Word;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SentenceParserTest {

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
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('J'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('v'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('!'));
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

    private static WordsDtoImpl WORD_DTO = new WordsDtoImpl(WORDS);

    private static List<Sentence> EXPECTED_SENTENCES = new ArrayList<>();

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

        EXPECTED_SENTENCES.add(sentence1);
        EXPECTED_SENTENCES.add(sentence2);
        EXPECTED_SENTENCES.add(sentence3);
        EXPECTED_SENTENCES.add(sentence4);
    }

    private Method method;

    private void makeMethodWithTwoArgumentsAccessible() throws NoSuchMethodException {
        method = SENTENCE_PARSER.getClass().getDeclaredMethod("checkSymbolIsAbsolutelyTheEndOfSentence",
                SymbolsDtoImpl.class, int.class);
        method.setAccessible(true);
    }

    private void makeMethodWithThreeArgumentsAccessible() throws NoSuchMethodException {
        method = SENTENCE_PARSER.getClass().getDeclaredMethod("checkSymbolIsFirstNextWordLetter",
                WordsDtoImpl.class, int.class, Symbol.class);
        method.setAccessible(true);
    }

    @Test
    public void shouldReturnExpectedSentences() {
        List<Sentence> expected = EXPECTED_SENTENCES;
        List<Sentence> actual = SENTENCE_PARSER.fillSentences(WORD_DTO, SYMBOLS_DTO);
        assertEquals(expected, actual);
    }

    @Test
    public void symbolIsAbsolutelyTheEndOfSentence() throws Exception {
        makeMethodWithTwoArgumentsAccessible();
        assertEquals(true, method.invoke(SENTENCE_PARSER, SYMBOLS_DTO, 17));
    }

    @Test
    public void checkDotIsNotAbsoluteTheEndOfSentence() throws Exception {
        makeMethodWithTwoArgumentsAccessible();
        assertEquals(false, method.invoke(SENTENCE_PARSER, SYMBOLS_DTO, 12));
    }

    @Test
    public void jIsTheFirstsLetterOfTheSecondWord() throws Exception {
        makeMethodWithThreeArgumentsAccessible();
        assertEquals(true, method.invoke(SENTENCE_PARSER, WORD_DTO, 1, SYMBOLS_DTO.getSymbolByIndex(13)));
    }


}