import java.util.*;

public class submarinetest
{
    public static void main(String [] args)
    {
            submarineclass[] submarine = new submarineclass[4];
            submarine[0] = new submarineclass();
            submarine[1] = new submarineclass("248.887",2001,5,Engine.DI,submarineclass.AL,-200);
            submarine[2] = new submarineclass(submarine[1]);
            submarine[3] = new submarineclass().clone();

            System.out.println("TESTS");
            for (int i = 0; i < submarine.length; i++)
            {
                System.out.println("Submarine[" + i + "]:" + submarine[i].toString());
            }

            System.out.println("\nEQUAL METHODS TESTS:");
            System.out.println("Equals (object) expected TRUE: " + submarine[1].toString().equals(submarine[2].toString()));
            System.out.println("Equals (object) expected FALSE: " + submarine[0].equals(submarine[2]));

            System.out.println("\nGETTERS AND SETTERS:");
            submarine[0].setSerialnumber(submarine[1].getSerialnumber());
            System.out.println(submarine[0].getSerialnumber() + " = " + submarine[1].getSerialnumber());

            submarine[0].setComissionyear(submarine[1].getComissionyear());
            System.out.println(submarine[0].getComissionyear() + " = " + submarine[1].getComissionyear());

            submarine[0].setHull(submarine[1].gethull());
            System.out.println(submarine[0].gethull() + " = " + submarine[1].gethull());

            submarine[0].setMaxdepth(submarine[1].getmaxdepth());
            System.out.println(submarine[0].getmaxdepth() + " = " + submarine[1].getmaxdepth());

            submarine[0].setShipengine(submarine[1].getShipengine());
            System.out.println(submarine[0].getShipengine() + " = "+ submarine[1].getShipengine());


            System.out.println("Calculate Travel Tests:");
            double result;
            result = submarine[1].calcTravel(83);
            System.out.println(result+" Hours");
        }
    }
