package sn.dev.ct.core.domain.account.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sn.dev.ct.core.domain.base.BaseEntity;

@Entity
@Table(name = "accounts")
@Getter @Setter @SuperBuilder
public class Account extends BaseEntity{
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountRole role;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public Account() {

    }

    public void setup(){
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
