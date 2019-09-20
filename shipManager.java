import java.io.File;
import java.util.*;

public class shipManager extends shipStorage
{
	public static final int MAX = 15;
    public static void main(String[] args)
    {
    	userInterface userInterface = new userInterface();
	    FileManager fileManager = new FileManager();

        String filename=null;
        int menu;
		do
			{
			menu = userInterface.dropdownMenu();

			switch (menu)
			{
				case 1:
					userInterface.addSubmarine();
					break;
				case 2:
					userInterface.addFighterJet();
					break;
				case 3:
					userInterface.findduplicates();
					break;
				case 4:
					filename = userInterface.FileName();
					fileManager.readFile(filename);
					break;
				case 5:
					userInterface.viewShips();
					break;
				case 6:
					int dist;
					dist = userInterface.destinationCheck();
					destinationcheck(dist);
					break;
				case 7:
					userInterface.toFileWrite();
					break;
			}
			}
			while(menu > 0 && menu < 8);

    }
}