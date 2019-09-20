import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Year;
import java.util.*;
import java.lang.*;

public class userInterface extends shipStorage
{
    public static final int MAX = 30;
    public int shipCount;

    Scanner input = new Scanner(System.in);
	/************************************************************
     *Default Constructor: userInterface
     *IMPORT: none
     *EXPORT: address of new userInterface
     *ASSERTION: initialize the jet and sub count to 0 and construct a valid array.
     *
     ************************************************************/
    public userInterface ()
    {
     shipsCollection = new shipClass[MAX];
     shipCount=0;
    }

	/************************************************************
	 *SUBMODULE: dropdownMenu
	 *IMPORT: NONE
	 *EXPORT: NONE
	 *ASSERTION: output menu to the user.
	 ************************************************************/
	public int dropdownMenu()
	{

		int menu = 0;
		Scanner input = new Scanner(System.in);
		try
		{
			System.out.println("Please enter a number \n" +
					"1: Enter Submarine Manually\n" +
					"2: Enter Fighter Jet Manually\n" +
					"3: Find Duplicates\n" +
					"4: Enter Ships Using External File\n" +
					"5: View Ships\n" +
					"6: Destination Check\n" +
					"7: Save Ships to a file\n" +
					"8: Exit");
			menu = input.nextInt();
			while (menu < 1 || menu > 8)
			{
				System.out.println("Please enter a number \n" +
						"1: Enter Submarine Manually\n" +
						"2: Enter Fighter Jet Manually\n" +
						"3: Find Duplicates\n" +
						"4: Enter Ships Using External File\n" +
						"5: View Ships\n" +
						"6: Destination Check\n" +
						"7: Save Ships to a file\n" +
						"8: EXIT");
				menu = input.nextInt();
			}
		}
		catch (InputMismatchException ex)
		{
			System.out.println("Please enter an integer between 1 and 7");
		}
		return menu;
	}

	/************************************************************
	 *SUBMODULE: addSubmarine
	 *IMPORT: NONE
	 *EXPORT: NONE
	 *ASSERTION: ask the user to input submarine details and store it in shipStorage.
	 ************************************************************/

    public void addSubmarine()
    {
        int year = 0,st = 0,nd = 0,cyl = 0;
        double maxdepth = 0;
        String hull = null ,serialnumber = null ,fuel =null;
        Scanner subinput = new Scanner(System.in);
		try
		{
			System.out.println("Firstly, add six digit numbers\n" +
					"The first half should be an integer between 100 and 300");
			st = validateSer1(subinput.nextInt());
			System.out.println("The Second half should be an integer between 1 -  999");
			nd = validateSer2(subinput.nextInt());
			serialnumber = String.valueOf(st) + "." + String.format("%3d", nd);

			System.out.println("Please enter the year the submarine was commissioned\n" +
					"A valid commission year should be between 1955 and 2022 ");
			year = validateYear(subinput.nextInt());
		}
		catch (InputMismatchException E)
		{
			System.out.println("You've entered something that is not integer. Please Restart the program.");
		}
		System.out.println("Please enter the hull type\n" +
					"A valid hull type should be\n" +
					"AL - ALLOY\n" +
					"TI - TITANIUM\n" +
					"ST - STEEL");
		hull = validatehull(subinput.next().toUpperCase());
		hull = hullType(hull);
        try
        {
	        System.out.println("Please enter the maximum diving depth of the submarine\n" +
			        "A valid diving depth should be between 0 and -500 metres");
	        maxdepth = validateMaxDepth(subinput.nextDouble());

	        System.out.println("Please enter the number of cylinder in its engine\n" +
			        "A valid cylinder number should be between 2 to 20 inclusive");
	        cyl = validateCylinders(subinput.nextInt());
        }
        catch (InputMismatchException ex)
        {
        	System.out.println("You've entered something that is not a number. Please restart the program");
        }
        System.out.println("Please enter the fuel type the engine use\n" +
                "Valid fuel types are as follow\n" +
                "BI - BIODIESEL\n" +
                "BA - BATTERY\n" +
                "DI - DIESEL");
        fuel = fuelType(validateFuel(subinput.next().toUpperCase()));

        submarineclass subs;

        subs = new submarineclass(serialnumber, year, cyl, fuel, hull, maxdepth);
        System.out.println("Submarine has been added to the system with serial number of" +serialnumber+", commision year: "+year+", Cylinders: "+cyl+", Fuel: " +fuel+ ", Hull: "+hull+", Maximum diving depth: "+maxdepth);
        addships(subs);
    }

