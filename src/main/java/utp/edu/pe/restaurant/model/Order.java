package utp.edu.pe.restaurant.model;

import utp.edu.pe.restaurant.model.enums.State;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private long order_id;
    private User user;
    private long user_id; // Nuevo atributo
    private LocalDateTime order_date;
    private String address;
    private double amount;
    private State state;
    private String evidence_image;

    public Order(Builder builder) {
        this.order_id = builder.order_id;
        this.user = builder.user;
        this.user_id = builder.user_id; // Asignar user_id
        this.order_date = builder.order_date;
        this.address = builder.address;
        this.amount = builder.amount;
        this.state = builder.state;
        this.evidence_image = builder.evidence_image;
    }

    // INNER CLASS: BUILDER
    public static class Builder {
        private long order_id;
        private User user;
        private long user_id; // Nuevo atributo en Builder
        private LocalDateTime order_date;
        private String address;
        private double amount;
        private State state;
        private String evidence_image;

        public Builder(LocalDateTime order_date, String address, double amount, State state, String evidence_image) {
            this.order_id = 0;
            this.order_date = order_date;
            this.address = address;
            this.amount = amount;
            this.state = state;
            this.evidence_image = evidence_image;
        }

        // Método para establecer un objeto User
        public Builder withUser(User user) {
            this.user = user;
            this.user_id = user.getUser_id(); // Almacenar el user_id del objeto User
            return this;
        }

        // Método para establecer solo el user_id
        public Builder withUserId(long user_id) {
            this.user_id = user_id; // Almacenar el user_id directamente
            return this;
        }

        public Builder withOrder_id(long customer_order_id) {
            this.order_id = customer_order_id;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    // GETTERS
    public long getOrder_id() {
        return order_id;
    }
    public User getUser() {
        return user;
    }
    public long getUser_id() { // Getter para user_id
        return user_id;
    }
    public String getAddress() {
        return address;
    }
    public double getAmount() {
        return amount;
    }
    public State getState() {
        return state;
    }
    public String getEvidence_image() {
        return evidence_image;
    }
    public LocalDateTime getOrder_date() {
        return order_date;
    }
    public String getOrderDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return order_date.format(formatter);
    }
    public String getOrderDateOnly() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return order_date.format(formatter);
    }
    public String getOrderTimeOnly() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return order_date.format(formatter);
    }

    // CREATE ORDER
    public static Order createOrderWithoutId(User user, LocalDateTime order_date, String address, double amount, String evidence_image) throws IOException {
        return new Builder(order_date,address,amount, State.on_hold, evidence_image).withUser(user).build();
    }
    public static Order createOrderWithoutId(long user_id, LocalDateTime order_date, String address, double amount, String evidence_image) throws IOException {
        return new Builder(order_date,address,amount, State.on_hold, evidence_image).withUserId(user_id).build();
    }
    public static Order createOrder(long order_id, User user, LocalDateTime order_date, String address, double amount, State state, String evidence_image){
        return new Builder(order_date,address,amount,state,evidence_image).withUser(user).withOrder_id(order_id).build();
    }
    public static Order createOrder(long order_id, long user_id, LocalDateTime order_date, String address, double amount, State state, String evidence_image){
        return new Builder(order_date,address,amount,state,evidence_image).withUserId(user_id).withOrder_id(order_id).build();
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", user=" + user +
                ", user_id=" + user_id +
                ", order_date=" + order_date +
                ", address='" + address + '\'' +
                ", amount=" + amount +
                ", state=" + state +
                ", evidence_image='" + evidence_image + '\'' +
                '}';
    }

    // Setter obligatorio para asignar el id de la orden
    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }
}
