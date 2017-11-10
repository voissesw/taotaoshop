import com.voissesw.common.ftp.FtpUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by YC on 2017/11/6.
 */
public class FTPTest {
    FileInputStream stream = new FileInputStream("D:\\腾讯游戏\\英雄联盟\\Air\\assets\\storeImages\\content\\skins\\championsskin_57010.jpg");

    public FTPTest() throws FileNotFoundException {
    }

    @Test
    public void testFtpClient() throws IOException {
        System.out.println(FtpUtil.uploadFile("ftp.voissesw.com", 21, "ftpuser", "admin", "/home/ftpuser/upload", "/2017/11/6", "1.jpg", stream));
//        FTPClient ftpClient = new FTPClient();
//        ftpClient.connect("192.168.199.118",21);
//        ftpClient.login("ftpuser","admin");
//        System.out.println(ftpClient.changeWorkingDirectory("/home/ftpuser/upload"));
//        System.out.println(ftpClient.makeDirectory("/home/ftpuser/upload/test"));
//        ftpClient.storeFile("1.jpg",stream);
    }
}
