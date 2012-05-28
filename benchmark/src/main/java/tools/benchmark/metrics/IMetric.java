package tools.benchmark.metrics;

public interface IMetric {
    /**
     * 执行测量的校准，在进行metric测量之前执行
     */
    public void calibrate();
    /**
     * 开始记录
     */
    public void start();
    /**
     * 停止记录
     */
    public void stop();
    /**
     * 格式化metric记录结果
     * @return
     */
    public String metricResult(int measureTimes);
}
