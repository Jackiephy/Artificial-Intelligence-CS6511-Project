package p4;

import static p4.Main.Dice.*;
import static p4.Main.Activity.*;

public class Main
{
    static enum Dice
    {
        D1,
        D2,
        D3,
    }
    static enum Activity
    {
        N1,
        N2,
        N3,
    }
    static int[] states = new int[]{D1.ordinal(), D2.ordinal(), D3.ordinal()};
    static int[] observations = new int[]{N3.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N1.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N1.ordinal(), N3.ordinal(), N1.ordinal(), N2.ordinal(), N1.ordinal(), N2.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N2.ordinal(), N1.ordinal(), N3.ordinal(), N1.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N3.ordinal(), N3.ordinal(), N2.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N1.ordinal(), N2.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N1.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N1.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N2.ordinal(), N1.ordinal(), N3.ordinal(), N2.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N3.ordinal(), N2.ordinal(), N1.ordinal(), N1.ordinal(), N3.ordinal(), N1.ordinal(), N1.ordinal(), N3.ordinal(), N1.ordinal(), N1.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal(), N1.ordinal(), N3.ordinal(), N3.ordinal(), N3.ordinal()};
    static double[] start_probability = new double[]{0.33, 0.33, 0.33};
    static double[][] transititon_probability = new double[][]{
            {0.9600141, 0.01999295, 0.01999295},
            {0.01999295, 0.9600141, 0.01999295},
            {0.01999295, 0.01999295, 0.9600141},
    };
    static double[][] emission_probability = new double[][]{
            {0.17749698, 0.27015644, 0.5523466},
            {0.32282755, 0.38856372, 0.2886087},
            {0.22987872, 0.37872106, 0.39140022},
    };
 
    public static void main(String[] args)
    {
        int[] result = Viterbi.compute(observations, states, start_probability, transititon_probability, emission_probability);
        for(int r:result){
            System.out.print(Dice.values()[r] + " ");
        }
        System.out.println();
    }
}
