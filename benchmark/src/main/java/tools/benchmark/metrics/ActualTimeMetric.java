package tools.benchmark.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class ActualTimeMetric extends AbstractMetric {
    AtomicLong startNanos = new AtomicLong(Long.MAX_VALUE);
    AtomicLong stopNanos = new AtomicLong();
    String format = "Actual run time metric[avg: %,.4fms, total: %,.4fms, tps: %,.4f.]";
    final double MILLIS_BY_NANOS = 1_000_000.0;
    final double SECONDS_BY_NANOS = 1_000_000_000.0;

    @Override
    public void start() {
        long current = startNanos.get();
        long start = Math.min(System.nanoTime(), current);
        startNanos.compareAndSet(current, start);
    }

    @Override
    public void stop() {
        long current = stopNanos.get();
        long stop = Math.max(System.nanoTime(), current);
        stopNanos.compareAndSet(current, stop);
    }

    @Override
    public String metricResult(int measureTimes) {
        long total = stopNanos.get() - startNanos.get();
        return String.format(format, total / MILLIS_BY_NANOS / measureTimes,
                total / MILLIS_BY_NANOS, measureTimes / (total / SECONDS_BY_NANOS));
    }

}
