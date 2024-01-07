package si.ape.authentication.models.entities;

import javax.persistence.*;

/**
 * The RoleEntity class is used to map to the `role` table in the database. The `role` table represents a role of the
 * user of the messaging sub-system of the APE system.
 */
@Entity
@Table(name = "role")
@NamedQueries(value =
        {
                @NamedQuery(name = "RoleEntity.getAll",
                        query = "SELECT r FROM RoleEntity r")
        })
public class RoleEntity {

    /** The role's id. */
    @Id
    private Integer id;

    /** The role's name. */
    @Column(name = "role_name")
    private String roleName;

    /**
     * Gets the role's id.
     *
     * @return The role's id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the role's id.
     *
     * @param id The role's id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the role's name.
     *
     * @return The role's name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the role's name.
     *
     * @param roleName The role's name.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}