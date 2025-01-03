package Controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.crypto.Data;

import Modul.Transaction;

public class UpdateShipment {
    public static void updateShipment(Transaction t){
        String query = "update transaction set shipment_status = ? where id_transaksi = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, t.getShipmentStatus().toString());
            st.setInt(2,Integer.parseInt(t.getIdTransaksi()));
            st.executeUpdate();
            DatabaseHandler.disconnect();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
