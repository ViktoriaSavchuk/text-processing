package parser;

import entity.Symbol;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SymbolsParser {

    public List<Symbol> fillSymbols(StringBuilder text){
        return text.codePoints()
                .mapToObj(c -> (char) c)
                .map(Symbol::new)
                .collect(toList());

    }
}
