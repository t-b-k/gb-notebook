package notebook.model;

import java.util.Objects;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;

    public User(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public User(Long id, String firstName, String lastName, String phone) {
        this(firstName, lastName, phone);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %s\nИмя: %s,\nФамилия: %s,\nТелефон: %s", id, firstName, lastName, phone);
    }

    @Override
    public boolean equals(Object o) {
        User other = (User) o;
        return id == other.getId() && firstName.equals(other.getFirstName()) && lastName.equals(other.getLastName()) &&
                phone.equals(other.getPhone());
    }

    @Override
    public int hashCode() {
        int result = 19;
        int factor = 31;
        result = result*factor + this.getId().hashCode();
        result = result*factor + this.getFirstName().hashCode();
        result = result*factor + this.getLastName().hashCode();
        result = result*factor + this.getPhone().hashCode();
        return result>>>1;
    }
}
