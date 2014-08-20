package programmingchallenge.threenplusone;
//package programmingchallenge.threenplusone;
//package programmingchallenge.threenplusone;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Program to solve "3n + 1 problem" challenge with enhancement of caching.
 * <p>
 * 
 * @author pirent
 *
 */
public class EnhancedThreeNPlusOne {
	static final int MAXIMUM_NUMBER = 1000000;
	static final int[] CACHE = new int[MAXIMUM_NUMBER];
	static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }
	
	public static void main(String[] args) {
		EnhancedThreeNPlusOne myWork = new EnhancedThreeNPlusOne();  // create a dinamic instance
        myWork.Begin();            // the true entry point
	}

	void Begin()
    {
        String input;
        StringTokenizer idata;
        int a;
        int b;
        int min;
        int max;
        int maxCycleLength;
        StringBuilder sb;
        
        //--------------------------------------------------
        
        
        while ((input = EnhancedThreeNPlusOne.ReadLn (255)) != null)
        {
          idata = new StringTokenizer (input);
          a = Integer.parseInt (idata.nextToken());
          b = Integer.parseInt (idata.nextToken());
          
          if (a < b) { min=a; max=b; } else { min=b; max=a; }
          maxCycleLength = getMaxCycleLength(min, max);

          sb = new StringBuilder();
          sb.append(a).append(" ").append(b).append(" ").append(maxCycleLength);
          System.out.println (sb.toString());
        }
    }
	
	
	int getMaxCycleLength(int a, int b) {
		int maxCycleLength = 1;
		for (int i = a; i <= b; i++) {
			maxCycleLength = Math.max(maxCycleLength, getCycleLength(i));
		}
		return maxCycleLength;
	}
	
	int getCycleLength(long n) {
		if (n == 1) {
			return 1;
		}

		// Find the cycle length of n in cache
		if (n < MAXIMUM_NUMBER) {
			int cacheValue = CACHE[(int) n];
			if (cacheValue != 0) {
				return cacheValue;
			}
		}

		// If not found in cache, calculates it.
		// Each pass is counted as one cycle length.
		int cycleLength = 1 + getCycleLength(nextNumber(n));
		if (n < MAXIMUM_NUMBER) {
			CACHE[(int) n] = cycleLength;
		}
		return cycleLength;
	}
	
	long nextNumber(long n) {
		if (n % 2 == 0) {
			n >>= 1;
		} else {
			n *= 3;
			n++;
		}
		return n;
	}
}
