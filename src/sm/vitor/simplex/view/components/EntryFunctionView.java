package sm.vitor.simplex.view.components;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sm.vitor.simplex.util.Parameters;

public class EntryFunctionView extends JPanel {
	private List<JTextField> textFields;
	private JButton btAddVariable;
	private JButton btDelVariable;
	
	private int columns;
	private Font fontLabel;
	private Font fontText;
	private List<String> operators;
	private int counterVariable;
	
//	private JLabel lPlus;
	private JLabel lFunction;
	
	private JPanel panelCoef;
	private JPanel panelB;
	private JTextField textB;
	private JComboBox<String> boxOperators;
	
	private List<JTextField> textVariables;
	
	
	public EntryFunctionView(List<String> operators) {
		columns = Parameters.sizeColumEntryNumber;
		fontLabel = Parameters.fontH3Bold;
		fontText = Parameters.fontH3;
		this.operators = operators;
		
		textVariables = new ArrayList<JTextField>();
		
		initBoxOperators();
		
		lFunction = new JLabel();
		lFunction.setFont(fontLabel);
		
		textB = new JTextField(columns);
		textB.setFont(fontText);
		
		btAddVariable = new JButton("+");
		btDelVariable = new JButton("-");
		
		panelCoef = new JPanel();
		
		this.add(lFunction);
		
		this.add(panelCoef);
		
		if (boxOperators != null) {
			this.add(boxOperators);
			this.add(textB);
		}
		
		this.add(btAddVariable);
		this.add(btDelVariable);
		
		addVariable();
		addVariable();
	}

	public List<JTextField> getTextFields() {
		return textFields;
	}

	public JButton getBtAddVariable() {
		return btAddVariable;
	}

	public JButton getBtDelVariable() {
		return btDelVariable;
	}
	
	private void updateFunction() {
		if (boxOperators == null) {
			String text = "";
			
			if (counterVariable > 0) {
				text = "f(x1";
				for (int i = 2; i <= counterVariable; i++) {
					text += ", x" + i;
				}
				text += ") = ";
			}
			lFunction.setText(text);
		}
	}
	
	private void initBoxOperators() {
		if (this.operators != null) {
			this.boxOperators = new JComboBox<String>();
			
			for (String str : this.operators) {
				boxOperators.addItem(str);
			}
		}
	}
	
	public void addVariable() {
		if (counterVariable > 0) {
//			this.add(lPlus);
			JLabel lPlus = new JLabel("+");
			lPlus.setFont(fontLabel);
			panelCoef.add(lPlus);
		}
		
		counterVariable++;
		
		JPanel panel = new JPanel();
		
		JTextField tV1 = new JTextField(columns);
		tV1.setFont(fontText);
		textVariables.add(tV1);
		
		JLabel lV1 = new JLabel("x" + counterVariable);
		lV1.setFont(fontLabel);
		
		panel.add(tV1);
		panel.add(lV1);
		
		panelCoef.add(panel);
		
		panelCoef.updateUI();
		
		updateFunction();
	}
	
	public void delVariable() {
		if (counterVariable > 1) {
			panelCoef.remove(panelCoef.getComponentCount() - 1);
			
			if (counterVariable > 1) {
				panelCoef.remove(panelCoef.getComponentCount() - 1);
			}
			
			textVariables.remove(textVariables.size() - 1);
			
			counterVariable--;
			
			panelCoef.updateUI();
			updateFunction();
		}
	}
	
	public String getType() {
		if (boxOperators == null) {
			return "";
		} else {
			return boxOperators.getSelectedItem().toString();
		}
	}
	
	public float getB() {
		float b = 0;
		
		try {
			b = Float.parseFloat(textB.getText().trim());
		} catch (NumberFormatException ex) {
		}
		
		return b;
	}
	
	public List<Float> getListCoefficients() {
		List<Float> coefficients = new ArrayList<Float>();
		
		for (JTextField t : textVariables) {
			float f = 0;
			
			try {
				f = Float.parseFloat(t.getText().trim());
			} catch (NumberFormatException ex) {
			}
			
			coefficients.add(f);
		}
		
		return coefficients;
	}
}
