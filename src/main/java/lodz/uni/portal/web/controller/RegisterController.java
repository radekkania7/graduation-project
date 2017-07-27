package lodz.uni.portal.web.controller;


import javax.validation.Valid;

import lodz.uni.portal.model.UserAccountStatus;
import lodz.uni.portal.model.UserProfile;
import lodz.uni.portal.model.type.UserAccountStatusType;
import lodz.uni.portal.model.type.UserProfileType;
import lodz.uni.portal.service.UserAccountStatusService;
import lodz.uni.portal.service.UserProfileService;

import lodz.uni.portal.service.UserService;
import lodz.uni.portal.utils.CustomDateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lodz.uni.portal.model.PortalUser;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping({ "/register" })
public class RegisterController {
	private static final String REGISTER_PAGE = "registerform";
	private static final String REGISTER_SUCCESS_PAGE = "registersucces";
	private static final String PASSWORD_CONFIRMED = "CONFIRMED";

	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	UserAccountStatusService userAccountStatusService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(method=RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new PortalUser());
		return REGISTER_PAGE;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String createNewUser(@Valid @ModelAttribute("user") PortalUser user,
								BindingResult result) {

		validateUserBean(user, result);
		if (result.hasErrors()) {
			return REGISTER_PAGE;
		}

		completeDefaultValuesForUser(user);
		persistNewUser(user);
		return REGISTER_SUCCESS_PAGE;
	}

	private void validateUserBean(PortalUser user, BindingResult result) {
		validateNicknameUnique(user.getNickname(), result);
		validateEqualsPasswordAndConfirm(user.getPassword(), user.getConfirmPassword(), result);
		validateEmailUnique(user.getEmail(), result);
		validateDateBeforeToday(user.getDateOfBirth(), result);
	}

	private void validateNicknameUnique(String nickname, BindingResult result) {
		if (userService.isNicknameUnique(nickname)) {
			String message = messageSource.getMessage("non.unique.nickname", new String[]{nickname}, Locale.getDefault());
			FieldError error = new FieldError("user","nickname", message);
			result.addError(error);
		}
	}

	private void validateEqualsPasswordAndConfirm(String pass, String confirmPass, BindingResult result) {
		if (!isPasswordEqualsConfirmPassword(pass, confirmPass)) {
			String message = messageSource.getMessage("NotEqual.confirm.password", new String[]{""}, Locale.getDefault());
			FieldError error = new FieldError("user","confirmPassword", message);
			result.addError(error);
		}
	}

	private void validateEmailUnique(String email, BindingResult result) {
		if (userService.isEmailUnique(email)) {
			String message = messageSource.getMessage("non.unique.email", new String[]{""}, Locale.getDefault());
			FieldError error = new FieldError("user","email", message);
			result.addError(error);
		}
	}

	private void validateDateBeforeToday(Date date, BindingResult result) {
		if (date == null || !CustomDateUtils.isDateBeforeToday(date)) {
			String message = messageSource.getMessage("before.today.dateOfBirth", new String[]{""}, Locale.getDefault());
			FieldError error = new FieldError("user", "dateOfBirth", message);
			result.addError(error);
		}
	}

	private boolean isPasswordEqualsConfirmPassword(String pass, String confirmedPass) {
		return (pass.equals(confirmedPass));
	}

	private void completeDefaultValuesForUser(PortalUser user) {
		user.setUserAccountStatus(getCreatedStatus());
		user.setUserProfiles(getListOfUserProfileWithUserType());
		user.setConfirmPassword(PASSWORD_CONFIRMED);
		String encryptedPassword = getEncryptedPassword(user.getPassword());
		user.setPassword(encryptedPassword);
	}

	private String getEncryptedPassword(String pass){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(pass);
	}

	private List<UserProfile> getListOfUserProfileWithUserType() {
		String type = UserProfileType.USER.getType();
		UserProfile profile = userProfileService.getProfileByType(type);
		return Arrays.asList(profile);
	}

	private UserAccountStatus getCreatedStatus() {
		String type = UserAccountStatusType.CREATED.getType();
		return userAccountStatusService.getStatusByType(type);
	}

	private void persistNewUser(PortalUser user) {
		userService.save(user);
	}
}