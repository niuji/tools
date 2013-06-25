package tools.misc.container.provider;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * User: jiangyixin.stephen
 * Date: 2013-06-25 13:23
 */
public class FileContentProvider implements ReloadableContentProvider<File> {
    @Override
    public Comparable getVersion(File obj) {
        return obj.lastModified();
    }

    @Override
    public Object getContent(File obj, Object extraParam) throws Exception {
        return FileUtils.readFileToByteArray(obj);
    }

    @Override
    public Comparable getDefaultVersion() {
        return Long.MIN_VALUE;
    }
}
