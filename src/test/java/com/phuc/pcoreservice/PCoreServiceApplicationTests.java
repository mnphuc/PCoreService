package com.phuc.pcoreservice;

import com.phuc.pcoreservice.config.FtpFileExchange;
import com.phuc.pcoreservice.service.IFTPService;
import com.phuc.pcoreservice.service.impl.FTPServiceImpl;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootTest

class PCoreServiceApplicationTests {

    @Autowired
    private FTPServiceImpl service;

    @Test
    void contextLoads() throws Exception {
//        long currentTimeMillis = System.currentTimeMillis();
//        String directoryPath = String.format("/create-directory-test-%d", currentTimeMillis);
//        String path = String.format("/gmailte.txt", currentTimeMillis);
//        String newPath = String.format("/test-rename-file-%d.jpg", currentTimeMillis);
//        FTPSClient ftpClient = service.loginFtp("supreme-007.seedbox.vip", 21, "rapidseedbox53134", "4ff5c01ef00a");
////        service.printTree("/", ftpClient);
////        service.createDirectory(directoryPath, ftpClient);
//        Path uploadPath = Paths.get("file/fingerprint");
//        File file = new File(uploadPath + "/" + "phucmans.json");
//        FileWriter writer = new FileWriter(file);
//        writer.write("json");
//        writer.close();
//        service.uploadFile(file, ftpClient);


//
//        FtpFileExchange ftpExchangeFile = new FtpFileExchange();
//        ftpExchangeFile.setHost("supreme-007.seedbox.vip").setPort(21).setUser("rapidseedbox53134").setPassword("4ff5c01ef00a");
//        ftpExchangeFile.connectionOpen();
//
//        System.out.println("Directory Status :- " + ftpExchangeFile.isDirectoryExist("/upload"));
//        System.out.println("Directory Create Status :- " +  ftpExchangeFile.createDirectory("/upload"));
//        System.out.println("Directory Status :- " + ftpExchangeFile.isDirectoryExist("/upload"));
//        ftpExchangeFile.setDirectoryPath("/upload");
//        ftpExchangeFile.uploadFile(new File("C:\\Users\\phucm\\Downloads\\b4352345068bfb2e227b2d7f921af990-576655397d6e5b5170c6f2bcd72027f8a6fb4ca0.zip"));
//        ftpExchangeFile.close();

//        service.renameFile(path, newPath, ftpClient);
//        byte[] downloadFile = service.downloadFile(newPath, ftpClient);
//        System.out.println("Downloaded file : " + new String(downloadFile));
//        service.deleteFile(newPath, ftpClient);
//        service.deleteDirectory(directoryPath, ftpClient);
//        ftpClient.disconnect();
    }

//    @Test
    public void testString(){
        String str = "HC Gaming Almira TV Top 1 rank Valorant day 63,https://www.youtube.com/watch?v=BFsv7gwlKTg\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 62,https://www.youtube.com/watch?v=JMxj5jR1DQ0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 61,https://www.youtube.com/watch?v=GJ8iMCFYBXA\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 60,https://www.youtube.com/watch?v=EaV7mLW5xTg\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 59,https://www.youtube.com/watch?v=3oj7_-wj5x0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 58,https://www.youtube.com/watch?v=YZZDIC1oEPM\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 57,https://www.youtube.com/watch?v=sK4ZdXSveE0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 56,https://www.youtube.com/watch?v=OQ-QNwly_o4\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 55,https://www.youtube.com/watch?v=3rAIAjiQPlw\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 55,https://www.youtube.com/watch?v=6xRydy0zmmI\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 54,https://www.youtube.com/watch?v=zwwkwSzNuaE\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 53,https://www.youtube.com/watch?v=aWFuiXTxntA\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 52,https://www.youtube.com/watch?v=o5oAOH0vKJ4\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 51,https://www.youtube.com/watch?v=Z9sZBRTOJD0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 50,https://www.youtube.com/watch?v=3rD7m63Isuw\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 49,https://www.youtube.com/watch?v=lI_CjW37DPQ\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 48,https://www.youtube.com/watch?v=o6nDAiLPUII\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 47,https://www.youtube.com/watch?v=WJ_pZdgmPE0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 46,https://www.youtube.com/watch?v=2CC1knPUss4\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 45,https://www.youtube.com/watch?v=aVyWKzhSNmI\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 44,https://www.youtube.com/watch?v=boGCkBE2v5s\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 43,https://www.youtube.com/watch?v=g2BRqEP0Fh8\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 42,https://www.youtube.com/watch?v=HiI70L9HZ_s\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 41,https://www.youtube.com/watch?v=BXbLd1hJhMU\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 40,https://www.youtube.com/watch?v=QiuPFATMtM0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 39,https://www.youtube.com/watch?v=ELwc2pAEmN0\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 38,https://www.youtube.com/watch?v=vc0tWRdZ4yo\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 37,https://www.youtube.com/watch?v=IYxxsEWeP1Y\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 36,https://www.youtube.com/watch?v=VZEZ8bz-v3g\n" +
                "HC Gaming Almira TV Top 1 rank Valorant day 35,https://www.youtube.com/watch?v=BvPegqQqXYQ\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 34,https://www.youtube.com/watch?v=uJ08KgJykuA\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 33,https://www.youtube.com/watch?v=GZRJVO_X6fc\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 32,https://www.youtube.com/watch?v=xwSI1hAap-A\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 31,https://www.youtube.com/watch?v=WqNtY8pOD44\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 30,https://www.youtube.com/watch?v=J2ag_deu_AI\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 29,https://www.youtube.com/watch?v=d02LyUJ5JMs\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 28,https://www.youtube.com/watch?v=w3Kta4zQlYw\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 27,https://www.youtube.com/watch?v=cW4NBU_-HOs\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 26,https://www.youtube.com/watch?v=-WeUGBENYYE\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 25,https://www.youtube.com/watch?v=-ulYwOnNoW4\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 22,https://www.youtube.com/watch?v=nf6cvj4bTuQ\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 20,https://www.youtube.com/watch?v=FltEzz_vByc\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 21,https://www.youtube.com/watch?v=VN-R-thia2w\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 19,https://www.youtube.com/watch?v=kNBa_OLeAaU\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 17,https://www.youtube.com/watch?v=DC4lMIJNPfc\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 18,https://www.youtube.com/watch?v=7cA1NAXuVrM\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 15,https://www.youtube.com/watch?v=669tHb4mKao\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 16,https://www.youtube.com/watch?v=P4NacTO90D4\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 14,https://www.youtube.com/watch?v=GNbBePbjmNo\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 4,https://www.youtube.com/watch?v=5yM__JHK8H4\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 6,https://www.youtube.com/watch?v=80IghZ812sg\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 2,https://www.youtube.com/watch?v=YhKKJxJpw4Y\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 1,https://www.youtube.com/watch?v=mzMXTbE6XHA\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 55,https://www.youtube.com/watch?v=zndTHgA6X3s\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 54,https://www.youtube.com/watch?v=JSng26puk6I\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 53,https://www.youtube.com/watch?v=jMmx6fZCnJw\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 52,https://www.youtube.com/watch?v=2sKqKfiDBd4\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 51,https://www.youtube.com/watch?v=B-s7qFY0oNk\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 50,https://www.youtube.com/watch?v=HODKyeq2UJ0\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 49,https://www.youtube.com/watch?v=HyGyeBUzg-Y\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 48,https://www.youtube.com/watch?v=dswIiLsdSeA\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 47,https://www.youtube.com/watch?v=bVVUTegQrUM\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 46,https://www.youtube.com/watch?v=_GFc3Wq5KB0\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 45,https://www.youtube.com/watch?v=TvM9mt-XSzo\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 44,https://www.youtube.com/watch?v=zeC9ZbDfxh8\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 43,https://www.youtube.com/watch?v=P0sI9aE9XbQ\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 42,https://www.youtube.com/watch?v=rnB5UEHzGhI\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 41,https://www.youtube.com/watch?v=gYwNAxsF6WM\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 40,https://www.youtube.com/watch?v=eEBqZC8V-5M\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 39,https://www.youtube.com/watch?v=Wl7iPgvog_E\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 38,https://www.youtube.com/watch?v=MajPCJnyCJU\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 37,https://www.youtube.com/watch?v=pr_UpuDzm0M\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 36,https://www.youtube.com/watch?v=XngM2evwLGo\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 35,https://www.youtube.com/watch?v=_97gKkrqehI\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 33,https://www.youtube.com/watch?v=XNfa8zLra8c\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 32,https://www.youtube.com/watch?v=Xd6feNE3thE\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 31,https://www.youtube.com/watch?v=Mcn8tuNEyHE\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 30,https://www.youtube.com/watch?v=9vS2AJrMHww\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 29,https://www.youtube.com/watch?v=Abmayh1KEbc\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 27,https://www.youtube.com/watch?v=6-j3fkB1l3s\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 26,https://www.youtube.com/watch?v=rjO0jb8oAm8\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 25,https://www.youtube.com/watch?v=Y-O9kqOUFcc\n" +
                "HC Gaming Florence TV Top 1 rank Valorant day 24,https://www.youtube.com/watch?v=b39qWRIUgqI\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 20,https://www.youtube.com/watch?v=1ubYMAPraGQ\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 23,https://www.youtube.com/watch?v=2aEMK3t0VbI\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 21,https://www.youtube.com/watch?v=THU72XKg6LE\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 16,https://www.youtube.com/watch?v=ibRwQWmFO30\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 17,https://www.youtube.com/watch?v=iAvjUlYBf5g\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 19,https://www.youtube.com/watch?v=XDCP-YLm6Po\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 18,https://www.youtube.com/watch?v=_IjnQndefbY\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 15,https://www.youtube.com/watch?v=EHzZbcZX5AY\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 14,https://www.youtube.com/watch?v=X5vlBeeInk0\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 13,https://www.youtube.com/watch?v=Yv2Vj-UvYXA\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 12,https://www.youtube.com/watch?v=h-TkwD-dVmE\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 11,https://www.youtube.com/watch?v=bRWKyMVQsug\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 10,https://www.youtube.com/watch?v=h2uAWjhvu-E\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 9,https://www.youtube.com/watch?v=zrIhYricAgI\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 8,https://www.youtube.com/watch?v=_w8xDWya6dc\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 6,https://www.youtube.com/watch?v=NN5-MxVU3Ys\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 7,https://www.youtube.com/watch?v=zp2XSkcVxTQ\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 4,https://www.youtube.com/watch?v=GpkA45tAvSE\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 2,https://www.youtube.com/watch?v=DDyLhHyIDXc\n" +
                "HC Gaming Ariadne TV Top 1 rank Valorant day 1,https://www.youtube.com/watch?v=WpEDKYNEsho\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú trải nghiệm bản cập nhập mới part 22,https://www.youtube.com/watch?v=3Hhx4VtJfWk\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú get gô,https://www.youtube.com/watch?v=rL3U6f174P4\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // cân team,https://www.youtube.com/watch?v=Z-M6pbv_mJo\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // đi ăn hành,https://www.youtube.com/watch?v=yGvyL8ZqgrU\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Test tướng thôi nào,https://www.youtube.com/watch?v=z_qpTb4KfK4\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // một mình lặng thầm trong đêm,https://www.youtube.com/watch?v=IIjEVf_QFg8\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // bộ 3 tấu hài part 3,https://www.youtube.com/watch?v=9_aGkvQlTNk\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // bộ 3 tấu hài part 2,https://www.youtube.com/watch?v=4A1FeZtl9zI\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // bộ 3 tấu hài,https://www.youtube.com/watch?v=Ttqz73fuWqE\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // lại gấp tiếp thôi nào,https://www.youtube.com/watch?v=oRXRl0xSnWI\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Team 3 toàn tạ,https://www.youtube.com/watch?v=SeDja9WlWXo\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Tiếp tục gấp,https://www.youtube.com/watch?v=ER3_Vj4bNYc\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // thua hơi nhiều rồi,https://www.youtube.com/watch?v=FA5OablcnQw\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // quẩy tiếp thôi,https://www.youtube.com/watch?v=T4IIRazWQow\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // làm vài game để đi ngủ nào,https://www.youtube.com/watch?v=7AJ8-VhVXQI\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Chiều rảnh làm vài game nào,https://www.youtube.com/watch?v=84T8IFsRZpE\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Quẩy cùng em nhà,https://www.youtube.com/watch?v=vNP8nQjDz-8\n" +
                "Long Ka TV Live Stream // Team 3 vực gió hú - Aram quẩy thôi nào,https://www.youtube.com/watch?v=JIRojFz8-So\n" +
                "Long Ka TV Live Stream // tấu hài cùng đồng đội,https://www.youtube.com/watch?v=ctrfYI57-mw\n" +
                "Long Ka TV Live Stream // Vực gió hú - Aram tấu hài nào,https://www.youtube.com/watch?v=m-bEKJVs8as\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Chơi game cùng mình nào,https://www.youtube.com/watch?v=uMESbnzRYY4\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Team 3 Đến giờ quẩy rồi,https://www.youtube.com/watch?v=BBUteDjYTNo\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Đến giờ quẩy rồi,https://www.youtube.com/watch?v=ySTkxFuYgXU\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Chiến thôi nào,https://www.youtube.com/watch?v=w-eTWoMTL6U\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú // Quẩy tiếp nào,https://www.youtube.com/watch?v=cvWEWZHkOSA\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú Bộ 3 tấu hài,https://www.youtube.com/watch?v=-VZHFVYxZd8\n" +
                "Long Ka TV Live Stream // Aram - vực gió hú Chơi game cùng mình nào,https://www.youtube.com/watch?v=q1EhROHHyXg\n" +
                "Long Ka TV Live Stream // Buối chiều vui vẻ cùng vài game aram nào,https://www.youtube.com/watch?v=LS6ajr68ka0\n" +
                "Long Ka TV Live Stream // Quẩy Aram thôi nào,https://www.youtube.com/watch?v=3ctgo0vb8-g\n" +
                "Xạ thủ bất lực trước những trang bị thần thoại trong bản cập nhập mới // long Ka TV,https://www.youtube.com/watch?v=KqCqQ_CoaWo\n" +
                "Long Ka TV Live Stream // Mưa gió làm vài ván game nào,https://www.youtube.com/watch?v=oBz3_7SnW9s\n" +
                "Long Ka TV Live Stream // Vực gió hú - Aram nữa nào,https://www.youtube.com/watch?v=5xMQQiJXdRI\n" +
                "lllaoi chế độ aram Chơi game là chính mà tấu hài là chủ yếu,https://www.youtube.com/watch?v=6_Zt2oMGLxA\n" +
                "Long Ka TV Live Stream // Chốt hạng chuyển qua Aram,https://www.youtube.com/watch?v=7EqerqIX_q8\n" +
                "Long Ka TV Live Stream // Vực gió hú ăn hành,https://www.youtube.com/watch?v=hvGy8RMgIhs\n" +
                "Ngập hành cùng team khi vác Jinx vào chế độ Aram | Long Ka TV,https://www.youtube.com/watch?v=bHafg0Tg-ew\n" +
                "Long Ka TV Live Stream // Vực gió hú - Aram ăn hành,https://www.youtube.com/watch?v=OII5dtdTgC8\n" +
                "Long Ka TV Live Stream // Chế độ Aram làm nhiệm vụ sự kiện,https://www.youtube.com/watch?v=mdQmyChiWD4\n" +
                "Cassiopeia cùng team trong chế độ Aram | Long Ka TV,https://www.youtube.com/watch?v=R8iFRWvdv1s\n" +
                "Quấy nát team bạn với con bài tủ Darius trong chế độ URF | Long Ka TV,https://www.youtube.com/watch?v=mZITXXraAH0\n";

        String[] arr = str.split("\n");
        for (String s : arr) {
            String[] a = s.split(",");
            System.out.println(a[0]);
        }
    }

}
