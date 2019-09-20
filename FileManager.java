import javax.swing.text.StringContent;
import java.time.Year;
import java.util.*;
import java.io.*;
import java.util.stream.Collector;


public class FileManager extends shipStorage
{
    public final static int MAX = 30;


    /************************************************************
     *SUBMODULE: readFile
     *IMPORT: filename(String), serialNumber(String), commissionYear(Integer), Cylinders(Integer), Fuel(String),
     * Speciality(String), characteristics(real).
     *EXPORT: count (integer)
     *ASSERTION: Read the input file, process the line and catch any input output exception
     *
     ************************************************************/

    public void readFile (String filename)
    {
        String serialNumber = null;
        int commissionYear = 0;
        int cylinders = 0;
        String Fuel=null;
        String Speciality=null;
        double characteristics=0;


        FileInputStream fstream = null;
        InputStreamReader inReader;
        BufferedReader rdr = null;

        String line;
        int count = 0;

        try
        {
            fstream = new FileInputStream(filename+".csv");
            inReader = new InputStreamReader(fstream);
            rdr = new BufferedReader(inReader);

            line = rdr.readLine();
            lineProcessing(line, serialNumber, commissionYear, cylinders, Fuel, Speciality, characteristics, count);
            count++;
            while (line !=null)
            {
                line = rdr.readLine();
                lineProcessing(line, serialNumber, commissionYear, cylinders, Fuel, Speciality, characteristics, count);
                count++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("No such file called that.");
        }
        catch (IOException e)
        {
            try
            {
                if (fstream != null)
                {
                    fstream.close();
                }
            }
            catch (IOException ex)
            {
                System.out.println("No such file called " +filename);
            }
        }
    }
    /************************************************************
     *SUBMODULE: lineProcessing
     *IMPORT: line(String), serialNumber(String), commissionYear(Integer), Cylinders(Integer), Fuel(String),
     *      Speciality(String), characteristics(real), count(Integer).
     *EXPORT: none
     *FUNCTION: Split the each line and assigned each bit into a array corresponds to their variables. Read the first
     * bit character to determine whether its Submarine or FighterJet. Catch any lines that does not have 7 bits of information.
     *
     ************************************************************/

    public void lineProcessing(String line, String serialNumber, int Year, int Cylinders, String Fuel,
                                      String Speciality, double characteristics, int count)
    {
        try
        {
            String[] seperate = line.split(",");

            //check if there is a empty line
            if (seperate.length == 0)
            {
                throw new IllegalArgumentException("Empty Line Are detected");
            }
            try
            {
                switch (seperate[0].charAt(0))
                {
                    case 'S':
                    case 's':
                        //addship
                        System.out.println("Adding Submarine: " + line);
                        Submarine(seperate, serialNumber, Year, Cylinders, Fuel, Speciality, characteristics, count);
                        System.out.println("Submarine has been added to the system");
                        break;

                    case 'F':
                    case 'f':
                        //add jet
                        System.out.println("Adding Fighter Jet: " + line);
                        Jet(seperate, serialNumber, Year, Cylinders, Fuel, Speciality, characteristics, count);
                        System.out.println("Fighter Jet has been added to the system");
                        break;


                    default:
                        System.out.println("The line does not represent a submarine nor fighter jet");
                }
            }
            catch (StringIndexOutOfBoundsException ex)
            {

            }
            catch (NullPointerException ex)
            {

            }
        }
        catch (NullPointerException e)
        {

        }
    }

    /************************************************************
     *SUBMODULE: Submarine
     *IMPORT: seperate (STRING ARRAY), SerialNumber(STRING), Year(Integer), Cylinders (Integer),
     * Fuel(String), Speciality (String), characteristics(DOUBLE)
     *EXPORT: none
     *ASSERTION: check if the length of lines separated is equal to 7. Assigned each bits to their corresponding variable
     * and validate it
     *
     ************************************************************/

    public void Submarine(String [] seperate, String SerialNumber, int Year, int Cylinders,
                                 String Fuel,String Speciality, double characteristics, int count)
            throws IllegalArgumentException
    {
        // here speciality is for submarine's hull and characteristics is submarine max diving depth
        /*
            seperate[0] = S
            seperate[1] = SerialNumber
            seperate[2] = Year
            seperate[3] = Cylinders
            sepearate[4] = Fuel
            seperate [5] = Speciality - > Hull Type
            Seperate [6] = characteristics -> Maximum Depth
         */

        /* The Input file  format for submarine
         * S, <serial number>, <commision year>, <cylinders>, <fuel>, <hull>, <maxdepth> */

        if (seperate.length != 7)
        {
            throw new IllegalArgumentException("You miss something on the line. Cannot import");
        }
        try {
            validateLength(seperate[1]);
            SerialNumber = validateSerialNumber(removeSpace(seperate[1])); // serial number

            Year = validateYear(removeSpace(seperate[2]));//commission year

            Cylinders = validateCylinders((removeSpace(seperate[3]))); // cylinders

            Fuel = fuelType(validateFuel(removeSpace(seperate[4].toUpperCase()))); // fuel

            Speciality = hullType(validateHull(removeSpace(seperate[5].toUpperCase()))); // hull type

            characteristics = validateMaximumDepth(Double.parseDouble(removeSpace(seperate[6])));


            submarineclass subs;
            subs = new submarineclass(SerialNumber, Year, Cylinders, Fuel, Speciality, characteristics);
            addships(subs);
            count++;
        }
        catch (NumberFormatException ex)
        {

        }
        catch (NullPointerException ex)
        {

        }
        catch (IllegalArgumentException ex)
        {

        }
        catch (ArrayIndexOutOfBoundsException ex)
        {

        }
    }
    /************************************************************
     *SUBMODULE: Jet
     *IMPORT: seperate (STRING ), SerialNumber(STRING), Year(Integer), Cylinders (Integer),
     * Fuel(String), Speciality (String), characteristics(DOUBLE), count(Integer)
     *EXPORT: none
     *ASSERTION: check if the length of lines separated is equal to 7. Assigned each bits to their corresponding variable
     * and validate it
     *
     ************************************************************/
    public void Jet(String [] seperate, String SerialNumber, int Year, int Cylinders,
                           String Fuel,String Speciality, double characteristics, int count)
            throws IllegalArgumentException
    {
        /*
            seperate[0] = S
            seperate[1] = SerialNumber
            seperate[2] = Year
            seperate[3] = Cylinders
            sepearate[4] = Fuel
            seperate [5] = Speciality - > wingspan
            Seperate [6] = characteristics -> Ordnance
         */
        /* The Input file  format for jet
         * J, <serial number>, <commision year>, <cylinders>, <fuel>, <wing span>, <Ordnance> */
        if (seperate.length != 7)
        {
            System.out.println("You miss something on the line. Cannot import");
        }
        try
        {
            validateLength(seperate[1]);
            SerialNumber = validateSerialNumber(removeSpace(seperate[1]));

            Year = validateYear(removeSpace(seperate[2]));

            Cylinders = validateCylinders(removeSpace(seperate[3]));

            Fuel = fuelType(validateFuel(removeSpace(seperate[4].toUpperCase())));

            characteristics = validateWingSpan(Double.parseDouble(removeSpace(seperate[5])));

            Speciality = validateOrdnance(seperate[6]);

            FighterJet jets;
            jets = new FighterJet(SerialNumber, Year, Cylinders, Fuel, Speciality, characteristics);
            addships(jets);
            count++;
        }
        catch (NumberFormatException ex)
        {

        }
        catch (NullPointerException ex)
        {

        }
        catch (IllegalArgumentException ex)
        {

        }
        catch (ArrayIndexOutOfBoundsException ex)
        {

        }

    }

    /************************************************************
     *SUBMODULE: removeSpaces
     *IMPORT: Seperate <- STRING
     *EXPORT: string
     *ASSERTION: remove space inside the string valid
     *
     ************************************************************/
    private String removeSpace(String check)
    {
        String newCheck;

        newCheck = check.replaceAll("\\s+",""); // will remove spaces

        return newCheck;
    }

    /************************************************************
     *SUBMODULE: validateLength
     *IMPORT: SerialNumber String
     *EXPORT: boolean
     *ASSERTION: check if the serial numbers contain 7 characters
     *
     ************************************************************/


    private static boolean validateLength(String SerialNumber) throws IllegalArgumentException
    {
        boolean match = true;
        if (SerialNumber.length()==7)
        {
            match = true;
        }
        else
            {
            match = false;
        }
        return match;
    }

    /************************************************************
     *SUBMODULE: validateSerialNumber
     *IMPORT: SerialNumber (STRING)
     *EXPORT: SerialNumber STRING
     *ASSERTION: Validate Serial Number
     *
     ************************************************************/

    private String validateSerialNumber(String SerialNumber)
    {
        String serial1,serial2;
        if (SerialNumber == null || SerialNumber.equals("") || SerialNumber.equals(" "))
        {
           System.out.println("Serial number cannot be null");
        }
        String [] splits = SerialNumber.split("\\."); // splitting the number

        //checking the first half of the serial number
	    String.format("%3d", Integer.parseInt(splits[0]));
        serial1 = checkFirst(splits[0]);

        //checking the second half of the serial number
	    String.format("%3d", Integer.parseInt(splits[1]));
        serial2 = checkSecond(splits[1]);

        SerialNumber =serial1+"."+serial2;

        return SerialNumber;
    }
    /************************************************************
     *SUBMODULE: checkFirst
     *IMPORT: First (STRING)
     *EXPORT: First
     *ASSERTION: Validate the first half of serial number, catch any thing that is not an integer
     *
     ************************************************************/

    private String checkFirst(String First)
    {
        try
        {
            if (Integer.parseInt(First) < 100 || Integer.parseInt(First) > 300)
            {
                System.out.println("Invalid First Half of Serial Number. " + First);

            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Serial number must only contains integer: " +First);

        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at processing the first half of serial number: " +First);
        }

        return First;
    }
    /************************************************************
     *SUBMODULE: checkSecond
     *IMPORT: Second(STRING)
     *EXPORT: Second
     *ASSERTION: Validate the second half of the serial number to within a range. catch any thing that is not an integer
     *
     ************************************************************/

    private String checkSecond(String Second)
    {
        try
        {
            if (Integer.parseInt(Second) < 1 || Integer.parseInt(Second) > 999)
            {
                System.out.println("Invalid Second Half of Serial Number: " + Second);
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Error at processing last half of serial number: " +Second);

        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at processing last half of serial number: " +Second);
        }
        return Second;
    }

    /************************************************************
     *SUBMODULE: validateYear
     *IMPORT: Year STRING
     *EXPORT: Year String
     *ASSERTION: Validate that Year is within the range, catch anything that is not an integer
     *
     ************************************************************/


    private static int validateYear(String Year) throws IllegalArgumentException
    {
    	int yr = Integer.parseInt(Year);
        try
        {
            if (yr < 1952 || yr > 2022)
            {
                System.out.println("Invalid Commission Year " +Year);
            }
        }
        catch (NumberFormatException Illegal)
        {
            System.out.println("Error at processing commission year: " +Year);
        }
        catch (NullPointerException ex)
        {
           System.out.println("Error at processing commission year: " +Year);
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at Processing commission year: " +Year);
        }
        return yr;
    }

    /************************************************************
     *SUBMODULE: validateCylinders
     *IMPORT: Cylinders STRING
     *EXPORT: cyl <- Integer
     *ASSERTION: Validate Cylinders
     *
     ************************************************************/
    private static int validateCylinders(String Cylinders) throws IllegalArgumentException
    {
    	int cyl;
    	cyl = Integer.parseInt(Cylinders);
        try
        {
            if (cyl < 2 || cyl> 20)
            {
                System.out.println("INVALID CYLINDER NUMBER : "+Cylinders);
            }
        }
        catch (NumberFormatException num)
        {
            System.out.println("The number of cylinder must be an integer. Invalid number of Cylinder");
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at processing Cylinders: " +Cylinders);
        }

        return cyl;
    }
    /************************************************************
     *SUBMODULE: validateFuel
     *IMPORT: Fuel (STRING)
     *EXPORT: Fuel
     *ASSERTION: Validate if Fuel is either BA, BI OR DI. catch any number
     *
     ************************************************************/

    private static String validateFuel(String Fuel) throws IllegalArgumentException
    {
        try
        {
            if (Fuel == null || Fuel.equals("") || Fuel.equals(" "))
            {
                System.out.println("Fuel cannot be empty");
            }
            if (!Fuel.equals("BIO") && !Fuel.equals("BATTERY") && !Fuel.equals("DIESEL"))
            {
                System.out.println("The fuel type is invalid " + Fuel);
            }
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at processing Fuel: "+Fuel);
        }

        return Fuel;
    }

    /************************************************************
     *SUBMODULE: validateHull
     *IMPORT: Hull
     *EXPORT: Fuel
     *ASSERTION: Validating Hull, check the hull is one of the pre-defined hull
     *
     *
     ************************************************************/

    private static String validateHull(String Hull) throws IllegalArgumentException
    {
        try
        {
            if (!Hull.equals("TITANIUM") && !Hull.equals("STEEL") && !Hull.equals("ALLOY"))
            {
                System.out.println("Invalid Hull type: " +Hull);

            }
            if (Hull.equals(null) || Hull.equals("") || Hull.equals(" "))
            {
                System.out.println("Invalid Hull. Hull cannnot be empty");
                Hull = null;
            }
        }
        catch (IllegalArgumentException f)
        {
         System.out.println("Error at processing Submarine Hull " +Hull);

        }
        catch (NullPointerException es)
        {
            System.out.println("Hull is empty !! Add something.");
        }
        return Hull;
    }
    /************************************************************
     *SUBMODULE: validateMaximumDepth
     *IMPORT: MaximumDepth <- Double
     *EXPORT: MaximumDepth <-REAL
     *ASSERTION: check if Maximum Depth is within the given range
     *
     ************************************************************/

    private static double validateMaximumDepth(double MaximumDepth) throws IllegalArgumentException
    {
        try
        {
            if (MaximumDepth > 0 || MaximumDepth < -500)
            {
                System.out.println("Invalid diving depth: " +MaximumDepth);
            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Maximum Depth must be a real integer: " +MaximumDepth);

        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at processing Maximum Depth: " +MaximumDepth);
        }
        return MaximumDepth;
    }

    /************************************************************
     *SUBMODULE: validateWingSpan
     *IMPORT: wingSpan <-real
     *EXPORT: wingSpan <- Real
     *ASSERTION: check if the wingspan is within the given range
     *
     ************************************************************/

    private static double validateWingSpan(double wingSpan) throws IllegalArgumentException
    {
        try
        {
            if (wingSpan < 2.20 || wingSpan > 25.6)
            {
                System.out.println("Invalid wing span length : " +wingSpan);

            }
        }
        catch (NumberFormatException ex)
        {
            System.out.println("Wing Span must be a real integer : " +wingSpan);

        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error at processing wingSpan: " +wingSpan);

        }

        return wingSpan;
    }

    /************************************************************
     *SUBMODULE: validateOrdnance
     *IMPORT: ordnance <- STRING
     *EXPORT: ordnance <- String
     *ASSERTION: check if ordanace is not empty
     *
     ************************************************************/

    private static String validateOrdnance(String ordnance) throws IllegalArgumentException
    {
        try
        {
            if (ordnance.equals(null) || ordnance.equals("") || ordnance.equals(" "))
            {
                System.out.println("Ordnace cannot be empty");
            }
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("Error on processing Ordanace");
        }
        catch (NullPointerException ex)
        {
            System.out.println("Ordnace is null !! Add something");
        }

        return ordnance;
    }


    /************************************************************
     *SUBMODULE: hullType
     *IMPORT: hull <- String
     *EXPORT: hull
     *ASSERTION:
     *
     ************************************************************/


    private static String hullType(String hull)
    {
        if(hull.equals("ALLOY"))
        {
            hull = submarineclass.AL;
        }
        else if (hull.equals("TITANIUM"))
        {
            hull = submarineclass.TI;
        }
        else if (hull.equals("STEEL"))
        {
            hull = submarineclass.ST;
        }
        return hull;
    }
    /************************************************************
     *SUBMODULE: fuelType
     *IMPORT:  Fuel <- String
     *EXPORT: Fuel
     *ASSERTION:
     *
     ************************************************************/
    private static String fuelType(String Fuel)
    {
        if(Fuel.equals("BIO"))
        {
            Fuel = Engine.BI;
        }
        else if (Fuel.equals("BATTERY"))
        {
            Fuel = Engine.BA;
        }
        else if (Fuel.equals("DIESEL"))
        {
            Fuel = Engine.DI;
        }

        return Fuel;
    }

}
