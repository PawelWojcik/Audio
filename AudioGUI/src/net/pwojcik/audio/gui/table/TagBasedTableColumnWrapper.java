package net.pwojcik.audio.gui.table;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.tag.AudioTagType;

/**
 * Implementation of table column based on {@linkplain AudioTagType} value.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class TagBasedTableColumnWrapper {

	private final TableView<Audio> table;
	private final TableColumn<Audio, String> column;
	private final AudioTagType columnType;
	
	public TagBasedTableColumnWrapper(TableView<Audio> tableView, AudioTagType type, float percentWidth,
			Callback<CellDataFeatures<Audio, String>, ObservableValue<String>> valueCallback) {
		columnType = type;
		table = tableView;
		column = new TableColumn<>(type.getTableLabel());
		column.setVisible(false);
		column.setCellValueFactory((feature) -> {
			return valueCallback
					.call(new CellDataFeatures<>(feature.getTableView(), feature.getTableColumn(), feature.getValue()));
		});
		setColumnWidth(percentWidth);
	}

	/**
	 * Sets new percent width of column (according to whole table width).
	 * @param percentWidth width of column
	 */
	public void setColumnWidth(float percentWidth) {
		double widthFactor = 1 / percentWidth;
		column.prefWidthProperty().bind(table.widthProperty().divide(widthFactor));
	}

	/**
	 * Returns contained column.
	 * @return column
	 */
	public TableColumn<Audio, String> getColumn() {
		return column;
	}
	
	/**
	 * Returns contained column type.
	 * @return column type
	 */
	public AudioTagType getColumnType() {
		return columnType;
	}

}
