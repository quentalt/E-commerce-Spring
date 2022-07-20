package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "admin_roles",joinColumns = @JoinColumn(name = "admin_id", referencedColumnName = "admin_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Admin admin = (Admin) o;
        return id != null && Objects.equals(id, admin.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
