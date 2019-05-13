package dto.impl;

import dto.SymbolsDto;
import entity.Symbol;

import java.util.List;

public class SymbolsDtoImpl implements SymbolsDto {

    private List<Symbol> symbols;


    public SymbolsDtoImpl(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    @Override
    public List<Symbol> getAllSymbols() {
        return symbols;
    }

    @Override
    public Symbol getSymbolByIndex(int index) {
        return symbols.get(index);
    }

    @Override
    public Symbol getNextSymbolByIndex(int index) {
        return hasNext(index) ? getAllSymbols().get(index + 1) : new Symbol();
    }

    @Override
    public Symbol getPreviousSymbolByIndex(int index) {
        return hasPrevious(index) ? getAllSymbols().get(index - 1) : new Symbol();
    }

    private boolean hasNext(int index) {
        return index < getAllSymbols().size() - 1 && index >= 0;
    }

    private boolean hasPrevious(int index) {
        return index < getAllSymbols().size() && index > 0;
    }

    @Override
    public String toString() {
        return symbols.toString()
                .replaceAll(", ", "")
                .replaceAll("]", "")
                .replaceAll("\\[", "");
    }
}
