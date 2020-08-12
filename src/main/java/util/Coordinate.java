package util;

public class Coordinate {
	private int X;
	private int Y;
	
	public Coordinate(int x, int y) {
		super();
		this.X = x;
		this.Y = y;
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}
	
	public void incrementX() {
		this.X++;
	}
	
	public void incrementY() {
		this.Y++;
	}
	
	public void decrementX() {
		this.X--;
	}
	
	public void decrementY() {
		this.Y--;
	}
	
	public boolean equals(Coordinate other) {
		return this.X == other.getX() && this.Y == other.getY();
	}
	
	@Override
	public String toString() {
		return "("+ this.X + ", " + this.Y + ")";
	}
}
