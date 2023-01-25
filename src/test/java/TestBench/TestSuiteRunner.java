package TestBench;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PositiveTests.class,
        NegativeTests.class
})

public class TestSuiteRunner {

}
