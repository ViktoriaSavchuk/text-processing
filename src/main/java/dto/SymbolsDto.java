package dto;

import entity.Symbol;

import java.util.List;

public interface SymbolsDto {

    List<Symbol> getAllSymbols();

    Symbol getSymbolByIndex(int index);

    Symbol getNextSymbolByIndex(int index);

    Symbol getPreviousSymbolByIndex(int index);

}
