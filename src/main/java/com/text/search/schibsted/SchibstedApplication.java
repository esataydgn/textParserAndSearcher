package com.text.search.schibsted;

import com.text.search.schibsted.service.impl.ParserServiceImpl;
import com.text.search.schibsted.service.impl.QuantityRateServiceImpl;
import com.text.search.schibsted.service.impl.SearchMatchingWordsImpl;
import com.text.search.schibsted.service.impl.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;


@SpringBootApplication
public class SchibstedApplication implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(SchibstedApplication.class);

    @Autowired
    private ParserServiceImpl parserService;

    @Autowired
    private SearchMatchingWordsImpl searchIncludedWords;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private QuantityRateServiceImpl quantityRateService;

    private final static String COMMAND_EXIT = "quit";

    public static void main(String[] args) {
        SpringApplication.run(SchibstedApplication.class, args);
    }

    @Override
    public void run(String... args) {

        LOG.info("EXECUTING : command line runner");

        validationService.isAValidDirectoryEntered(args);
        final File indexableDirectory = new File(args[0]);
        validationService.isGivenDirectoryExist(indexableDirectory);

        int countOfTxtFiles = validationService.isGivenDirectoryConsistTxtFiles(indexableDirectory);
        System.out.println(countOfTxtFiles + " files has found at the given directory : " + indexableDirectory.getName());

        Arrays.stream(indexableDirectory.listFiles(file -> file.getName().endsWith(".txt"))).forEach(file -> parserService.parserFile(file));

        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("search for = ");
            String command = keyboard.nextLine();
            if (command.equals(COMMAND_EXIT)) {
                LOG.info("command line runner is Stopped");
                break;
            } else {
                ConcurrentHashMap<String, Integer> includedWordMap = searchIncludedWords.calculateRankOfContainsWords(command);
                printResults(includedWordMap);
                parserService.removeParsedSearchedWords();
            }
        }
    }

    private void printResults(ConcurrentHashMap<String, Integer> includedWordMap) {

        includedWordMap.entrySet().stream().limit(10)
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).forEach(e -> {
            System.out.println(e.getKey() + " %" + quantityRateService.getQuantityRate(e.getValue()).toString());
        });
    }
}
