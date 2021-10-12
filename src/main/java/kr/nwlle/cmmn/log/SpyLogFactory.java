/**
 * Copyright 2007-2010 Arthur Blake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.nwlle.cmmn.log;

/**
 * A provider for a SpyLogDelegator.  This allows a single switch point to abstract
 * away which logging system to use for spying on JDBC calls.
 *
 * The SLF4J logging facade is used, which is a very good general purpose facade for plugging into
 * numerous java logging systems, simply and easily.
 *
 * @author Arthur Blake
 * @author Tim Azzopardi Allow the SpyLogDelegator to be set
 */
public class SpyLogFactory
{
  /**
   * Do not allow instantiation.  Access is through static method.
   */
  private SpyLogFactory() {}

  /**
   * The logging system of choice.
   */
  private static SpyLogDelegator logger = new Slf4jSpyLogDelegator();
  //new Log4jSpyLogDelegator();

  /**
   * Get the default SpyLogDelegator for logging to the logger.
   *
   * @return the default SpyLogDelegator for logging to the logger.
   */
  public static SpyLogDelegator getSpyLogDelegator()
  {
    return logger;
  }
  
  /**
   * Optionally override the {@link SpyLogDelegator} implementation  
   * and thus take more control over the logging.<br/>
   * Note: It is the caller's responsibility to make sure this is set before
   * JDBC activity occurs and to ensure thread safety.
   * 
   * @param logDelegator the log delegator responsible for actually logging
   * JDBC events.
   */
  public static void setSpyLogDelegator(SpyLogDelegator logDelegator) {
    if (logDelegator == null) {
      throw new IllegalArgumentException("log4jdbc: logDelegator cannot be null.");
    }
    logger = logDelegator;
  }
  
}