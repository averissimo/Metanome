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

package de.metanome.backend.input.file;

import de.metanome.algorithm_integration.configuration.ConfigurationSettingFileInput;
import de.metanome.algorithm_integration.input.InputIterationException;

import java.io.StringReader;

/**
 * A fixture generating a file file with a header and a short line.
 *
 * @author Jakob Zwiener
 */
public class CsvFileShortLineWithHeaderFixture {

  protected static final char QUOTE_CHAR = '\'';
  protected static final char SEPARATOR = ',';
  protected static final char ESCAPE = '\\';
  protected static final boolean STRICT_QUOTES = false;
  protected static final boolean IGNORE_LEADING_WHITESPACES = true;
  protected static final boolean SKIP_DIFFERING_LINES = false;
  protected static final boolean HAS_HEADER = true;
  protected static final int SKIP_LINES = 0;

  public FileIterator getTestData() throws InputIterationException {
    ConfigurationSettingFileInput setting = new ConfigurationSettingFileInput("some_file")
      .setSeparatorChar(String.valueOf(SEPARATOR))
      .setHeader(HAS_HEADER)
      .setIgnoreLeadingWhiteSpace(IGNORE_LEADING_WHITESPACES)
      .setStrictQuotes(STRICT_QUOTES)
      .setEscapeChar(String.valueOf(ESCAPE))
      .setQuoteChar(String.valueOf(QUOTE_CHAR))
      .setSkipLines(SKIP_LINES)
      .setSkipDifferingLines(SKIP_DIFFERING_LINES);

    return new FileIterator("some_file",
      new StringReader("headerOne,headerTwo,headerThree\nfour,five\n"),
      setting);
  }
}
