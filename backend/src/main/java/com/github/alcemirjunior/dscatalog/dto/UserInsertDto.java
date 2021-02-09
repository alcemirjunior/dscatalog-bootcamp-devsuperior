package com.github.alcemirjunior.dscatalog.dto;

public class UserInsertDto extends UserDTO{
    private static final long serialVersionUID = 1L;

    private String password;

    UserInsertDto(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
