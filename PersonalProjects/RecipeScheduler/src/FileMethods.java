import java.io.File;

public class FileMethods
{
	public static void deleteFileRecursive(File element) {
	    if (element.isDirectory()) {
	        for (File sub : element.listFiles()) {
	            deleteFileRecursive(sub);
	        }
	    }
	    element.delete();
	}
}
