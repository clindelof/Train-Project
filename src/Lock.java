
public class Lock{
	public Integer start;
	public Integer end;
	
	public Lock(Integer start, Integer end) {
		this.start = start;
		this.end = end;
	}
	
	public Integer getStart() {
		return this.start;
	}
	
	public Integer getEnd() {
		return this.end;
	}
	
	public boolean before(Lock o) {
		return this.end <= o.start;
	}
	
	public boolean after(Lock o){
		return this.start >= o.end;
	}
	
	public int delay(Lock o) {
		return this.end - o.start;
	}
}
