package uzytkownik;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


/* Klasa tworzy kreator modyfikacji ustawien uzytkownika, uruchamiany przy pierwszym odpaleniu programu */
public class KreatorUsera extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private	String imie;
	private String nazwisko;
	private int wiek;
	private boolean plec;
	private JTextField textField;
	private JLabel lblInfo;

	private int iloscWczytanych=1;	//okresla ile elementów zostało już wczytanych i jaki element trzeba teraz wczytać
	private JLabel lblImie;
	private JComboBox comboBox;
	/**
	 * Create the dialog.
	 */
	public KreatorUsera() {

		setAutoRequestFocus(false);
		setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 639, 422);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(102, 153, 153));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblPamitaczbukw = new JLabel("PamiętaczBuków");
		lblPamitaczbukw.setFont(new Font("Nimbus Mono L", Font.BOLD, 18));
		lblPamitaczbukw.setForeground(new Color(255, 0, 0));
		lblPamitaczbukw.setBounds(12, 12, 229, 31);
		contentPanel.add(lblPamitaczbukw);
		
		JLabel lblZdradCoO = new JLabel("Zdradź coś o sobie :)");
		lblZdradCoO.setFont(new Font("Dialog", Font.BOLD, 20));
		lblZdradCoO.setForeground(new Color(255, 255, 153));
		lblZdradCoO.setBounds(196, 57, 370, 24);
		contentPanel.add(lblZdradCoO);
		
		lblImie = new JLabel("Imię");
		lblImie.setForeground(new Color(255, 204, 0));
		lblImie.setFont(new Font("Dialog", Font.BOLD, 20));
		lblImie.setBounds(58, 175, 183, 31);
		contentPanel.add(lblImie);
		
		textField = new JTextField();
		textField.setBounds(289, 181, 241, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnZatwierd = new JButton("Zatwierdź");
		btnZatwierd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				zczytajDane();
				textField.setText("");
				zmienInfo();

			}
		});
		btnZatwierd.setBounds(283, 237, 117, 25);
		contentPanel.add(btnZatwierd);
		
		lblInfo = new JLabel("Info");
		lblInfo.setBounds(117, 116, 413, 15);
		contentPanel.add(lblInfo);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"mężczyzna", "kobieta"}));
		comboBox.setEditable(true);
		comboBox.setBounds(289, 180, 152, 26);
		contentPanel.add(comboBox);
		KreatorUsera.this.comboBox.setVisible(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					KreatorUsera.this.setVisible(false);
					KreatorUsera.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	/* Metoda wczytuje dane z lineEdit'a wczytujac dane do poszczególnych pól imie,nazwisko,wiek,plec */
	private void zczytajDane(){
		if(textField.getText().equals(""))
			KreatorUsera.this.lblInfo.setText("Pole jest puste!");
		else{
			if(iloscWczytanych==1){
				imie=textField.getText();
				iloscWczytanych++;
			}
			else if(iloscWczytanych==2){
				nazwisko=textField.getText();
				iloscWczytanych++;
			}
			else if(iloscWczytanych==3){
				wiek=Integer.parseInt(textField.getText());
				iloscWczytanych++;
			}
			else if(iloscWczytanych==4){
//				plec=weryfikujComboBox((String) comboBox.getSelectedItem());
				iloscWczytanych++;
			}
		}
	}
	/* Metoda zmienia pola w oknie informujace jakie dane są teraz wczytywane'
	 * 
	 */
	private void zmienInfo(){
		if(iloscWczytanych==1){
			lblImie.setText("Imie");
//			textField.setText("");
			}
		else if(iloscWczytanych==2){
			lblImie.setText("Nazwisko");
//			textField.setText("");
			}
		else if(iloscWczytanych==3){
			lblImie.setText("Wiek");
//			textField.setText("");
		}
		else if(iloscWczytanych==4){
			lblImie.setText("Plec");
			textField.setVisible(false);
			comboBox.setVisible(true);
			//			textField.setText("");
		}

	}
	private boolean weryfikujComboBox(String nazwa){
		if(nazwa.equals("mężczyzna"))
			return true;
		else if(nazwa.equals("kobieta"))
			return false;
	return true;
	}
}
