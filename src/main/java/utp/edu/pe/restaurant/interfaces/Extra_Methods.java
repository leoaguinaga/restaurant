package utp.edu.pe.restaurant.interfaces;

import utp.edu.pe.restaurant.model.Extra;

import java.sql.SQLException;
import java.util.List;

public interface Extra_Methods {
    void registerExtra(Extra extra) throws SQLException;
    List<Extra> getAllExtras() throws SQLException;
    Extra getExtraById(long extra_id) throws SQLException;
    void updateExtra(Extra extra, long extra_id) throws SQLException;
    void deleteExtra(long extra_id) throws SQLException;
    String getExtraNameById(long extra_id) throws SQLException;
}
