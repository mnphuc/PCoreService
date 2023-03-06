package com.phuc.pcoreservice.service.impl;

import com.phuc.pcoreservice.config.ModifiedFTPSClient;
import com.phuc.pcoreservice.controller.FingerprintController;
import com.phuc.pcoreservice.service.IFTPService;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import java.security.cert.CertificateException;

@Service
public class FTPServiceImpl implements IFTPService {

    private static final Logger logger = LoggerFactory.getLogger(FTPServiceImpl.class);


    private final String P = "P";

    private final String directoryPath = "/upload";

    private final Integer TENSECONDS = 10 * 1000;

    private final String SSL = "SSL";
    private final String TLS = "TLS";

    @Value("${ftp.server.host}")
    private String host;
    @Value("${ftp.server.port}")
    private Integer port;
    @Value("${ftp.server.username}")
    private String user;
    @Value("${ftp.server.password}")
    private String password;
    private ModifiedFTPSClient ftpsClient;

    @Override
    public Boolean openFTP() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Boolean isLogin = false;
//        if(this.port > 100) {
//            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            TrustManager[] trustAllCerts = new TrustManager[][] {
//                    new X509TrustManager() {
//                        public X509Certificate[] getAcceptedIssuers() { return null; }
//                        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException { return; }
//                        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException { return; }
//                    }
//            };
//            SSLContext sc = SSLContext.getInstance(SSL);
//            sc.init(null, trustAllCerts, null);
//            this.ftpsClient  = new ModifiedFTPSClient(true, sc);
//        } else {
//            // fro ftp
        this.ftpsClient = new ModifiedFTPSClient();
//        }
        this.ftpsClient.setControlKeepAliveTimeout(TENSECONDS);
        this.showServerReply(this.ftpsClient);
        System.out.println("FTP :- Connection try :- IP :- (" + this.host + ") , Port :- " + this.port + " Start");
        this.ftpsClient.connect(this.host, this.port);
        // show the real ftp logs
        //this.ftpsClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        System.out.println("FTP :- Connection try :- IP :- (" + this.host + ") , Port :- " + this.port + " Done");
        int reply = this.ftpsClient.getReplyCode();
        System.out.println("FTP :- Connection Code :- " + reply);
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.ftpsClient.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        isLogin = this.ftpsClient.login(user, password);
        this.showServerReply(this.ftpsClient);
        System.out.println("FTP :- Login Status :- " + isLogin);
        return isLogin;
    }

    @Override
    public FTPSClient loginFtp(String host, int port, String username, String password) throws Exception {
        FTPSClient ftpClient = new FTPSClient();
        ftpClient.addProtocolCommandListener(new ProtocolCommandListener() {
            @Override
            public void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent) {
                logger.info(String.format("[%s][%d] Command sent : [%s]-%s", Thread.currentThread().getName(),
                        System.currentTimeMillis(), protocolCommandEvent.getCommand(),
                        protocolCommandEvent.getMessage()));
            }

            @Override
            public void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent) {
                logger.info(String.format("[%s][%d] Reply received : %s", Thread.currentThread().getName(),
                        System.currentTimeMillis(), protocolCommandEvent.getMessage()));
            }
        });
        ftpClient.connect(host, port);
        ftpClient.login(username, password);
        ftpClient = new ModifiedFTPSClient();
        ftpClient.setControlKeepAliveTimeout(TENSECONDS);

        return ftpClient;
    }

    public void printTree(String path, FTPSClient ftpClient) throws Exception {
        for (FTPFile ftpFile : ftpClient.listFiles(path)) {
            System.out.println();
            System.out.printf("[printTree][%d]\n", System.currentTimeMillis());
            System.out.printf("[printTree][%d] Get name : %s \n", System.currentTimeMillis(), ftpFile.getName());
            System.out.printf("[printTree][%d] Get timestamp : %s \n", System.currentTimeMillis(), ftpFile.getTimestamp().getTimeInMillis());
            System.out.printf("[printTree][%d] Get group : %s \n", System.currentTimeMillis(), ftpFile.getGroup());
            System.out.printf("[printTree][%d] Get link : %s \n", System.currentTimeMillis(), ftpFile.getLink());
            System.out.printf("[printTree][%d] Get user : %s \n", System.currentTimeMillis(), ftpFile.getUser());
            System.out.printf("[printTree][%d] Get type : %s \n", System.currentTimeMillis(), ftpFile.getType());
            System.out.printf("[printTree][%d] Is file : %s \n", System.currentTimeMillis(), ftpFile.isFile());
            System.out.printf("[printTree][%d] Is directory : %s \n", System.currentTimeMillis(), ftpFile.isDirectory());
            System.out.printf("[printTree][%d] Formatted string : %s \n", System.currentTimeMillis(), ftpFile.toFormattedString());
            System.out.println();

            if (ftpFile.isDirectory()) {
                printTree(path + File.separator + ftpFile.getName(), ftpClient);
            }
        }
    }

    @Override
    public void createDirectory(String path, FTPSClient ftpClient) throws Exception {
        System.out.println();
        System.out.printf("[createDirectory][%d] Is success to create directory : %s -> %b",
                System.currentTimeMillis(), path, ftpClient.makeDirectory(path));
        System.out.println();
    }

    @Override
    public void uploadFile(File file, String remotePath, FTPSClient ftpClient) throws Exception {
        InputStream fileInputStream = new FileInputStream(file);
        Boolean statusUpload = ftpClient.storeFile(remotePath, fileInputStream);
        logger.info(String.format("[uploadFile][%d] Is success to upload file : %s -> %b", System.currentTimeMillis(), remotePath, statusUpload));
    }

    @Override
    public Boolean uploadFile(File file, FTPSClient ftpClient) throws Exception {
        Boolean isUpload = false;
        if (this.directoryPath != null) {
            if (!this.isDirectoryExist(this.directoryPath, ftpClient)) {
                throw new Exception("Directory Not Exist");
            }
            // if directory exist then change the directory
            ftpClient.changeWorkingDirectory(this.directoryPath);
        }
        ftpClient.enterLocalPassiveMode();
        ftpClient.execPBSZ(0);
        ftpClient.execPROT(P);
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        // show the directory where file exist
        System.out.println("Current Directory " + ftpClient.printWorkingDirectory());
        InputStream fileInputStream = new FileInputStream(file);
        String filePath = file.getName();
        System.out.println("Final Path :- " + filePath);
        isUpload = ftpClient.storeFile(filePath, fileInputStream);
        if (isUpload) {
            System.out.println("The file is uploaded successfully.");
        }
        // close the stream
        fileInputStream.close();
        file.delete();
        return isUpload;
    }

    private Boolean isDirectoryExist(String directoryPath, FTPSClient ftpClient) throws IOException {
        Boolean isDirectory = false;
        if (ftpClient.cwd(directoryPath) == 550) {
            System.out.println("Directory Doesn't Exists");
            isDirectory = false;
        } else if (ftpClient.cwd(directoryPath) == 250) {
            isDirectory = true;
            System.out.println("Directory Exists");
        } else {
            isDirectory = false;
            System.out.println("Unknown Status");
        }
        this.showServerReply(ftpClient);
        return isDirectory;
    }

    private void showServerReply(FTPSClient ftpsClient) {
        String[] replies = ftpsClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                logger.info("SERVER: " + aReply);
            }
        }
    }

    @Override
    public void renameFile(String oldPath, String newPath, FTPSClient ftpClient) throws Exception {
        System.out.println();
        System.out.printf("[renameFile][%d] Is success to rename file : from %s to %s -> %b",
                System.currentTimeMillis(), oldPath, newPath, ftpClient.rename(oldPath, newPath));
        System.out.println();
    }

    @Override
    public byte[] downloadFile(String path, FTPSClient ftpClient) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.out.println();
        System.out.printf("[downloadFile][%d] Is success to download file : %s -> %b",
                System.currentTimeMillis(), path, ftpClient.retrieveFile(path, byteArrayOutputStream));
        System.out.println();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void deleteFile(String path, FTPSClient ftpClient) throws Exception {
        System.out.println();
        System.out.printf("[deleteFile][%d] Is success to delete file : %s -> %b",
                System.currentTimeMillis(), path, ftpClient.deleteFile(path));
        System.out.println();
    }

    @Override
    public void deleteDirectory(String path, FTPSClient ftpClient) throws Exception {
        System.out.println();
        System.out.printf("[deleteDirectory][%d] Is success to delete directory : %s -> %b",
                System.currentTimeMillis(), path, ftpClient.removeDirectory(path));
        System.out.println();
    }


}
