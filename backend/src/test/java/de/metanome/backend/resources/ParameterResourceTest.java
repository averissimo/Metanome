/*
 * Copyright 2014 by the Metanome project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.metanome.backend.resources;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.configuration.ConfigurationRequirement;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ParameterResourceTest {

  @Test
  public void testRetrieveUniqueColumnCombinationsParameters() throws AlgorithmExecutionException {
    //Setup
    ParameterResource parameterService = new ParameterResource();

    //Execute
    List<ConfigurationRequirement<?>>
      inputParameters =
      parameterService.retrieveParameters("example_ucc_algorithm.jar");

    //Check
    assertNotNull(inputParameters);
    assertFalse(inputParameters.isEmpty());
    assertNotNull(inputParameters.get(0));
  }
}
