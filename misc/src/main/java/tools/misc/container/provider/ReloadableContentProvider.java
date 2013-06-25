package tools.misc.container.provider;

/**
 * User: jiangyixin.stephen
 * Date: 2013-06-25 11:01
 */
public interface ReloadableContentProvider<T> {
    /**
     * 获得版本值
     * @param obj
     * @return
     */
    public Comparable getVersion(T obj);

    /**
     * 获得实际的内容
     * @param obj
     * @return
     */
    public Object getContent(T obj, Object extraParam) throws Exception;

    /**
     * 获得缺省版本值。必须是个最小值，否则后续的更新不能保证能获取到。（比如Integer.MIN_VALUE）
     * @return
     */
    public Comparable getDefaultVersion();
}
