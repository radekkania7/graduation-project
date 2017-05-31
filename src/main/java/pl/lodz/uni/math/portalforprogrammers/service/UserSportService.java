package pl.lodz.uni.math.portalforprogrammers.service;

import pl.lodz.uni.math.portalforprogrammers.model.UserSport;

public interface UserSportService {
	public void save(UserSport userSport);
	
	public UserSport findById(Integer id);
}
