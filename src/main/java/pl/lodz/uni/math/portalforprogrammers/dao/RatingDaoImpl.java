package pl.lodz.uni.math.portalforprogrammers.dao;

import org.springframework.stereotype.Repository;

import pl.lodz.uni.math.portalforprogrammers.model.Rating;

@Repository("ratingDao")
public class RatingDaoImpl extends AbstracDao<Integer, Rating> implements RatingDao {

	@Override
	public void save(Rating rating) {
		persist(rating);
	}
}
