package falcon.springpractice.service;

import falcon.springpractice.beans.pipeline.PipelineRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PipelineRegistrySelector {
  private final Map<String, PipelineRegistry> registryMap;

  public PipelineRegistry getRegistry(String registryName) {

    registryMap.forEach((key, value)-> System.out.println("key: " + key + " value: " + value));

    return this.registryMap.get(registryName);
  }
}
