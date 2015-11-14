package net.pwojcik.audio.module;

import com.google.common.collect.Lists;

import javafx.application.Platform;
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
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.container.LibrarySelectedContentContainer;
import net.pwojcik.audio.dataprovider.SourceDirectory;
import net.pwojcik.audio.evaluator.AddDirectoryToLibraryEvaluator;
import net.pwojcik.audio.evaluator.RemoveDirectoryFromLibraryEvaluator;
import net.pwojcik.audio.evaluator.UpdateLibraryEvaluator;
import net.pwojcik.audio.gui.canvasfactory.AbstractCanvasFactory;
import net.pwojcik.audio.gui.util.ImageProvider;
import net.pwojcik.audio.locale.I18N;

/**
 * Factory for GUI panel representing library content.
 * 
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class LibraryManagementModuleCanvasFactory extends AbstractCanvasFactory {

	public static enum LibraryCanvasButtonType {
		ADD, REMOVE, UPDATE
	}

	private static final String UPDATE_LIBRARY_ICON = "save.png";
	private static final String REMOVE_FOLDER_ICON = "folder_delete.png";
	private static final String ADD_FOLDER_ICON = "folder_add.png";
	private static final String I18N_ADD_DIR = "LibraryManagement_AddDirectory";
	private static final String I18N_REMOVE_DIR = "LibraryManagement_RemoveSelected";
	private static final String I18N_UPDATE = "LibraryManagement_Update";

	private final LibrarySelectedContentContainer selectionContainer;
	private ListView<SourceDirectory> listView;
	private ProgressIndicator progressIndicator;
	private Button addButton;
	private Button removeButton;
	private Button updateButton;

	public LibraryManagementModuleCanvasFactory(Broadcaster primaryBroadcaster) {
		super(primaryBroadcaster);
		selectionContainer = new LibrarySelectedContentContainer();
	}

	/**
	 * Updates table with directories handled by application.
	 * 
	 * @param elements
	 *            all directories handled by application
	 */
	public void setListViewItems(ObservableList<SourceDirectory> elements) {
		Platform.runLater(() -> listView.setItems(elements));
	}

	/**
	 * Enables/disables button specified by 'type' parameter.
	 * 
	 * @param type
	 *            modified button type
	 * @param enabled
	 *            TRUE if button should be enabled
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
	
	public LibrarySelectedContentContainer getSelectionContainer() {
		return selectionContainer;
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
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			selectionContainer.setSelectedItems(Lists.newArrayList(newValue));
		});
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
		progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);
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

	private void prepareAddButton() {
		addButton = new Button();
		addButton.setTooltip(new Tooltip(I18N.label(I18N_ADD_DIR)));
		addButton.setGraphic(ImageProvider.getImageView(LibraryManagementModuleCanvasFactory.class, ADD_FOLDER_ICON));
		addButton.setOnAction(new AddDirectoryToLibraryEvaluator(progressIndicator, getBroadcaster()));
	}

	private void prepareRemoveButton() {
		removeButton = new Button();
		removeButton.setTooltip(new Tooltip(I18N.label(I18N_REMOVE_DIR)));
		removeButton
				.setGraphic(ImageProvider.getImageView(LibraryManagementModuleCanvasFactory.class, REMOVE_FOLDER_ICON));
		removeButton.setOnAction(new RemoveDirectoryFromLibraryEvaluator(progressIndicator, getBroadcaster()));
	}

	private void prepareUpdateButton() {
		updateButton = new Button();
		updateButton.setTooltip(new Tooltip(I18N.label(I18N_UPDATE)));
		updateButton.setDisable(true);
		updateButton.setGraphic(
				ImageProvider.getImageView(LibraryManagementModuleCanvasFactory.class, UPDATE_LIBRARY_ICON));
		updateButton.setOnAction(new UpdateLibraryEvaluator(progressIndicator, getBroadcaster()));
	}

}
