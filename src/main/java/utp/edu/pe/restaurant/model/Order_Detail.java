package utp.edu.pe.restaurant.model;

import java.util.List;

public class Order_Detail {
    private long order_id;
    private Product product;
    private int quantity;
    private List<Extra> extras;

    public Order_Detail(Builder builder) {
        this.order_id = builder.order_id;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.extras = builder.extras;
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private long order_id;
        private Product product;
        private int quantity;
        private List<Extra> extras;

        public Builder(long order_id, Product product, int quantity, List<Extra> extras) {
            this.order_id = order_id;
            this.product = product;
            this.quantity = quantity;
            this.extras = extras;
        }

        public Order_Detail build() {
            return new Order_Detail(this);
        }
    }

    // GETTERS
    public long getOrder_id() {
        return order_id;
    }
    public Product getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    public List<Extra> getExtras() {
        return extras;
    }

    // CREATE ORDER DETAIL
    public static Order_Detail createOrderDetail(long order_id, Product product, int quantity, List<Extra> extras){
        return new Builder(order_id, product, quantity, extras).build();
    }

    @Override
    public String toString() {
        return "Order_Detail{" +
                "order_id=" + order_id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", extras=" + extras +
                '}';
    }
}
