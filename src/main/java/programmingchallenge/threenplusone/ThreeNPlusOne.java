package programmingchallenge.threenplusone;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Program to solve "3n + 1 problem" challenge. First draft that passed the UVa
 * online judge with 0.985 seconds.
 * <p>
 * The code structure here is used for submitting to UVa. To submit it again,
 * remove the package snippet and change class name to Main.
 * 
 * @author pirent
 *
 */
public class ThreeNPlusOne {

	static String ReadLn(int maxLg) // utility function to read from stdin
	{
		byte lin[] = new byte[maxLg];
		int lg = 0, car = -1;
		String line = "";

		try {
			while (lg < maxLg) {
				car = System.in.read();
				if ((car < 0) || (car == '\n'))
					break;
				lin[lg++] += car;
			}
		} catch (IOException e) {
			return (null);
		}

		if ((car < 0) && (lg == 0))
			return (null); // eof
		return (new String(lin, 0, lg));
	}

	public static void main(String[] args) {
		ThreeNPlusOne myWork = new ThreeNPlusOne(); // create a dinamic instance
		myWork.Begin(); // the true entry point
	}

	void Begin() {
		String input;
		StringTokenizer idata;
		int a;
		int b;
		int min;
		int max;
		int maxCycleLength;
		StringBuilder sb;

		// --------------------------------------------------

		while ((input = ThreeNPlusOne.ReadLn(255)) != null) {
			idata = new StringTokenizer(input);
			a = Integer.parseInt(idata.nextToken());
			b = Integer.parseInt(idata.nextToken());
			maxCycleLength = 1;

			if (a < b) {
				min = a;
				max = b;
			} else {
				min = b;
				max = a;
			}
			for (int i = min; i <= max; i++) {
				int temp = foo(i);
				if (temp > maxCycleLength) {
					maxCycleLength = temp;
				}
			}

			sb = new StringBuilder();
			sb.append(a).append(" ").append(b).append(" ")
					.append(maxCycleLength);
			System.out.println(sb.toString());
		}
	}

	int foo(long n) {
		int cycleLength = 1;
		while (n != 1) {
			if (n % 2 == 0) {
				n = n >> 1;
				cycleLength++;
			} else {
				n = (3 * n + 1) >> 1;
				cycleLength += 2;
			}

		}
		return cycleLength;
	}
}
