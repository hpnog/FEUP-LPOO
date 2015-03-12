package maze.logic;

public class Object {
	protected int x_coord;
	protected int y_coord;

	Object(int x, int y) {
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
}
