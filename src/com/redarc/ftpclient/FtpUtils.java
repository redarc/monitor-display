package com.redarc.ftpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUtils {
	public boolean upload(File file){
		boolean ret = false;
		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;
		try{
			ftpClient.connect("10.186.135.173");
			ftpClient.login("root", "root");
			if(!FTPReply.isPositiveCompletion(ftpClient.getReply())){
				ret = false;
				System.out.println("FTP connect error!");
			}else{
				fis = new FileInputStream(file);
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("GBK");
				ftpClient.changeWorkingDirectory("/opt/3party_software/apache/htdocs");
				ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
				boolean isStore = ftpClient.storeFile(file.getName(),fis);
				if(isStore){
					System.out.println(file.getName() + "upload success");
					ret = true;
				}else{
					System.out.println("Ftp file store error");
					ret = false;
				}
				fis.close();
				ftpClient.logout();
			}
		}catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException("FTP Client error !", e);
		}finally{
			if(ftpClient.isConnected()){
				try{
					ftpClient.disconnect();
				}catch(IOException e){
					e.printStackTrace();
					throw new RuntimeException("Ftp close exception !", e);
				}
			}
		}
		return ret;
	}
	
	public void download(){
		
	}
}
