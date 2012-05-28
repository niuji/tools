package tools.benchmark.metrics;

import java.util.concurrent.atomic.AtomicLong;

public class MemoryUsageMetric extends AbstractMetric {
    AtomicLong startMem = new AtomicLong(0);
    AtomicLong stopMem = new AtomicLong(0);
    String format = "Memory usage metric[avg: %,.4fM, total: %,.4fM.] %s";
    final double MEGA_BYTES = 1024.0 * 1024;

    @Override
    public void calibrate() {
        // TODO Auto-generated method stub
        super.calibrate();
    }

    @Override
    public void start() {
        startMem.compareAndSet(0, memoryUsed());
    }

    @Override
    public void stop() {
        long stopMemUsed = memoryUsed();
        long current = 0;
        do {
            current = stopMem.get();
            stopMemUsed = Math.max(stopMemUsed, current);
        } while (!stopMem.compareAndSet(current, stopMemUsed));
    }

    @Override
    public String metricResult(int measureTimes) {
        long total = stopMem.get() - startMem.get();

        return String.format(format, total / MEGA_BYTES / measureTimes, total
                / MEGA_BYTES);
    }

    public static long memoryUsed() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }
}
