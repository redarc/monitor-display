package com.redarc.ftpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.redarc.Resconfig;

public class FtpUtils {
	
	/**
	 * @param file
	 * @return
	 */
	public static boolean upload(String uploadFileName){
		boolean ret = false;
		FTPClient ftpClient = new FTPClient();
		FileInputStream fis = null;
		try{
			ftpClient.connect(Resconfig.getInstance().getLocalsrv());
			ftpClient.login(Resconfig.getInstance().getUser(), Resconfig.getInstance().getPasswd());
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
				ret = false;
				System.out.println("FTP connect error!");
			}else{
				File file = new File(uploadFileName);
				fis = new FileInputStream(new File(uploadFileName));
				ftpClient.setBufferSize(1024);
				ftpClient.setControlEncoding("GBK");
				ftpClient.changeWorkingDirectory(Resconfig.getInstance().getLocalpath());
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
	
	/**
	 * @param remoteFileName
	 * @param savaAsFileName
	 * @return
	 */
	public static boolean download(String remoteFileName, String savaAsFileName){
		boolean ret = false;
		FTPClient ftpClient = new FTPClient();
		FileOutputStream fos = null;
        try {
    		ftpClient.connect(Resconfig.getInstance().getLocalsrv());
			ftpClient.login(Resconfig.getInstance().getUser(), Resconfig.getInstance().getPasswd());
            
			if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
				ret = false;
				System.out.println("FTP connect error!");
			}else{
	            fos = new FileOutputStream(savaAsFileName);
	            ftpClient.setBufferSize(1024);
	            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
	            ftpClient.changeWorkingDirectory(Resconfig.getInstance().getLocalpath());
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
