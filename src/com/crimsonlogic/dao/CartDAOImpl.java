package com.crimsonlogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.crimsonlogic.model.Medicine;

public class CartDAOImpl implements CartDAO {

    
    @Override
    public int getAvailableStock(int medicineId) {
        int availableStock = 0;
        String query = "SELECT medicinestockquantity FROM public.medicines WHERE medicineid = ?";
        
        try (Connection conn = DBConnect.dbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, medicineId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                availableStock = rs.getInt("medicinestockquantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableStock;
    }

    
    @Override
    public void updateStock(int medicineId, int newStock) {
        String query = "UPDATE public.medicines SET medicinestockquantity = ? WHERE medicineid = ?";
        
        try (Connection conn = DBConnect.dbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, medicineId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    @Override
    public Medicine getMedicineById(int medicineId) {
        Medicine medicine = null;
        String query = "SELECT medicineid, medicinename, medicinecategory, medicinedescription, medicineprice, medicinestockquantity, medicinecompany, medicinemanufactureddate, medicineexpiredate, medicinephoto FROM public.medicines WHERE medicineid = ?";
        
        try (Connection conn = DBConnect.dbConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, medicineId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                medicine = new Medicine(
                    rs.getInt("medicineid"),
                    rs.getString("medicinename"),
                    rs.getString("medicinecategory"),
                    rs.getString("medicinedescription"),
                    rs.getDouble("medicineprice"),
                    rs.getInt("medicinestockquantity"),
                    rs.getString("medicinecompany"),
                    rs.getDate("medicinemanufactureddate").toLocalDate(),
                    rs.getDate("medicineexpiredate").toLocalDate(),
                    rs.getString("medicinephoto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicine;
    }
}
