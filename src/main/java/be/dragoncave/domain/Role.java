package be.dragoncave.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by benoit on 11/11/2016.
 */
@Entity
public class Role {

    static {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private int id;
    private String RoleType;

    @OneToMany(mappedBy = "role", targetEntity = UserDetail.class,
            fetch = FetchType.EAGER)

    private List<UserDetail> userDetails;

    public Role() {
    }

    public Role(String roleType) {
        RoleType = roleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleType() {
        return RoleType;
    }

    public void setRoleType(String roleType) {
        RoleType = roleType;
    }

    public List<UserDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetail> userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (getId() != role.getId()) return false;
        if (getRoleType() != null ? !getRoleType().equals(role.getRoleType()) : role.getRoleType() != null)
            return false;
        return getUserDetails() != null ? getUserDetails().equals(role.getUserDetails()) : role.getUserDetails() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getRoleType() != null ? getRoleType().hashCode() : 0);
        result = 31 * result + (getUserDetails() != null ? getUserDetails().hashCode() : 0);
        return result;
    }


    public void addUserDetail(UserDetail userDetail){
        userDetails.add(userDetail);
    }



    public static List<GrantedAuthority> getRoles(){


        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority("ADMIN"));
        result.add(new SimpleGrantedAuthority("USER"));

        return result;
    }

}
