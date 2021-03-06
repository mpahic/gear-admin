package com.cloudcog.gears.admin.screen.admin;

import org.cloudcog.kani.RepositoryContext;
import org.cloudcog.kani.i18n.Messages;
import org.cloudcog.kani.login.LogoutClickListener;
import org.cloudcog.kani.repository.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudcog.gears.admin.controller.admin.AdminScreenController;
import com.cloudcog.gears.admin.controller.users.UserHelper;
import com.cloudcog.gears.admin.user.GearsUser;
import com.cloudcog.gears.admin.util.ImageResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.MenuBar;

public class AdminMenu extends MenuBar {
	private static final long serialVersionUID = -3386763713474566088L;
	private static final Logger log = LoggerFactory.getLogger(AdminMenu.class);

	public AdminMenu(AdminScreenController controller) {
		try {
			init(controller);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private void init(AdminScreenController controller) throws Exception {
		this.setImmediate(false);
		this.setWidth("100%"); //$NON-NLS-1$
		this.setHeight("-1px"); //$NON-NLS-1$
		this.setStyleName("small");
		this.setStyleName("borderless");

		MenuBar.MenuItem administration = this.addItem(Messages.getString("AdminMenu.administration"), null); //$NON-NLS-1$
		administration.addItem(Messages.getString("AdminMenu.users"), ImageResource.getResource(ImageResource.USERS_16), controller.getHeaderClickCommand("users"));
		administration.addItem(Messages.getString("AdminMenu.groups"), ImageResource.getResource(ImageResource.USERS_16), controller.getHeaderClickCommand("groups"));
		administration.addItem(Messages.getString("AdminMenu.settings"), ImageResource.getResource(ImageResource.WRENCH_SCREWDRIVER_16), null); //$NON-NLS-1$
		administration.addItem(Messages.getString("AdminMenu.statuses"), ImageResource.getResource(ImageResource.WRENCH_16), null); //$NON-NLS-1$

		MenuBar.MenuItem modules = this.addItem(Messages.getString("AdminMenu.modules"), null); //$NON-NLS-1$
//		for (Module module : RepositoryContext.getModuleService().getModules()) {
//			modules.addItem(module.getName(), null);
//		}

		GearsUser user = new GearsUser(UserDAO.getCurrentUser(RepositoryContext.getJcrSession()));
		String username = user.getUsername();
		ThemeResource userIcon = UserHelper.getUserIcon(user);
		MenuBar.MenuItem userSetting = this.addItem(username, userIcon, null);

		userSetting.setStyleName("menuRight"); //$NON-NLS-1$

		userSetting.addItem(Messages.getString("AdminMenu.Profile"), userIcon, null); //$NON-NLS-1$
		userSetting.addItem(Messages.getString("AdminMenu.Logout"), ImageResource.getResource(ImageResource.PROHIBITION_16), new LogoutClickListener()); //$NON-NLS-1$
	}
}
