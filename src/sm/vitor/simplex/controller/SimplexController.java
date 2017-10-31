package sm.vitor.simplex.controller;

import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;
import sm.vitor.simplex.models.ConstraintFunction;
import sm.vitor.simplex.models.CostFunction;
import sm.vitor.simplex.models.Variable;

public class SimplexController {
	private boolean showCalc;
	
	private String lineBreak;
	private String tab;
	private String line;
	
	public SimplexController() {
		showCalc = true;
		
		lineBreak = "<br>";
		tab = "&nbsp;";
		line = lineBreak + "--------------------------------------------------------------------------------------------------" + lineBreak;
	}
	
	public boolean isShowCalc() {
		return showCalc;
	}

	public void setShowCalc(boolean showCalc) {
		this.showCalc = showCalc;
	}

//	public String simplex(CostFunction costFunction, List<ConstraintFunction> constraintFunctions, int qtdIterMax, boolean showCalc) {
//		this.showCalc = showCalc;
//		String strReturn = "<html>";
//		
//		convertToNormalForm(costFunction, constraintFunctions);
//		
//		if (showCalc) {
//			strReturn += "Função na forma normal: \r\n\r\n";
//			
//			strReturn += "\t" + costFunction.toString() + "\r\n";
//			for (ConstraintFunction c : constraintFunctions) {
//				strReturn += "\t" + c.toString() + "\r\n";	
//			}
//			
//			strReturn += "\r\n";
//		}
//
//		List<Integer> vetorB = getVetorB(costFunction, constraintFunctions);
//		List<Integer> vetorN = getVetorN(costFunction, constraintFunctions);
//		
//		if (showCalc) {
//			strReturn += "Vetor de indices B: \r\n\r\n";
//		}
//
//		int counterIter = 1;
//		while (qtdIterMax < 0 || qtdIterMax > 0) {
//			strReturn += "\r\n--------------------------------------------------------------------------------------------------\r\n";
//			strReturn += "\t\t\t Iteração " + counterIter++;
//			
//			double matrizB[][] = getMatrizX(vetorB, constraintFunctions);
//			double matrizN[][] = getMatrizX(vetorN, constraintFunctions);
//			double vetorb[][] = getVetorb(constraintFunctions);
//			
//			double matrizBT[][] = transpose(matrizB);
//			
//			if (showCalc) {
//				strReturn += "Matriz B: \r\n\r\n";
//				strReturn += printMatriz(matrizB) + "\r\n";
//				
//				strReturn += "Matriz N: \r\n\r\n";
//				strReturn += printMatriz(matrizN) + "\r\n";
//				
//				strReturn += "Vetor b: \r\n\r\n";
//				strReturn += printMatriz(vetorb) + "\r\n";
//			}
//		
//			Matrix B = new Matrix(matrizB);
//			Matrix BInverse = B.inverse();
//			
//			double vetorXBHat[][] = multiplication(BInverse.getArray(), vetorb);
//			
//			if (showCalc) {
//				strReturn += "vetor XB chapéu: \r\n";
//				strReturn += printMatriz(vetorXBHat) + "\r\n";
//				
//				strReturn += "Matriz BT: \r\n";
//				strReturn += printMatriz(matrizBT) + "\r\n";
//			}
//			
//			double vetorCB[][] = getVetorC(costFunction, vetorB);
//			
//			Matrix cB = new Matrix(vetorCB);
//			Matrix BT = new Matrix(transpose(matrizB));
//			
//			Matrix lambda = BT.solve(cB);
//			double vetorLambda[][] = lambda.getArray();
//
//			double vetorCNHat[][] = getVetorCHat(costFunction, constraintFunctions, vetorN, vetorLambda);
//			
//			if (showCalc) {
//				strReturn += "Vetor cB: \r\n";
//				strReturn += printMatriz(vetorCB) + "\r\n";
//				
//				strReturn += "Vetor lambda: \r\n";
//				strReturn += printMatriz(vetorLambda) + "\r\n";
//				
//				strReturn += "Vetor cNChapeu: \r\n";
//				strReturn += printMatriz(vetorCNHat) + "\r\n";
//			}
//			
//			if (isOptimal(vetorCNHat)) {
//				if (showCalc) {
//					strReturn += "\r\n\tSolução ótima encontrada\r\n";
//				}
//				
//				break;
//			}
//			
//			List<Integer> indexPositionEntryBase = new ArrayList<Integer>();
//			int indexEntryBase = vetorEntryBase(vetorN, vetorCNHat, indexPositionEntryBase);
//			
//			double vetorY[][] = getVetorY(matrizB, getCoefficientsByPosition(indexEntryBase, constraintFunctions));
//			
//			if (showCalc) {
//				strReturn += "Indice a entrar na base: " + indexEntryBase + "\r\n";
//				
//				strReturn += "Vetor direcao simplex y: \r\n";
//				strReturn += printMatriz(vetorY) + "\r\n";
//			}
//			
//			if (isInfiniteSolution(vetorY)) {
//				strReturn += "\r\nNão foi possível encontrar uma solução. Infinitas soluções.\r\n";
//				
//				break;
//			}
//			
//			List<Integer> indexPositionGetOutBase = new ArrayList<Integer>();
//			int indexGetOutBase = vetorGetOutBase(vetorB, matrizB, vetorXBHat, vetorY, indexPositionGetOutBase);
//			
//			
//			updateBN(vetorN, vetorB, indexPositionEntryBase.get(0), indexPositionGetOutBase.get(0));
//			
//			
//			if (showCalc) {
//				strReturn += "Indice a sair na base: " + indexGetOutBase + "\r\n";
//				
//				strReturn += "Vetor B: \r\n";
//				strReturn += "\t" + vetorB + "\r\n";
//				
//				strReturn += "Vetor N: \r\n";
//				strReturn += "\t" + vetorN + "\r\n";
//			}
//			
//			
//			qtdIterMax--;
//		}
//		
//		if (qtdIterMax == 0) {
//			strReturn += "\r\n\r\n Fim da execução. Não foi encontrada solução ótima com essa quantidade de interações.\r\n";
//		}
//		
//		strReturn += "</html>";
//		return strReturn;
//	}
	
