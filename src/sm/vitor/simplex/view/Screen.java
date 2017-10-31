package sm.vitor.simplex.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Gerenciamento.BoundsTela;
import sm.vitor.simplex.util.Parameters;
import sm.vitor.simplex.view.components.EntryFunctionView;

public class Screen extends JFrame {
	private JPanel contentPanel;
	private JScrollPane contentBar;
	
	private JPanel panelInput;
	private JPanel panelConstraint;
	private int counterConstraint;
	
	private JButton btAddConstraint;
	private JButton btDelConstraint;
	private JCheckBox checkShowMemoryCalc;
	private JTextField tQtdMaxIterations;
	private JButton btRunSimplex;
	
//	private JTextArea textResponse;
	private JEditorPane textResponse;
	
	public Screen() {
		super();
		BoundsTela bounds = new BoundsTela(1024, 700);
		
		contentPanel = new JPanel(new GridBagLayout());
		contentBar = new JScrollPane(contentPanel);
		
		this.setBounds(bounds.getComecaX(), bounds.getComecaY(), bounds.getTamTelaX(), bounds.getTamTelaY());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setContentPane(contentPanel);
		this.setContentPane(contentBar);
		
		panelInput = new JPanel(new GridBagLayout());
		
		GridBagConstraints gInput = new GridBagConstraints();
		gInput.gridx = 0;
		gInput.gridy = 1;
		contentPanel.add(panelInput, gInput);
		
		panelConstraint = new JPanel(new GridBagLayout());
		
		JLabel lTitleConstraint = new JLabel("Restrições");
		lTitleConstraint.setFont(Parameters.fontH2);
		JPanel pTitleConstraint = new JPanel();
		pTitleConstraint.add(lTitleConstraint);
		
		GridBagConstraints gTitleConstraint = new GridBagConstraints();
		gTitleConstraint.gridx = 0;
		gTitleConstraint.gridy = 2;
		panelConstraint.add(pTitleConstraint, gTitleConstraint);
		
		panelInput.add(pTitleConstraint, gTitleConstraint);
		
		GridBagConstraints gConstraint = new GridBagConstraints();
		gConstraint.gridx = 0;
		gConstraint.gridy = 4;
		panelInput.add(panelConstraint, gConstraint);
		
		btAddConstraint = new JButton("Add restrição");
		btDelConstraint  = new JButton("Remover restrição");
		
		JPanel pBtsConstraint = new JPanel();
		pBtsConstraint.add(btAddConstraint);
		pBtsConstraint.add(btDelConstraint);
		
		GridBagConstraints gBtsConstraint = new GridBagConstraints();
		gBtsConstraint.gridx = 0;
		gBtsConstraint.gridy = 3;
		panelInput.add(pBtsConstraint, gBtsConstraint);
		
		JPanel pConfig = new JPanel();
		
		checkShowMemoryCalc = new JCheckBox("Exibir memória dos cálculos");
		checkShowMemoryCalc.setFont(Parameters.fontH3);
		pConfig.add(checkShowMemoryCalc);
		
		tQtdMaxIterations = new JTextField(Parameters.sizeColumEntryNumber);
		tQtdMaxIterations.setFont(Parameters.fontH3);
		
		pConfig.add(tQtdMaxIterations);
		
		JLabel lIterations = new JLabel(" iterações no máximo");
		lIterations.setFont(Parameters.fontH3);
		pConfig.add(lIterations);
		
		GridBagConstraints gConfig = new GridBagConstraints();
		gConfig.gridx = 0;
		gConfig.gridy = 5;
		panelInput.add(pConfig, gConfig);
		
		btRunSimplex = new JButton("Calcular");
		
		GridBagConstraints gRunSimplex = new GridBagConstraints();
		gRunSimplex.gridx = 0;
		gRunSimplex.gridy = 6;
		
		panelInput.add(btRunSimplex, gRunSimplex);
		
		
//		textResponse = new JTextArea(Parameters.sizeResponseArea[0], Parameters.sizeResponseArea[1]);
		textResponse = new JEditorPane();
		textResponse.setContentType("text/html");
		
		textResponse.setEditable(false);
		JScrollPane barResponse = new JScrollPane(textResponse);
		
		GridBagConstraints gResponse = new GridBagConstraints();
		gResponse.gridx = 0;
		gResponse.gridy = 7;
		
		panelInput.add(barResponse, gResponse);
	}
	
	public JButton getBtAddConstraint() {
		return btAddConstraint;
	}

	public JButton getBtDelConstraint() {
		return btDelConstraint;
	}

	public JCheckBox getCheckShowMemoryCalc() {
		return checkShowMemoryCalc;
	}

	public JTextField gettQtdMaxIterations() {
		return tQtdMaxIterations;
	}

	public JButton getBtRunSimplex() {
		return btRunSimplex;
	}

//	public JTextArea getTextResponse() {
//		return textResponse;
//	}
	
	public JEditorPane getTextResponse() {
		return textResponse;
	}

	public JPanel getMainPanel() {
		return this.contentPanel;
	}
	public void addCostFunction(EntryFunctionView entryFunction) {
		JPanel pCost = new JPanel(new BorderLayout());
		
		JLabel lTitle = new JLabel("Função de custo");
		lTitle.setFont(Parameters.fontH2);
		
		JPanel pTitle = new JPanel();
		pTitle.add(lTitle);
		
		pCost.add(pTitle, BorderLayout.NORTH);
		pCost.add(entryFunction);
		
		
//		panelInput.add(pCost, BorderLayout.NORTH);
		
		GridBagConstraints gCostFunction = new GridBagConstraints();
		gCostFunction.gridx = 0;
		gCostFunction.gridy = 1;
		panelInput.add(pCost, gCostFunction);
		
		panelInput.updateUI();
	}
	
	public void addConstraint(EntryFunctionView entryFunction) {
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = 0;
		g.gridy = counterConstraint++;
		
		panelConstraint.add(entryFunction, g);
		
		panelConstraint.updateUI();
	}
	
	public void delConstraint() {
		if (counterConstraint > 0) {
			panelConstraint.remove(panelConstraint.getComponentCount() - 1);
			counterConstraint--;
			
			panelConstraint.updateUI();
		}
	}
	
}
