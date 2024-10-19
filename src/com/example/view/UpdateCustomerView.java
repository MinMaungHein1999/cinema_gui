package com.example.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import com.cinema.dao.CustomerDao;
import com.cinema.dao.AbstractDao;
import com.cinema.model.Customer;


public class UpdateCustomerView {
	private AbstractDao<Customer> customerDao;
	private JFrame customerUpdateFrame;
	private JPanel registerPanel;
	private JLabel customerNameLabel;
	private JTextField customerTextField;
	
	private JLabel customerEmailLabel;
	private JTextField customerEmailTextField;
	
	private JLabel customerAddressLabel;
	private JTextField customerAddressTextField;
	
	private JButton createBtn;
	private JButton cancleBtn;
	private CustomerListingView parentFrame;
	
	private Customer customer;
	
	public UpdateCustomerView(CustomerListingView parentFrame, int customerId) {
		this.customerDao = new CustomerDao();
		this.customer = this.customerDao.findbyId(customerId);
		initializeComponent();
		this.parentFrame = parentFrame;
		this.customerUpdateFrame.setSize(300, 250);
		this.customerUpdateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.customerUpdateFrame.setLocationRelativeTo(null);
		this.customerUpdateFrame.setVisible(true);
	}

	private void initializeComponent() {
		this.customerUpdateFrame = new JFrame("Customer Update Form");
		this.customerUpdateFrame.setLayout(new BorderLayout());
		
		this.createBtn = new JButton("Update");
		this.cancleBtn = new JButton("Reset");
		
		this.customerNameLabel = new JLabel("Customer Name");
		this.customerTextField = new JTextField(this.customer.getName());
		
		this.customerEmailLabel = new JLabel("Customer Email");
		this.customerEmailTextField = new JTextField(this.customer.getEmail());
		
		this.customerAddressLabel = new JLabel("Customer Address");
		this.customerAddressTextField = new JTextField(this.customer.getAddress());
		
		
		this.registerPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(4,2);
		this.registerPanel.setLayout(gridLayout);
		
		this.registerPanel.add(this.customerNameLabel);
		this.registerPanel.add(this.customerTextField);
		
		this.registerPanel.add(this.customerEmailLabel);
		this.registerPanel.add(this.customerEmailTextField);
		
		this.registerPanel.add(this.customerAddressLabel);
		this.registerPanel.add(this.customerAddressTextField);
		
		this.registerPanel.add(this.createBtn);
		this.registerPanel.add(this.cancleBtn);
		
		this.customerUpdateFrame.add(registerPanel, BorderLayout.NORTH);
		
		addActionRegisterBtn();
	}
	
	private void addActionRegisterBtn() {
		this.createBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				customerUpdateAction();
			}
		});
	}
	
	private void customerUpdateAction() {
		
		String address = this.customerAddressTextField.getText();
		String name = this.customerTextField.getText();
		String email = this.customerEmailTextField.getText();
		
		Customer customer = new Customer(this.customer.getId(), name, email, address);
		
		this.customerDao.update(customer);
		
		JOptionPane.showMessageDialog(this.customerUpdateFrame, "Customer Successfully Updated!!!");
		this.parentFrame.refreshCustomerTable();
		this.customerUpdateFrame.dispose();
	}
}
