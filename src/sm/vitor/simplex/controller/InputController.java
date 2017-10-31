package sm.vitor.simplex.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import sm.vitor.simplex.models.ConstraintFunction;
import sm.vitor.simplex.models.CostFunction;
import sm.vitor.simplex.view.Screen;
import sm.vitor.simplex.view.components.EntryFunctionView;

public class InputController {
	private Screen screen;
	
	private List<String> operators;
	private List<EntryFunctionView> constraintFuncs;
	private EntryFunctionView costFunction;
	
	public InputController() {
		screen = new Screen();
		screen.setVisible(true);
		
		initOperators();
		costFunction = new EntryFunctionView(null);
		constraintFuncs = new ArrayList<EntryFunctionView>();
		
		
		screen.addCostFunction(costFunction);
		
		costFunction.getBtAddVariable().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				costFunction.addVariable();
			}
		});
		
		costFunction.getBtDelVariable().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				costFunction.delVariable();
			}
		});
		
		
		screen.getBtAddConstraint().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addConstraint();
			}
		});
		
		screen.getBtDelConstraint().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delConstraint();
			}
		});
		
		screen.getBtRunSimplex().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					SimplexController simplex = new SimplexController();
					
					int qtdIterMax = -1;
					
					try {
						qtdIterMax = Integer.parseInt(screen.gettQtdMaxIterations().getText());
					} catch (NumberFormatException ex) {
					}
					
					CostFunction costF = new CostFunction();
					costF.setVariablesFloat(costFunction.getListCoefficients());
					
					List<ConstraintFunction> constraintFunctions = new ArrayList<ConstraintFunction>();
					
					for (EntryFunctionView eF : constraintFuncs) {
						ConstraintFunction cF = new ConstraintFunction();
						
//						cF.setCoefficients(eF.getListCoefficients());
						cF.setVariablesFloat(eF.getListCoefficients());
						cF.setB(eF.getB());
						cF.setType(eF.getType());
						
						constraintFunctions.add(cF);
					}
					
					String ret = simplex.simplex(costF, constraintFunctions, qtdIterMax, screen.getCheckShowMemoryCalc().isSelected());
					
					screen.getTextResponse().setText(ret);
					screen.getTextResponse().updateUI();
					screen.getMainPanel().updateUI();
					((JScrollPane)screen.getContentPane()).updateUI();
					
					
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}

			}
		});
		
	}
	
	
	private void addConstraint() {
		EntryFunctionView eF = new EntryFunctionView(operators);
		
		eF.getBtAddVariable().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eF.addVariable();
			}
		});
		
		eF.getBtDelVariable().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eF.delVariable();
			}
		});
		
		constraintFuncs.add(eF);
		screen.addConstraint(eF);
	}
	
	private void delConstraint() {
		if (constraintFuncs.size() > 0) {
			EntryFunctionView eF = constraintFuncs.get(constraintFuncs.size() - 1);
			screen.delConstraint();
			constraintFuncs.remove(eF);
		}
	}
	
	public void initOperators() {
		operators = new ArrayList<String>();
		operators.add("<=");
		operators.add(">=");
		operators.add("=");
		operators.add("<");
		operators.add(">");
	}
}