	/************************************************************
	 *SUBMODULE: addFighterJet
	 *IMPORT: NONE
	 *EXPORT: NONE
	 *ASSERTION: Ask the user to input the FighterJet details manually and store it in shipStorage.
	 ************************************************************/

    public void addFighterJet()
    {
	    int year=0,st=0,nd=0,cyl=0;
	    double wingSpan=0;
	    String Ordnance=null,serialnumber=null,fuel=null;
	    Scanner jetinput = new Scanner(System.in);
		try
		{

			System.out.println("Firstly, edd a six digit number\n" +
					"The first half should be an integer between 100 - 300");
			st = validateSer1(jetinput.nextInt());
			System.out.println("The second half should be an integer between 001 -  999");
			nd = validateSer2(jetinput.nextInt());
			serialnumber = String.valueOf(st) + "." + String.format("%3d", nd);

			System.out.println("Please enter the submarine was commissioned\n" +
					"The year should be between 1955 and 2022 ");
			year = validateYear(jetinput.nextInt());
		}
		catch (InputMismatchException ex)
		{
			System.out.println("You've entered something that is not a number. Please restart the program");
		}
	    System.out.println("Please enter the ordnance of the jet\n"+
			    "a valid ordnance can be anything");
	    Ordnance = validateOrdnance(jetinput.next());

	    try
	    {
		    System.out.println("Please enter the wing span of the fighter jet\n" +
				    "A valid wing span should be between 2.20 to 25.6 metres");
		    wingSpan = validateWingSpan(jetinput.nextDouble());

		    System.out.println("Please enter the number of cylinder in its engine\n" +
				    "A valid cylinder number should be between 2 to 20 inclusive");
		    cyl = validateCylinders(jetinput.nextInt());
	    }
	    catch (InputMismatchException ex)
	    {
		    System.out.println("You've entered something that is not a number. Please restart the program");
	    }
	    System.out.println("Please enter the fuel type the engine use\n" +
			    "Valid fuel types are as follow\n" +
			    "BI - BIODIESEL\n" +
			    "BA - BATTERY\n" +
			    "DI - DIESEL");
	    fuel = fuelType(jetinput.next().toUpperCase());

	    FighterJet jets;

	    jets = new FighterJet(serialnumber, year, cyl, fuel, Ordnance, wingSpan);
	    System.out.println("Fighter Jet has been added to the system with serial number of" +serialnumber+", commision year: "+year+", Cylinders: "+cyl+", Fuel: " +fuel+ ", Wing Span: "+wingSpan+", Ordnance "+Ordnance);
	    addships(jets);
    }
	/************************************************************
	 *SUBMODULE: findDuplicates
	 *IMPORT: none
	 *EXPORT: none
	 *ASSERTION: find duplicates within the array
	 *
	 ************************************************************/
	public void findduplicates()
	{
		System.out.println("Looking for duplicates:\n");
		findDuplicates();
	}
	/************************************************************
	 *SUBMODULE: hullType
	 *IMPORT: hull STRING
	 *EXPORT: hull
	 *ASSERTION:
	 *
	 ************************************************************/


