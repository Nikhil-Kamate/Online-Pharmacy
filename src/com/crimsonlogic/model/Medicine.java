package com.crimsonlogic.model;

import java.time.LocalDate;
import java.util.Arrays;

public class Medicine {
	
	private int medicineId;
	private String medicineName;
	private String medicineCategory;
	private String medicineDescription;
	private double medicinePrice;
	private int medicineStockQuantity;
	private String medicineCompany;
	private LocalDate medicineManufacturedDate;
	private LocalDate medicineExpireDate;
	 private String medicinePhotoPath;
	
	public Medicine() {
		super();
	}
	

	public Medicine(String medicineName, double medicinePrice) {
		super();
		this.medicineName = medicineName;
		this.medicinePrice = medicinePrice;
	}


	public Medicine(int medicineId, String medicineName, String medicineCategory, String medicineDescription,
			double medicinePrice, int medicineStockQuantity, String medicineCompany, LocalDate medicineManufacturedDate,
			LocalDate medicineExpireDate,String medicinePhotoPath) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineCategory = medicineCategory;
		this.medicineDescription = medicineDescription;
		this.medicinePrice = medicinePrice;
		this.medicineStockQuantity = medicineStockQuantity;
		this.medicineCompany = medicineCompany;
		this.medicineManufacturedDate = medicineManufacturedDate;
		this.medicineExpireDate = medicineExpireDate;
		this.medicinePhotoPath = medicinePhotoPath;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineCategory() {
		return medicineCategory;
	}

	public void setMedicineCategory(String medicineCategory) {
		this.medicineCategory = medicineCategory;
	}

	public String getMedicineDescription() {
		return medicineDescription;
	}

	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}

	public double getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public int getMedicineStockQuantity() {
		return medicineStockQuantity;
	}

	public void setMedicineStockQuantity(int medicineStockQuantity) {
		this.medicineStockQuantity = medicineStockQuantity;
	}

	public String getMedicineCompany() {
		return medicineCompany;
	}

	public void setMedicineCompany(String medicineCompany) {
		this.medicineCompany = medicineCompany;
	}

	public LocalDate getMedicineManufacturedDate() {
		return medicineManufacturedDate;
	}

	public void setMedicineManufacturedDate(LocalDate medicineManufacturedDate) {
		this.medicineManufacturedDate = medicineManufacturedDate;
	}

	public LocalDate getMedicineExpireDate() {
		return medicineExpireDate;
	}

	public void setMedicineExpireDate(LocalDate medicineExpireDate) {
		this.medicineExpireDate = medicineExpireDate;
	}
	

	public String getMedicinePhotoPath() {
		return medicinePhotoPath;
	}

	public void setMedicinePhotoPath(String medicinePhotoPath) {
		this.medicinePhotoPath = medicinePhotoPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicineCategory == null) ? 0 : medicineCategory.hashCode());
		result = prime * result + ((medicineCompany == null) ? 0 : medicineCompany.hashCode());
		result = prime * result + ((medicineDescription == null) ? 0 : medicineDescription.hashCode());
		result = prime * result + ((medicineExpireDate == null) ? 0 : medicineExpireDate.hashCode());
		result = prime * result + medicineId;
		result = prime * result + ((medicineManufacturedDate == null) ? 0 : medicineManufacturedDate.hashCode());
		result = prime * result + ((medicineName == null) ? 0 : medicineName.hashCode());
		result = prime * result + ((medicinePhotoPath == null) ? 0 : medicinePhotoPath.hashCode());
		long temp;
		temp = Double.doubleToLongBits(medicinePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + medicineStockQuantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		if (medicineCategory == null) {
			if (other.medicineCategory != null)
				return false;
		} else if (!medicineCategory.equals(other.medicineCategory))
			return false;
		if (medicineCompany == null) {
			if (other.medicineCompany != null)
				return false;
		} else if (!medicineCompany.equals(other.medicineCompany))
			return false;
		if (medicineDescription == null) {
			if (other.medicineDescription != null)
				return false;
		} else if (!medicineDescription.equals(other.medicineDescription))
			return false;
		if (medicineExpireDate == null) {
			if (other.medicineExpireDate != null)
				return false;
		} else if (!medicineExpireDate.equals(other.medicineExpireDate))
			return false;
		if (medicineId != other.medicineId)
			return false;
		if (medicineManufacturedDate == null) {
			if (other.medicineManufacturedDate != null)
				return false;
		} else if (!medicineManufacturedDate.equals(other.medicineManufacturedDate))
			return false;
		if (medicineName == null) {
			if (other.medicineName != null)
				return false;
		} else if (!medicineName.equals(other.medicineName))
			return false;
		if (medicinePhotoPath == null) {
			if (other.medicinePhotoPath != null)
				return false;
		} else if (!medicinePhotoPath.equals(other.medicinePhotoPath))
			return false;
		if (Double.doubleToLongBits(medicinePrice) != Double.doubleToLongBits(other.medicinePrice))
			return false;
		if (medicineStockQuantity != other.medicineStockQuantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineCategory="
				+ medicineCategory + ", medicineDescription=" + medicineDescription + ", medicinePrice=" + medicinePrice
				+ ", medicineStockQuantity=" + medicineStockQuantity + ", medicineCompany=" + medicineCompany
				+ ", medicineManufacturedDate=" + medicineManufacturedDate + ", medicineExpireDate="
				+ medicineExpireDate + ", medicinePhotoPath=" + medicinePhotoPath + "]";
	}
}
