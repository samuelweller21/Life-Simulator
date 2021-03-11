package Game.Utils;

import java.util.Random;
import java.util.stream.DoubleStream;

public abstract class Utils {

	public static final int UNINITIATED_MEMORY = 0;

	public static int[] arrayRandomTotalAllocation(int length, int total) {
		// Returns an integer array of length length with randomly assigned integers
		// totalling total
		Random r = new Random();
		DoubleStream sDouble = r.doubles(length - 1);
		double[] DoubleN = sDouble.toArray();
		double dRunningTotal = 0;
		for (int i = 0; i < DoubleN.length; i++) {
			dRunningTotal += DoubleN[i];
		}
		for (int i = 0; i < DoubleN.length; i++) {
			DoubleN[i] = DoubleN[i] / dRunningTotal * (((double) (length - 1)) / length) * total;
		}
		int[] IntN = new int[length];
		int runningTotal = 0;
		for (int i = 0; i < IntN.length - 1; i++) {
			IntN[i] = (int) Math.round(DoubleN[i]);
			runningTotal += IntN[i];
		}

		IntN[length - 1] = total - runningTotal;
		return IntN;
	}

}
