package net.pwojcik.audio.gui.table;

import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.tag.AudioTagType;

/**
 * Builder for {@linkplain TagBasedTableColumnWrapper} objects.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class TagBasedColumnWrapperBuilder {

	private Optional<Callback<CellDataFeatures<Audio, String>, ObservableValue<String>>> expression;
	private TableView<Audio> table;
	private AudioTagType type;
	private float percentWidth;

	/**
	 * Creates empty builder.
	 * @return builder
	 */
	public static TagBasedColumnWrapperBuilder create() {
		TagBasedColumnWrapperBuilder builder = new TagBasedColumnWrapperBuilder();
		return builder;
	}

	/**
	 * Adds table in which coumn is located.
	 * @param mainTable parent table
	 * @return builder
	 */
	public TagBasedColumnWrapperBuilder addMainTable(TableView<Audio> mainTable) {
		table = mainTable;
		return this;
	}

	/**
	 * Adds type of column.
	 * @param columnType type of column
	 * @return
	 */
	public TagBasedColumnWrapperBuilder addType(AudioTagType columnType) {
		type = columnType;
		return this;
	}
	
	/**
	 * Adds width property of column.
	 * @param columnWidth width in percents (according to table size)
	 * @return builder
	 */
	public TagBasedColumnWrapperBuilder addPercentWidth(float columnWidth) {
		percentWidth = columnWidth;
		return this;
	}

	/**
	 * Adds expression responible for generating values for this column.
	 * @param callback expression
	 * @return builder
	 */
	public TagBasedColumnWrapperBuilder addExpression(
			Callback<CellDataFeatures<Audio, String>, ObservableValue<String>> callback) {
		expression = Optional.of(callback);
		return this;
	}

	/**
	 * Builds {@linkplain TagBasedTableColumnWrapper} instance.
	 * @return new column
	 */
	public TagBasedTableColumnWrapper build() {
		if (!expression.isPresent()) {
			expression = Optional
					.of((feature) -> new SimpleStringProperty(feature.getValue().getAudioTag().getTagByType(type)));
		}

		if (percentWidth == 0.0f) {
			percentWidth = 0.00001f;
		}
		
		TagBasedTableColumnWrapper wrapper = new TagBasedTableColumnWrapper(table, type, percentWidth,
				expression.get());
		return wrapper;
	}

	private TagBasedColumnWrapperBuilder() {
		expression = Optional.empty();
	}
}
