/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.customerapp;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CustomerForm extends JFrame {
    private JTextField idField, nameField, emailField;
    private JTextArea displayArea;
    private CustomerDAO dao;

    public CustomerForm() {
        dao = new CustomerDAO();

        setTitle("Customer Manager");
        setSize(400, 400);
        setLayout(new FlowLayout());

        add(new JLabel("ID:"));
        idField = new JTextField(20);
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField(20);
        add(nameField);

        add(new JLabel("Email:"));
        emailField = new JTextField(20);
        add(emailField);

        JButton addButton = new JButton("Add");
        JButton showButton = new JButton("Show All");
        JButton deleteButton = new JButton("Delete");

        add(addButton);
        add(showButton);
        add(deleteButton);

        displayArea = new JTextArea(10, 30);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(e -> {
            Customer c = new Customer(
                idField.getText(), nameField.getText(), emailField.getText()
            );
            dao.insertCustomer(c);
            JOptionPane.showMessageDialog(this, "Customer added.");
        });

        showButton.addActionListener(e -> {
            displayArea.setText("");
            List<Customer> customers = dao.getAllCustomers();
            for (Customer c : customers) {
                displayArea.append(c.getId() + " - " + c.getName() + " - " + c.getEmail() + "\n");
            }
        });

        deleteButton.addActionListener(e -> {
            dao.deleteCustomer(idField.getText());
            JOptionPane.showMessageDialog(this, "Customer deleted.");
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
