package com.cloudcog.gears.admin.screen.admin.users;

import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.jackrabbit.api.security.user.User;
import org.cloudcog.kani.repository.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudcog.gears.admin.controller.admin.AdminScreenController;
import com.cloudcog.gears.admin.controller.filters.SimpleStringFilter;
import com.cloudcog.gears.admin.controller.users.UserHelper;
import com.cloudcog.gears.admin.user.GearsUser;
import com.cloudcog.gears.admin.util.ImageResource;
import com.vaadin.data.Container;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.themes.ValoTheme;

public final class UsersMenu extends CustomComponent {
	private static final long serialVersionUID = 2766023547249134875L;
	private static final Logger log = LoggerFactory.getLogger(UsersMenu.class);

	public static final String ID = "users-menu";
	public static final String REPORTS_BADGE_ID = "dashboard-menu-reports-badge";
	public static final String NOTIFICATIONS_BADGE_ID = "dashboard-menu-notifications-badge";
	private HierarchicalContainer container;

	public UsersMenu(AdminScreenController adminScreenController, List<User> users) {
		addStyleName("valo-menu");
		setId(ID);
		this.setWidth("100%");
		this.setHeight("100%");

		try {
			setCompositionRoot(buildContent(adminScreenController, users));
		} catch (RepositoryException e) {
			log.error(e.getMessage(), e);
		}
	}

	private Component buildContent(AdminScreenController adminScreenController, List<User> users) throws RepositoryException {

		container = new HierarchicalContainer();
		container.addContainerProperty("icon", Resource.class, ImageResource.getResource(ImageResource.USER_GREEN_16));
		for (User jcrUser : users) {
			GearsUser user = new GearsUser(jcrUser);
			if (!UserDAO.ANONIMOUS_USER.equals(user.getUsername())) {
				container.addItem(user);
				container.setChildrenAllowed(user, false);
				container.getContainerProperty(user, "icon").setValue(UserHelper.getUserIcon(user));
			}
		}
		final CssLayout menuContent = new CssLayout();
		menuContent.addStyleName("sidebar");
		menuContent.addStyleName(ValoTheme.MENU_PART);
		menuContent.addStyleName("no-vertical-drag-hints");
		menuContent.addStyleName("no-horizontal-drag-hints");
		menuContent.setWidth("100%");
		menuContent.setHeight("100%");

		menuContent.addComponent(buildTitle(adminScreenController));
		menuContent.addComponent(buildSearchFilter());
		menuContent.addComponent(buildMenuItems(adminScreenController));

		return menuContent;
	}

	private Component buildTitle(final AdminScreenController adminScreenController) {
		Label logo = new Label("Users");
		logo.setSizeUndefined();
		Button addButton = new Button(ImageResource.getResource(ImageResource.PLUS_BUTTON_16));
		addButton.setStyleName("small");
		addButton.setStyleName("icon-only");
		addButton.setStyleName("borderless");
		addButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				adminScreenController.createNewPanel(GearsUser.class);

			}
		});
		HorizontalLayout logoWrapper = new HorizontalLayout(addButton, logo);
		logoWrapper.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
		return logoWrapper;
	}

	private Component buildSearchFilter() {
		TextField searchFilter = new TextField();
		searchFilter.setInputPrompt("Search");
		searchFilter.setWidth("100%");
		searchFilter.setStyleName("small");
		searchFilter.addTextChangeListener(new FieldEvents.TextChangeListener() {

			@Override
			public void textChange(TextChangeEvent event) {
				container.removeAllContainerFilters();
				if (!event.getText().isEmpty()) {
					Container.Filter filter = new SimpleStringFilter(event.getText());
					container.addContainerFilter(filter);
				}
			}
		});
		return searchFilter;
	}

	private Component buildMenuItems(final AdminScreenController adminScreenController) throws RepositoryException {

		final Tree usersTree = new Tree();
		usersTree.setContainerDataSource(this.container);
		usersTree.setItemIconPropertyId("icon");

		usersTree.addItemClickListener(new ItemClickListener() {
			@Override
			public void itemClick(final ItemClickEvent event) {
				adminScreenController.setSelectedItem(event.getItemId());
			}
		});

		return usersTree;

	}

}
