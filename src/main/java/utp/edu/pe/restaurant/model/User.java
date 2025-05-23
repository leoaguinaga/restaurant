package utp.edu.pe.restaurant.model;

import utp.edu.pe.restaurant.model.enums.User_Type;

public class User {
    private long user_id;
    private String full_name;
    private String email;
    private String phone_number;
    private String pwd;
    private User_Type user_type;

    public User(Builder builder) {
        this.user_id= builder.user_id;
        this.full_name = builder.full_name;
        this.email = builder.email;
        this.phone_number = builder.phone_number;
        this.pwd = builder.pwd;
        this.user_type = builder.user_type;
    }

    //INNER CLASS: BUILDER
    public static class Builder{
        private long user_id;
        private String full_name;
        private String email;
        private String phone_number;
        private String pwd;
        private User_Type user_type;

        public Builder(String full_name, String email, String phone_number, String pwd, User_Type user_type){
            this.user_id = 0;
            this.full_name = full_name;
            this.email = email;
            this.phone_number = phone_number;
            this.pwd = pwd;
            this.user_type = user_type;
        }

        public Builder withUser_id(long user_id){
            this.user_id = user_id;
            return this;
        }

        public User build(){return new User(this);}

    }

    // GETTERS
    public long getUser_id() {
        return user_id;
    }
    public String getFull_name() {
        return full_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public String getPwd() {
        return pwd;
    }
    public User_Type getUser_type() {return user_type;}

    // CREATE WORKER
    public static User createUserWithoutId(String full_name, String email, String phone_number, String pwd, User_Type user_type){
        return new Builder(full_name, email, phone_number, pwd, user_type).build();
    }

    public static User createUser(long worker_id, String full_name, String email, String phone_number, String pwd, User_Type user_type){
        return new Builder(full_name, email, phone_number, pwd, user_type).withUser_id(worker_id).build();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public User() {}
}
