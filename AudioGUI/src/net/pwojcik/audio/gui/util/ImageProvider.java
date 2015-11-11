package net.pwojcik.audio.gui.util;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Static class providing useful methods for obtaining images.
 * @author Pawel Wojcik
 * @version 1.0
 */
public final class ImageProvider {
	
	private static final String GRAPHICS_FOLDER_PREFIX = "gfx/";

	/**
	 * Obtains new Image file.
	 * @param sourceClass class that is calling for Image
	 * @param fileName name of image file
	 * @return image
	 */
	public static Image getImage(Class<?> sourceClass, String fileName) {
		ClassLoader classLoader = sourceClass.getClassLoader();
		File file = new File(classLoader.getResource(GRAPHICS_FOLDER_PREFIX + fileName).getFile());
		String fileURI = file.toURI().toString();
		Image image = new Image(fileURI);
		return image;
	}

	/**
	 * Returns new Image wrapped in ImageView
	 * @param sourceClass class that is calling for Image
	 * @param fileName name of image file
	 * @return image wrapped in ImageView
	 */
	public static ImageView getImageView(Class<?> sourceClass, String fileName) {
		Image img = ImageProvider.getImage(sourceClass, fileName);
		ImageView imageView = new ImageView(img);
		return imageView;
	}
	
	private ImageProvider() {
	}
}
