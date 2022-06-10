package com.fleetmanagement.fleetmanagementservice.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hakan.dagdelen
 */
@Slf4j
public class GsonUtil {

  private static final Gson gson = new GsonBuilder().setPrettyPrinting()
                                                    .create();

  private GsonUtil() {
  }

  public static <T> String toJson(T objInstance) {
    try {
      return gson.toJson(objInstance);
    } catch (Exception e) {
      log.error("Exception occurred at toJson objInstance:{}", objInstance, e);
    }
    return "";
  }
}