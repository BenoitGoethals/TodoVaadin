package be.dragoncave.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benoit on 02/11/2016.
 */
@Entity

public class User {

    @OneToMany(mappedBy = "user", targetEntity = Task.class,
            fetch = FetchType.EAGER)
    private List<Task> tasks;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private int id;
    @Column(unique = false, updatable = true, nullable = false)
    @NotNull
    private String name;
    @Column(unique = false, updatable = true, nullable = false)
    @NotNull
    private String forName;
    @Column(unique = true, updatable = true, nullable = false)
    @NotNull
    private String userID;
    @Column(unique = false, updatable = true, nullable = false)
    @NotNull
    private String street;
    @Column(unique = false, updatable = true, nullable = false)
    @NotNull
    private String zip;
    @Column(unique = false, updatable = true, nullable = false)
    @NotNull
    private String city;
    @ManyToOne(optional = false)

    private Country country;
    @Column(unique = false, updatable = true, nullable = false)
    @NotNull
    private LocalDateTime birthDate;

    @OneToOne
    private UserDetail userDetail;

    public User(String name, String forName, String userID, String street, String zip, String city, Country country, LocalDateTime birthDate) {
        this.name = name;
        this.forName = forName;
        this.userID = userID;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.birthDate = birthDate;
        tasks = new ArrayList<Task>();
    }
    public User() {

    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;


    }


    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getId() == user.getId();

    }

    @Override
    public int hashCode() {
        return getId();
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", forName='" + forName + '\'' +
                ", userID='" + userID + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                ", country=" + country +
                ", birthDate=" + birthDate +
                '}';
    }
}
