package com.booking.system.customer.dataaccess.db.entity;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation_order_history")
public class ReservationOrderHistoryEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 3405171944021718782L;

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private CustomerReservationStatus status;

    @NotNull
    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;


    @Column(name = "failure_reason", nullable = true, length = 8000)
    private String failureReason;

    @ManyToOne
    @JoinColumn(name = "reservation_order_id")
    private ReservationOrderEntity reservationOrder;

}
