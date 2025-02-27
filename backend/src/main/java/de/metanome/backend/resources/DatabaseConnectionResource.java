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

import de.metanome.backend.results_db.DatabaseConnection;
import de.metanome.backend.results_db.HibernateUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Responsible for the database communication for DatabaseConnection and for handling all restful
 * calls of DatabaseConnections.
 *
 * @author Moritz Finke
 */

@Path("database-connections")
public class DatabaseConnectionResource implements Resource<DatabaseConnection> {

  /**
   * @return all DatabaseConnections in the database
   */
  @GET
  @Produces("application/json")
  @SuppressWarnings("unchecked")
  @Override
  public List<DatabaseConnection> getAll() {
    try {
      return (List<DatabaseConnection>) HibernateUtil.queryCriteria(DatabaseConnection.class);
    } catch (Exception e) {
      e.printStackTrace();
      throw new WebException(e, Response.Status.BAD_REQUEST);
    }
  }

  /**
   * retrieves a DatabaseConnection
   *
   * @param id the id of the DatabaseConnection
   * @return the retrieved DatabaseConnection
   */
  @GET
  @Path("/get/{id}")
  @Produces("application/json")
  @Override
  public DatabaseConnection get(@PathParam("id") long id) {
    try {
      return (DatabaseConnection) HibernateUtil.retrieve(DatabaseConnection.class, id);
    } catch (Exception e) {
      e.printStackTrace();
      throw new WebException(e, Response.Status.BAD_REQUEST);
    }
  }

  /**
   * Adds a DatabaseConnection to the database.
   *
   * @param dbConnection the database to be stored
   * @return the stored DatabaseConnection
   */
  @POST
  @Path("/store")
  @Consumes("application/json")
  @Produces("application/json")
  @Override
  public DatabaseConnection store(DatabaseConnection dbConnection) {
    try {
      HibernateUtil.store(dbConnection);
      return dbConnection;
    } catch (Exception e) {
      e.printStackTrace();
      throw new WebException(e, Response.Status.BAD_REQUEST);
    }
  }

  /**
   * Deletes the DatabaseConnection, which has the given id, from the database.
   *
   * @param id the id of the DatabaseConnection, which should be deleted
   */
  @DELETE
  @Path("/delete/{id}")
  @Override
  public void delete(@PathParam("id") long id) {
    try {
      DatabaseConnection
        dbConnection =
        (DatabaseConnection) HibernateUtil.retrieve(DatabaseConnection.class, id);
      HibernateUtil.delete(dbConnection);
    } catch (Exception e) {
      e.printStackTrace();
      throw new WebException(e, Response.Status.BAD_REQUEST);
    }
  }

  /**
   * Updates a database connection in the database.
   *
   * @param databaseConnection the database connection
   * @return the updated database connection
   */
  @POST
  @Path("/update")
  @Consumes("application/json")
  @Produces("application/json")
  @Override
  public DatabaseConnection update(DatabaseConnection databaseConnection) {
    try {
      HibernateUtil.update(databaseConnection);
      return databaseConnection;
    } catch (Exception e) {
      e.printStackTrace();
      throw new WebException(e, Response.Status.BAD_REQUEST);
    }
  }

}
