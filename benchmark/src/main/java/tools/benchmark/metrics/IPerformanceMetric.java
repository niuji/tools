package tools.benchmark.metrics;

public interface IPerformanceMetric {
    /**
     * ��ʼһ�β���
     */
    public void start();
    /**
     * ����һ�β���
     */
    public void stop();
    /**
     * ����ͳ�ƽ��
     * @return
     */
    public String metricResult(int measureTimes);
}
