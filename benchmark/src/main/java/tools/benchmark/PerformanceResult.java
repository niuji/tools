package tools.benchmark;

import java.util.ArrayList;
import java.util.List;

import tools.benchmark.metrics.IPerformanceMetric;

public class PerformanceResult {
    private int measureTimes;
    private int threads;
    private List<IPerformanceMetric> metrics = new ArrayList<>();

    public PerformanceResult(int measureTimes, int threads) {
        this.measureTimes = measureTimes;
        this.threads = threads;
    }

    public void addMetric(IPerformanceMetric metric) {
        metrics.add(metric);
    }

    public String getFormatedResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "run %d measurements %d times using %d threads.%n",
                metrics.size(), measureTimes, threads));
        for (IPerformanceMetric metric : metrics) {
            sb.append(metric.metricResult(measureTimes)).append('\n');
        }
        return sb.toString();
    }
}
