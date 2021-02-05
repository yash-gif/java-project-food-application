import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.logging.Logger;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login_System extends JFrame  {
	JFrame frame;
	File f = new File("C:/Practice/yash");
	
	int ln;
	String Username,Password;
	JLabel lblUsername,lblPassword,lblLogin_System;
	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin,btnReset,btnRegister,btnTest;
	JSeparator s1,s2;
   	
	public Login_System() {}
	public Login_System(String s) { 
		
		super(s);
	}
	
	public void run() {
		
		
		lblLogin_System = new JLabel("Login System");
		
		
		lblUsername = new JLabel("Username");
		lblPassword = new JLabel("Password");
		txtUsername = new JTextField();
		txtPassword = new JPasswordField();
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				createFolder();
				readFile();
				countLines();
				if(logic(txtUsername.getText(),txtPassword.getText())) {
					
					Food_application second = new Food_application();
					Food_application.main(null);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect username/password");
				}
					
				
				
			}
		});
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtUsername.setText(null);
				txtPassword.setText(null);
				
			}
		});
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createFolder();
				readFile();
				countLines();
				addData(txtUsername.getText(),txtPassword.getText());
	    	}
	    
			});
		
	
		s1 = new JSeparator();
		s2 = new JSeparator();
		
		setLayout(null);
		lblLogin_System.setBounds(190, 16, 88, 16);
		lblUsername.setBounds(65, 69, 73, 36);
		txtUsername.setBounds(190, 75, 212, 30);
		lblPassword.setBounds(65, 148, 73, 16);
		txtPassword.setBounds(190, 145, 212, 30);
		btnLogin.setBounds(37, 204, 97, 25);
		btnReset.setBounds(188, 204, 97, 25);
		btnRegister.setBounds(330, 204, 97, 25);
		
		s1.setBounds(12, 183, 458, 8);
		s2.setBounds(12, 47, 458, 2);
		
		add(lblLogin_System);
		add(lblUsername);
		add(txtUsername);
		add(lblPassword);
		add(txtPassword);
		add(btnLogin);
		add(btnReset);
		add(btnRegister);
		add(s1);
		add(s2);
	}
	
	void createFolder() {
		if (!f.exists()) {
			f.mkdirs();
		}
	}
	
	void readFile() {
		try {
		FileReader fr = new FileReader(f+"\\logins.txt");
		
		}catch(FileNotFoundException ex) {
			try {
			FileWriter fw = new FileWriter(f+"\\logins.txt");
			
			}catch(IOException ex1) {
				System.out.println("error");
			}
            
		}
	       
		
	}
	
	void addData(String usr , String pawd) {
		try {
		RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt","rwd");
		for(int i=0;i<ln;i++)
		{
			raf.readLine();
		}
		raf.writeBytes("\r\n");
		raf.writeBytes("\r\n");
		raf.writeBytes("Username:"+usr+ "\r\n");
		raf.writeBytes("Password:"+pawd+ "\r\n");
		}catch(FileNotFoundException ex) {
			System.out.println("file not found");
		
		}
		catch(IOException ex) {
			System.out.println("error");
		}
	}
	
	void checkData(String usr , String pawd) {
		try {
		RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt","rw");
		String line = raf.readLine();
		Username = line.substring(9);
		Password = raf.readLine().substring(9);
		if(usr.equals(Username)& pawd.equals(Password)) {
			JOptionPane.showMessageDialog(null, "Password Matched");
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Wrong Username/Password");
		}
		}catch(FileNotFoundException ex) {
			System.out.println("error , filenotfound");
		}catch(IOException ex) {
			System.out.println("error");
		}
		
	}
	
	boolean logic(String usr , String pawd) {
		try {
			RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt","rw");
			for(int i=0;i<ln;i+=4) {
				String forUser = raf.readLine().substring(9);
				String forPawd = raf.readLine().substring(9);
				if(usr.equals(forUser)& pawd.equals(forPawd)) {
					//JOptionPane.showMessageDialog(null, "password matched");
					return true;
					//break;
				}
				else if(i==(ln-2)){
					//JOptionPane.showMessageDialog(null, "Incorrect username/password");
					return false;
				}
				for(int k=1;k<=2;k++) {
					raf.readLine();
				}
					
			}
		}catch(FileNotFoundException ex) {
			System.out.println("error , filenotfound");
		}catch(IOException ex) {
			System.out.println("error");
		}catch(StringIndexOutOfBoundsException e) {
			System.out.println("index out of bond");
		}
		return rootPaneCheckingEnabled;
		
	}
	
	void countLines() {
		try {
			ln=0;
		 RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt","rw");
		 for(int i = 0 ;raf.readLine()!=null;i++)
		 {
			ln++; 
		 }
		 
		}catch(FileNotFoundException ex) {
			System.out.println("error , filenotfound");
		}catch(IOException ex) {
			System.out.println("error");
		}
	}
	

	
	
	public static void main(String[] args) {
		Login_System frmLogin = new Login_System("Login");
		frmLogin.run();
		//Thread t1 = new Thread(frmLogin);
		//t1.start();
		
		
		frmLogin.setBounds(300,300,500,300);
		frmLogin.setVisible(true);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

}
