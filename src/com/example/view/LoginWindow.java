package com.example.view;

import com.cinema.dto.LoginDto;
import com.cinema.error.AuthenticationFail;
import com.cinema.service.AuthenticationService;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow {
	private AuthenticationService authenticationService;
	private JFrame frame;
	private JLabel userLable;
	private JTextField userTextField;
	private JLabel passLabel;
	private JPasswordField passField;
	private JButton loginButton;
	private JButton signUpButton;

	
	public LoginWindow() {
		this.authenticationService = new AuthenticationService();
		initializeComponents();
		setupLayout();
		setupLoginButtonAction();
		setupSignupAction();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void setupSignupAction(){
		this.signUpButton.addActionListener(e -> signUpBtnAction());
	}

	private void signUpBtnAction() {
		new UserCreateForm();
	}

	private void setupLoginButtonAction() {
		this.loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String username = userTextField.getText(); 
				String password = new String(passField.getPassword());
				LoginDto loginDto = new LoginDto();
				loginDto.setPassword(password);
				loginDto.setUserName(username);
				try {
					authenticationService.call(loginDto);
					JOptionPane.showMessageDialog(frame, "Login Successful!!");
					new HomePage();
					frame.dispose();
				}catch (AuthenticationFail e1) {
					JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
	}

	private void initializeComponents() {
		this.frame = new JFrame("Login Window");
		this.userLable = new JLabel("Username:");
		this.userTextField = new JTextField(15);
		this.passLabel = new JLabel("Password:");
		this.passField = new JPasswordField(15);
		this.loginButton = new JButton("Login");
		this.signUpButton = new JButton("Sign Up");
		
	}
	
	private void setupLayout() {
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(350, 200);
		this.frame.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		frame.add(this.userLable, constraints);
		
		constraints.gridx = 1;
		
		frame.add(this.userTextField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		
		frame.add(this.passLabel, constraints);
		
		constraints.gridx = 1;
		
		frame.add(this.passField, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		
		frame.add(this.loginButton, constraints);
		
		constraints.gridx = 1;
		
		frame.add(this.signUpButton, constraints);
	
		
	}
	
	

}
