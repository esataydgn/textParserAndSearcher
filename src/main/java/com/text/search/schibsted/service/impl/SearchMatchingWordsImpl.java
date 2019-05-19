package com.text.search.schibsted.service.impl;

import com.text.search.schibsted.store.WordStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchMatchingWordsImpl {

    private final WordStore wordStore;


    public ConcurrentHashMap<String, Integer> calculateRankOfContainsWords(String searchText) {

        wordStore.getSearchedTextSet().addAll(Arrays.stream(searchText.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+")).collect(Collectors.toSet()));
        return findIncludedWordCount();
    }

    private ConcurrentHashMap<String, Integer> findIncludedWordCount() {
        ConcurrentHashMap<String, Integer> includedWordMap = new ConcurrentHashMap<>();
        for (String searchedWord : wordStore.getSearchedTextSet()) {
            for (Map.Entry<String, Set<String>> entry : wordStore.getWordsMap().entrySet()) {
                String key = entry.getKey();
                Set<String> existedWordList = entry.getValue();
                if (existedWordList.contains(searchedWord)) {
                    if (ObjectUtils.isEmpty(includedWordMap.get(key))) {
                        includedWordMap.put(key, 1);
                    } else {
                        includedWordMap.put(key, includedWordMap.get(key) + 1);
                    }
                }
            }
        }
        return includedWordMap;
    }
}
