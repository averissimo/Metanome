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

package de.metanome.algorithm_integration.results;

import de.metanome.algorithm_integration.ColumnCombination;
import de.metanome.algorithm_integration.ColumnIdentifier;
import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Test for {@link FunctionalDependency}
 *
 * @author Jakob Zwiener
 */
public class FunctionalDependencyTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for {@link FunctionalDependency#sendResultTo(OmniscientResultReceiver)} <p/> The
   * {@link FunctionalDependency} should be sendable to the {@link OmniscientResultReceiver}.
   */
  @Test
  public void testSendResultTo() throws CouldNotReceiveResultException, ColumnNameMismatchException {
    // Setup
    OmniscientResultReceiver resultReceiver = mock(OmniscientResultReceiver.class);
    FunctionalDependency
      fd =
      new FunctionalDependency(mock(ColumnCombination.class), mock(ColumnIdentifier.class));

    // Execute functionality
    fd.sendResultTo(resultReceiver);

    // Check result
    verify(resultReceiver).receiveResult(fd);
  }

  /**
   * Test method for {@link FunctionalDependency#FunctionalDependency(ColumnCombination,
   * ColumnIdentifier)} <p/> A {@link FunctionalDependency} should store the determinant {@link
   * ColumnCombination} and the dependant {@link ColumnIdentifier}.
   */
  @Test
  public void testConstructor() {
    // Setup
    // Expected values
    ColumnCombination
      expectedDeterminant =
      new ColumnCombination(new ColumnIdentifier("table1", "column1"),
        new ColumnIdentifier("table2", "column2"));
    ColumnIdentifier expectedDependant = new ColumnIdentifier("table1", "column7");
    // Execute functionality
    FunctionalDependency
      functionalDependency =
      new FunctionalDependency(expectedDeterminant, expectedDependant);

    // Check result
    assertEquals(expectedDeterminant, functionalDependency.getDeterminant());
    assertEquals(expectedDependant, functionalDependency.getDependant());
  }

  /**
   * Test method for {@link FunctionalDependency#toString()} <p/> A {@link FunctionalDependency}
   * should return a human readable string representation.
   */
  @Test
  public void testToString() {
    // Setup
    ColumnCombination
      expectedDeterminant =
      new ColumnCombination(new ColumnIdentifier("table1", "column1"),
        new ColumnIdentifier("table2", "column2"));
    ColumnIdentifier expectedDependant = new ColumnIdentifier("table1", "column7");
    FunctionalDependency
      functionalDependency =
      new FunctionalDependency(expectedDeterminant, expectedDependant);
    // Expected values
    String
      expectedStringRepresentation =
      expectedDeterminant + FunctionalDependency.FD_SEPARATOR + expectedDependant;

    // Execute functionality
    // Check result
    assertEquals(expectedStringRepresentation, functionalDependency.toString());
  }

  /**
   * Test method for {@link FunctionalDependency#toString()} <p/> A {@link FunctionalDependency}
   * should return a human readable string representation.
   */
  @Test
  public void testToFromStringWithMapping() {
    // Setup
    Map<String, String> tableMappingTo = new HashMap<>();
    tableMappingTo.put("table1", "1");
    tableMappingTo.put("table2", "2");
    Map<String, String> columnMappingTo = new HashMap<>();
    columnMappingTo.put("1.column1", "1");
    columnMappingTo.put("2.column2", "2");
    columnMappingTo.put("1.column7", "3");
    Map<String, String> tableMappingFrom = new HashMap<>();
    tableMappingFrom.put("1", "table1");
    tableMappingFrom.put("2", "table2");
    Map<String, String> columnMappingFrom = new HashMap<>();
    columnMappingFrom.put("1", "1.column1");
    columnMappingFrom.put("2", "2.column2");
    columnMappingFrom.put("3", "1.column7");

    ColumnCombination determinant = new ColumnCombination(new ColumnIdentifier("table1", "column1"),
        new ColumnIdentifier("table2", "column2"));
    ColumnIdentifier dependant = new ColumnIdentifier("table1", "column7");

    // Expected values
    FunctionalDependency expectedFD = new FunctionalDependency(determinant, dependant);
    String expectedString = "1,2->3";

    // Execute functionality
    String actualString = expectedFD.toString(tableMappingTo, columnMappingTo);
    FunctionalDependency actualFD = FunctionalDependency.fromString(tableMappingFrom, columnMappingFrom, expectedString);

    // Check result
    assertEquals(expectedString, actualString);
    assertEquals(expectedFD, actualFD);
  }

  /**
   * Test method for {@link FunctionalDependency#equals(Object)} and {@link
   * FunctionalDependency#hashCode()} <p/> {@link FunctionalDependency}s with equal determinant and
   * dependants should be equal.
   */
  @Test
  public void testEqualsHashCode() {
    // Setup
    FunctionalDependency expectedFd = new FunctionalDependency(
      new ColumnCombination(
        new ColumnIdentifier("table1", "column2")),
      new ColumnIdentifier("table1", "column47")
    );
    FunctionalDependency expectedEqualFd = new FunctionalDependency(
      new ColumnCombination(
        new ColumnIdentifier("table1", "column2")),
      new ColumnIdentifier("table1", "column47")
    );
    FunctionalDependency expectedNotEqualDeterminantFd = new FunctionalDependency(
      new ColumnCombination(
        new ColumnIdentifier("table1", "column4")),
      new ColumnIdentifier("table1", "column47")
    );
    FunctionalDependency expectedNotEqualDependantFd = new FunctionalDependency(
      new ColumnCombination(
        new ColumnIdentifier("table1", "column2")),
      new ColumnIdentifier("table1", "column57")
    );

    // Execute functionality
    // Check result
    assertEquals(expectedFd, expectedFd);
    assertEquals(expectedFd.hashCode(), expectedFd.hashCode());
    assertNotSame(expectedFd, expectedEqualFd);
    assertEquals(expectedFd, expectedEqualFd);
    assertEquals(expectedFd.hashCode(), expectedEqualFd.hashCode());
    assertNotEquals(expectedFd, expectedNotEqualDeterminantFd);
    assertNotEquals(expectedFd.hashCode(), expectedNotEqualDeterminantFd.hashCode());
    assertNotEquals(expectedFd, expectedNotEqualDependantFd);
    assertNotEquals(expectedFd.hashCode(), expectedNotEqualDependantFd.hashCode());
  }


}
