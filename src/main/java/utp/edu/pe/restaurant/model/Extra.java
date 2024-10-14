package utp.edu.pe.restaurant.model;

public class Extra {
    private long extra_id;
    private String name;
    private double price;

    public Extra(Builder builder) {
        this.extra_id = builder.extra_id;
        this.name = builder.name;
        this.price = builder.price;
    }

    //INNER CLASS: BUILDER
    public static class Builder {
        private long extra_id;
        private String name;
        private double price;

        public Builder(String name, double price) {
            this.extra_id = 0;
            this.name = name;
            this.price = price;
        }

        public Builder withExtra_id(long extra_id){
            this.extra_id = extra_id;
            return this;
        }

        public Extra build() {
            return new Extra(this);
        }
    }

    // GETTERS
    public long getExtra_id() {
        return extra_id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {return price;}

    // CREATE INGREDIENT
    public static Extra createExtraWithoutId(String name, double price){
        return new Builder(name, price).build();
    }
    public static Extra createExtra(long extra_id, String name, double price){
        return new Builder(name, price).withExtra_id(extra_id).build();
    }
}
