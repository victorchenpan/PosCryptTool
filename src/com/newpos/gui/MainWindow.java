package com.newpos.gui;

import java.util.*;
import com.newpos.crypto.selftest.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;

import com.newpos.crypto.Crypto;
import com.newpos.crypto.aes.*;
import com.newpos.crypto.format.*;

public final class MainWindow extends JFrame {
	// Panels
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel panel6;

	// Labels
	private JLabel lCryptoSelect;
	private JLabel lEncryptType;
	private JLabel lKey;
	private JLabel lDataIn;
	private JLabel lDataOut;
	private JLabel lStatus;
	private JLabel lKeyLength;
	private JLabel lDataInLength;
	private JLabel lDataOutLength;
	
	// ComboBoxs
	private JComboBox cCryptoSelect;
	private JComboBox cEncryptType;

	// TextFields
	private JTextField tKey;
	private JTextField tDataIn;
	private JTextField tDataOut;

	// TextArea
	private JTextArea statusDetails;

	// Button
	private JButton bStartCrypt;

	private void createSelectGroup() {
	
		lCryptoSelect = new JLabel("Crypto Select:");//AES-ECB, AES-CBC
		cCryptoSelect = new JComboBox();

		lCryptoSelect.setBounds(2, 0, 80, 30);
		cCryptoSelect.setBounds(92, 0, 80, 30);
		
		cCryptoSelect.addItem("AES-ECB");
		cCryptoSelect.addItem("AES-CBC");
		
		panel1.add(lCryptoSelect);
		panel1.add(cCryptoSelect);
	}

	private void createTypeGroup() {
	
		lEncryptType = new JLabel("Crypto Type:");//Encrypt, Decrypt
		cEncryptType = new JComboBox();
		
		lEncryptType.setBounds(182, 0, 80, 30);
		cEncryptType.setBounds(272, 0, 80, 30);
		
		cEncryptType.addItem("Encrypt");
		cEncryptType.addItem("Decrypt");
		
		panel1.add(lEncryptType);
		panel1.add(cEncryptType);
	}

	private void createKeyGroup() {
 		lKey =  new JLabel("KEY:");
 		lKey.setBounds(2, 0, 60, 30);
		panel2.add(lKey);

		tKey = new JTextField(16);
		tKey.setBounds(72, 5, 400, 25);
		panel2.add(tKey);

 		lKeyLength = new JLabel("Length:");
 		lKeyLength.setBounds(482, 0, 80, 30);
		panel2.add(lKeyLength);

	
	}

	private void createDataInGroup() {
		lDataIn = new JLabel("DataIn:");
		lDataIn.setBounds(2, 0, 60, 30);
		panel3.add(lDataIn);

		tDataIn = new JTextField(16);
		tDataIn.setBounds(72, 5, 400, 25);
		panel3.add(tDataIn);

 		lDataInLength = new JLabel("Length:");
 		lDataInLength.setBounds(482, 0, 80, 30);
		panel3.add(lDataInLength);
		
	}

	private void createDataOutGroup() {
		lDataOut = new JLabel("DataOut:");
		lDataOut.setBounds(2, 0, 60, 30);
		panel4.add(lDataOut);

		tDataOut = new JTextField(16);
		tDataOut.setBounds(72, 5, 400, 25);
		panel4.add(tDataOut);

 		lDataOutLength = new JLabel("Length:");
 		lDataOutLength.setBounds(482, 0, 80, 30);
		panel4.add(lDataOutLength);
	}

	private void createStatusGroup() {
		lStatus = new JLabel("Status:");
		lStatus.setBounds(2, 0, 60, 30);
		panel5.add(lStatus);

		statusDetails = new JTextArea();
		statusDetails.setBounds(72, 0, 400, 30);
		statusDetails.setEditable(false);
		panel5.add(statusDetails);
	}
	
	private void createStartGroup() {
		bStartCrypt = new JButton("start");
		bStartCrypt.setBounds(500, 2, 65, 30);
		panel6.add(bStartCrypt);

	}

	private void createPanels() {
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new GridLayout(6, 1, 5, 5));

		panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel1.setBackground(new Color(0xfffacd));
		panel1.setLayout(null);

		panel2 = new JPanel();
		panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel2.setBackground(new Color(0xe0ffff));
		panel2.setLayout(null);

		panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel3.setBackground(new Color(0xfffacd));
		panel3.setLayout(null);

		panel4 = new JPanel();
		panel4.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel4.setBackground(new Color(0xe0ffff));
		panel4.setLayout(null);

		panel5 = new JPanel();
		panel5.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel5.setBackground(new Color(0xfffacd));
		panel5.setLayout(null);

		panel6 = new JPanel();
		//panel6.setBorder(BorderFactory.createLoweredBevelBorder());
		panel6.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel6.setBackground(new Color(0xe0ffff));
		panel6.setLayout(null);

	
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.add(panel5);
		mainPanel.add(panel6);

		this.setContentPane(mainPanel);
	}

	private void initActionHandler() {
		ActionListener startHandler = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			//获取数据参数
			String modeCrypt = (String)cCryptoSelect.getSelectedItem();
			String modeDecrypt = (String)cEncryptType.getSelectedItem();
			String key = tKey.getText();
			String dataIn = tDataIn.getText();
			String account = "66666666666666666666666666666666";
			
			System.out.println(modeCrypt);
			System.out.println(modeDecrypt);
			System.out.println(dataIn);
			//加解密操作
			statusDetails.setText(Crypto.doDencrypt(modeCrypt, modeDecrypt, key, dataIn, account));
			
			}
		};
		
		bStartCrypt.addActionListener(startHandler);
	}
	
	MainWindow() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("PED TEST TOOL");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createPanels();

		createSelectGroup();
		createTypeGroup();
		createKeyGroup();
		createDataInGroup();
		createDataOutGroup();
		createStatusGroup();
		createStartGroup();
		
		initActionHandler();
		
		this.pack();
		this.setVisible(true);
		this.setSize(600,280);
	}

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow mainObj = new MainWindow();
			}
		});
		
		//AesPinBlock.selfTest();
	}
}
