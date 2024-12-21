package fr.isima.codereview.tp1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions; 
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope; 

@State(Scope.Thread)
public class AwesomePasswordCheckerPerformanceTest {

    private String testString;

    @Setup
    public void setUp() {
        testString = "password123";
    }

    @Test
    public void testComputeMD5() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> AwesomePasswordChecker.computeMD5(null));
    }

    @Benchmark
    public void benchmarkComputeMD5() {
        AwesomePasswordChecker.computeMD5(testString);
    }
}
