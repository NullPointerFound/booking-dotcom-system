package com.booking.system.customer.dataaccess.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -2356473134112369947L;
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @CPF
    @NotNull
    @Size(max = 60)
    @Column(name = "email", nullable = false, length = 60)
    private String email;

}