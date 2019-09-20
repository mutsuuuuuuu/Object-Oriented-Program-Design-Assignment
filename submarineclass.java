import java.text.DecimalFormat;

/************************************************************
 NAME: DICKY GULTOM
 STUDENT ID : 19487537
 PURPOSE : To make a Submarine cLASS object
 ************************************************************/

public class submarineclass extends shipClass
{
    public static final String ST = "STEEL";
    public static final String AL = "ALLOY";
    public static final String TI = "TITANIUM";

    private double maxdepth;
    private String hull;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
     *EXPORT: address of new submarineclass object
     *ASSERTION: no serialnumber, 2003 commission year, Alloy hull and max depth of 230 metres
     *
     ************************************************************/
    public submarineclass()
    {
        super();
        hull = AL;
        maxdepth = -230;
    }
    /************************************************************
     *Alternate Constructor:
     *IMPORT: a_serialnumber (String), a_year (integer), a_hull (String), a_maxdepth(real)
     *EXPORT: address of new submarineclass object
     *ASSERTION: Creates the object if the imports are valid and FAILS otherwise
     ************************************************************/

    public submarineclass(String a_serialnumber, int a_year, int a_cyl, String a_fuel, String a_hull, double a_maxdepth)
    {
        super(a_serialnumber,a_year,a_cyl,a_fuel);
        if (valhull(a_hull) && valdepth(a_maxdepth))
        {
            hull = a_hull.toUpperCase();
            maxdepth = a_maxdepth;
        }
        else
        {
            throw new IllegalArgumentException ("cannot Import");
        }

    }
    /************************************************************
     *Copy Constructor:
     *IMPORT: copysubmarine (submarineclass)
     *EXPORT: address of new submarineclass object
     *ASSERTION: Creates an object with an identical object state as the import.
     ************************************************************/

    public submarineclass(submarineclass copysubmarine)
    {
        super(copysubmarine);
        hull = copysubmarine.gethull();
        maxdepth = copysubmarine.getmaxdepth();
    }

    /************************************************************
     *SUBMODULE: setMaxdepth
     *IMPORT: m_maxdepth (real)
     *EXPORT: none
     *ASSERTION: set m_maxdepth to maxdepth if valid and fails otherwise
     ************************************************************/

    public void setMaxdepth(double a_maxdepth)
    {
        if (valdepth(a_maxdepth))
        {
            maxdepth = a_maxdepth;
        }
        else
        {
            throw new IllegalArgumentException("Invalid maximum depth");
        }
    }

    /************************************************************
     *SUBMODULE: setHull
     *IMPORT: a_hull
     *EXPORT: none
     *ASSERTION: if valid, set m_hull to hull and fails otherwise
     ************************************************************/

    public void setHull(String a_hull)
    {
        if(valhull(a_hull))
        {
            hull = a_hull.toUpperCase();
        }
        else
        {
            throw new IllegalArgumentException("Invalid hull type");
        }
    }

    /************************************************************
     SUBMODULE: gethull
     EXPORT: hull <- String
     IMPORT: NONE
     ASSERTION:
     ************************************************************/

    public  String gethull()
    {

        return hull;
    }
    /************************************************************
     SUBMODULE: getmaxdepth
     EXPORT: maxdepth <- REAL
     IMPORT: none
     ASSERTION:
     ************************************************************/

    public double getmaxdepth()
    {

        return maxdepth;
    }


    /********************************************************************
     *SUBMODULE: equals
     *IMPORT: inObj (Object)
     *EXPORT: same
     *ASSERTION: Two submarines are interchangeable if they have the same serial number,year,hull and maxdepth
     *********************************************************************/

    public boolean equals(Object inObj)
    {
       submarineclass subs;
        boolean same = false;
        if (inObj instanceof submarineclass)
        {
            subs = (submarineclass) inObj;
            if(super.equals(subs))
                if(hull == subs.gethull())
                    if (maxdepth == subs.getmaxdepth())
                        same = true;
        }
        return same;
    }
    /************************************************************
     SUBMODULE: clone
     EXPORT: copySubmarine <- object
     IMPORT: NONE
     ASSERTION: will clone the submarine object with the exact same features
     ************************************************************/


    public submarineclass clone()
    {
        return new submarineclass(this);
    }

    /************************************************************
     SUBMODULE: calcTravel
     EXPORT: Result (REAL)
     IMPORT: fighterJet OBJECT, distance REAL
     ASSERTION: will calculate the max travel
     ************************************************************/

    public double calcTravel(int distance)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        double result,firsthalf,secondhalf;

        firsthalf = distance/super.getShipengine().getCylinders();

        secondhalf = 1/(10+Math.abs(getmaxdepth()));

        result = Double.parseDouble(df.format(firsthalf*secondhalf));
        return result;
    }

    /************************************************************
     SUBMODULE: toString
     EXPORT: String of messages
     IMPORT: NONE
     ASSERTION: will output message to the user
     ************************************************************/

    public String toString()
    {
        String strs = ". It is a submarine with hull of " +hull+ " hull and maximum depth of" +maxdepth+" meters.";
        return super.toString()+strs;
    }

    /************************************************************
     SUBMODULE: shipType
     EXPORT: "Submarine"
     IMPORT: none
     ASSERTION: returning a string ship type
     ************************************************************/

    public String shipType()
    {
        return "Submarine";
    }

    /************************************************************
     SUBMODULE: toFile
     EXPORT: write <- String
     IMPORT: NONE
     ASSERTION:
     ************************************************************/

    public String toFile()
    {
        String write="S," +super.getSerialnumber()+ "," +super.getComissionyear()+","+super.getShipengine().getCylinders()+","+super.getShipengine().getFuel()+","+gethull()+","+getmaxdepth();
        return write;
    }
    /************************************************************
     SUBMODULE: valhull
     EXPORT: BOOLEAN
     IMPORT: a_hull
     ASSERTION: check if hull is either ST AL OR TI
     ************************************************************/

    private boolean valhull(String a_hull)
    {
        String HULL = a_hull.toUpperCase();
        return (HULL.equals(ST) || HULL.equals(AL) || HULL.equals(TI));
    }
    /************************************************************
     SUBMODULE: valdepth
     EXPORT: BOOLEAN
     IMPORT:a_maxdepth
     ASSERTION: check if the maximum diving depth is within the set range
     ************************************************************/

    private boolean valdepth(double a_maxdepth)
    {

        return((a_maxdepth>=-500)&&(a_maxdepth<=0));
    }


}