	public static String hullType(String hull)
	{
		if(hull.equals("AL"))
		{
			hull = submarineclass.AL;
		}
		else if (hull.equals("TI"))
		{
			hull = submarineclass.TI;
		}
		else if (hull.equals("ST"))
		{
			hull = submarineclass.ST;
		}
		return hull;
	}
    /************************************************************
     *SUBMODULE: fuelType
     *IMPORT: Fuel STRING
     *EXPORT: fuel
     *ASSERTION:
     *
     ************************************************************/
    public static String fuelType(String Fuel)
    {
	    if(Fuel.equals("BI"))
	    {
		    Fuel = Engine.BI;
	    }
	    else if (Fuel.equals("BA"))
	    {
		    Fuel = Engine.BA;
	    }
	    else if (Fuel.equals("DI"))
	    {
		    Fuel = Engine.DI;
	    }

    	return Fuel;
    }


    /************************************************************
     *SUBMODULE: destinationCheck
     *IMPORT: NONE
     *EXPORT: NONE
     *ASSERTION: User input the distance, then find the fastest ship
     *
     ************************************************************/

    public static int destinationCheck()
    {
    	Scanner sc = new Scanner(System.in);
    	int d;
    	System.out.println("To find the fastest ship, please enter a valid distance the ship can reach." +
			    "the distance must be a real positive number.");
    	d = sc.nextInt();

    	while(d < 0)
	    {
	    	System.out.println("To find the fastest ship, please enter a valid distance the ship can reach."+
				    "the distance must be a real positive number.");
	    	d = sc.nextInt();
	    }
        return d;
    }

	/************************************************************
	 *SUBMODULE: toFileWrite
	 *IMPORT: none
	 *EXPORT: none
	 *ASSERTION:
	 *
	 ************************************************************/

    public void toFileWrite()
    {
    	System.out.println("Writing into a text file named: Output.txt");
    	FileWrite();
    }
    /************************************************************
     *SUBMODULE: viewShips
     *IMPORT: none
     *EXPORT: none
     *ASSERTION: Print all ships that are stored in the library.
     *
     ************************************************************/

    public static void viewShips()
    {
		    System.out.println("The ships listed inside the library are:\n");
		    shipStorage.viewShips();

    }
    /************************************************************
     *SUBMODULE: FileName
     *IMPORT: none
     *EXPORT: none
     *ASSERTION: Asks for file name to the user and return the string of filename
     *
     ************************************************************/

    public static String FileName()
    {
		    Scanner input = new Scanner(System.in);
		    String filename;
		    System.out.println("Please enter the file name without the extension");
		    filename = input.next();
		    return filename;

    }

	/************************************************************
	 *SUBMODULE: validateSer1
	 *IMPORT: st INTEGER
	 *EXPORT: st Integer
	 *ASSERTION: check if the first half of serial number is within the range.
	 *
	 ************************************************************/

	private int validateSer1(int st)
	{
		try
		{
			while (st < 100 || st > 300)
			{

				System.out.println("You've entered incorrect first three digit of the serial number\n" +
						"Please re enter");
				st = input.nextInt();
			}
		}
		catch (InputMismatchException ex)
		{
			System.out.println("You've entered something that is not a number. Please restart the program");
		}
		return st;
	}

	/************************************************************
	 *SUBMODULE: validateSer2
	 *IMPORT: nd INTEGER
	 *EXPORT: nd INTEGER
	 *ASSERTION: check if the second half of serial number is within the range.
	 *
	 ************************************************************/

	private int validateSer2 (int nd)
	{
		try
		{
			while (nd < 1 || nd > 999)
			{

				System.out.println("You've entered incorrect last three digit of the serial number.\n" +
						"Please re-enter");
				nd = input.nextInt();

			}
		}
		catch (InputMismatchException ex)
		{
			System.out.println("You've entered something that is not a number. Please restart the program");
		}
		return nd;
	}

	/************************************************************
	 *SUBMODULE: validateYear
	 *IMPORT: year INTEGER
	 *EXPORT: year INTEGER
	 *ASSERTION: check if the inputted year is within the range
	 *
	 ************************************************************/

