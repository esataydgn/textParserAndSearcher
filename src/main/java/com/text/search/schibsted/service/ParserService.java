package com.text.search.schibsted.service;

import java.io.File;

public interface ParserService {
    void parserFile(File file);

    void removeParsedSearchedWords();
}
