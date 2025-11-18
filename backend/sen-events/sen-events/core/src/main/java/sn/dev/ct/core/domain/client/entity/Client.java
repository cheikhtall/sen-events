package sn.dev.ct.core.domain.client.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.base.BaseEntity;

@Entity
@Table(name = "clients")
@Getter @Setter @SuperBuilder
public class Client extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String phone;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Client() {}
}
