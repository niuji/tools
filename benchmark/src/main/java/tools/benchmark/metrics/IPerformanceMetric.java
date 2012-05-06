package tools.benchmark.metrics;

public interface IPerformanceMetric {
    /**
     * 开始一次测量
     */
    public void start();
    /**
     * 结束一次测量
     */
    public void stop();
    /**
     * 返回统计结果
     * @return
     */
    public String metricResult(int measureTimes);
}
