package backend;

public class FilenameUtils {

	public static String removeExtension(String filename) {
	    if (filename == null) {
	        return null;
	    }
	    int index = indexOfExtension(filename);
	    if (index == -1) {
	        return filename;
	    } else {
	        return filename.substring(0, index);
	    }
	}

	public static int indexOfExtension(String filename) {
	    if (filename == null) {
	        return -1;
	    }
	    int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
	    int lastSeparator = indexOfLastSeparator(filename);
	    return lastSeparator > extensionPos ? -1 : extensionPos;
	}

	public static int indexOfLastSeparator(String filename) {
	    if (filename == null) {
	        return -1;
	    }
	    int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
	    int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
	    return Math.max(lastUnixPos, lastWindowsPos);
	}

	public static final char EXTENSION_SEPARATOR = '.';
	private static final char UNIX_SEPARATOR = '/';
	private static final char WINDOWS_SEPARATOR = '\\';
}
