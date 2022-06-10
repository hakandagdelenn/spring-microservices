package com.fleetmanagement.fleetmanagementservice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author hakan.dagdelen
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Base implements Serializable {

  private static final long serialVersionUID = 7843770840623752459L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Version
  @Column(name = "version")
  private Integer version;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  private Date createdAt;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "modified_at")
  private Date modifiedAt;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  @Column(name = "is_active")
  private Boolean isActive = Boolean.TRUE;

  @Transient
  public boolean isNew() {
    return null == this.id;
  }

  @Transient
  public boolean isModified() {
    return !isNew() && (modifiedAt != null || modifiedBy != null);
  }

  public Long getId() {
    return id;
  }

  public Integer getVersion() {
    return version;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public Date getModifiedAt() {
    return modifiedAt;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setActive(Boolean active) {
    isActive = active;
  }

  public Boolean getIsActive() {
    return isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Base that = (Base) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "BaseEntity{" +
           "id=" + id +
           ", version=" + version +
           ", createdAt=" + createdAt +
           ", createdBy='" + createdBy + '\'' +
           ", modifiedAt=" + modifiedAt +
           ", modifiedBy='" + modifiedBy + '\'' +
           ", isActive=" + isActive +
           '}';
  }
}
