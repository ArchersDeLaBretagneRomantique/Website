package bzh.abr.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity(name = "users")
@Getter @Setter
@EqualsAndHashCode
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_id_seq")
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false)
    private String role;

    @JsonIgnore
    @Column(nullable = false)
    private boolean enabled;

    @JsonIgnore
    @Column(nullable = false)
    private boolean locked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
