package com.cloudcog.gears.admin.screen.admin.users;

import org.cloudcog.kani.RepositoryContext;
import org.cloudcog.kani.i18n.Messages;
import org.cloudcog.kani.repository.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudcog.gears.admin.controller.admin.AdminScreenController;
import com.cloudcog.gears.admin.controller.users.UserHelper;
import com.cloudcog.gears.admin.screen.GearsWindow;
import com.cloudcog.gears.admin.screen.mainPanel.ObjectMenuBar;
import com.cloudcog.gears.admin.screen.mainPanel.handlers.SaveObjectInterface;
import com.cloudcog.gears.admin.user.GearsUser;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UsersPanel extends GearsWindow implements SaveObjectInterface {
	private static final long serialVersionUID = 4560102818476134442L;
	private static final Logger log = LoggerFactory.getLogger(UsersPanel.class);
	@AutoGenerated
	private VerticalLayout mainLayout;

	@AutoGenerated
	private TabSheet detailsTab;

	@AutoGenerated
	private Panel groupsPanel;

	@AutoGenerated
	private VerticalLayout verticalLayout_2;

	@AutoGenerated
	private Panel additionalAttributes;

	@AutoGenerated
	private VerticalLayout attributesLayout;

	@AutoGenerated
	private Table propertiesTable;

	@AutoGenerated
	private TextArea userAddressTxt;

	@AutoGenerated
	private CheckBox genderMale;

	@AutoGenerated
	private TextField userEmailTxt;

	@AutoGenerated
	private TextField userLastNameTxt;

	@AutoGenerated
	private TextField userFirstNameTxt;

	@AutoGenerated
	private TextField usernameTxt;

	private GearsUser user;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the composition
	 * root and then do any custom initialization.
	 * 
	 * The constructor will not be automatically regenerated by the visual
	 * editor.
	 */

	@Override
	public void initialize(AdminScreenController adminScreenController, Object item) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		this.setSizeFull();

		setFormData(item);
	}

	private void setFormData(Object item) {
		if (item != null) {
			this.setData(item);
			try {
				this.user = (GearsUser) item;
				usernameTxt.setValue(user.getUsername());
				userFirstNameTxt.setValue(user.getFirstName());
				userLastNameTxt.setValue(user.getLastName());
				userAddressTxt.setValue(user.getAddress());
				userEmailTxt.setValue(user.getEmail());

			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public String getCaption() {
		if (user != null) {
			return user.toString();
		} else {
			return "_New";
		}
	}

	@Override
	public Resource getIcon() {
		try {
			return UserHelper.getUserIcon(this.user);
		} catch (Exception e) {
			return super.getIcon();
		}
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%"); //$NON-NLS-1$
		mainLayout.setHeight("100%"); //$NON-NLS-1$
		mainLayout.setMargin(true);

		mainLayout.addComponent(new ObjectMenuBar(this));

		// top-level component properties
		setWidth("100.0%"); //$NON-NLS-1$
		setHeight("100.0%"); //$NON-NLS-1$

		// usernameTxt
		usernameTxt = new TextField();
		usernameTxt.setStyleName("small"); //$NON-NLS-1$
		usernameTxt.setCaption(Messages.getString("UsersPanel.username")); //$NON-NLS-1$
		usernameTxt.setImmediate(false);
		usernameTxt.setWidth("-1px"); //$NON-NLS-1$
		usernameTxt.setHeight("-1px"); //$NON-NLS-1$
		mainLayout.addComponent(usernameTxt);

		// userFirstNameTxt
		userFirstNameTxt = new TextField();
		userFirstNameTxt.setStyleName("small"); //$NON-NLS-1$
		userFirstNameTxt.setCaption(Messages.getString("UsersPanel.FirstName")); //$NON-NLS-1$
		userFirstNameTxt.setImmediate(false);
		userFirstNameTxt.setWidth("-1px"); //$NON-NLS-1$
		userFirstNameTxt.setHeight("-1px"); //$NON-NLS-1$
		mainLayout.addComponent(userFirstNameTxt);

		// userLastNameTxt
		userLastNameTxt = new TextField();
		userLastNameTxt.setStyleName("small"); //$NON-NLS-1$
		userLastNameTxt.setCaption(Messages.getString("UsersPanel.LastName")); //$NON-NLS-1$
		userLastNameTxt.setImmediate(false);
		userLastNameTxt.setWidth("-1px"); //$NON-NLS-1$
		userLastNameTxt.setHeight("-1px"); //$NON-NLS-1$
		mainLayout.addComponent(userLastNameTxt);

		// userEmailTxt
		userEmailTxt = new TextField();
		userEmailTxt.setCaption(Messages.getString("UsersPanel.Email")); //$NON-NLS-1$
		userEmailTxt.setImmediate(false);
		userEmailTxt.setStyleName("small"); //$NON-NLS-1$
		userEmailTxt.setWidth("-1px"); //$NON-NLS-1$
		userEmailTxt.setHeight("-1px"); //$NON-NLS-1$
		mainLayout.addComponent(userEmailTxt);

		// genderMale
		genderMale = new CheckBox();
		genderMale.setStyleName("small"); //$NON-NLS-1$
		genderMale.setCaption(Messages.getString("UsersPanel.Gender")); //$NON-NLS-1$
		genderMale.setImmediate(false);
		genderMale.setWidth("-1px"); //$NON-NLS-1$
		genderMale.setHeight("-1px"); //$NON-NLS-1$
		mainLayout.addComponent(genderMale);

		// userAddressTxt
		userAddressTxt = new TextArea();
		userAddressTxt.setStyleName("small"); //$NON-NLS-1$
		userAddressTxt.setCaption(Messages.getString("UsersPanel.Address")); //$NON-NLS-1$
		userAddressTxt.setImmediate(false);
		userAddressTxt.setWidth("300px"); //$NON-NLS-1$
		userAddressTxt.setHeight("100px"); //$NON-NLS-1$
		mainLayout.addComponent(userAddressTxt);

		// detailsTab
		detailsTab = buildDetailsTab();
		mainLayout.addComponent(detailsTab);
		mainLayout.setComponentAlignment(detailsTab, new Alignment(33));

		return mainLayout;
	}

	public void save() {
		try {
			GearsUser user;
			if (this.getData() != null) {
				user = (GearsUser) this.getData();
			} else {
				user = new GearsUser(UserDAO.createUser(RepositoryContext.getJcrSession(), usernameTxt.getValue(), usernameTxt.getValue()));
			}
			user.setFirstName(userFirstNameTxt.getValue());
			user.setLastName(userLastNameTxt.getValue());
			user.setEmail(userEmailTxt.getValue());
			user.setAddress(userAddressTxt.getValue());
			RepositoryContext.getJcrSession().save();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	@AutoGenerated
	private TabSheet buildDetailsTab() {
		// common part: create layout
		detailsTab = new TabSheet();
		detailsTab.setImmediate(true);
		detailsTab.setWidth("100.0%"); //$NON-NLS-1$
		detailsTab.setHeight("100.0%"); //$NON-NLS-1$

		// additionalAttributes
		additionalAttributes = buildAdditionalAttributes();
		detailsTab.addTab(additionalAttributes, Messages.getString("UsersPanel.AdditionalAttributes"), null); //$NON-NLS-1$

		// groupsPanel
		groupsPanel = buildGroupsPanel();
		detailsTab.addTab(groupsPanel, Messages.getString("UsersPanel.Groups"), null); //$NON-NLS-1$

		return detailsTab;
	}

	@AutoGenerated
	private Panel buildAdditionalAttributes() {
		// common part: create layout
		additionalAttributes = new Panel();
		additionalAttributes.setCaption(Messages.getString("UsersPanel.AdditionalAttributes")); //$NON-NLS-1$
		additionalAttributes.setImmediate(false);
		additionalAttributes.setWidth("100px"); //$NON-NLS-1$
		additionalAttributes.setHeight("30px"); //$NON-NLS-1$

		// attributesLayout
		attributesLayout = buildAttributesLayout();
		additionalAttributes.setContent(attributesLayout);

		return additionalAttributes;
	}

	@AutoGenerated
	private VerticalLayout buildAttributesLayout() {
		// common part: create layout
		attributesLayout = new VerticalLayout();
		attributesLayout.setImmediate(false);
		attributesLayout.setWidth("100.0%"); //$NON-NLS-1$
		attributesLayout.setHeight("100.0%"); //$NON-NLS-1$
		attributesLayout.setMargin(false);

		// propertiesTable
		propertiesTable = new Table();
		propertiesTable.setImmediate(false);
		propertiesTable.setWidth("100.0%"); //$NON-NLS-1$
		propertiesTable.setHeight("100.0%"); //$NON-NLS-1$
		attributesLayout.addComponent(propertiesTable);

		return attributesLayout;
	}

	@AutoGenerated
	private Panel buildGroupsPanel() {
		// common part: create layout
		groupsPanel = new Panel();
		groupsPanel.setCaption(Messages.getString("UsersPanel.Groups")); //$NON-NLS-1$
		groupsPanel.setImmediate(false);
		groupsPanel.setWidth("100px"); //$NON-NLS-1$
		groupsPanel.setHeight("30px"); //$NON-NLS-1$

		// verticalLayout_2
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%"); //$NON-NLS-1$
		verticalLayout_2.setHeight("100.0%"); //$NON-NLS-1$
		verticalLayout_2.setMargin(false);
		groupsPanel.setContent(verticalLayout_2);

		return groupsPanel;
	}

}