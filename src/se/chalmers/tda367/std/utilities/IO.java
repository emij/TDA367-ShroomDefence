package se.chalmers.tda367.std.utilities;

import java.io.*;

/**
 * A IO class for use when (de)serializing objects.
 * @author Emil Edholm
 * @date May 12, 2012
 */
public enum IO {
	; // Prevents instantiation.
	/**
	 * Used to save a supplied class to disk. I.e. serialize it.
	 * Saves it to the specified path.
	 * @param data - the object to serialize. 
	 * @param file - the file that should be written.
	 * @throws IOException - if an I/O error occurs while writing stream header
	 * @throws FileNotFoundException - if the file exists but is a directory rather than a regular file, 
	 * 								   does not exist but cannot be created, or cannot be opened for any other reason
	 */
	public static <T> void saveObject(T data, File file) throws FileNotFoundException, IOException{
		ObjectOutput stream = new ObjectOutputStream(new FileOutputStream(file));
		stream.writeObject(data);
		stream.flush();
		stream.close();
	}
	
	/**
	 * Tries to deserialize the object from the specified path.
	 * @param expectedType - the type of the file about the be read.
	 * @param file - file that is supposed to be read.
	 * @throws IOException - if an I/O error occurs while reading stream header
	 * @throws FileNotFoundException - if the file does not exist, is a directory rather than a regular file,
	 * 								   or for some other reason cannot be opened for reading.
	 * @throws ClassNotFoundException - If the class of the serialized object cannot be found.
	 */
	public static <T> T loadObject(Class<T> expectedType, File file) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInput stream = new ObjectInputStream(new FileInputStream(file));
		T returnValue = expectedType.cast(stream.readObject());
		stream.close();
		
		return returnValue;
	}
}
