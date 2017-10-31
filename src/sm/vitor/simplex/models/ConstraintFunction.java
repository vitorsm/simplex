package sm.vitor.simplex.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConstraintFunction {
	
	final public static int EQUAL = 1;
	final public static int LESS_EQUAL = 2;
	final public static int GREATER_EQUAL = 3;
	final public static int LESS = 4;
	final public static int GREATER = 5;
	
//	private List<Float> coefficients;
	private List<Variable> variables;
	private float b;
	private int type;
	
	public ConstraintFunction() {
		variables = new ArrayList<Variable>();
	}
//	public List<Float> getCoefficients() {
//		return coefficients;
//	}
//
//	public void setCoefficients(List<Float> coefficients) {
//		this.coefficients = coefficients;
//	}

	public int getType() {
		return type;
	}
	
	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariablesFloat(List<Float> values) {
		this.variables = new ArrayList<Variable>();
		System.out.println();
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				Float value = values.get(i);
				System.out.println("vai colocar a poxicao: " + i);
				Variable variable = new Variable(i, value);
				variables.add(variable);
			}
		}
	}
	
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public void setType(String str) throws IOException {
		if (str.equals("<=")) {
			this.type = LESS_EQUAL;
		} else if (str.equals("=")) {
			this.type = EQUAL;
		} else {
			throw new IOException("O operador " + str + " ainda não foi implementado.");
		}
	}
	
	public void addVariable(float f) {
//		coefficients.add(new Float(f));
		Variable variable = new Variable(variables.size(), f);
		variables.add(variable);
	}
	
	public void addVariableZero() {
//		coefficients.add(new Float(0));
		Variable variable = new Variable(variables.size(), 0);
		variables.add(variable);
	}
	
	public Variable getVariableByPosition(int position) {
//		System.out.println("ConstraintFunction - Vai buscar a posicao " + position);
		for (Variable variable : variables) {
//			System.out.println(variable.getPosition());
			if (variable.getPosition() == position) {
				return variable;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		if (variables != null) {
			String coef = "";
			String strType = "";
			
			switch (type) {
				case EQUAL:
					strType = "=";
					break;
				case LESS_EQUAL:
					strType = "<=";
					break;
				case GREATER_EQUAL:
					strType = ">=";
					break;
				case LESS:
					strType = "<";
					break;
				case GREATER:
					strType = ">";
					break;
				default:
					break;
			}
			
			for (int i = 0; i < variables.size(); i++) {
				Float f = variables.get(i).getValue();
				
				if (i > 0) {
					if (f.floatValue() < 0) {
						coef = coef.substring(0, coef.length() - 2) + "- ";
						coef += -f.floatValue() + "x" + (i + 1) + " + ";
					} else {
						coef += f.floatValue() + "x" + (i + 1) + " + ";
					}
				} else {
					coef += f.floatValue() + "x" + (i + 1) + " + ";
				}
				
				
			}
			
			if (variables.size() > 0) {
				coef = coef.substring(0, coef.length() - 2);
			}
			
			return coef + " " + strType + " " + b;
		} else {
			return null;
		}
	}
}
