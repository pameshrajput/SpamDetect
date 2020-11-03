
import java.io.IOException;

public class spam
{
	private static String Runtime;

	public static void main(String[] args) 
    { 
        try
        { 
            // Command to create an external process 
            //String command = "C:\\Users\\Yo Yo\\AppData\\Local\\JetBrains\\PyCharm Community Edition 2019.2\\bin";
              Process process = new ProcessBuilder("C:\\Users\\Yo Yo\\AppData\\Local\\JetBrains\\PyCharm Community Edition 2019.2\\bin\\pycharm64.exe").start();    
            // Running the above command +
            //Runtime run = Runtime.getRuntime(); 
            //Process proc = run.exec(command); 
        } 
  
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
    } 
}	



