package utp.edu.pe.restaurant.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum User_Type {
    client("Cliente"),
    admin("Administrador"),
    delivery("Repartidor"),
    chef("Cocinero");

    private final String displayName;
    User_Type(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static List<User_Type> getUser_Types() {
        return new ArrayList<>(Arrays.asList(User_Type.values()));
    }
}
