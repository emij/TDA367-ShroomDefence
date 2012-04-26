package se.chalmers.tda367.std.utilities;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * A utility class used for retrieving a list of (normal) files from a specified path.
 * @author Emil Edholm
 * @date   Apr 22, 2012
 */
public final class FileScanner {
	/**
	 * Used to filter out directories from {@code File.listFiles(...) }.
	 */
	private static FileFilter onlyNormalFiles = new FileFilter() {
		@Override
		public boolean accept(File pathname) {
			return pathname.isFile();
		}
	};
	
	private FileScanner() {}
	
	/**
	 * Returns the normal files in specified path.
	 * @param searchPath the path to the directory in which to list files.
	 * @return a list of the normal files found. Empty list if {@code searchPath} is not a folder or does not exits.
	 */
	public static List<File> getFiles(Path searchPath){
		File originFolder = new File(searchPath.toUri());
		if(!originFolder.isDirectory()){
			Logger.getLogger("se.chalmers.tda367.std.utilities").warning("searchPath does not exist or not a directory. \nAffected path: " + searchPath);
			return Collections.emptyList();
		}
	
		return Arrays.asList(originFolder.listFiles(onlyNormalFiles));
	}
}
