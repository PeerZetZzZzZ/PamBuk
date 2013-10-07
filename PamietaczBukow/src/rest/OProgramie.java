package rest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class OProgramie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	/**
	 * Create the dialog.
	 */
	public OProgramie() {
		setResizable(false);
		setBackground(new Color(128, 128, 128));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 160, 122));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPamitaczBukwVer = new JLabel("Pamiętacz Buków ver.1.0.");
			lblPamitaczBukwVer.setFont(new Font("Dialog", Font.BOLD, 19));
			lblPamitaczBukwVer.setBounds(12, 12, 288, 15);
			contentPanel.add(lblPamitaczBukwVer);
		}
		{
			JLabel lblNewLabel = new JLabel("Autor: Przemysław Thomann");
			lblNewLabel.setBounds(12, 39, 254, 15);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblEmailPeerzetgmailcom = new JLabel("e-mail: przemyslaw.thomann@gmail.com");
		lblEmailPeerzetgmailcom.setBounds(12, 72, 303, 15);
		contentPanel.add(lblEmailPeerzetgmailcom);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(128, 128, 128));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zamknij");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						OProgramie.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
