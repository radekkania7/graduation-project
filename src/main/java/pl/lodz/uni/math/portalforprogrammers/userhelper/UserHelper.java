package pl.lodz.uni.math.portalforprogrammers.userhelper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserHelper {
	
	/**
	 * Method returns name of actual logged in user.
	 * @return String.
	 */
	public static String getLoggedinUserName() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
