import java.util.LinkedList;

public class Track{

	int point1;
	int point2;
	double weight;
	LinkedList<Lock> locks;

	public Track(int p1, int p2, double weight) {
		this.point1 = p1;
		this.point2 = p2;
		this.weight = weight;
		locks = new LinkedList<Lock>();
	}

	public boolean equals(Track second) {
		return (((this.point1 == second.point1 && this.point2 == second.point2) || (this.point2 == second.point1 && this.point1 == second.point2))) && (this.weight == second.weight);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append('{');
		sb.append('[');
		sb.append(this.point1 +", " +this.point2);
		sb.append(']');
		sb.append(", " + this.weight);
		sb.append('}');

		return sb.toString();
	}

	public Lock addLock (int start, int end) {
		Lock temp = new Lock(start, end);

		if (locks.isEmpty()) {
			locks.add(temp);
			return temp;
		} else {
			for (int i = 0; i < locks.size(); i++) {
				if (locks.get(i).before(temp)) {
					continue;
				} else if (locks.get(i).after(temp)) {
					locks.add(i, temp);
					return temp;
				} else {
					int delay = locks.get(i).delay(temp);
					this.delayLock(temp, delay);
					return addLock(temp, i + 1);
				}
			}
			throw new RuntimeException("Lock not added");
		}
	}

	public Lock addLock(Lock temp, int startPosition) {

		for (int i = startPosition; i < locks.size(); i++) {
			if (locks.get(i).before(temp)) {
				continue;
			} else if (locks.get(i).after(temp)) {
				locks.add(i, temp);
				return temp;
			} else {
				int delay = locks.get(i).delay(temp);
				this.delayLock(temp, delay);
				return addLock(temp, i);
			}
		}
		throw new RuntimeException("Lock not added");
	}

	public void delayLock(Lock lock, int delay) {
		lock.start += delay;
		lock.end += delay;
	}

}
