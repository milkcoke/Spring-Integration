package falcon.springpractice.service;

import falcon.springpractice.beans.schema.SchemaRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SchemaRegistrySelector {
  private final Map<String, SchemaRegistry> registryMap;

  public SchemaRegistry getRegistry(String registryName) {

    registryMap.forEach((key, value)-> System.out.println("key: " + key + " value: " + value));

    return this.registryMap.get(registryName);
  }
}
