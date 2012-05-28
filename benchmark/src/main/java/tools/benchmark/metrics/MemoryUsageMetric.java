package tools.benchmark.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryUsageMetric extends AbstractMetric {
    AtomicLong startMem = new AtomicLong(0);
    AtomicLong stopMem = new AtomicLong(0);
    String format = "Memory usage metric[avg: %,.4fM, total: %,.4fM.]";
    final double MEGA_BYTES = 1024.0 * 1024;

    @Override
    public void calibrate() {
        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        long lastUsed = memoryUsed();
        for (int i = 0; i < 10; i++) {
            System.gc();
            System.runFinalization();
            long used = memoryUsed();
            if (mxBean.getObjectPendingFinalizationCount() == 0
                    && used >= lastUsed) {
                break;
            } else {
                lastUsed = used;
            }
        }
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
