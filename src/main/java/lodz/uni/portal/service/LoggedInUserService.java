package lodz.uni.portal.service;

import lodz.uni.portal.dao.PortalUserDao;
import lodz.uni.portal.model.PortalUser;
import lodz.uni.portal.utils.LoggedInUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loggedInUserService")
@Transactional
public class LoggedInUserService {

    @Autowired
    PortalUserDao portalUserDao;

    public PortalUser getLoggedInUser() {
        String loggedInUserNickname = LoggedInUserUtils.getNickname();
        return portalUserDao.findByUsername(loggedInUserNickname);
    }
}
