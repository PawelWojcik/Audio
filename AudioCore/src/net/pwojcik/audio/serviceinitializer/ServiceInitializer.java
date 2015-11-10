package net.pwojcik.audio.serviceinitializer;

/**
 * Initializes necessary Segments, Modules and sets off communication between
 * units. Main interface essential to run application.
 * @author Pawel Wojcik
 * @version 1.0
 */
public interface ServiceInitializer {

	/**
	 * Starts initialization of application.
	 */
	void initialize();
}
