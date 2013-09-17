import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import static org.apache.hadoop.fs.permission.FsPermission.createImmutable;

/**
 * Created with IntelliJ IDEA.
 * User: pairing
 * Date: 9/17/13
 * Time: 11:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileCopyForHive {

    public void copy(String localSrc, String dst) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(localSrc));
            Configuration conf = new Configuration();
            FileSystem fs = FileSystem.get(URI.create(dst), conf);
            out = fs.create(fs, new Path(dst), createImmutable((short) 0777));
            IOUtils.copyBytes(in, out, 4096, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                IOUtils.closeStream(in);
            }
            if (out != null) {
                IOUtils.closeStream(out);
            }
        }
    }
}
