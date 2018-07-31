package com.ust.transein.bmw.smoss.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {

    public long id;
    public String username;
    public String password;
    public String lastname;
    public String email;
    public String image_url;
    public boolean activated;
    public String lang_key;
    public String activation_key;
    public String reset_key;
    public String created_by;
    public Date reset_date;
    public String last_modfied_by;
    private List<Authority> authorities;

}
