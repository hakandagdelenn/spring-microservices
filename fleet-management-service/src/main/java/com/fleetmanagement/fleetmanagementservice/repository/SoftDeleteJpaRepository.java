package com.fleetmanagement.fleetmanagementservice.repository;

import com.fleetmanagement.fleetmanagementservice.model.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author hakan.dagdelen
 */

@NoRepositoryBean
public interface SoftDeleteJpaRepository<T extends Base, ID extends Long> extends JpaRepository<T, ID> {

  @Override
  @Transactional
  @Modifying
  @Query("update #{#entityName} e set e.isActive=false where e.id = ?1")
  void deleteById(Long id);

  @Override
  @Transactional
  @Modifying
  default void delete(T entity) {
    Assert.notNull(entity, "The entity must not be null!");
    deleteById(entity.getId());
  }

  @Override
  @Transactional
  @Modifying
  default void deleteAll(Iterable<? extends T> entities) {
    Assert.notNull(entities, "The given Iterable of entities not be null!");
    entities.forEach(this::delete);
  }

  @Override
  @Transactional
  @Modifying
  @Query("update #{#entityName} e set e.isActive=false")
  void deleteAll();
}