	private int validateYear(int year)
	{
		while(year < 1955 || year > 2022)
		{
			try
			{
				System.out.println("You've entered incorrect commission year.\n" +
						"Please re-enter the correct commission year");
				year = input.nextInt();
			}
			catch (InputMismatchException ex)
			{
				System.out.println("You've entered something that is not a number. Please restart the program");
				year= input.nextInt();
			}
		}
		return year;
	}

	/************************************************************
	 *SUBMODULE: validatehull
	 *IMPORT: hull STRING
	 *EXPORT: hull STRING
	 *ASSERTION: check if the hull is one of the predefined hull type
	 *
	 ************************************************************/

	private String validatehull(String hull)
	{
		while (!hull.equals("AL") && !hull.equals("TI") && !hull.equals("ST"))
		{
			System.out.println("You've entered incorrect hull type.\n" +
					"Please re enter");
			hull = input.next().toUpperCase();
		}
		return hull;
	}

	/************************************************************
	 *SUBMODULE: validateCylinders
	 *IMPORT: cylinders INTEGER
	 *EXPORT: cylinders INTEGER
	 *ASSERTION: check if Cylinders is within the given range
	 *
	 ************************************************************/

	private int validateCylinders(int cylinders)
	{
		while (cylinders < 2 || cylinders > 20)
		{
			try
			{
				System.out.println("You've entered invalid number of cylinders.\n" +
						"Please re-enter a valid cylinder");
				cylinders = input.nextInt();
			}
			catch (InputMismatchException ex)
			{
				System.out.println("You've entered something that is not a number. Please restart the program");
				cylinders = input.nextInt();
			}
		}
		return cylinders;
	}

	/************************************************************
	 *SUBMODULE: validateFuel
	 *IMPORT: fuel STRING
	 *EXPORT: fuel STRING
	 *ASSERTION: check if Fuel is one of the predefine full type
	 *
	 ************************************************************/

	private String validateFuel(String fuel)
	{
		while(!fuel.equals("BA")&&!fuel.equals("DI")&&!fuel.equals("BI"))
		{
			System.out.println("You've entered invalid fuel type.\n" +
					"Please re-enter a valid fuel type");
			fuel = input.nextLine().toUpperCase();
		}
		return fuel;
	}
	/************************************************************
	 *SUBMODULE: validateOrdnance
	 *IMPORT: Ordnance STRING
	 *EXPORT: Ordnance STRING
	 *ASSERTION: check if Ordnance is not null
	 *
	 ************************************************************/
	private String validateOrdnance(String Ordnance)
	{
		while(Ordnance.equals("")||Ordnance.equals(null)||Ordnance.equals(" "))
		{
			System.out.println("Invalid Ordnance, Ordnance cannot be empty.\n" +
					"Please re enter a valid Ordnance");
			Ordnance = input.next();
		}
		return Ordnance;
	}

	/************************************************************
	 *SUBMODULE: validateMaxDepth
	 *IMPORT: REAL depth
	 *EXPORT: REAL depth
	 *ASSERTION: check if depth is within the range
	 *
	 ************************************************************/

	private double validateMaxDepth(double depth)
	{
		while(depth > 0 || depth <-500)
		{
			try
			{
				System.out.println("You've entered invalid maximum diving depth.\n" +
						"Please re-enter a valid one");
				depth = input.nextDouble();
			}
			catch (InputMismatchException ex)
			{
				System.out.println("You've entered something that is not a number. Please restart the program");
				depth=input.nextDouble();
			}
		}
    	return depth;
	}

	/************************************************************
	 *SUBMODULE: validateWingSpan
	 *IMPORT: REAL span
	 *EXPORT: REAL span
	 *ASSERTION: check if span is whithin the range
	 *
	 ************************************************************/

	private double validateWingSpan(double span)
	{
		while(span < 2.20 || span> 25.6)
		{
			try
			{
				System.out.println("You've entered invalid wingspan length.\n" +
						"Please re-enter a valid one");
				span = input.nextDouble();
			}
			catch (InputMismatchException p)
			{
				System.out.println("You've entered something that is not a number. Please restart the program");
				span=input.nextDouble();
			}
		}
		return span;
	}

}
