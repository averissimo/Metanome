/*
 * Copyright 2015 by the Metanome project
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

package de.metanome.backend.result_postprocessing.result_analyzer;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithm_integration.results.BasicStatistic;
import de.metanome.backend.result_postprocessing.result_ranking.BasicStatisticRanking;
import de.metanome.backend.result_postprocessing.results.BasicStatisticResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Analyzes Basic Statistic Results.
 */
public class BasicStatisticResultAnalyzer
  extends ResultAnalyzer<BasicStatistic, BasicStatisticResult> {

  public BasicStatisticResultAnalyzer(
    List<RelationalInputGenerator> inputGenerators,
    boolean useDataIndependentStatistics)
    throws InputGenerationException, InputIterationException, AlgorithmConfigurationException {
    super(inputGenerators, useDataIndependentStatistics);
  }

  @Override
  protected List<BasicStatisticResult> analyzeResultsDataIndependent(
    List<BasicStatistic> prevResults) {
    List<BasicStatisticResult> results = convertResults(prevResults);

//    try {
//      if (!this.tableInformationMap.isEmpty()) {
//        BasicStatisticRanking
//            ranking =
//            new BasicStatisticRanking(results, tableInformationMap);
//        ranking.calculateDataIndependentRankings();
//      }
//    } catch (Exception e) {
//      // Could not analyze results due to error
//    }

    return results;
  }

  @Override
  protected List<BasicStatisticResult> analyzeResultsDataDependent(
    List<BasicStatistic> prevResults) {
    List<BasicStatisticResult> results = convertResults(prevResults);

    try {
      if (!this.tableInformationMap.isEmpty()) {
        BasicStatisticRanking
          ranking =
          new BasicStatisticRanking(results, tableInformationMap);
        ranking.calculateDataDependentRankings();
      }
    } catch (Exception e) {
      // Could not analyze results due to error
    }

    return results;
  }

  @Override
  protected List<BasicStatisticResult> convertResults(List<BasicStatistic> prevResults) {
    List<BasicStatisticResult> results = new ArrayList<>();

    for (BasicStatistic prevResult : prevResults) {
      BasicStatisticResult result = new BasicStatisticResult(prevResult);
      results.add(result);
    }

    return results;
  }


}
