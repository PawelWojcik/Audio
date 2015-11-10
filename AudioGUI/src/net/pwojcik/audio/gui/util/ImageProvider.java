package net.pwojcik.audio.gui.util;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ImageProvider {
	
	private static final String GRAPHICS_FOLDER_PREFIX = "gfx/";

	public static Image getImage(String fileName) {
		ClassLoader classLoader = ImageProvider.class.getClassLoader();
		File file = new File(classLoader.getResource(GRAPHICS_FOLDER_PREFIX + fileName).getFile());
		String fileURI = file.toURI().toString();
		Image image = new Image(fileURI);
		return image;
	}

	public static ImageView getImageView(String fileName) {
		Image img = ImageProvider.getImage(fileName);
		ImageView imageView = new ImageView(img);
		return imageView;
	}
}
