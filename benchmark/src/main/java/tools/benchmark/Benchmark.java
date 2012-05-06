package tools.benchmark;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tools.benchmark.metrics.ActualTimeMetric;
import tools.benchmark.metrics.IPerformanceMetric;
import tools.benchmark.metrics.TotalTimeMetric;

public class Benchmark {
    private int measureTimes = 1;
    private int warmupTimes = 1;
    private int threads = 1;
    private List<IPerformanceMetric> metrics;

    private Benchmark() {
        metrics = Arrays.asList(new IPerformanceMetric[] {
                new TotalTimeMetric(), new ActualTimeMetric() });
    }

    public static Benchmark newInstance() {
        return new Benchmark();
    }

    /**
     * 测试任务执行次数，默认1
     * 
     * @param times
     * @return
     */
    public Benchmark measureTimes(int measureTimes) {
        this.measureTimes = measureTimes;
        return this;
    }

    /**
     * jvm预热任务执行次数，默认1
     * 
     * @param times
     * @return
     */
    public Benchmark warmupTimes(int warmupTimes) {
        this.warmupTimes = warmupTimes;
        return this;
    }

    /**
     * 测试使用线程数，默认1
     * 
     * @param threads
     * @return
     */
    public Benchmark useThreads(int threads) {
        this.threads = threads;
        return this;
    }

    public PerformanceResult benchmark(Runnable testTask) {
        PerformanceResult result = new PerformanceResult(measureTimes, threads);
        ExecutorService es = Executors.newFixedThreadPool(threads);
        for (IPerformanceMetric metric : metrics) {
            CountDownLatch latch = new CountDownLatch(measureTimes);
            for (int i = 0; i < measureTimes; i++) {
                es.execute(new MeasureTask(metric, testTask, latch));
            }
            try {
                latch.await();
                result.addMetric(metric);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
        return result;
    }

    class MeasureTask implements Runnable {
        private IPerformanceMetric metric;
        private Runnable testTask;
        private CountDownLatch latch;

        public MeasureTask(IPerformanceMetric metric, Runnable testTask,
                CountDownLatch latch) {
            this.metric = metric;
            this.testTask = testTask;
            this.latch = latch;
        }

        @Override
        public void run() {
            metric.start();
            testTask.run();
            metric.stop();
            latch.countDown();
        }

    }
}
