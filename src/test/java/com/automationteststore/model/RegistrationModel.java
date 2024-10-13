package com.automationteststore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@AllArgsConstructor
@Builder

public class RegistrationModel {
    private String firstName;
    private String lastName;
    private String telephone;
    private String fax;
    private String company;
    private String address1;
    private String address2;
    private String city;
    private String regionOrState;
    private String zipCode;
    private String password;
    private String confirmPassword;

    public RegistrationModel() {

    }

    public static RegistrationModel createAnAccountModel(Map<String, String> entry) {
        RegistrationModel registrationModel = new RegistrationModel();
        //Table of the feature file
        registrationModel.setFirstName(entry.get("firstName"));
        registrationModel.setLastName(entry.get("lastName"));
        registrationModel.setTelephone(entry.get("telephoneNumber"));
        registrationModel.setFax(entry.get("faxNumber"));
        registrationModel.setCompany(entry.get("companyName"));
        registrationModel.setAddress1(entry.get("address_One"));
        registrationModel.setAddress2(entry.get("address_Two"));
        registrationModel.setCity(entry.get("city_name"));
        registrationModel.setRegionOrState(entry.get("region_or_state"));
        registrationModel.setZipCode(entry.get("zip_code"));
        registrationModel.setPassword(entry.get("user_password"));
        registrationModel.setConfirmPassword(entry.get("user_confirm_password"));
        return registrationModel;
    }

    public String getFirstName() {
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public String getLastName() {
        return lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

    public String getTelephone() {
        return telephone;

    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;

    }

    public String getFax() {
        return fax;

    }

    public void setFax(String fax) {
        this.fax = fax;

    }

    public String getCompany() {
        return company;

    }

    public void setCompany(String company) {
        this.company = company;

    }

    public String getAddress1() {
        return address1;

    }

    public void setAddress1(String address1) {
        this.address1 = address1;

    }

    public String getAddress2() {
        return address2;

    }

    public void setAddress2(String address2) {
        this.address2 = address2;

    }

    public String getCity() {
        return city;

    }

    public void setCity(String city) {
        this.city = city;

    }

    public String getRegionOrState() {
        return regionOrState;
    }

    public void setRegionOrState(String regionOrState) {
        this.regionOrState = regionOrState;
    }

    public String getZipCode() {
        return zipCode;

    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;

    }

    public String getPassword() {
        return password;

    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getConfirmPassword() {
        return confirmPassword;

    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;

    }


}
