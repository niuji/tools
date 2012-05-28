package tools.benchmark;

import org.junit.Test;

import tools.benchmark.metrics.ActualTimeMetric;
import tools.benchmark.metrics.IMetric;
import tools.benchmark.metrics.MemoryUsageMetric;
import tools.benchmark.metrics.TotalTimeMetric;

public class BenchmarkTest {
    @Test
    public void benchTest() {
        int cnt = 20_000;
        int thread = Runtime.getRuntime().availableProcessors();

        PerformanceResult result = Benchmark.newInstance().warmupTimes(10)
                .measureTimes(cnt).useThreads(thread)
                .warmupAndBenchmark(new Runnable() {

                    @Override
                    public void run() {
                        Benchmark
                                .newInstance()
                                .setMetrics(
                                        new IMetric[] { new TotalTimeMetric(),
                                                new ActualTimeMetric() })
                                .benchmark(new Runnable() {

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
        System.out.printf("single run time: %fms.%n",
                (System.nanoTime() - s) / 1_000_000.0);

    }
}
