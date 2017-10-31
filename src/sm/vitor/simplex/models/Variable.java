package sm.vitor.simplex.models;

public class Variable {
	private int position;
	private float value;
	
	public Variable(int position, float value) {
		this.position = position;
		this.value = value;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
}
