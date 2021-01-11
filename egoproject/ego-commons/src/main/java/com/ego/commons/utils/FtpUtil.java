package com.ego.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


/**
 * ftp�ϴ����ع�����
 */
public class FtpUtil {
	
	/**
	 * Description: ��FTP�������ϴ��ļ�
	 * @param host  FTP������hostname 
	 * @param port  FTP�������˿� 
	 * @param username  FTP��¼�˺� 
	 * @param password  FTP��¼���� 
	 * @param basePath  FTP����������Ŀ¼
	 * @param filePath  FTP�������ļ����·������������ڴ�ţ�/2018/01/01���ļ���·��ΪbasePath+filePath
	 * @param filename  �ϴ���FTP�������ϵ��ļ��� 
	 * @param input  ������ 
	 * @return  �ɹ�����true�����򷵻�false 
	 */
	public static boolean uploadFile(String host, int port, String username, String password, String basePath,
			String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// ����FTP������
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(host)�ķ�ʽֱ������FTP������
			ftp.login(username, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			//�л����ϴ�Ŀ¼
			if (!ftp.changeWorkingDirectory(basePath+filePath)) {
				//���Ŀ¼�����ڴ���Ŀ¼
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//ftp.enterLocalPassiveMode();
			//�����ϴ��ļ�������Ϊ����������
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//�ϴ��ļ�
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
				
				}
	
	/** 
	 * Description: ��FTP�����������ļ� 
	 * @param host FTP������hostname 
	 * @param port FTP�������˿� 
	 * @param username FTP��¼�˺� 
	 * @param password FTP��¼���� 
	 * @param remotePath FTP�������ϵ����·�� 
	 * @param fileName Ҫ���ص��ļ��� 
	 * @param localPath ���غ󱣴浽���ص�·�� 
	 * @return 
	 */  
	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// �������Ĭ�϶˿ڣ�����ʹ��ftp.connect(host)�ķ�ʽֱ������FTP������
			ftp.login(username, password);// ��¼
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// ת�Ƶ�FTP������Ŀ¼
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		try {  
	        FileInputStream in=new FileInputStream(new File("E:/bz.jpg"));  
	        boolean flag = uploadFile("192.168.198.139", 21, "ftpuser", "12345678", "/home/ftpuser/","/", "bz.jpg", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	}
}
