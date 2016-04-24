import java.io.IOException;

public class Testing {
	
	public static void main(String[] args) throws IOException {
		final int tests = 1000; //change this number to change the number of tests running
		String[] cases = {"base", "optimized"}; 
		System.out.println("TESTING...");
		
		for (int i = 1; i <= tests; i++) {
			//uses numbers 1 and 2 for testing
			for (int j = 0; j < 2; j++) {
				System.out.println("Testing: " + i * 10 + " trains, using " + cases[j] + " case.");
				long start = System.currentTimeMillis();
				String[] arguments = {Integer.toString(i * 10), Integer.toString(j), "true"};
				Driver.main(arguments);
				long end = System.currentTimeMillis();
				System.out.println("Elapsed time: " + (end - start));
			}
		}
	}

}
