package com.crimsonlogic.model;

public class OrderDetails {
    private int orderDetailsId;
    private int orderId;
    private int medicineId;
    private int quantity;
    private double price;
    private Medicine medicine;
    
    
    public OrderDetails(int quantity, double price, Medicine medicine) {
		super();
		this.quantity = quantity;
		this.price = price;
		this.medicine = medicine;
	}

	public OrderDetails() {
		super();
	}

	public OrderDetails(int orderDetailsId, int orderId, int medicineId, int quantity, double price,
			Medicine medicine) {
		super();
		this.orderDetailsId = orderDetailsId;
		this.orderId = orderId;
		this.medicineId = medicineId;
		this.quantity = quantity;
		this.price = price;
		this.medicine = medicine;
	}



	public OrderDetails(Medicine medicine2, int quantity2, double price2) {
		this.medicine=medicine2;
		this.quantity=quantity2;
		this.price = price2;
	}

	// Getters and Setters
    public int getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((medicine == null) ? 0 : medicine.hashCode());
		result = prime * result + medicineId;
		result = prime * result + orderDetailsId;
		result = prime * result + orderId;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
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
		OrderDetails other = (OrderDetails) obj;
		if (medicine == null) {
			if (other.medicine != null)
				return false;
		} else if (!medicine.equals(other.medicine))
			return false;
		if (medicineId != other.medicineId)
			return false;
		if (orderDetailsId != other.orderDetailsId)
			return false;
		if (orderId != other.orderId)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailsId=" + orderDetailsId + ", orderId=" + orderId + ", medicineId=" + medicineId
				+ ", quantity=" + quantity + ", price=" + price + ", medicine=" + medicine + "]";
	}
	
	
	
    
}