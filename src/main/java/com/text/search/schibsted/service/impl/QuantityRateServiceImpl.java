package com.text.search.schibsted.service.impl;

import com.text.search.schibsted.service.QuantityRateService;
import com.text.search.schibsted.store.WordStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuantityRateServiceImpl implements QuantityRateService {
    private final WordStore wordStore;

    @Override
    public Integer getQuantityRate(Integer includedWordCounts) {
        int includedPercentage = includedWordCounts * 100 / wordStore.getSearchedTextSet().size();
        return includedPercentage;
    }
}
