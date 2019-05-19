package com.text.search.schibsted.service.impl;

import com.text.search.schibsted.exception.FileReaderException;
import com.text.search.schibsted.service.ParserService;
import com.text.search.schibsted.store.WordStore;
import com.text.search.schibsted.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ParserServiceImpl implements ParserService {

    private final WordStore wordStore;

    public void parserFile(File file) {
        try {
            Files.lines(file.toPath())
                    .filter(StringUtils::isNotBlank)
                    .forEach(line -> generateWordsFromFile(line, file));
        } catch (IOException e) {
            throw new FileReaderException("something went wrong while reading from file.");
        }
    }


    private void generateWordsFromFile(String line, File file) {

        String fileName = file.getName();
        String[] newExtractedWords = splitLineWords(line);
        if (isFileNameExistInMap(fileName)) {
            Arrays.stream(newExtractedWords).forEach(word -> wordStore.getWordsMap().get(fileName).add(word));
        } else {
            Set<String> newWordsSet = new HashSet<>();
            Arrays.stream(newExtractedWords).forEach(newWord -> newWordsSet.add(newWord));
            wordStore.getWordsMap().put(fileName, newWordsSet);
        }
    }

    private String[] splitLineWords(String line) {
        return line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");
    }

    private boolean isFileNameExistInMap(String fileName) {
        return !ObjectUtils.isEmpty(wordStore) && !ObjectUtils.isEmpty(wordStore.getWordsMap().get(fileName));
    }

    public void removeParsedSearchedWords() {
        wordStore.getSearchedTextSet().clear();
    }
}
