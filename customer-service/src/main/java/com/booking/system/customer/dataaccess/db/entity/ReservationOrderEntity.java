package com.booking.system.customer.dataaccess.db.entity;

import com.booking.system.commons.domain.core.valueobject.CustomerReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation_order")
public class ReservationOrderEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7189344749418655926L;

    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, length = 36)
    private UUID id;

    @NotNull
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "hotel_id", nullable = false, length = 36)
    private UUID hotelId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @NotNull
    @Column(name = "guests", nullable = false)
    private Integer guests;

    @NotNull
    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @NotNull
    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    @NotNull
    @Column(name = "total_price", nullable = false, precision = 10)
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "current_status", nullable = false, length = 50)
    private CustomerReservationStatus currentStatus;

    @OneToMany(
            mappedBy = "reservationOrder",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<ReservationOrderHistoryEntity> history;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        final ReservationOrderEntity that = (ReservationOrderEntity) o;

        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }


}
