package tools.benchmark.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class TimeMetric implements IPerformanceMetric {
    ThreadLocal<Long> stopWatch = new ThreadLocal<>();
    AtomicLong nanos = new AtomicLong();
    String format = "Time metric[avg: %,fms, total: %,fms, tps: %,.4f.]";
    final double MILLIS_BY_NANOS = 1_000_000.0;
    final double SECONDS_BY_NANOS = 1_000_000_000.0;

    @Override
    public void start() {
        stopWatch.set(System.nanoTime());
    }

    @Override
    public void stop() {
        nanos.addAndGet(System.nanoTime() - stopWatch.get());
    }

    @Override
    public String metricResult(int measureTimes) {
        long total = nanos.get();
        return String.format(format, total / MILLIS_BY_NANOS / measureTimes,
                total / MILLIS_BY_NANOS, measureTimes / (total / SECONDS_BY_NANOS));
    }

}
