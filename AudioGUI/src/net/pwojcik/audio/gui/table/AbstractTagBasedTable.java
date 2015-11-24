package net.pwojcik.audio.gui.table;

import static net.pwojcik.audio.model.tag.AudioTagType.ALBUM;
import static net.pwojcik.audio.model.tag.AudioTagType.ARTIST;
import static net.pwojcik.audio.model.tag.AudioTagType.COMMENT;
import static net.pwojcik.audio.model.tag.AudioTagType.DISC_NUMBER;
import static net.pwojcik.audio.model.tag.AudioTagType.GENRE;
import static net.pwojcik.audio.model.tag.AudioTagType.LENGTH;
import static net.pwojcik.audio.model.tag.AudioTagType.RELEASE_YEAR;
import static net.pwojcik.audio.model.tag.AudioTagType.TITLE;
import static net.pwojcik.audio.model.tag.AudioTagType.TOTAL_DISCS;
import static net.pwojcik.audio.model.tag.AudioTagType.TRACK_NUMBER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import net.pwojcik.audio.broadcast.Broadcaster;
import net.pwojcik.audio.evaluator.TagBasedTableClickEvaluator;
import net.pwojcik.audio.locale.I18N;
import net.pwojcik.audio.model.Audio;
import net.pwojcik.audio.model.tag.AudioTagType;

/**
 * Abstract implementation of table, which columns are based on {@linkplain AudioTagType} values.
 * @author Pawel Wojcik
 * @version 1.0
 */
public abstract class AbstractTagBasedTable extends TableView<Audio> {

	private final Map<AudioTagType, TagBasedTableColumnWrapper> columnsMap;

	public AbstractTagBasedTable(Broadcaster primaryBroadcaster, String emptyTableLabel) {
		configurePlaceholder(emptyTableLabel);
		configureSelectionMode();
		columnsMap = configureColumns(getVisibleColumns());
		configureLengthComparator();
		setOnMouseClicked(new TagBasedTableClickEvaluator(primaryBroadcaster, getSelectionModel().getSelectedItems()));
	}

	/**
	 * Sets whether given column should be visible.
	 * @param columnType type of column
	 * @param visible true if column should be visible
	 */
	public final void setColumnVisible(AudioTagType columnType, boolean visible) {
		columnsMap.get(columnType).getColumn().setVisible(visible);
	}

	private void configurePlaceholder(String emptyTableLabel) {
		Label placeholder = new Label(I18N.label(emptyTableLabel));
		placeholderProperty().set(placeholder);
	}

	private void configureSelectionMode() {
		getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	private Map<AudioTagType, TagBasedTableColumnWrapper> configureColumns(Map<AudioTagType, Float> visibleColumns) {
		Map<AudioTagType, TagBasedTableColumnWrapper> map = new HashMap<>();
		initializeAllColumns(map);
		modifyVisibleColumns(visibleColumns, map);
		return map;
	}

	private void modifyVisibleColumns(Map<AudioTagType, Float> visibleColumns,
			Map<AudioTagType, TagBasedTableColumnWrapper> map) {
		for (Entry<AudioTagType, Float> entry : visibleColumns.entrySet()) {
			TagBasedTableColumnWrapper column = map.get(entry.getKey());
			column.setColumnWidth(entry.getValue().floatValue());
			column.getColumn().setVisible(true);
		}
	}

	private void initializeAllColumns(Map<AudioTagType, TagBasedTableColumnWrapper> map) {
		Collection<TagBasedColumnWrapperBuilder> allColumnBuilders = getAllColumnBuilders();
		for (TagBasedColumnWrapperBuilder builder : allColumnBuilders) {
			builder.addMainTable(this);
			TagBasedTableColumnWrapper columnWrapper = builder.build();
			map.put(columnWrapper.getColumnType(), columnWrapper);
			getColumns().add(columnWrapper.getColumn());
		}
	}

	private Collection<TagBasedColumnWrapperBuilder> getAllColumnBuilders() {
		Collection<TagBasedColumnWrapperBuilder> wrappers = new ArrayList<>();
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(TRACK_NUMBER).addPercentWidth(0.074f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(TITLE).addPercentWidth(0.28f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(ARTIST).addPercentWidth(0.27f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(ALBUM).addPercentWidth(0.28f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(LENGTH).addPercentWidth(0.085f).addExpression(
				(callback) -> new SimpleStringProperty(callback.getValue().getAudioTag().getFormattedAudioLength())));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(GENRE).addPercentWidth(0.184f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(RELEASE_YEAR).addPercentWidth(0.1f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(DISC_NUMBER).addPercentWidth(0.085f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(TOTAL_DISCS).addPercentWidth(0.085f));
		wrappers.add(TagBasedColumnWrapperBuilder.create().addType(COMMENT).addPercentWidth(0.353f));
		return wrappers;
	}

	private void configureLengthComparator() {
		NumericTableColumnComparator comparator = new NumericTableColumnComparator();
		columnsMap.get(AudioTagType.TRACK_NUMBER).getColumn().setComparator(comparator);
	}

	protected abstract Map<AudioTagType, Float> getVisibleColumns();

}
