/************************************************************
 NAME: DICKY GULTOM
 STUDENT ID : 19487537
 PURPOSE : To make a Engine class object
 ************************************************************/
public class Engine
{
    public static final String BA = "BATTERY";
    public static final String DI = "DIESEL";
    public static final String BI = "BIO";

    private int cylinders;
    private String fuel;

    //default constructor

    public Engine()
    {
        cylinders = 3;
        fuel = BA;
    }

    //alternate constructor
    public Engine(int inCylinders, String inFuel)
    {
        if((valcylinder(inCylinders))&&valfuel(inFuel))
        {
            cylinders = inCylinders;
            fuel = inFuel;
        }
    }
    //copy constructor
    public Engine(Engine inEngine)
    {
        cylinders=inEngine.getCylinders();
        fuel = inEngine.getFuel();
    }

    public void setCylinders(int cylindersnumber)
    {
        if(valcylinder(cylindersnumber))
        {
            cylinders=cylindersnumber;
        }
        else
        {
            throw new IllegalArgumentException("Invalid cylinder");
        }
    }

    public void setFuel(String fueltype)
    {
        if(valfuel((fueltype)))
        {
            fuel = fueltype.toUpperCase();
        }
        else
        {
            throw new IllegalArgumentException("Invalid fuel type");
        }
    }

    public int getCylinders()
    {
        return cylinders;
    }

    public String getFuel()
    {
        return fuel;
    }

    public boolean equals(Object inObject)
    {
        boolean isEqual=false;
        if (inObject instanceof Engine)
        {
            Engine inEngine=(Engine)inObject;
            isEqual = cylinders==inEngine.getCylinders() && fuel.equals(inEngine.getFuel());
        }
        return isEqual;
    }

    public Engine clone()
    {
        Engine copyEngine;

        copyEngine = new Engine(this.cylinders, this.fuel);

        return copyEngine;
    }
    public String toString()
    {
        return ("This engine has "+cylinders+" cylinders and use "+fuel);
    }


    private boolean valcylinder(int numcyl)
    {
        return ((numcyl>=2) && (numcyl<=20));
    }

    private boolean valfuel(String fueltype)
    {
        return (fueltype.equals(BI) || fueltype.equals(BA) || fueltype.equals(DI));
    }
}
