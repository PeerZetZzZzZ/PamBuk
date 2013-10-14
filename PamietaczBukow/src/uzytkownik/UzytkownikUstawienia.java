package uzytkownik;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class UzytkownikUstawienia extends Uzytkownik{

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldImie;
	private JTextField textField_1Nazwisko;
	private JSpinner spinnerWiek;
	private JComboBox comboBoxPlec;

	/**
	 * Create the dialog.
	 */
	public UzytkownikUstawienia() {
		setTitle("Ustawienia Użytkownika");
		setBackground(new Color(128, 128, 128));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 153, 102));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblUstawieniaUytkownika = new JLabel("Ustawienia użytkownika");
		lblUstawieniaUytkownika.setForeground(new Color(0, 0, 0));
		lblUstawieniaUytkownika.setBounds(12, 12, 212, 15);
		contentPanel.add(lblUstawieniaUytkownika);
		
		JLabel lblImi = new JLabel("Imię");
		lblImi.setForeground(new Color(0, 0, 0));
		lblImi.setBounds(12, 39, 70, 15);
		contentPanel.add(lblImi);
		
		textFieldImie = new JTextField();
		textFieldImie.setForeground(new Color(0, 0, 0));
		textFieldImie.setBounds(99, 39, 235, 19);
		contentPanel.add(textFieldImie);
		textFieldImie.setColumns(10);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setForeground(new Color(0, 0, 0));
		lblNazwisko.setBounds(12, 71, 84, 15);
		contentPanel.add(lblNazwisko);
		
		textField_1Nazwisko = new JTextField();
		textField_1Nazwisko.setForeground(new Color(0, 0, 0));
		textField_1Nazwisko.setBounds(99, 69, 235, 19);
		contentPanel.add(textField_1Nazwisko);
		textField_1Nazwisko.setColumns(10);
		
		JLabel lblWiek = new JLabel("Wiek");
		lblWiek.setForeground(new Color(0, 0, 0));
		lblWiek.setBounds(12, 102, 70, 15);
		contentPanel.add(lblWiek);
		
		spinnerWiek = new JSpinner();
		spinnerWiek.setForeground(new Color(0, 0, 0));
		spinnerWiek.setBounds(99, 100, 46, 20);
		contentPanel.add(spinnerWiek);
		
		JLabel lblPe = new JLabel("Płeć");
		lblPe.setForeground(new Color(0, 0, 0));
		lblPe.setBounds(12, 140, 70, 15);
		contentPanel.add(lblPe);
		
		comboBoxPlec = new JComboBox();
		comboBoxPlec.setForeground(new Color(0, 0, 0));
		comboBoxPlec.setModel(new DefaultComboBoxModel(new String[] {"kobieta", "mężczyzna"}));
		comboBoxPlec.setBounds(99, 135, 90, 24);
		contentPanel.add(comboBoxPlec);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(105, 105, 105));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zapisz");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cofnij");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		ladujUsera();
	}
	/* Metoda ustawia pola okna odpowiednimi wartościami z INSTANCJi uzytownika z klasy ProfilUzytkownika*/
	private void ladujUsera(){
		textFieldImie.setText(ProfilUzytkownika.INSTANCJA.imie);
		textField_1Nazwisko.setText(ProfilUzytkownika.INSTANCJA.nazwisko);
		spinnerWiek.setValue((int)ProfilUzytkownika.INSTANCJA.wiek);
		int plec;
		if(ProfilUzytkownika.INSTANCJA.plec==true)
			plec=1;
		else plec=0;
		comboBoxPlec.setSelectedIndex(plec);
	}
}
