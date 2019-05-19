package com.text.search.schibsted;

import com.text.search.schibsted.service.impl.ParserServiceImpl;
import com.text.search.schibsted.service.impl.QuantityRateServiceImpl;
import com.text.search.schibsted.service.impl.SearchMatchingWordsImpl;
import com.text.search.schibsted.store.WordStore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class SchibstedApplicationTests {

    @Autowired
    private ParserServiceImpl parserService;

    @Autowired
    private SearchMatchingWordsImpl searchIncludedWords;

    @Autowired
    private QuantityRateServiceImpl quantityRateService;

    @Autowired
    private WordStore wordStore;

    private final File testFile = new File("src/main/resources/exampleFile.txt");

    private final static String SEARCH_TEXT = "Lorem ipsum Platea consectetur esat ayodgan example search text";

    @Test
    public void retrievingWordsFromFileTest() {
        parserService.parserFile(testFile);

        Assert.assertTrue(!wordStore.getWordsMap().isEmpty());
    }

    @Test
    public void searchInTestFileTest() {
        parserService.parserFile(testFile);

        Assert.assertTrue(!wordStore.getSearchedTextSet().isEmpty());
    }

    @Test
    public void findSearchedWordsRank() {
        //init
        parserService.parserFile(testFile);

        //process
        ConcurrentHashMap<String, Integer> includedWordMap = searchIncludedWords.calculateRankOfContainsWords(SEARCH_TEXT);
        Integer rate = quantityRateService.getQuantityRate(includedWordMap.get("exampleFile.txt"));

        //result
        Assert.assertTrue(rate > 0);
    }
}