	public String simplex(CostFunction costFunction, List<ConstraintFunction> constraintFunctions, int qtdIterMax, boolean showCalc) {
		this.showCalc = showCalc;
		String strReturn = "<html>";
		
		convertToNormalForm(costFunction, constraintFunctions);
		
		if (showCalc) {
			strReturn += "<h2>Função na forma normal:</h2>" + lineBreak + lineBreak;
			
			strReturn += tab + costFunction.toString() + lineBreak;
			System.out.println(costFunction.toString());
			for (ConstraintFunction c : constraintFunctions) {
				strReturn += tab + c.toString() + lineBreak;
				System.out.println(c.toString());
			}
			
			strReturn += lineBreak;
		}

		List<Integer> vetorB = getVetorB(costFunction, constraintFunctions);
		List<Integer> vetorN = getVetorN(costFunction, constraintFunctions);
		
		int counterIter = 1;
		while (qtdIterMax < 0 || qtdIterMax > 0) {
			strReturn += line;
			strReturn += tab + tab + tab + "<h2>Iteração " + (counterIter++) + "</h2>" + lineBreak;
			
			if (showCalc) {
				strReturn += "<h3>Vetor de indices B:</h3>";
				strReturn += tab + vetorB  + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor de indices N:</h3>";
				strReturn += tab + vetorN + lineBreak + lineBreak;
			}
			
			double matrizB[][] = getMatrizX(vetorB, constraintFunctions);
			double matrizN[][] = getMatrizX(vetorN, constraintFunctions);
			double vetorb[][] = getVetorb(constraintFunctions);
			
			double matrizBT[][] = transpose(matrizB);
			
			if (showCalc) {
				strReturn += "<h3>Matriz B:</h3>";
				strReturn += printMatriz(matrizB) + lineBreak + lineBreak;
				
				strReturn += "<h3>Matriz N:</h3>";
				strReturn += printMatriz(matrizN) + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor b:</h3>" + lineBreak;
				strReturn += printMatriz(vetorb) + lineBreak + lineBreak;
			}
		
			Matrix B = new Matrix(matrizB);
			Matrix BInverse = B.inverse();
			
			double vetorXBHat[][] = multiplication(BInverse.getArray(), vetorb);
			
			if (showCalc) {
				strReturn += "<h3>vetor XB chapéu:</h3>";
				strReturn += printMatriz(vetorXBHat) + lineBreak + lineBreak;
				
				strReturn += "<h3>Matriz BT:</h3>";
				strReturn += printMatriz(matrizBT) + lineBreak + lineBreak;
			}
			
			double vetorCB[][] = getVetorC(costFunction, vetorB);
			
			Matrix cB = new Matrix(vetorCB);
			Matrix BT = new Matrix(transpose(matrizB));
			
			Matrix lambda = BT.solve(cB);
			double vetorLambda[][] = lambda.getArray();

			double vetorCNHat[][] = getVetorCHat(costFunction, constraintFunctions, vetorN, vetorLambda);
			
			if (showCalc) {
				strReturn += "<h3>Vetor cB:</h3>";
				strReturn += printMatriz(vetorCB) + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor lambda:</h3>";
				strReturn += printMatriz(vetorLambda) + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor cNChapeu:</h3>";
				strReturn += printMatriz(vetorCNHat) + lineBreak + lineBreak;
			}
			
			if (isOptimal(vetorCNHat)) {
				if (showCalc) {
					strReturn += line;
					strReturn += lineBreak + lineBreak + tab + tab + tab + "<h2>Solução ótima encontrada</h2>"+ lineBreak;
					printMatriz(vetorXBHat);
					strReturn += line;
				}
				
				break;
			}
			
			List<Integer> indexPositionEntryBase = new ArrayList<Integer>();
			int indexEntryBase = vetorEntryBase(vetorN, vetorCNHat, indexPositionEntryBase);
			
			double vetorY[][] = getVetorY(matrizB, getCoefficientsByPosition(indexEntryBase, constraintFunctions));
			
			if (showCalc) {
				strReturn += "<h3>Indice a entrar na base:</h3>" + indexEntryBase + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor direcao simplex y:</h3>";
				strReturn += printMatriz(vetorY) + lineBreak + lineBreak;
			}
			
			if (isInfiniteSolution(vetorY)) {
				strReturn += lineBreak + "<h2>Não foi possível encontrar uma solução. Infinitas soluções.</h2>" + lineBreak;
				
				break;
			}
			
			List<Integer> indexPositionGetOutBase = new ArrayList<Integer>();
			int indexGetOutBase = vetorGetOutBase(vetorB, matrizB, vetorXBHat, vetorY, indexPositionGetOutBase);
			
			
			updateBN(vetorN, vetorB, indexPositionEntryBase.get(0), indexPositionGetOutBase.get(0));
			
			
			if (showCalc) {
				strReturn += "<h3>Indice a sair na base:</h3>" + indexGetOutBase + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor B:</h3>";
				strReturn += tab + vetorB + lineBreak + lineBreak;
				
				strReturn += "<h3>Vetor N:</h3>";
				strReturn += tab + vetorN + lineBreak + lineBreak;
			}
			
			
			qtdIterMax--;
		}
		
		if (qtdIterMax == 0) {
			strReturn += lineBreak + lineBreak + "<h2>Fim da execução. Não foi encontrada solução ótima com essa quantidade de interações.</h2>" + lineBreak;
		}
		
		strReturn += "</html>";
		return strReturn;
	}
	
