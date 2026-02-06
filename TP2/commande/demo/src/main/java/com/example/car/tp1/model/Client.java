package com.example.car.tp1.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Client {
    @Id
    private String email;
    private String name;
    private String prename;
    private String password;
    // Constructor chưa có tham số (default constructor) là bắt buộc
    public Client() {
    }
    // Constructor có tham số dùng để tạo đối tượng Client
    public Client(String email, String name, String prename) {
        this.email = email;
        this.name = name;
        this.prename = prename;
    }
    // Getters and Setters


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrename() {
            return prename;
        }

        public void setPrename(String prename) {
            this.prename = prename;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
