package com.crimsonlogic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.model.Medicine;

public class MedicineDAOImpl implements MedicineDAO {

    private Connection connection;

    public MedicineDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public MedicineDAOImpl() {
        this.connection = DBConnect.dbConnection(); 
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        String query = "INSERT INTO medicines (medicineName, medicineCategory, medicineDescription, medicinePrice, "
                + "medicineStockQuantity, medicineCompany, medicineManufacturedDate, medicineExpireDate, medicinePhotoPath) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setString(2, medicine.getMedicineCategory());
            preparedStatement.setString(3, medicine.getMedicineDescription());
            preparedStatement.setDouble(4, medicine.getMedicinePrice());
            preparedStatement.setInt(5, medicine.getMedicineStockQuantity());
            preparedStatement.setString(6, medicine.getMedicineCompany());
            preparedStatement.setDate(7, java.sql.Date.valueOf(medicine.getMedicineManufacturedDate()));
            preparedStatement.setDate(8, java.sql.Date.valueOf(medicine.getMedicineExpireDate()));
            preparedStatement.setString(9, medicine.getMedicinePhotoPath());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Medicine> getAllMedicines() {
        String query = "SELECT * FROM medicines";
        List<Medicine> medicines = new ArrayList<>();
        try (Statement statement = connection.createStatement(); 
        		ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Medicine medicine = new Medicine(resultSet.getInt("medicineId"), resultSet.getString("medicineName"),
                        resultSet.getString("medicineCategory"), resultSet.getString("medicineDescription"),
                        resultSet.getDouble("medicinePrice"), resultSet.getInt("medicineStockQuantity"),
                        resultSet.getString("medicineCompany"),
                        resultSet.getDate("medicineManufacturedDate").toLocalDate(),
                        resultSet.getDate("medicineExpireDate").toLocalDate(), resultSet.getString("medicinePhotoPath"));
                medicines.add(medicine);
            }
            System.out.println("Medicines retrieved: " + medicines.size()); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }


    @Override
    public boolean updateMedicine(Medicine medicine) {
        
        String query = "UPDATE medicines SET medicineName = ?, medicineCategory = ?, medicineDescription = ?, "
                + "medicinePrice = ?, medicineStockQuantity = ?, medicineCompany = ?, "
                + "medicineManufacturedDate = ?, medicineExpireDate = ? WHERE medicineId = ?";
        
        // Add a condition to check if a new photo path is provided
        if (medicine.getMedicinePhotoPath() != null && !medicine.getMedicinePhotoPath().isEmpty()) {
            query = "UPDATE medicines SET medicineName = ?, medicineCategory = ?, medicineDescription = ?, "
                    + "medicinePrice = ?, medicineStockQuantity = ?, medicineCompany = ?, "
                    + "medicineManufacturedDate = ?, medicineExpireDate = ?, medicinePhotoPath = ? WHERE medicineId = ?";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, medicine.getMedicineName());
            preparedStatement.setString(2, medicine.getMedicineCategory());
            preparedStatement.setString(3, medicine.getMedicineDescription());
            preparedStatement.setDouble(4, medicine.getMedicinePrice());
            preparedStatement.setInt(5, medicine.getMedicineStockQuantity());
            preparedStatement.setString(6, medicine.getMedicineCompany());
            preparedStatement.setDate(7, java.sql.Date.valueOf(medicine.getMedicineManufacturedDate()));
            preparedStatement.setDate(8, java.sql.Date.valueOf(medicine.getMedicineExpireDate()));

            if (medicine.getMedicinePhotoPath() != null && !medicine.getMedicinePhotoPath().isEmpty()) {
               
                preparedStatement.setString(9, medicine.getMedicinePhotoPath());
                preparedStatement.setInt(10, medicine.getMedicineId());
            } else {
                
                preparedStatement.setInt(9, medicine.getMedicineId());
            }

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteMedicine(int medicineId) {
        String query = "DELETE FROM medicines WHERE medicineId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, medicineId);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Medicine getMedicineById(int medicineId) {
        String query = "SELECT * FROM medicines WHERE medicineId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, medicineId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Medicine(resultSet.getInt("medicineId"), resultSet.getString("medicineName"),
                            resultSet.getString("medicineCategory"), resultSet.getString("medicineDescription"),
                            resultSet.getDouble("medicinePrice"), resultSet.getInt("medicineStockQuantity"),
                            resultSet.getString("medicineCompany"),
                            resultSet.getDate("medicineManufacturedDate").toLocalDate(),
                            resultSet.getDate("medicineExpireDate").toLocalDate(), resultSet.getString("medicinePhotoPath"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Medicine> getMedicinesByName(String medicineName) {
        String query = "SELECT * FROM medicines WHERE LOWER(medicineName) LIKE ?";
        List<Medicine> medicines = new ArrayList<>();
        
        // Adding '%' around the input to match any sequence of characters before and after the input
        String searchPattern = "%" + medicineName.toLowerCase() + "%";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, searchPattern);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    medicines.add(new Medicine(
                            resultSet.getInt("medicineId"),
                            resultSet.getString("medicineName"),
                            resultSet.getString("medicineCategory"),
                            resultSet.getString("medicineDescription"),
                            resultSet.getDouble("medicinePrice"),
                            resultSet.getInt("medicineStockQuantity"),
                            resultSet.getString("medicineCompany"),
                            resultSet.getDate("medicineManufacturedDate").toLocalDate(),
                            resultSet.getDate("medicineExpireDate").toLocalDate(),
                            resultSet.getString("medicinePhotoPath")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return medicines;
    }

}
