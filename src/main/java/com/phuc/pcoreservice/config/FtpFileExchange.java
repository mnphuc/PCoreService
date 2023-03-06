package com.phuc.pcoreservice.config;


import com.phuc.pcoreservice.service.impl.FTPServiceImpl;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class FtpFileExchange {

    private static final Logger logger = LoggerFactory.getLogger(FtpFileExchange.class);

    private final Integer TENSECONDS = 60 * 1000; // 10 second
    private final String P = "P";
    private final String SSL = "SSL";
    private final String TLS = "TLS";
    // mean directory path for default server
    private String directoryPath;
    private String host;
    private Integer port;
    private String user;
    private String password;

    private ModifiedFTPSClient ftpsClient;

    public FtpFileExchange() {
    }

    public String getHost() {
        return host;
    }

    public FtpFileExchange setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public FtpFileExchange setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getUser() {
        return user;
    }

    public FtpFileExchange setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public FtpFileExchange setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public FtpFileExchange setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
        return this;
    }

    public Boolean connectionOpen() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        Boolean isLogin = false;
        if (this.port > 100) {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                            return;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                            return;
                        }
                    }
            };
            SSLContext sc = SSLContext.getInstance(SSL);
            sc.init(null, trustAllCerts, null);
            this.ftpsClient = new ModifiedFTPSClient(true, sc);
        } else {
            // fro ftp
            this.ftpsClient = new ModifiedFTPSClient();
        }
        this.ftpsClient.setControlKeepAliveTimeout(TENSECONDS);
        this.showServerReply(this.ftpsClient);
        logger.info("FTP :- Connection try :- IP :- (" + this.host + ") , Port :- " + this.port + " Start");
        this.ftpsClient.connect(this.host, this.port);
        // show the real ftp logs
        //this.ftpsClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        logger.info("FTP :- Connection try :- IP :- (" + this.host + ") , Port :- " + this.port + " Done");
        int reply = this.ftpsClient.getReplyCode();
        logger.info("FTP :- Connection Code :- " + reply);
        if (!FTPReply.isPositiveCompletion(reply)) {
            this.ftpsClient.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        isLogin = this.ftpsClient.login(user, password);
        this.showServerReply(this.ftpsClient);
        logger.info("FTP :- Login Status :- " + isLogin);
        return isLogin;
    }

    public Boolean uploadFile(File file) throws Exception {
        Boolean isUpload = false;
        if (this.directoryPath != null) {
            if (!this.isDirectoryExist(this.directoryPath)) {
                throw new Exception("Directory Not Exist");
            }
            // if directory exist then change the directory
            this.ftpsClient.changeWorkingDirectory(this.directoryPath);
        }
        this.ftpsClient.enterLocalPassiveMode();
        this.ftpsClient.execPBSZ(0);
        this.ftpsClient.execPROT(P);
        this.ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);
        // show the directory where file exist
        logger.info("Current Directory " + this.ftpsClient.printWorkingDirectory());
        InputStream fileInputStream = new FileInputStream(file);
        String filePath = file.getName();
        logger.info("Final Path :- " + filePath);
        isUpload = this.ftpsClient.storeFile(filePath, fileInputStream);
        if (isUpload) {
            logger.info("The file is uploaded successfully.");
        }
        // close the stream
        fileInputStream.close();
        file.delete();
        return isUpload;
    }

    public InputStream downloadFile(String directoryPath) throws Exception {
        if (directoryPath == null) {
            throw new NullPointerException("Directory Path Null");
        }
        return this.ftpsClient.retrieveFileStream(directoryPath);
    }

    public Boolean isDirectoryExist(String directoryPath) throws IOException {
        Boolean isDirectory = false;
        if (this.ftpsClient.cwd(directoryPath) == 550) {
            logger.error("Directory Doesn't Exists");
            isDirectory = false;
        } else if (this.ftpsClient.cwd(directoryPath) == 250) {
            isDirectory = true;
            logger.info("Directory Exists");
        } else {
            isDirectory = false;
            logger.error("Unknown Status");
        }
        this.showServerReply(this.ftpsClient);
        return isDirectory;
    }

    public Boolean createDirectory(String directoryPath) throws IOException {
        if (this.isDirectoryExist(directoryPath)) {
            logger.error("Directory Already Exist");
            return false;
        }
        return this.ftpsClient.makeDirectory(directoryPath);
    }

    private void showServerReply(FTPSClient ftpsClient) {
        String[] replies = ftpsClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                logger.info("SERVER: " + aReply);
            }
        }
    }

    // connection close for client
    public void close() throws IOException {
        if (this.ftpsClient.isConnected()) {
            this.ftpsClient.logout();
            this.ftpsClient.disconnect();
            this.showServerReply(this.ftpsClient);
        }
    }
}
