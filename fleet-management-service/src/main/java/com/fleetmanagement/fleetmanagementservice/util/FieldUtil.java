package com.fleetmanagement.fleetmanagementservice.util;

import com.fleetmanagement.fleetmanagementservice.annotation.Crop;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * @author hakan.dagdelen
 */
@Slf4j
public class FieldUtil {

  private FieldUtil() {
  }

  public static void cropValueWithAnnotation(Object entity) {
    FieldUtils.getFieldsListWithAnnotation(entity.getClass(), Crop.class).forEach(field -> {
      field.setAccessible(true);
      try {
        int maxSize = field.getAnnotation(Crop.class).value();
        Object fieldValue = field.get(entity);
        if (Objects.isNull(fieldValue)) {
          return;
        }
        field.set(entity, StringUtil.cropAtMaxSize(fieldValue.toString(), maxSize));
      } catch (IllegalAccessException e) {
        log.info("Exception occurred while cropping field: {}; ", field.getName(), e);
      }
    });
  }
}
