import com.voissesw.common.ftp.FtpUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by YC on 2017/11/6.
 */
public class FTPTest {
    FileInputStream stream = new FileInputStream("C:\\Users\\YC\\Desktop\\p专业软件\\QQ图片20150611115548.gif");

    public FTPTest() throws FileNotFoundException {
    }

    @Test
    public void testFtpClient() throws IOException {
        System.out.println(FtpUtil.uploadFile("192.168.199.118", 21, "ftpuser", "admin", "/home/ftpuser/upload", "/2017/11/6", "1.jpg", stream));
//        FTPClient ftpClient = new FTPClient();
//        ftpClient.connect("192.168.199.118",21);
//        ftpClient.login("ftpuser","admin");
//        System.out.println(ftpClient.changeWorkingDirectory("/home/ftpuser/upload"));
//        System.out.println(ftpClient.makeDirectory("/home/ftpuser/upload/test"));
//        ftpClient.storeFile("1.jpg",stream);
    }
}
