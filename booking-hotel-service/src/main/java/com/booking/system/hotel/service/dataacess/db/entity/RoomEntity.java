package com.booking.system.hotel.service.dataacess.db.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
public class RoomEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -8682110681781633045L;

    @Id
    @Column(name = "id", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;


    @Column(name = "name", nullable = false, length = 30)
    private String name;


    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "current_price", nullable = false, precision = 10)
    private BigDecimal currentPrice;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private HotelEntity hotel;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        final RoomEntity that = (RoomEntity) o;

        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
