
public class Departure implements Comparable<Departure>{
	int[] route;
	int expectedDeparture;
	
	public Departure (int[] route_, int expectDeparture_) {
		this.route = route_;
		this.expectedDeparture = expectDeparture_;
	}

	@Override
	public int compareTo(Departure o) {
		if (this.expectedDeparture > o.expectedDeparture) {
			return 1;
		} else if (this.expectedDeparture < o.expectedDeparture) {
			return -1;
		} else {
			return 0;
		}
		
		public String toString() {
			
			int levels = 0;		
			int levelLimit = lineCount();	
			int num = 1; 	
			int numCount = 0;		
			StringBuilder sb = new StringBuilder();	
			
			sb.append("Expected Departure " + elements + " elements\n");
			
			while (levels < levelLimit) {
			
				if (levels < 1) {
					numCount = 1;
				} else {
					numCount = (int) Math.pow(levels, 2);
					if (numCount == 1) {
						numCount = 2;
					}
				}
				
				while (num <= elements && numCount > 0) {
				
					int tabs = levelLimit - levels;
						
						while (tabs > 0) {
							--tabs;
							sb.append("\t");
						}
					
					sb.append(data[num].toString());
					
					num++;
					numCount--;
				}
				
				sb.append("\n");
				levels++;
			}
		
			return new String(sb);
		}
			
	}
	
}
