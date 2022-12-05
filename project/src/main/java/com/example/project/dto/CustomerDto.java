package com.example.project.dto;

import com.example.project.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @Author Fusheng Tan
 * @Version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;

    @NotEmpty(message = "You can not leave the NRIC blank!")
    private String nric;

    @NotEmpty(message = "You can not leave the Name blank!")
    private String name;

    @Range(min = 18, max = 100, message = "Minimum age to register is 18. Maximum age to register is 100.")
    private Long age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Pattern(
            message = "Please enter a valid email and you can not leave it blank!",
            regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$")
    private String emailAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Please pick your birth date!")
    private LocalDate birthDate;

    @NotEmpty(message = "Please fill in your contact number!")
    @Pattern(
            message = "Please enter a valid contact number (Only numbers are allowed)",
            regexp = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")
    private String phone;

    @NotEmpty(message = "Please fill in your address!")
    private String address;

    private String nomineeNric;

    private String nomineeName;

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", nric='" + nric + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", emailAddress='" + emailAddress + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", nomineeNric='" + nomineeNric + '\'' +
                ", nomineeName='" + nomineeName + '\'' +
                '}';
    }
}
