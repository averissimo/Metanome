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

package de.metanome.algorithm_integration;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * Test for {@link de.metanome.algorithm_integration.AlgorithmExecutionException}
 *
 * @author Jakob Zwiener
 */
public class AlgorithmExecutionExceptionTest {

  /**
   * Test method for {@link de.metanome.algorithm_integration.AlgorithmExecutionException#AlgorithmExecutionException(String)}
   * <p/> The exception should store the message.
   */
  @Test
  public void testAlgorithmExecutionExceptionString() {
    // Expected values
    String expectedMessage = "some message";

    // Execute functionality
    String actualMessage;
    try {
      throw new AlgorithmExecutionException(expectedMessage);
    } catch (AlgorithmExecutionException e) {
      actualMessage = e.getMessage();
    }

    // Check result
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test method for {@link de.metanome.algorithm_integration.AlgorithmExecutionException#AlgorithmExecutionException(String,
   * Throwable)}
   * <p/>
   * The exception should store the message and the cause.
   */
  @Test
  public void testAlgorithmExecutionExceptionStringThrowable() {
    // Setup
    // Expected values
    String expectedMessage = "some message";
    Throwable expectedCause = new FileNotFoundException("some file was not found");

    // Execute functionality
    String actualMessage;
    Throwable actualCause;
    try {
      throw new AlgorithmExecutionException(expectedMessage, expectedCause);
    } catch (AlgorithmExecutionException e) {
      actualMessage = e.getMessage();
      actualCause = e.getCause();
    }

    // Check result
    assertEquals(expectedMessage, actualMessage);
    assertEquals(expectedCause, actualCause);
  }

}
