package tools.misc.container;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: jiangyixin.stephen
 * Date: 2013-06-24 10:38
 */
public class ReloadableFileHolder {
    private Map<Object, Object> backedMap;

    public ReloadableFileHolder(){
        this(new HashMap<>());
    }

    public ReloadableFileHolder(HashMap backedMap) {
        this.backedMap = backedMap;
    }

    public void put(Object key, File file){
        backedMap.put(key, new ReloadableFileItem(file));
    }

    public String get(Object key) throws IOException {
        return get(key, "UTF-8");
    }

    public String get(Object key, String encoding) throws IOException {
        ReloadableFileItem obj = (ReloadableFileItem)backedMap.get(key);
        return obj == null ? null : obj.readString(encoding);
    }

    class ReloadableFileItem {
        private File file;
        private long lastModified;
        private String contentString;
        private byte[] contentBytes;
        public ReloadableFileItem(File file) {
            this.file = file;
            this.lastModified = -1;
        }

        public String readString(String encoding) throws IOException {
            if(file.lastModified() > lastModified){
                lastModified = file.lastModified();
                contentString = FileUtils.readFileToString(file, encoding);
            }
            return contentString;
        }
    }
}
