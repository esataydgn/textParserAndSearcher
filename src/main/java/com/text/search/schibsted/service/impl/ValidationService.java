package com.text.search.schibsted.service.impl;

import com.text.search.schibsted.SchibstedApplication;
import com.text.search.schibsted.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationService {
    private static Logger LOG = LoggerFactory.getLogger(SchibstedApplication.class);


    public void isAValidDirectoryEntered(String[] args) {
        if (args.length == 0) {
            LOG.error("ERROR_LOG : Directory does not entered!");
            throw new ValidationException("Directory does not entered!");
        }
    }

    public void isGivenDirectoryExist(File indexableDirectory) {
        if (!indexableDirectory.exists()) {
            LOG.error("ERROR_LOG : Entered Directory does not exist.");
            throw new ValidationException("Entered Directory does not exist.");
        }
    }

    public int isGivenDirectoryConsistTxtFiles(File indexableDirectory) {
        if (ObjectUtils.isEmpty(indexableDirectory.listFiles())) {
            LOG.error("ERROR_LOG : The directory does not have any files");
            throw new ValidationException("The directory does not have any files");
        }

        List<File> txtFiles = Arrays.stream(indexableDirectory.listFiles(file -> file.getName().endsWith(".txt"))).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(txtFiles)) {
            LOG.error("ERROR_LOG : The directory does not have any .txt files");
            throw new ValidationException("The directory does not have any .txt files");
        }

        return txtFiles.size();
    }
}
