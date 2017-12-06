package auction.ui.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Contract {

    private int id;

    private String term;


    private Country country;

    private User user;

    public Contract() {
    }

    public Contract(String term, Country country, User user) {
        this();
        this.term = term;
        this.country = country;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        return id == contract.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
