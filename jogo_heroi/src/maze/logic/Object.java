package maze.logic;

public class Object {
	protected int x_coord;
	protected int y_coord;

	public Object(int x, int y) {
		x_coord = x;
		y_coord = y;
	}
	
	public int get_x_coord() {
		return x_coord;
	}
	public int get_y_coord() {
		return y_coord;
	}
	
	public void set_x_coord(int coord) {
		x_coord = coord;
	}
	public void set_y_coord(int coord) {
		y_coord = coord;
	}
	
	public void x_coord_inc() {
		x_coord++;
	}
	public void y_coord_inc() {
		y_coord++;
	}
	public void x_coord_dec() {
		x_coord--;
	}
	public void y_coord_dec() {
		y_coord--;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x_coord;
		result = prime * result + y_coord;
		return result;
	}

	@Override
	public boolean equals(java.lang.Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Object))
			return false;
		Object other = (Object) obj;
		if (x_coord != other.x_coord)
			return false;
		if (y_coord != other.y_coord)
			return false;
		return true;
	}

}
