package org.edupro.webapi.user.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.edupro.webapi.user.model.TokenType;
import org.edupro.webapi.user.model.UserEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_token")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "token", unique = true)
    public String token;

    @Default
    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    public TokenType tokenType = TokenType.BEARER;

    @Column(name = "is_revoked")
    public boolean revoked;

    @Column(name = "is_expired")
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserEntity user;
}
