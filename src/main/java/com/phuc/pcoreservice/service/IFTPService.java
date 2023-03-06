package com.phuc.pcoreservice.service;

import org.apache.commons.net.ftp.FTPSClient;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface IFTPService {

    Boolean openFTP() throws IOException, NoSuchAlgorithmException, KeyManagementException;

    FTPSClient loginFtp(String host, int port, String username, String password) throws Exception;

    void createDirectory(String path, FTPSClient ftpClient) throws Exception;

    void uploadFile(File file, String remotePath, FTPSClient ftpClient) throws Exception;


    void renameFile(String oldPath, String newPath, FTPSClient ftpClient) throws Exception;


    byte[] downloadFile(String path, FTPSClient ftpClient) throws Exception;

    void deleteFile(String path, FTPSClient ftpClient) throws Exception;


    void deleteDirectory(String path, FTPSClient ftpClient) throws Exception;

    Boolean uploadFile(File file, FTPSClient ftpClient) throws Exception;
}
