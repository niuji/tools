package tools.benchmark;

import org.junit.Test;

public class BenchmarkTest {
    @Test
    public void benchTest() {
        int cnt = 20_000;
        int thread = Runtime.getRuntime().availableProcessors();

        PerformanceResult result = Benchmark.newInstance().warmupTimes(10).measureTimes(cnt)
                .useThreads(thread).warmupAndBenchmark(new Runnable() {

                    @Override
                    public void run() {
                        Benchmark.newInstance().benchmark(new Runnable() {

                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                            }
                        }).getFormatedResult();
                    }
                });
        System.out.println(result.getFormatedResult());

        long s = System.nanoTime();
        Benchmark.newInstance().benchmark(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub

            }
        }).getFormatedResult();
        System.out.printf("single run time: %dns.%n", System.nanoTime() - s);

    }
}
