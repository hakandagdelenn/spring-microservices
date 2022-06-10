package com.fleetmanagement.fleetmanagementservice.model;

import com.fleetmanagement.fleetmanagementservice.enums.DeliveryPointType;
import com.fleetmanagement.fleetmanagementservice.util.FieldUtil;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "delivery_point",
       indexes = {
           @Index(name = "idx_name", columnList = "name", unique = true),
           @Index(name = "idx_type", columnList = "type")})
public class DeliveryPoint extends Base implements Serializable {

  private static final long serialVersionUID = -2931623130668161413L;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  private DeliveryPointType type;

  @PrePersist
  @PreUpdate
  void preUpdate() {
    FieldUtil.cropValueWithAnnotation(this);
  }
}