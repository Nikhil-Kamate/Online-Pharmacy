package com.crimsonlogic.dao;

import com.crimsonlogic.model.Medicine;

public interface CartDAO {
    int getAvailableStock(int medicineId);
    void updateStock(int medicineId, int newStock);
    Medicine getMedicineById(int medicineId);
}

