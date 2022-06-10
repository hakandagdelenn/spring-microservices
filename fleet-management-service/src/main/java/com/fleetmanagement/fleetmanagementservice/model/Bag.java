package com.fleetmanagement.fleetmanagementservice.model;

import com.fleetmanagement.fleetmanagementservice.enums.BagStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

/**
 * @author hakan.dagdelen
 */

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_active=true")
@Table(name = "bag",
       indexes = {@Index(name = "idx_barcode", columnList = "barcode", unique = true),
                  @Index(name = "idx_delivery_point_id", columnList = "delivery_point_id"),
                  @Index(name = "idx_vehicle_id", columnList = "vehicle_id")})
public class Bag extends Base implements Serializable {

  private static final long serialVersionUID = -2931623130668161413L;

  @Column(name = "barcode", nullable = false, unique = true)
  private String barcode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "delivery_point_id", referencedColumnName = "id")
  private DeliveryPoint deliveryPoint;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 30)
  private BagStatus status = BagStatus.CREATED;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
  private Vehicle vehicle;
}
