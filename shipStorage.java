import java.io.FileWriter;
import java.io.IOException;

public class shipStorage
{

    public static final int MAX = 30;
    public static shipClass [] shipsCollection;
    public int shipCount;

    /************************************************************
     *Default Constructor:
     *IMPORT: none
     *EXPORT: address of new shipStorage object
     *ASSERTION: initialize the shipcount to 0 and create shipsCollection array size 30
     *
     ************************************************************/

    public shipStorage()
    {
      shipCount = 0;
      shipsCollection = new shipClass[MAX];
    }
    /************************************************************
     * SUBMODULE : addShip
     *IMPORT: ships(OBJECT)
     *EXPORT: NONE
     *ASSERTION: Add the ships object to ships collection array if there is space and the
     *             object is not null
     *
     ************************************************************/

    public void addships (shipClass ships)
    {
            if (shipsCollection[shipCount] == null && ships != null)
            {
                shipsCollection[shipCount] = ships;
                shipCount++;
            }
    }

    /************************************************************
     * SUBMODULE : destinationCheck
     *IMPORT: distance
     *EXPORT: NONE
     *ASSERTION: Find the fastest ship and print it to the user if shipsCollection is not null
     *
     ************************************************************/

    public static void destinationcheck(int distance)
    {

	    String FastestshipType, ElementshipsType;
	    String serialNumber, serialNumberFast;
	    double fastestship, elementship;

	    fastestship = shipsCollection[0].clone().calcTravel(distance);
	    FastestshipType = shipsCollection[0].shipType();
	    serialNumberFast = shipsCollection[0].getSerialnumber();
	    for (int x = 0; x < shipsCollection.length; x++)
	    {
		    try
		    {
			    elementship = shipsCollection[x].clone().calcTravel(distance);
			    ElementshipsType = shipsCollection[x].shipType();
			    serialNumber = shipsCollection[x].getSerialnumber();

			    if (fastestship < elementship)
			    {
				    fastestship = elementship;
				    FastestshipType = ElementshipsType;
				    serialNumberFast = serialNumber;
			    }
		    }
		    catch (NullPointerException ex)
		    {

		    }
	    }
	    System.out.println("the fastest ship is " + FastestshipType + " with serial number of : " +serialNumberFast+" and it takes " + fastestship + " hours to cover " + distance + " M of distance");
    }

	/************************************************************
	 *SUBMODULE: equals
	 *IMPORT: obj
	 *EXPORT: obj
	 *ASSERTION: two objects are equal if they have the same information
	 ************************************************************/
	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}


	/*
	SUBMDOULE: findDuplicates
	IMPORT: none
	EXPORT: none
	ASSERTION: searching for duplicates object
	ALGORITHM:
	*/
	public void findDuplicates()
    {
    	int duplicatesCount = 0;
		    for (int x = 0; x <shipsCollection.length; x++)
		    {
			    try
			    {
				    for (int y = x + 1; y < shipsCollection.length; y++)
				    {
					    try
					    {
						    if (shipsCollection[x].toString().equals(shipsCollection[y].toString()))
						    {
							    System.out.println("A duplicate has been found: " + shipsCollection[x]);
							    duplicatesCount++;
							    System.out.println("Duplicate count: " + duplicatesCount);
						    }
					    }
					    catch (NullPointerException ex)
					    {

					    }
				    }
			    }
			    catch (NullPointerException ex)
			    {

			    }
		    }
    }

	/************************************************************
     *SUBMODULE: viewShips
     *IMPORT: NONE
     *EXPORT: NONE
     *ASSERTION: loop through THE ARRAY and construct a string.
     ************************************************************/

    public static void viewShips()
    {
    	try
	    {
	    	if(shipsCollection != null)
	    	{
	    		for (int z = 0; z <= shipsCollection.length; z++)
	    		{
	    			System.out.println((z + 1) + ". " + shipsCollection[z].toString());
	    		}
	    	}
	    }
    	catch (NullPointerException E)
	    {

	    }
    }

	/************************************************************
	 *SUBMODULE: FileWrite
	 *IMPORT: NONE
	 *EXPORT: NONE
	 *ASSERTION: save ships in the array to a text file
	 ************************************************************/


	public void FileWrite()
    {
    	try
	    {
		    FileWriter writer = new FileWriter("outputSHIPS.txt");

			    for (int x = 0; x < shipsCollection.length; x++)
			    {
			    	try
				    {
					    writer.write(shipsCollection[x].toFile()+"\n");
				    }
			    	catch (NullPointerException ex)
				    {

				    }
			    }
			    writer.close();
	    }
    	catch (IOException ex)
	    {

	    }
    }

	/************************************************************
	 *SUBMODULE: setShipsCollection
	 *IMPORT: NONE
	 *EXPORT: NONE
	 *ASSERTION:
	 ************************************************************/

	public static void setShipsCollection(shipClass[] shipsCollection)
	{
		shipStorage.shipsCollection = shipsCollection;
	}
	/************************************************************
	 *SUBMODULE: setShipCount
	 *IMPORT: NONE
	 *EXPORT: NONE
	 *ASSERTION:
	 ************************************************************/

	public void setShipCount(int shipCount)
	{
		this.shipCount = shipCount;
	}

	/************************************************************
	 *SUBMODULE: getShipsCollection
	 *IMPORT: NONE
	 *EXPORT: shipsCollection
	 *ASSERTION:
	 ************************************************************/

	public static shipClass[] getShipsCollection()
	{
		return shipsCollection;
	}

	/************************************************************
	 *SUBMODULE: getShipCount
	 *IMPORT: NONE
	 *EXPORT: shipCount
	 *ASSERTION:
	 ************************************************************/

	public int getShipCount()
	{
		return shipCount;
	}



}
