package com.app.dojo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Long id;

    @NotNull(message = "The field dni couldn't be null")
    @NotEmpty(message = "The field dni couldn't be empty")
    @Min(message = "The dni must be greater than 5", value = 5)
    @Max(message = "The dni must be less than 50", value = 50)
    private String dni;

    @NotNull(message = "The field names couldn't be null")
    @NotEmpty(message = "The field names couldn't be empty")
    @Max(message = "The field names must be less than 50", value = 50)
    private String names;

    @NotNull(message = "The field lastnames couldn't be null")
    @NotEmpty(message = "The field lastnames couldn't be empty")
    @Max(message = "The field lastnames must be less than 50", value = 50)
    private String lastnames;

    @NotNull(message = "The field birthday couldn't be null")
    @NotEmpty(message = "The field birthday couldn't be empty")
    private Date birthday;

    @NotNull(message = "The field email couldn't be null")
    @NotEmpty(message = "The field email couldn't be empty")
    @Min(message = "The field email must be greater than 5", value = 5)
    @Max(message = "The field email must be less than 50", value = 50)
    private String email;

    @NotNull(message = "The field password couldn't be null")
    @NotEmpty(message = "The field password couldn't be empty")
    @Min(message = "The field email must be greater than 8", value = 8)
    @Max(message = "The field email must be less than 50", value = 50)
    private String password;

    public StudentDTO(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
        this.id = id;
        this.dni = dni;
        this.names = names;
        this.lastnames = lastnames;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNames() {
        return names;
    }

    public String getLastnames() {
        return lastnames;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
