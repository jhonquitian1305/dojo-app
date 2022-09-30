package com.app.dojo.dtos;

import javax.validation.constraints.*;
import java.util.Date;

public abstract class UserDTO {
    private final Long id;

    @NotNull
    @NotEmpty
    @Min(message = "The dni must be greater than 5", value = 5)
    @Max(message = "The dni must be less than 50", value = 50)
    private final String dni;

    @NotNull(message = "The field names couldn't be null")
    @NotEmpty(message = "The field names couldn't be empty")
    @Max(message = "The field names must be less than 50", value = 50)
    private final String names;

    @NotNull(message = "The field lastnames couldn't be null")
    @NotEmpty(message = "The field lastnames couldn't be empty")
    @Max(message = "The field lastnames must be less than 50", value = 50)
    private final String lastnames;

    @NotNull(message = "The field birthday couldn't be null")
    @NotEmpty(message = "The field birthday couldn't be empty")
    private final Date birthday;

    @NotNull(message = "The field email couldn't be null")
    @NotEmpty(message = "The field email couldn't be empty")
    @Min(message = "The field email must be greater than 5", value = 5)
    @Max(message = "The field email must be less than 50", value = 50)
    private final String email;

    @NotNull(message = "The field password couldn't be null")
    @NotEmpty(message = "The field password couldn't be empty")
    @Min(message = "The field password must be greater than 8", value = 8)
    @Max(message = "The field password must be less than 50", value = 50)
    private final String password;

    public UserDTO(Long id, String dni, String names, String lastnames, Date birthday, String email, String password) {
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
