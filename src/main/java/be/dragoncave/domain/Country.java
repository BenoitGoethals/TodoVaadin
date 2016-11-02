package be.dragoncave.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by benoit on 02/11/2016.
 */
@Entity
public class Country {


    public Country() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, updatable = false, nullable = false)
    @NotNull
    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (getId() != country.getId()) return false;
        return getCountryName() != null ? getCountryName().equals(country.getCountryName()) : country.getCountryName() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getCountryName() != null ? getCountryName().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
