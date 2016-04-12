package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestAbstractList.class,
    TestOneDirectSolver.class,
    TestBiDirectSolver.class,
    TestMyTrigonometry.class,
})

public class TestSuite {}