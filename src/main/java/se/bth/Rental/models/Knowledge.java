package se.bth.Rental.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@ToString( callSuper = true )

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Knowledge extends RentalResource {
    private String relatedProduct;
    private Type type;
    @Builder
    public Knowledge(Long id, String name, Double pricePerHour, LocalDateTime registerDateTime, ResourceStatus status, Integer quantity, String description, Category category, List<AvailableTime> availableTime, Customer owner, List<Reservation> reservations, String relatedProduct, Type type) {
        super(id, name, pricePerHour, registerDateTime, status, quantity, description, category, availableTime, owner, reservations);
        this.relatedProduct = relatedProduct;
        this.type = type;
    }
}
