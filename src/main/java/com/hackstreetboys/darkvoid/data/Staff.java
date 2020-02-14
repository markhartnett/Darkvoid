package com.hackstreetboys.darkvoid.data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Staff {
    private @Id @GeneratedValue Integer staffId;
    private @NotBlank String firstName;
    private @NotBlank String lastName;
    private @NotBlank String address;
    private @NotBlank String phoneNumber;
    private @NotBlank String email;

    public Staff() {}

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getStaffId() { return staffId; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setStaffId(Integer staffId) { this.staffId = staffId; }
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
}
