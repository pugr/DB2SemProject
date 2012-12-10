package cz.zcu.kiv.eegmongo.model;

import cz.zcu.kiv.eegmongo.crossstore.domain.UserInfo;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.impl.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;



/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
public class UserItem {

    @NotEmpty
    @Size(min = 1, max = 20)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String surname;

    @DateTimeFormat
    private String dateOfBirth;

    private String nationality;
    private UserInfo.Gender gender;
    private String description;

    private Integer height;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public UserInfo.Gender getGender() {
        return gender;
    }

    public void setGender(UserInfo.Gender gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
