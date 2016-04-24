
public class Pair<T, L> {
	T track;
	L lock;
	
	public Pair (T track, L lock) {
		this.track = track;
		this.lock = lock;
	}
}
