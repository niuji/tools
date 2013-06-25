package tools.misc.container;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.misc.container.provider.ReloadableContentProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jiangyixin.stephen
 * Date: 2013-06-24 10:38
 */
public class ReloadableHolder<T> {
    private static final Logger logger = LoggerFactory.getLogger(ReloadableHolder.class);
    private static Map<Class<?>, ReloadableContentProvider<?>> cpMap;

    static {
        cpMap = new HashMap<>();
        try {
            Enumeration<URL> urls = ReloadableHolder.class.getClassLoader().getResources("META-INF/devtools/tools.misc.container.provider.ReloadableContentProvider");
            if (urls != null) {
                while (urls.hasMoreElements()) {
                    URL url = urls.nextElement();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
                        try {
                            List<String> lines = IOUtils.readLines(reader);
                            String tmp;
                            for (String line : lines) {
                                tmp = line;
                                final int ci = line.indexOf('#');
                                if (ci >= 0) line = line.substring(0, ci);
                                line = line.trim();
                                if (line.length() > 0) {
                                    try {
                                        String name = null;
                                        int i = line.indexOf('=');
                                        if (i > 0) {
                                            name = line.substring(0, i).trim();
                                            line = line.substring(i + 1).trim();
                                        }
                                        if (line.length() > 0) {
                                            Class<?> key = Class.forName(name);
                                            ReloadableContentProvider cp = (ReloadableContentProvider) Class.forName(line).newInstance();
                                            cpMap.put(key, cp);
                                        }
                                    } catch (Exception e) {
                                        logger.warn("Failed to load ReloadableContentProvider from line: " + tmp, e);
                                    }
                                }
                            }
                        } finally {
                            reader.close();
                        }
                    } catch (Exception e) {
                        logger.warn("Failed to load contents from " + url, e);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Exception when initialize ReloadableHolder.class.", e);
        }
    }

    private Map<Object, Object> backedMap;

    public ReloadableHolder() {
        this(new HashMap<>());
    }

    public ReloadableHolder(HashMap backedMap) {
        this.backedMap = backedMap;
    }

    public void put(Object key, T value) {
        put(key, value, getDefaultProvider(value));
    }

    public void put(Object key, T value, ReloadableContentProvider cp) {
        backedMap.put(key, new ReloadableItem<T>(value, cp));
    }

    public Object get(Object key) throws Exception {
        return get(key, null);
    }

    public Object get(Object key, Object extraParam) throws Exception {
        ReloadableItem obj = (ReloadableItem) backedMap.get(key);
        return obj == null ? null : obj.getContent(extraParam);
    }

    private ReloadableContentProvider getDefaultProvider(T value) {
        return cpMap.get(value.getClass());
    }

    class ReloadableItem<T> {
        private T item;
        private Comparable version;
        private Object content;
        private ReloadableContentProvider cp;

        public ReloadableItem(T item, ReloadableContentProvider cp) {
            this.item = item;
            this.cp = cp;
            this.version = cp.getDefaultVersion();
        }

        public Object getContent(Object extraParam) throws Exception {
            Comparable newVersion = cp.getVersion(item);
            if (newVersion.compareTo(version) > 0) {
                version = newVersion;
                content = cp.getContent(item, extraParam);
            }
            return content;
        }
    }
}
