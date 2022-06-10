package com.fleetmanagement.fleetmanagementservice.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
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
@Table(name = "vehicle",
       indexes = {@Index(name = "idx_plate_number", columnList = "plate", unique = true)})
public class Vehicle extends Base implements Serializable {

  private static final long serialVersionUID = -2931623130668161413L;

  @Column(name = "plate", nullable = false, unique = true)
  private String plate;
}
