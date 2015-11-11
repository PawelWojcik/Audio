package net.pwojcik.audio.module;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import net.pwojcik.audio.dataprovider.SourceDirectory;
import net.pwojcik.audio.gui.canvasfactory.AbstractCanvasFactory;
import net.pwojcik.audio.gui.util.ImageProvider;
import net.pwojcik.audio.locale.I18N;

/**
 * Factory for GUI panel representing library content.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryManagementModuleCanvasFactory extends AbstractCanvasFactory {

	public static enum LibraryCanvasButtonType {
		ADD, REMOVE, UPDATE
	}

	private static final String I18N_ADD_DIR = "LibraryManagement_AddDirectory";
	private static final String I18N_REMOVE_DIR = "LibraryManagement_RemoveSelected";
	private static final String I18N_UPDATE = "LibraryManagement_Update";

	private ListView<SourceDirectory> listView;
	private ProgressIndicator progressIndicator;
	private Button addButton;
	private Button removeButton;
	private Button updateButton;

	/**
	 * Updates table with directories handled by application.
	 * @param elements all directories handled by application
	 */
	public void setListViewItems(ObservableList<SourceDirectory> elements) {
		listView.setItems(elements);
	}

	/**
	 * Enables/disables button specified by 'type' parameter.
	 * @param type modified button type
	 * @param enabled TRUE if button should be enabled
	 */
	public void setButtonEnabled(LibraryCanvasButtonType type, boolean enabled) {
		Button buttonToModify;
		if (type == LibraryCanvasButtonType.ADD) {
			buttonToModify = addButton;
		} else if (type == LibraryCanvasButtonType.REMOVE) {
			buttonToModify = removeButton;
		} else {
			buttonToModify = updateButton;
		}
		buttonToModify.setDisable(!enabled);
	}

	@Override
	protected Pane produce() {
		listView = createTable();
		Pane eastContainer = createEastContainer();
		BorderPane borderPane = new BorderPane();
		// TODO wyrzucic do CSSa
		borderPane.setPadding(new Insets(10));
		borderPane.setCenter(listView);
		borderPane.setRight(eastContainer);
		return borderPane;
	}

	private ListView<SourceDirectory> createTable() {
		ListView<SourceDirectory> listView = new ListView<>();
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		return listView;
	}

	private Pane createEastContainer() {
		prepareProgressIndicator();
		prepareAddButton();
		prepareRemoveButton();
		prepareUpdateButton();

		VBox buttonContainer = prepareButtonContainer();
		VBox footer = prepareProgressIndicatorContainer();
		
		BorderPane container = new BorderPane();
		container.setPadding(new Insets(0, 0, 0, 10));
		container.setTop(buttonContainer);
		container.setBottom(footer);
		return container;
	}

	private void prepareProgressIndicator() {
		progressIndicator = new ProgressIndicator(-1.0);
		progressIndicator.setVisible(false);
	}

	private VBox prepareProgressIndicatorContainer() {
		VBox footer = new VBox();
		footer.getChildren().add(progressIndicator);
		return footer;
	}

	private VBox prepareButtonContainer() {
		VBox verticalPanel = new VBox();
		verticalPanel.setAlignment(Pos.CENTER_LEFT);
		verticalPanel.setSpacing(10);
		verticalPanel.getChildren().addAll(addButton, removeButton, updateButton);
		return verticalPanel;
	}

	private void prepareUpdateButton() {
		updateButton = new Button();
		updateButton.setTooltip(new Tooltip(I18N.label(I18N_UPDATE)));
		updateButton.setDisable(true);
		updateButton.setGraphic(ImageProvider.getImageView(LibraryManagementModuleCanvasFactory.class, "save.png"));
		// updateButton.setOnAction();
	}

	private void prepareRemoveButton() {
		removeButton = new Button();
		removeButton.setTooltip(new Tooltip(I18N.label(I18N_REMOVE_DIR)));
		// removeButton.setDisable(listView.getItems().isEmpty());
		removeButton.setGraphic(
				ImageProvider.getImageView(LibraryManagementModuleCanvasFactory.class, "folder_delete.png"));
		removeButton.setOnAction(e -> {
			progressIndicator.setVisible(false);
		});
	}

	private void prepareAddButton() {
		addButton = new Button();
		addButton.setTooltip(new Tooltip(I18N.label(I18N_ADD_DIR)));
		addButton.setGraphic(ImageProvider.getImageView(LibraryManagementModuleCanvasFactory.class, "folder_add.png"));
		addButton.setOnAction(e -> {
			progressIndicator.setVisible(true);
		});
	}
}
