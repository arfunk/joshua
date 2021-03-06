/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.joshua.decoder;

import org.apache.joshua.decoder.ff.lm.KenLM;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kellen Sunderland
 */
public class LanguageModelStateManager {

  private Map<UUID, LmPool> languageModelPoolMapping = new HashMap<>();

  public LmPool getStatePool(UUID languageModelId, KenLM languageModel) {
    LmPool statePool = languageModelPoolMapping.get(languageModelId);
    if (statePool == null) {
      statePool = languageModel.createLMPool();
      languageModelPoolMapping.put(languageModelId, statePool);
    }
    return statePool;
  }

  public void clearStatePool() {
    languageModelPoolMapping.values().forEach(LmPool::close);
    languageModelPoolMapping.clear();
  }
}
