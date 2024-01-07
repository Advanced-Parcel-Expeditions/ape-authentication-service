package si.ape.authentication.models.entities;

import javax.persistence.*;

/**
 * The UserEntity class is used to map the `user` table in the database. It is used by the JPA
 * (Java Persistence API) to map the database table to the Java object. The `user` table represents a user (can be
 * either staff or customer, that is defined via the user's role) of the messaging sub-system of the APE system. The
 * table contains only the base data, other metadata about the user is stored in either the `staff` or `customer` tables,
 * depending on the user's role. Those tables are not needed for the messaging sub-system, so they are not mapped to
 * Java objects.
 */
@Entity
@Table(name = "user")
@NamedQueries(value =
        {
                @NamedQuery(name = "UserEntity.getAll",
                        query = "SELECT u FROM UserEntity u"),
                @NamedQuery(name = "UserEntity.getByUsername",
                        query = "SELECT u FROM UserEntity u WHERE u.username = :username")
        })
public class UserEntity {

    /** The user's id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** The user's username. */
    @Column(name = "username")
    private String username;

    /** The user's password. */
    @Column(name = "password")
    private String password;

    /** The user's role. */
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    /**
     * Gets the user's id.
     *
     * @return The user's id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's id.
     *
     * @param id The user's id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The user's username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     *
     * @return The user's role.
     */
    public RoleEntity getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The user's role.
     */
    public void setRole(RoleEntity role) {
        this.role = role;
    }

}