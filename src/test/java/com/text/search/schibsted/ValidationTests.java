package com.text.search.schibsted;

import com.text.search.schibsted.service.impl.ValidationService;
import com.text.search.schibsted.util.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfigurationTest.class)
public class ValidationTests {
    private File indexableDirectory;
    @Autowired
    private ValidationService validationService;

    @Autowired
    ApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        indexableDirectory = new File("src/main/resources");
    }

    @Test
    public void checkGivenPathIsExistTest() {
        validationService.isGivenDirectoryExist(indexableDirectory);
    }

    @Test
    public void checkGivenPathHasTxtFileTest() {
        validationService.isGivenDirectoryConsistTxtFiles(indexableDirectory);
    }

    @Test
    public void isStringEmptyTest() {
        Assert.assertTrue(StringUtils.isNotBlank("random"));
    }

    @Test
    public void isStringNotEmptyOrBlankTest() {
        Assert.assertTrue(StringUtils.isNotBlank(" "));
    }
}
