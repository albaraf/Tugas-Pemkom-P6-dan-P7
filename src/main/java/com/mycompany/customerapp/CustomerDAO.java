/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.customerapp;

/**
 *
 * @author ASUS
 */
import com.mongodb.client.*;
import org.bson.Document;
import java.util.*;

public class CustomerDAO {
    private MongoCollection<Document> collection;

    public CustomerDAO() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("customers");
        collection = db.getCollection("customer_db");
    }

    public void insertCustomer(Customer customer) {
        Document doc = new Document("_id", customer.getId())
                .append("name", customer.getName())
                .append("email", customer.getEmail());
        collection.insertOne(doc);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(new Customer(
                    doc.getString("_id"),
                    doc.getString("name"),
                    doc.getString("email")
            ));
        }
        return list;
    }

    public void deleteCustomer(String id) {
        collection.deleteOne(new Document("_id", id));
    }
}

