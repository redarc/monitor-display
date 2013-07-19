package com.redarc.ftpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
				if(ftpClient.storeFile(file.getName(),fis)){
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
	
	public boolean download(String remoteFileName, String savaAsFileName){
		boolean ret = false;
		FTPClient ftpClient = new FTPClient();
		FileOutputStream fos = null;
        try {
            ftpClient.connect("10.186.135.173");
            ftpClient.login("root", "root");
			if(!FTPReply.isPositiveCompletion(ftpClient.getReply())){
				ret = false;
				System.out.println("FTP connect error!");
			}else{
	            fos = new FileOutputStream(savaAsFileName);
	            ftpClient.setBufferSize(1024);
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	            ftpClient.changeWorkingDirectory("/opt/3party_software/apache/htdocs");
	            if(ftpClient.retrieveFile(remoteFileName, fos)){
					System.out.println(remoteFileName +  " download success");
					ret = true;
	            }else{
					System.out.println("Ftp file download error");
					ret = false;
	            }
			}
            fos.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FTP Client error", e);
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("FTP close exception !", e);
            }
        } 
        return ret;
	}
}