	public boolean isInfiniteSolution(double vetorY[][]) {
		for (int i = 0; i < vetorY.length; i++) {
			if (vetorY[i][0] >= 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public void convertToNormalForm(CostFunction costFunction, List<ConstraintFunction> constraintFunctions) {
		int countNewVariables = costFunction.getVariables().size();
		
		for (ConstraintFunction c : constraintFunctions) {
			if (c.getType() == ConstraintFunction.EQUAL) {
				c.addVariable(1);
				addVariablesArtificialBy(c, costFunction, constraintFunctions);
			} else if (c.getType() == ConstraintFunction.LESS_EQUAL) {
				c.addVariable(1);
				addVariablesZeroBy(c, costFunction, constraintFunctions);
				c.setType(ConstraintFunction.EQUAL);
			}
		}
	}
	
	private void addVariablesArtificialBy(ConstraintFunction constraint, CostFunction costFunction, List<ConstraintFunction> constraintFunctions) {
		costFunction.addVariableArticial();
		
		for (ConstraintFunction c : constraintFunctions) {
			if (!c.equals(constraint)) {
				c.addVariableZero();
			}
		}
	}
	
	private void addVariablesZeroBy(ConstraintFunction constraint, CostFunction costFunction, List<ConstraintFunction> constraintFunctions) {
		costFunction.addVariableZero();
		
		for (ConstraintFunction c : constraintFunctions) {
			if (!c.equals(constraint)) {
				c.addVariableZero();
			}
		}
	}
	
	private List<Integer> getVetorB(CostFunction costFunction, List<ConstraintFunction> constraintFunctions) {
		int countVariablesDecision = costFunction.getCountVariablesDecision();
		
//		double matrizB[][] = new double[constraintFunctions.size()][costFunction.getVariables().size() - countVariablesDecision];
		List<Integer> vetorB = new ArrayList<Integer>();
		
		for (int i = 0; i < costFunction.getVariables().size(); i++) {
			if (i >= countVariablesDecision) {
				vetorB.add(i);
			}
		}
//		for (int i = 0; i < constraintFunctions.size(); i++) {
//			ConstraintFunction c = constraintFunctions.get(i);
//			
//			for (int j = 0; j < c.getVariables().size(); j++) {
//				if (j >= countVariablesDecision) {
//					matrizB[i][j - countVariablesDecision] = c.getVariables().get(j).getValue();
//				}
//			}
//		}
		return vetorB;
	}
	
	private List<Integer> getVetorN(CostFunction costFunction, List<ConstraintFunction> constraintFunctions) {
		int countVariablesDecision = costFunction.getCountVariablesDecision();
		
		List<Integer> vetorN = new ArrayList<Integer>();
		
		for (int i = 0; i < costFunction.getVariables().size(); i++) {
			if (i < countVariablesDecision) {
				vetorN.add(i);
			}
		}
//		double matrizN[][] = new double[constraintFunctions.size()][countVariablesDecision];
		
//		for (int i = 0; i < constraintFunctions.size(); i++) {
//			ConstraintFunction c = constraintFunctions.get(i);
//			
//			for (int j = 0; j < c.getVariables().size(); j++) {
//				if (j < countVariablesDecision) {
//					matrizN[i][j] = c.getVariables().get(j).getValue();
//				} else {
//					break;
//				}
//			}
//		}
		
		return vetorN;
	}
	
	private double[][] getMatrizX(List<Integer> index, List<ConstraintFunction> constraintFunctions) {
		
		double matrizN[][] = new double[constraintFunctions.size()][index.size()];
		
		for (int i = 0; i < constraintFunctions.size(); i++) {
			ConstraintFunction c = constraintFunctions.get(i);
			
			for (int j = 0; j < index.size(); j++) {
				matrizN[i][j] = c.getVariableByPosition(index.get(j)).getValue();
			}
		}
		
		return matrizN;
	}
	
//	private double[][] getMatrizXN(CostFunction costFunction, List<ConstraintFunction> constraintFunctions) {
//		int countVariablesDecision = costFunction.getCountVariablesDecision();
//		
//		double matrizN[][] = new double[constraintFunctions.size()][countVariablesDecision];
//		
//		for (int i = 0; i < constraintFunctions.size(); i++) {
//			ConstraintFunction c = constraintFunctions.get(i);
//			
//			for (int j = 0; j < c.getVariables().size(); j++) {
//				if (j < countVariablesDecision) {
//					matrizN[i][j] = c.getVariables().get(j).getValue();
//				} else {
//					break;
//				}
//			}
//		}
//		
//		return matrizN;
//	}
//	
//	private double[][] getVetorb(List<ConstraintFunction> constraintFunctions) {
//		double vetorB[][] = new double[constraintFunctions.size()][1];
//		
//		for (int i = 0; i < constraintFunctions.size(); i++) {
//			ConstraintFunction c = constraintFunctions.get(i);
//			
//			vetorB[i][0] = c.getB();
//		}
//		
//		return vetorB;
//	}
	
	private double[][] getVetorb(List<ConstraintFunction> constraintFunctions) {
		double vetorB[][] = new double[constraintFunctions.size()][1];
		
		for (int i = 0; i < constraintFunctions.size(); i++) {
			ConstraintFunction c = constraintFunctions.get(i);
			
			vetorB[i][0] = c.getB();
		}
		
		return vetorB;
	}
	
	private String printMatriz(double matriz[][]) {
		String ret = "";
		
		for (int i = 0; i < matriz.length; i++) {
			ret += "|";
			for (int j = 0; j < matriz[0].length; j++) {
				ret += matriz[i][j] + " ";
			}
			ret = ret.substring(0, ret.length() - 1);
			ret += "|" + lineBreak;
		}
		
		return ret;
	}
	
	private double[][] transpose(double matriz[][]) {
		double[][] matrizReturn = new double[matriz[0].length][matriz.length];
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matrizReturn[j][i] = matriz[i][j];
			}
		}
		
		return matrizReturn;
	}
	
	private double[][] getVetorC(CostFunction costFunction, List<Integer> indexes) {
		double vetorCB[][] = new double[indexes.size()][1];
		
		for (int i = 0; i < indexes.size(); i++) {
			Integer index = indexes.get(i);
			vetorCB[i][0] = costFunction.getVariableByPosition(index).getValue();
		}
		
		return vetorCB;
	}
	
	private double[][] getCoefficientsByPosition(int position, List<ConstraintFunction> constraintFunctions) {
		double coefficients[][] = new double[constraintFunctions.size()][1];
		
		for (int i = 0; i < constraintFunctions.size(); i++) {
			ConstraintFunction c = constraintFunctions.get(i);
			coefficients[i][0] = c.getVariableByPosition(position).getValue();
		}
		
		return coefficients;
	}
	
	private double[][] multiplication(double[][] matrizA, double[][] matrizB) {
		double ret[][] = new double[matrizA.length][matrizB[0].length];
		
		int i, j , v;

		   for (i = 0 ; i < matrizA.length; i++ )
		      for (j = 0; j < matrizB[0].length; j++)
		         for (v = 0; v < matrizA[0].length; v++)
		            ret[i][j] = ret[i][j] + matrizA[i][v] * matrizB[v][j];
		   
		   return ret;
		
	}
	
	private double[][] getVetorCHat(CostFunction c, List<ConstraintFunction> constraintFunctions, List<Integer> indexes, double[][] lambda) {
		double[][] cHat = new double[indexes.size()][1];
		
		double[][] lambdaT = transpose(lambda);
		
		for (int i = 0; i < indexes.size(); i++) {
			Integer index = indexes.get(i);
			
			double mult[][] = multiplication(lambdaT, getCoefficientsByPosition(index, constraintFunctions));
			
			
			cHat[i][0] = c.getVariableByPosition(index).getValue() - mult[0][0];
		}
		
		return cHat;
	}
	
	private boolean isOptimal(double[][] vetorCNHat) {
		for (int i = 0; i < vetorCNHat.length; i++) {
			for (int j = 0; j < vetorCNHat[0].length; j++) {
				if (vetorCNHat[i][j] < 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private double[][] getVetorY(double[][]matrizB, double[][] vetorANk) {
		Matrix B = new Matrix(matrizB);
		
		return multiplication(B.inverse().getArray(), vetorANk);
	}
	
	private int vetorGetOutBase(List<Integer> vetorB, double[][] matrizB, double vetorXBHat[][], double[][] vetorY, List<Integer> indexEntryBase) {
		double valueLess = 127398217;
		int indexLess = 0;
		
		for (int i = 0; i < vetorXBHat.length; i++) {
			if (vetorY[i][0] > 0) {
				double value = vetorXBHat[i][0] / vetorY[i][0];
				if (value < valueLess) {
					valueLess = value;
					indexLess = i;
				}
			}
		}

		indexEntryBase.add(indexLess);
		
		return vetorB.get(indexLess);
	}
	
	private int vetorEntryBase(List<Integer> vetorN, double vetorCNHat[][], List<Integer> getOutPosition) {
		int indexLess = 0;
		double valueLess = 1982734;
		
		for (int i = 0; i < vetorCNHat.length; i++) {
			if (vetorCNHat[i][0] < valueLess) {
				valueLess = vetorCNHat[i][0];
				indexLess = i;
			}
		}
		
		getOutPosition.add(indexLess);
		int indexEntryBase = vetorN.get(indexLess);
		
//		return indexEntryBase;
		return vetorN.get(indexLess);
	}
	
	private void updateBN(List<Integer> vetorN, List<Integer> vetorB, int indexEntryBase, int indexGetOutBase) {
		List<Integer> vetorNTemp = new ArrayList<Integer>();
		List<Integer> vetorBTemp = new ArrayList<Integer>();
		
		System.out.println("updateBN - vetorB: " + vetorB + "\r\nvetorN: " + vetorN);
		
		System.out.println("updateBN - indexEntryBase: " + indexEntryBase + " | indexGetOutBase: " + indexGetOutBase);
		
		
		for (int i = 0; i < vetorN.size(); i++) {
			if (i != indexEntryBase) {
				vetorNTemp.add(vetorN.get(i));
			} else {
				//se tiver valores iguais em colunas diferentes vai dar merda
//				vetorNTemp.add(vetorB.get(vetorB.indexOf(indexGetOutBase)));
				vetorNTemp.add(vetorB.get(indexGetOutBase));
			}
		}
		
		for (int i = 0; i < vetorB.size(); i++) {
			if (i != indexGetOutBase) {
				vetorBTemp.add(vetorB.get(i));
			} else {
				//se tiver valores iguais em colunas diferentes vai dar merda
//				vetorBTemp.add(vetorN.get(vetorN.indexOf(indexEntryBase)));
				vetorBTemp.add(vetorN.get(indexEntryBase));
			}
		}
		
		vetorB.removeAll(vetorB);
		vetorB.addAll(vetorBTemp);
		
		vetorN.removeAll(vetorN);
		vetorN.addAll(vetorNTemp);
	}
	
//	private void updateBN(List<Integer> vetorB, List<Integer> vetorN, double vetorXB[][], double vetorXN[][], double[] vetorCNHat) {
//		int indexLess = 0;
//		double valueLess = 1982734;
//		
//		for (int i = 0; i < vetorCNHat.length; i++) {
//			if (vetorCNHat[i] < valueLess) {
//				valueLess = vetorCNHat[i];
//				indexLess = i;
//			}
//		}
//		
//		int indexEntryBase = vetorN.get(indexLess);
//		
//		double vetorY[][] = getVetorY(matrizB, getCoefficientsByPosition(indexEntryBase, constraintFunctions));
//	}
}
