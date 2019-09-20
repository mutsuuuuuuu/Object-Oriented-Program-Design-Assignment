public abstract class shipClass
{
	public String serialnumber;
	public int comissionyear;
	Engine shipengine;

	/*
	SUBMODULE: DEFAULT CONSTRUCTOR
	IMPORT: NONE
	EXPORT: NONE
	ASSERTION: address the new serial number, comissionyear and shipengine object
	ALGORITHM:
	*/

	public shipClass()
	{
		serialnumber = "";
		comissionyear = 2000;
		shipengine = new Engine();
	}

	/*
	SUBMODULE: ALTERNATIVE CONSTRUCTOR
	IMPORT: a_serialnumber STRING, a_comissionyear <- INTEGER, a_cylinder INTEGER, a_fuel STRING
	EXPORT: NONE
	ASSERTION: address the new serial number, comissionyear and shipengine object
	ALGORITHM:
	*/

	public shipClass(String a_serialnumber, int a_comissionyear, int a_cylinder, String a_fuel)
	{
		serialnumber = a_serialnumber;
		comissionyear = a_comissionyear;
		shipengine = new Engine(a_cylinder,a_fuel);
	}

	/*
	SUBMODULE: COPY CONSTRUCTOR
	IMPORT: copyshipClass shipClass
	EXPORT: NONE
	ASSERTION: copy the new serial number, comissionyear and shipengine object
	ALGORITHM:
	*/

	public shipClass(shipClass copyshipClass)
	{
		serialnumber = copyshipClass.getSerialnumber();
		comissionyear = copyshipClass.getComissionyear();
		shipengine = copyshipClass.getShipengine();
	}

	/*
	SUBMODULE: setSerialNumber
	IMPORT: serialNumber STRING
	EXPORT: None
	ASSERTION: set serialnumber to serialNumber if serialnumber is not null
	ALGORITHM:
	*/

	public void setSerialnumber(String serialNumber)
	{
		if(serialnumber == null)
		{
			throw new IllegalArgumentException("Invalid serial number");
		}
		else
		{
			serialnumber = serialNumber;
		}
	}
	/*
	SUBMODULE: setComissionyear
	IMPORT: year INTEGER
	EXPORT: NONE
	ASSERTION: set comissionyear to year if boolean returns TRUE else FAILS
	ALGORITHM:
	*/

	public void setComissionyear(int year)
	{
		if(valyear(year))
		{
			comissionyear = year;
		}
		else
		{
			throw new IllegalArgumentException("Invalid commission year");
		}
	}
	/*
	SUBMODULE: setShipengine
	IMPORT: engine Engine OBJECT
	EXPORT: NONE
	ASSERTION: set shipengine to engine
	ALGORITHM:
	*/

	public void setShipengine(Engine engine)
	{
		shipengine = engine;
	}

	/*
	SUBMODULE: getShipengine
	IMPORT: NONE
	EXPORT: shipengine
	ALGORITHM:
	*/
	public Engine getShipengine()
	{
		return shipengine;
	}

	/*
	SUBMODULE: getComissionyear
	IMPORT: NONE
	EXPORT: comissionyear
	ASSERTION:
	ALGORITHM:
	*/
	public int getComissionyear()
	{
		return comissionyear;
	}

	/*
	SUBMODULE: getSerialnumber
	IMPORT: NONE
	EXPORT: serialnumber
	ALGORITHM:
	*/
	public String getSerialnumber()
	{
		return serialnumber;
	}


	/*
	SUBMODULE: equals
	IMPORT: obj OBJECT
	EXPORT: super EQUALS obj
	ALGORITHM:
	*/
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}

	/*
	SUBMODULE: toString
	IMPORT:NONE
	EXPORT: str
	ASSERTION:
	ALGORITHM:
	*/
	public String toString()
	{
		String str = "The ship with serial number " +serialnumber+ " was commissioned in " +comissionyear+". Its engine has " +shipengine.getCylinders()+ " and uses a " +shipengine.getFuel();
		return str;
	}

	/*
	SUBMODULE: ABSTRACT calcTravel
	IMPORT: distance INTEGER
	EXPORT: NONE
	ASSERTION:
	ALGORITHM:
	*/
	public abstract double calcTravel(int distance);

	/*
	SUBMODULE: ABSTRACT clone
	IMPORT: NONE
	EXPORT: NONE
	ASSERTION:
	ALGORITHM:
	*/
	public abstract shipClass clone();

	/*
	SUBMODULE: ABSTRACT shipType
	IMPORT: NONE
	EXPORT: NONE
	ASSERTION:
	ALGORITHM:
	*/

	public abstract String shipType();

	/*
	SUBMODULE: ABSTRACT toFile
	IMPORT: NONE
	EXPORT: NONE
	ASSERTION:
	ALGORITHM:
	*/

	public abstract String toFile();

	/*
	SUBMODULE: valyear
	IMPORT: year INTEGER
	EXPORT: boolean
	ASSERTION:
	ALGORITHM:
	*/
	private boolean valyear(int a_year)
	{

		return ((a_year >= 1952)&&(a_year <=2022));
	}
}
