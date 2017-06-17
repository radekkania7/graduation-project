package pl.lodz.uni.math.portalforprogrammers.utils;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;



public class FileUtils {
	
	private static final Logger logger = Logger.getLogger(FileUtils.class);
	
	private static final String PHOTO_PATH = "WEB-INF/user-images/";
	private static final String USER_PHOTO_PATH = PHOTO_PATH 
			+ UserHelper.getLoggedinUserName() + ".jpg";
	
	public static BufferedImage getImage(String username) {
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "tmpFiles");
		File serverFile = new File(dir.getAbsolutePath() + File.separator + username);
		
		BufferedImage bi;
		try {
			bi = ImageIO.read(serverFile);
			return bi;
		} catch (IOException e) {
			logger.debug(e);
		}
		return null;
	}
	
	public static void saveImage() {
		
	}
}
