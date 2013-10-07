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


/* Klasa tworzy kreator modyfikacji ustawien uzytkownika, uruchamiany przy pierwszym odpaleniu programu */
public class KreatorUsera extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

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
		
		JLabel lblImie = new JLabel("Imię");
		lblImie.setForeground(new Color(255, 204, 0));
		lblImie.setFont(new Font("Dialog", Font.BOLD, 20));
		lblImie.setBounds(148, 175, 93, 31);
		contentPanel.add(lblImie);
		
		textField = new JTextField();
		textField.setBounds(289, 181, 241, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
