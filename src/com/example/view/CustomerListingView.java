package com.example.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.cinema.dao.AbstractDao;
import com.cinema.model.Customer;
import com.cinema.dao.CustomerDao;

public class CustomerListingView {
    private AbstractDao<Customer> customerDao;
    private JFrame customerListingFrame;
    private JTable customerTable;
    private JScrollPane scrollPane;
    private JButton createBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    private JPanel btnPanel;
    private String[] columns = {"Id", "Name", "Email", "Address"};
    private DefaultTableModel tableModel;
    
    public CustomerListingView() {
        this.customerDao = new CustomerDao();
        initializeComponents();
        loadCustomersData(); // Load initial data into the table
    }

    private void initializeComponents() {
        this.customerListingFrame = new JFrame("Customer Listing");
        this.customerListingFrame.setSize(700, 500);
        this.customerListingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.customerListingFrame.setLayout(new BorderLayout());

        // Initialize the table model with column names
        this.tableModel = new DefaultTableModel(null, this.columns);
        this.customerTable = new JTable(this.tableModel);
        
        TableColumn column = this.customerTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(100);
        
        this.scrollPane = new JScrollPane(this.customerTable);
        this.customerListingFrame.add(this.scrollPane, BorderLayout.CENTER);

        // Initialize buttons
        this.createBtn = new JButton("New Customer");
        this.updateBtn = new JButton("Edit");
        this.deleteBtn = new JButton("Delete");
        
        // Set up button panel
        this.btnPanel = new JPanel();
        this.btnPanel.setLayout(new GridLayout(1, 3));
        this.btnPanel.add(createBtn);
        this.btnPanel.add(updateBtn);
        this.btnPanel.add(deleteBtn);

        // Add button actions
        addCreateBtnAction();
        addEditBtnAction();
        addDeleteBtnAction();

        // Add components to frame
        customerListingFrame.add(btnPanel, BorderLayout.SOUTH);
        customerListingFrame.setVisible(true);
    }
    
    private void addDeleteBtnAction() {
    	this.deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteBtnAction();
			}
    	});
    }
    
    private void deleteBtnAction() {
    	int selectedRowIndex = this.customerTable.getSelectedRow();
        if (selectedRowIndex >= 0) {
            int customerId = Integer.parseInt(this.tableModel.getValueAt(selectedRowIndex, 0).toString());
            this.customerDao.delete(customerId);
            this.refreshCustomerTable();
            JOptionPane.showMessageDialog(null, "Successfully Deleted!!!");
        } else {
            JOptionPane.showMessageDialog(null, "Please select a customer to delete.");
        }
		
	}

    private void addCreateBtnAction() {
        this.createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createBtnAction();
            }
        });
    }

    private void addEditBtnAction() {
        this.updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBtnAction();
            }
        });
    }

    private void editBtnAction() {
        int selectedRowIndex = this.customerTable.getSelectedRow();
        if (selectedRowIndex >= 0) {
            int customerId = Integer.parseInt(this.tableModel.getValueAt(selectedRowIndex, 0).toString());
            UpdateCustomerView updateCustomerView = new UpdateCustomerView(this, customerId);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a customer to update.");
        }
    }

    private void createBtnAction() {
        CustomerCreatePage customerCreateView = new CustomerCreatePage(this);
    }

    public void refreshCustomerTable() {
        this.tableModel.setRowCount(0);
        
        loadCustomersData();
    }

    private void loadCustomersData() {
        List<Customer> customers = this.customerDao.getAll();
        for (Customer customer : customers) {
            this.tableModel.addRow(customer.toArray());
        }
    }
}
