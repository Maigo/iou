package com.maigo.iou.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "user:[user_id: %d, first_name: %s, last_name: %s, email: %s]",
                userId, firstName, lastName, email);
    }

}
