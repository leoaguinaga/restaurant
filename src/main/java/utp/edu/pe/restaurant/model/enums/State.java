package utp.edu.pe.restaurant.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum State {
    on_hold("En espera de confirmación"),
    on_process("En proceso de preparación"),
    on_the_way("En camino"),
    waiting_driver("En espera de asiganción de repartidor"),
    finished("Entregado"),
    canceled("Cancelado");

    private final String displayName;
    State(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }
    public static List<State> getStates() {
        return new ArrayList<>(Arrays.asList(State.values()));
    }
}
