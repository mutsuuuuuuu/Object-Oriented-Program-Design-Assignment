public class FighterJetTest
{

    public static void main(String[] args)
    {
        try
        {
            FighterJet[] jet = new FighterJet[4];
            jet[0] = new FighterJet();
            jet[1] = new FighterJet("300.999",2009,5,Engine.BI,"Laser Guided", 11); //has to use Engine.BI, it wont take a string "BI"
            jet[2] = new FighterJet(jet[1]);
            jet[3] = new FighterJet().clone();

            System.out.println("TESTS");
            for (int x = 0; x < jet.length; x++)
            {
                System.out.println("Jet Fighter[" + x + "]:" + jet[x].toString());
            }

            System.out.println("\nEQUALS METHOD TESTS:");
            System.out.println("Equals (object) expected TRUE: " + jet[1].toString().equals(jet[2].toString()));
            System.out.println("Equals (object) expected FALSE: " + jet[0].toString().equals(jet[2].toString()));

            //getters and setters
            System.out.println("\nGETTERS AND SETTERS:");
            jet[0].setSerialnumber(jet[1].getSerialnumber());
            System.out.println(jet[0].getSerialnumber() + " = " + jet[1].getSerialnumber());

            jet[0].setOrdanace(jet[1].getOrdanace());
            System.out.println(jet[0].getOrdanace() + " = " + jet[1].getOrdanace());

            jet[0].setComissionyear(jet[1].getComissionyear());
            System.out.println(jet[0].getComissionyear() + " = " + jet[1].getComissionyear());

            jet[0].setWingSpan(jet[1].getwingSpan());
            System.out.println(jet[0].getwingSpan() + " = " + jet[1].getwingSpan());

            jet[0].setShipengine(jet[1].getShipengine());
            System.out.println(jet[0].getShipengine() + " = " +jet[1].getShipengine());

            System.out.println("Calculate Travel Tests:");
            double result;
            result = jet[1].calcTravel(83);
            System.out.println(result+" Hours");

        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());

        }
    }
}
