package utp.edu.pe.restaurant.service;

public class Validator {
    public static boolean validateEmail(String email){
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean validatePhoneNumber(String phoneNumber){
        return phoneNumber.matches("^[0-9]{9}$");
    }
}