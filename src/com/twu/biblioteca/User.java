package com.twu.biblioteca;

public class User {

    private final String userName;
    private final String email;
    private final int phone;

    public User(String userName, String email, int phone) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return userName + " " + email + " " + phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (phone != user.phone) return false;
        if (userName != null ? !userName.equals(user.userName) : user.userName != null) return false;
        return !(email != null ? !email.equals(user.email) : user.email != null);

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + phone;
        return result;
    }
}
