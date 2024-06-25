package mk.finki.ukim.mk.wp.lab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name="bookstores")
public class BookStore {
    @Id
    private Long id;
    private String name;
    private String city;
    @Column(name = "bookstore_address")
    private String address;

    public BookStore(){}

    public BookStore(String name, String city, String address) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return  name + ", " + address + ", " + city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStore store = (BookStore) o;
        return Objects.equals(id, store.id) && Objects.equals(name, store.name) && Objects.equals(city, store.city) && Objects.equals(address, store.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city, address);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
