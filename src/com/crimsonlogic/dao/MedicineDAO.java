package com.crimsonlogic.dao;

import java.util.List;

import com.crimsonlogic.model.Medicine;

public interface MedicineDAO {

	boolean addMedicine(Medicine medicine);

	List<Medicine> getAllMedicines();

	boolean updateMedicine(Medicine medicine);

	boolean deleteMedicine(int medicineId);

	Medicine getMedicineById(int medicineId);

	List<Medicine> getMedicinesByName(String medicineName);

}
