package tools.benchmark;

import org.junit.Test;

public class BenchmarkTest {
    @Test
    public void benchTest() {
        int cnt = 100_000;
        int thread = 4;

        PerformanceResult result = Benchmark.newInstance().measureTimes(cnt)
                .useThreads(thread).benchmark(new Runnable() {

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
        System.out.println(System.nanoTime() - s);

    }
}
