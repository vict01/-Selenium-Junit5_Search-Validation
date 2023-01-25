package com.test.automationpractice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginAndLogoutTest.class,
        NotesAddingTest.class,
        SearchUpdateAndDelete.class
})

public class TestSuiteRunner {

}
