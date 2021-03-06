package com.cloudcog.gears.admin.user;

import java.security.Principal;
import java.util.Iterator;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.value.BooleanValue;
import org.apache.jackrabbit.value.StringValue;
import org.cloudcog.kani.repository.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GearsUser {

	private static final Logger log = LoggerFactory.getLogger(GearsUser.class);

	private static final String PROPERTY_GENDER = "gender";
	private static final String PROPERTY_FIRST_NAME = "firstName";
	private static final String PROPERTY_LAST_NAME = "lastName";
	private static final String PROPERTY_EMAIL = "email";
	private static final String PROPERTY_ADDRESS = "address";

	private User user;

	public enum Gender {
		MALE, FEMALE;

		public Boolean getBoolValue() {
			switch (this) {
			case MALE:
				return true;
			default:
				return false;
			}
		}
	}

	public GearsUser(Session session, String username, String password) throws RepositoryException {
		this.user = UserDAO.createUser(session, username, password);
	}

	public GearsUser(User user) {
		this.user = user;
	}

	public String getUsername() throws RepositoryException {
		return this.user.getID();
	}

	public boolean isGroup() {
		return this.user.isGroup();
	}

	public Principal getPrincipal() throws RepositoryException {
		return this.user.getPrincipal();
	}

	public Iterator<Group> declaredMemberOf() throws RepositoryException {
		return this.user.declaredMemberOf();
	}

	public Iterator<Group> memberOf() throws RepositoryException {
		return this.user.memberOf();
	}

	public boolean isAdmin() {
		return this.user.isAdmin();
	}

	public void changePassword(String password) throws RepositoryException {
		this.user.changePassword(password);
	}

	public void changePassword(String password, String oldPassword) throws RepositoryException {
		this.user.changePassword(password, oldPassword);
	}

	public void disable(String reason) throws RepositoryException {
		this.user.disable(reason);
	}

	public boolean isDisabled() throws RepositoryException {
		return this.user.isDisabled();
	}

	public String getDisabledReason() throws RepositoryException {
		return this.user.getDisabledReason();
	}

	public void setGender(Gender gender) throws RepositoryException {
		this.user.setProperty(PROPERTY_GENDER, new BooleanValue(gender.getBoolValue()));
	}

	public Gender getGender() throws RepositoryException {
		Value[] value = this.user.getProperty(PROPERTY_GENDER);
		if (value == null) {
			return null;
		} else if (value[0].getBoolean()) {
			return Gender.MALE;
		} else {
			return Gender.FEMALE;
		}
	}

	public void setFirstName(String firstName) throws RepositoryException {
		this.user.setProperty(PROPERTY_FIRST_NAME, new StringValue(firstName));
	}

	public String getFirstName() throws RepositoryException {
		Value[] value = this.user.getProperty(PROPERTY_FIRST_NAME);
		if (value == null) {
			return "";
		} else {
			return value[0].getString();
		}
	}

	public void setEmail(String email) throws RepositoryException {
		this.user.setProperty(PROPERTY_EMAIL, new StringValue(email));

	}

	public String getEmail() throws RepositoryException {
		Value[] value = this.user.getProperty(PROPERTY_EMAIL);
		if (value == null) {
			return "";
		} else {
			return value[0].getString();
		}

	}

	public void setLastName(String lastName) throws RepositoryException {
		this.user.setProperty(PROPERTY_LAST_NAME, new StringValue(lastName));
	}

	public String getLastName() throws RepositoryException {
		Value[] value = this.user.getProperty(PROPERTY_LAST_NAME);
		if (value == null) {
			return "";
		} else {
			return value[0].getString();
		}
	}

	public String getAddress() throws RepositoryException {
		Value[] value = this.user.getProperty(PROPERTY_ADDRESS);
		if (value == null) {
			return "";
		} else {
			return value[0].getString();
		}
	}

	public void setAddress(String address) throws RepositoryException {
		this.user.setProperty(PROPERTY_ADDRESS, new StringValue(address));
	}

	public String getFullName() throws RepositoryException {
		String fullName = (getFirstName() + " " + getLastName()).trim();
		if (fullName.isEmpty()) {
			fullName = getUsername();
		}
		return fullName;
	}

	public User getUser() {
		return user;
	}

	public boolean hasPermissions(Session session, String... permissions) throws RepositoryException {
		return UserDAO.userHasPermission(session, this.getUser(), permissions);
	}

	@Override
	public String toString() {
		try {
			return getFullName();
		} catch (RepositoryException e) {
			log.error(e.getMessage(), e);
			return "user" + super.toString();
		}
	}

}
