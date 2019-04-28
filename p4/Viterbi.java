package p4;

/**
 * Viterbi Algorithm
 */
public class Viterbi
{
    /**
     * HMM model
     * @param obs observation
     * @param states states
     * @param start_p start_probability
     * @param trans_p transition_probability
     * @param emit_p emission_probability
     * @return sequence
     */
    public static int[] compute(int[] obs, int[] states, double[] start_p, double[][] trans_p, double[][] emit_p)
    {
        double[][] V = new double[obs.length][states.length];
        int[][] path = new int[states.length][obs.length];
 
        for (int y : states)
        {
            V[0][y] = start_p[y] * emit_p[y][obs[0]];
            path[y][0] = y;
        }
 
        for (int t = 1; t < obs.length; ++t)
        {
            int[][] newpath = new int[states.length][obs.length];
 
            for (int y : states)
            {
                double prob = -1;
                int state;
                for (int y0 : states)
                {
                    double nprob = V[t - 1][y0] * trans_p[y0][y] * emit_p[y][obs[t]];
                    if (nprob > prob)
                    {
                        prob = nprob;
                        state = y0;
                        // record the biggest probability
                        V[t][y] = prob;
                        //System.out.println(t +" "+ y + " " + V[t][y]);
                        // record the path
                        System.arraycopy(path[state], 0, newpath[y], 0, t);
                        newpath[y][t] = y;
                    }
                }
            }
 
            path = newpath;
        }
 
        double prob = -1;
        int state = 0;
        for (int y : states)
        {
            if (V[obs.length - 1][y] > prob)
            {
                prob = V[obs.length - 1][y]; 
                state = y; 
            }       
        }
        System.out.println("Sequence Probability:" + prob);
        return path[state];
    }
}