package pl.lodz.uni.math.portalforprogrammers.logic.upload;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import pl.lodz.uni.math.portalforprogrammers.userhelper.LoggedinUserHelper;

public class FileUploader {
	
	private final static Logger logger = Logger.getLogger(FileUploader.class);
	
	/**
	 * 
	 * @param file
	 */
	public void uploadProfileImage(MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + LoggedinUserHelper.getLoggedinUserName() + 
						"profileFoto");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				logger.info("File for: " + LoggedinUserHelper.getLoggedinUserName()  +  " upload success");
			} catch (Exception e) {
				logger.error("File upload filed",e);
			}
		} else {
			logger.info("Can not upload file, file is empty");
		}
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public Image readImageFromServer(String username) {
		String path = System.getProperty("catalina.home");
		path = path + File.separator + "tmpFiles" +
				File.separator + LoggedinUserHelper.getLoggedinUserName() + "profileFoto";
		
		List<Byte> byteList = new LinkedList<Byte>();
		BufferedInputStream stream = null;
		//InputStream in = null;
		byte[] bytes = new byte[] {};
		Byte[] bytess;
		
		try {
			stream = new BufferedInputStream(new FileInputStream(path));
			while (stream.available() > 0) {
				byteList.add((byte)stream.read());
			}
			
			logger.debug(byteList.size());
			bytess = (Byte[]) byteList.toArray();
			
			//in = new ByteArrayInputStream(bytes);
			logger.debug("file read succesfuly");
			return new ImageIcon(bytes).getImage();
		} catch (Exception e) {
			logger.debug("loading failed");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception e) {
					logger.debug("stream closing failed");
				}
			}
		}
		return null;
	}
	
	 
}
