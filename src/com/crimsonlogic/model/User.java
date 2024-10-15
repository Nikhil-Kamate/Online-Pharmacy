package com.crimsonlogic.model;

import java.time.LocalDate;

public class User {

	    private int userId;
	    private String user_firstName;
	    private String user_lastName;
	    private String user_email;
	    private String user_password;
	    private long user_phoneNo;
	    private String user_address;
	    private LocalDate user_dateofBirth;
	    
	    
		public User() {
			super();
		}

		public User( int userId,String user_firstName, String user_lastName, String user_email, String user_password,
				long user_phoneNo, String user_address, LocalDate user_dateofBirth) {
			super();
			this.userId=userId;
			this.user_firstName = user_firstName;
			this.user_lastName = user_lastName;
			this.user_email = user_email;
			this.user_password = user_password;
			this.user_phoneNo = user_phoneNo;
			this.user_address = user_address;
			this.user_dateofBirth = user_dateofBirth;
			
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUser_firstName() {
			return user_firstName;
		}

		public void setUser_firstName(String user_firstName) {
			this.user_firstName = user_firstName;
		}

		public String getUser_lastName() {
			return user_lastName;
		}

		public void setUser_lastName(String user_lastName) {
			this.user_lastName = user_lastName;
		}

		public String getUser_email() {
			return user_email;
		}

		public void setUser_email(String user_email) {
			this.user_email = user_email;
		}

		public String getUser_password() {
			return user_password;
		}

		public void setUser_password(String user_password) {
			this.user_password = user_password;
		}

		public long getUser_phoneNo() {
			return user_phoneNo;
		}

		public void setUser_phoneNo(long user_phoneNo) {
			this.user_phoneNo = user_phoneNo;
		}

		public String getUser_address() {
			return user_address;
		}

		public void setUser_address(String user_address) {
			this.user_address = user_address;
		}

		public LocalDate getUser_dateofBirth() {
			return user_dateofBirth;
		}

		public void setUser_dateofBirth(LocalDate user_dateofBirth) {
			this.user_dateofBirth = user_dateofBirth;
		}

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + userId;
			result = prime * result + ((user_address == null) ? 0 : user_address.hashCode());
			result = prime * result + ((user_dateofBirth == null) ? 0 : user_dateofBirth.hashCode());
			result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
			result = prime * result + ((user_firstName == null) ? 0 : user_firstName.hashCode());
			result = prime * result + ((user_lastName == null) ? 0 : user_lastName.hashCode());
			result = prime * result + ((user_password == null) ? 0 : user_password.hashCode());
			result = prime * result + (int) (user_phoneNo ^ (user_phoneNo >>> 32));
			
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
			User other = (User) obj;
			if (userId != other.userId)
				return false;
			if (user_address == null) {
				if (other.user_address != null)
					return false;
			} else if (!user_address.equals(other.user_address))
				return false;
			if (user_dateofBirth == null) {
				if (other.user_dateofBirth != null)
					return false;
			} else if (!user_dateofBirth.equals(other.user_dateofBirth))
				return false;
			if (user_email == null) {
				if (other.user_email != null)
					return false;
			} else if (!user_email.equals(other.user_email))
				return false;
			if (user_firstName == null) {
				if (other.user_firstName != null)
					return false;
			} else if (!user_firstName.equals(other.user_firstName))
				return false;
			if (user_lastName == null) {
				if (other.user_lastName != null)
					return false;
			} else if (!user_lastName.equals(other.user_lastName))
				return false;
			if (user_password == null) {
				if (other.user_password != null)
					return false;
			} else if (!user_password.equals(other.user_password))
				return false;
			if (user_phoneNo != other.user_phoneNo) {
				return false;
			}
			
			return true;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", user_firstName=" + user_firstName + ", user_lastName=" + user_lastName
					+ ", user_email=" + user_email + ", user_password=" + user_password + ", user_phoneNo="
					+ user_phoneNo + ", user_address=" + user_address + ", user_dateofBirth=" + user_dateofBirth
					+ ", user_role="  + "]";
		}
	    
	    
	}



