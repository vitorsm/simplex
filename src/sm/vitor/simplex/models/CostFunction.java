package sm.vitor.simplex.models;

import java.util.ArrayList;
import java.util.List;

public class CostFunction {
//	private List<Float> coefficients;
	private List<Variable> variables;
	private int countVariablesDecision;
	
	public CostFunction() {
		variables = new ArrayList<Variable>();
	}
	
//	public List<Float> getCoefficients() {
//		return coefficients;
//	}
//
//	public void setCoefficients(List<Float> coefficients) {
//		this.coefficients = coefficients;
//		if (coefficients != null) {
//			countVariablesDecision = coefficients.size();
//		} else {
//			countVariablesDecision = 0;
//		}
//	}
	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariablesFloat(List<Float> values) {
		this.variables = new ArrayList<Variable>();
		if (values != null) {
			for (int i = 0; i < values.size(); i++) {
				Float v = values.get(i);
				Variable variable = new Variable(i, v);
				variables.add(variable);
			}
			countVariablesDecision = values.size();
		} else {
			countVariablesDecision = 0;
		}
	}
	
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
		if (variables != null) {
			countVariablesDecision = variables.size();
		} else {
			countVariablesDecision = 0;
		}
	}
	
	public void addVariableZero() {
		Variable variable = new Variable(variables.size(), 0);
		variables.add(variable);
	}
	
	public void addVariableArticial() {
		Variable variable = new Variable(variables.size(), 999999f);
		variables.add(variable);
	}
	
	public int getCountVariablesDecision() {
		return countVariablesDecision;
	}
	
	public Variable getVariableByPosition(int position) {
		for (Variable v : variables) {
			if (v.getPosition() == position) {
				return v;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		if (variables != null) {
			String fx = "";
			String coef = "";
			
			for (int i = 0; i < variables.size(); i++) {
				Float f = variables.get(i).getValue();
				fx += "x" + (i + 1) + ", ";
				
//				if (i > 0) {
//					if (f.floatValue() < 0) {
//						coef = coef.substring(0, coef.length() - 2) + "- ";
//					}
//				}
//				
//				coef += f.floatValue() + "x" + (i + 1) + " + ";
				
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
				fx = fx.substring(0, fx.length() - 2);
				coef = coef.substring(0, coef.length() - 2);
			}
			
			return "f(" + fx + ") = " + coef;
		} else {
			return null;
		}
	}
}
