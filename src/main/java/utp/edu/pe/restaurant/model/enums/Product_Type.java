package utp.edu.pe.restaurant.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Product_Type {
    burger("Burgers"),
    drink("Bebidas"),
    salchipapa("Salchipapas"),
    chaufa("Arroz chaufa");

    private final String displayName;
    Product_Type(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static List<Product_Type> getProductTypes() {
        return new ArrayList<>(Arrays.asList(Product_Type.values()));
    }
}
