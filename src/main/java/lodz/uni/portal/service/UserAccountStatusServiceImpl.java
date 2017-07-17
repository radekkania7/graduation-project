package lodz.uni.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lodz.uni.portal.dao.UserAccountStatusDao;
import lodz.uni.portal.model.UserAccountStatus;

@Service("userAccountStatusService")
@Transactional
public class UserAccountStatusServiceImpl implements UserAccountStatusService {
	
	@Autowired
	UserAccountStatusDao dao;
	
	public UserAccountStatus getStatusByType(String type) {
		return dao.findByType(type);
	}
}
