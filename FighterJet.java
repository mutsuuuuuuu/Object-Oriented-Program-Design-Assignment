import java.text.DecimalFormat;

/************************************************************
 NAME: DICKY GULTOM
 STUDENT ID : 19487537
 PURPOSE : To make a FighterJet object
 ************************************************************/
public class FighterJet extends shipClass
{
    private String Ordanace;
    private double wingSpan;

    /************************************************************
     SUBMODULE: DEFAULT CONSTRUCTOR
     EXPORT: NONE
     IMPORT: NONE
     ASSERTION: address the new FighterJet
     ************************************************************/
public FighterJet()
{
    super();
    Ordanace ="";
    wingSpan = 17.4;
}

    /************************************************************
     SUBMODULE: FighterJet
     IMPORT: a_serialnum <- String, a_ordanace <- String, a_cyl <- Integer, a_fuel <- String, a_year <- integer,
     a_wing <- double
     EXPORT: NONE
     ASSERTION: ADDRESS THE NEW Figther Jet
     ************************************************************/

public FighterJet(String a_serialnumber, int a_commissionyear, int a_cylinders, String a_fuel, String a_ordanace, double a_wing)
{
    super(a_serialnumber,a_commissionyear,a_cylinders,a_fuel);
    if (valwing(a_wing))
    {
      wingSpan = a_wing;
      Ordanace = a_ordanace;
    }
    else
    {
        throw new IllegalArgumentException ("cannot Import");
    }
}

    /************************************************************
     SUBMODULE:COPY CONSTRUCTOR
     EXPORT: NONE
     IMPORT: copyFighterJet <- OBJECT
     ASSERTION: will make a new fighterjet object
     ************************************************************/

public FighterJet(FighterJet copyFighterJet)
{
    super(copyFighterJet);
    Ordanace = copyFighterJet.getOrdanace();
    wingSpan = copyFighterJet.getwingSpan();
}

    /************************************************************
     SUBMODULE:setOrdanace
     EXPORT: NONE
     IMPORT: ordanace <- string
     ASSERTION: check if ordanace is empty, if true then Ordanace is set to custom output
                if false then Ordanace is set to ordaanace
     ************************************************************/
    public void setOrdanace(String ordanace)
    {
        if(ordanace == null || ordanace == "")
        {
            Ordanace = "no Ordnance at all.";
        }
        else
        {
        Ordanace = ordanace;
        }
    }
    /************************************************************
     SUBMODULE:setWingSpan
     EXPORT: NONE
     IMPORT: m_wingspan <- real
     ASSERTION: set wingSpan to m_wingspan if true, fail otherwise
     ************************************************************/

    public void setWingSpan(double m_wingspan)
    {
        if(valwing(m_wingspan))
        {
            wingSpan = m_wingspan;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Wing span length");
        }
    }

    /************************************************************
     SUBMODULE:getOrdanace
     EXPORT: Ordanace
     IMPORT: none
     ASSERTION:
     ************************************************************/

    public String getOrdanace()
    {
        return Ordanace;
    }

    /************************************************************
     SUBMODULE:getwingSpan
     EXPORT:wingspan
     IMPORT:none
     ASSERTION:
     ************************************************************/
    public double getwingSpan()
    {
        return wingSpan;
    }


    /************************************************************
     SUBMODULE:equals
     EXPORT:BOOLEAN
     IMPORT: inObj <- object
     ASSERTION:
     ************************************************************/
    public boolean equals(Object inObj)
    {
        FighterJet jet;
        boolean same = false;
        if (inObj instanceof FighterJet)
        {
            jet = (FighterJet)inObj;
            if(super.equals(jet))
                if(Ordanace == jet.getOrdanace())
                    if (wingSpan == jet.getwingSpan())
                        same = true;
        }

    return same;
    }
    /************************************************************
     SUBMODULE:clone
     EXPORT: copyFighterJet <- object
     IMPORT: none
     ASSERTION: will clone a fighter jet object with the same features
     ************************************************************/

    public FighterJet clone()
    {
        return new FighterJet(this);
    }

    /************************************************************
     SUBMODULE:toString
     EXPORT: String
     IMPORT: none
     ASSERTION: will output this message to the user
     ************************************************************/

    public String toString()
    {
        String lastbit = ". It is a fighter jet with a wing span of " +wingSpan+ " metres and equipped with " +Ordanace+".";
        return (super.toString()+lastbit);
    }

    /************************************************************
     SUBMODULE:calcTravel
     EXPORT: result <- real number
     IMPORT:  distance <- real
     ASSERTION: will calculate calcTravel

     ************************************************************/

    public double calcTravel(int distance)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        double result;
        result = Double.parseDouble(df.format(distance / (getwingSpan() * super.getShipengine().getCylinders()*150)));
        return result;
    }

    /************************************************************
     SUBMODULE:shipType
     EXPORT: "Fighter Jet"
     IMPORT: NONE
     ASSERTION: will return String "shipType"

     ************************************************************/

    public String shipType()
    {
        return "Fighter Jet";
    }

    /************************************************************
     SUBMODULE: toFile
     EXPORT: write <- String
     IMPORT: NONE
     ASSERTION:

     ************************************************************/

    public String toFile()
    {
        String write="F," +super.getSerialnumber()+ "," +super.getComissionyear()+","+super.getShipengine().getCylinders()+","+super.getShipengine().getFuel()+","+getwingSpan()+","+getOrdanace();
        return write;
    }

    /************************************************************
    SUBMODULE: valOrdnance
    EXPORT: Boolean
    IMPORT:  ordnance <- String
    ASSERTION: return boolean is 1 if Ordnance is not null and empty Else fails
     ************************************************************/

    private boolean valOrdnance(String ordanace)
    {
        return(!ordanace.equals(null)&&!ordanace.equals("")&&!ordanace.equals(" "));
    }

    /************************************************************
     SUBMODULE: valwing
     EXPORT: a_wing <- real number
     IMPORT:  a boolean
     ASSERTION: return boolean equals 1 if year between 2.20 and 25.6 else boolean is 0

     ************************************************************/

    private boolean valwing(double a_wing)
    {
        return ((a_wing>=2.20) && (a_wing<=25.6));
    }

}


