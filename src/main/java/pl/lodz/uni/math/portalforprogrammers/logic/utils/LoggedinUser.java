package pl.lodz.uni.math.portalforprogrammers.logic.utils;

import org.springframework.beans.factory.annotation.Autowired;

import pl.lodz.uni.math.portalforprogrammers.model.PortalUser;
import pl.lodz.uni.math.portalforprogrammers.model.Sport;
import pl.lodz.uni.math.portalforprogrammers.service.SportService;
import pl.lodz.uni.math.portalforprogrammers.service.UserService;
import pl.lodz.uni.math.portalforprogrammers.userhelper.UserHelper;

public class LoggedinUser {
	private final String name;
	
	private PortalUser user;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SportService sportService;
	
	public LoggedinUser(String username) {
		this.name = username;
		this.user = this.userService.findUserByUsername(name);
	}
	
	public PortalUser getUser() {
		return this.user;
	}

	public void setUser(PortalUser user) {
		this.user = user;
	}
	
	public void addSport(String sportName) {
		Sport sport = this.sportService.findSportByName(sportName);
		this.user.getUserSports().add(sport);
		this.userService.save(user);
		this.reload();
	}
	
	private void reload() {
		this.user = userService.findUserByUsername(name);
	}
}