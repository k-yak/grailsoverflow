package fr.isima.grailsoverflow

import javax.sql.DataSource
import java.sql.Connection
import java.sql.DatabaseMetaData
import java.sql.ResultSet
import java.sql.Statement

class DatabaseController {

    def DataSource dataSource

    def index() {
        Connection connection = dataSource.getConnection()

        DatabaseMetaData databaseMetaData = connection.getMetaData()

        Statement statement = connection.createStatement()
        ResultSet rst = statement.executeQuery("select 1")

        rst.next()

        String value = rst.getString(1)
        print("dataSource: $dataSource, value: $value")


        rst.close()
        statement.close()
        connection.close()
        return [databaseMetaData:databaseMetaData]

    }
}
