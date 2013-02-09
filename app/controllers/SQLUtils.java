package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SQLUtils {
  private static Log log = LogFactory.getLog(SQLUtils.class);

  public static void closeQuietly(Connection connection){
    try{
      if (connection != null)
      {
        connection.close();
      }
    }
    catch (SQLException e)
    {
      log.error("An error occurred closing connection.", e);
    }
  }

  public static void closeQuietly(Statement statement)
  {
    try
    {
      if (statement!= null)
      {
        statement.close();
      }
    }
    catch (SQLException e)
    {
      log.error("An error occurred closing statement.", e);
    }
  }

  public static void closeQuietly(ResultSet resultSet)
  {
    try
    {
      if (resultSet!= null)
      {
        resultSet.close();
      }
    }
    catch (SQLException e)
    {
      log.error("An error occurred closing result set.", e);
    }
  }
}