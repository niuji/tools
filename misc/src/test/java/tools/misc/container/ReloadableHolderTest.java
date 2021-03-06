package tools.misc.container;

import org.apache.commons.io.FileUtils;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: jiangyixin.stephen
 * Date: 2013-06-24 11:31
 */
public class ReloadableHolderTest {
    @org.junit.Test
    public void testPut() throws Exception {
        ReloadableHolder holder = new ReloadableHolder();
        File f = File.createTempFile("test", "txt");
        FileUtils.write(f, "test", "UTF-8");
        holder.put("test", f);
        assertThat(new String((byte[])holder.get("test")), is("test"));
    }

    @org.junit.Test
    public void testGet() throws Exception {
        ReloadableHolder holder = new ReloadableHolder();
        File f = File.createTempFile("test", "txt");
        FileUtils.write(f, "test", "UTF-8");
        holder.put("test", f);
        assertThat(new String((byte[])holder.get("test")), is("test"));
        Thread.sleep(100);
        FileUtils.write(f, "test1", "UTF-8");
        assertThat(new String((byte[])holder.get("test")), is("test1"));
    }
}
