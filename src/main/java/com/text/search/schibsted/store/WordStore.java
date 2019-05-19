package com.text.search.schibsted.store;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@Setter
public class WordStore {

    private ConcurrentHashMap<String, Set<String>> wordsMap = new ConcurrentHashMap<>();

    private Set<String> searchedTextSet = new HashSet<>();
}
