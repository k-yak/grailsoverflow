<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Database Info</title>
</head>
<body>

<h1>Connection Meta Data</h1>
<table border="1">
    <tr>
        <td>databaseProductName</td>
        <td>${databaseMetaData.getDatabaseProductName()}</td>
    </tr>
    <tr>
        <td>databaseProductVersion</td>
        <td>${databaseMetaData.getDatabaseProductVersion()}</td>
    </tr>
    <tr>
        <td>JdbcDriverName</td>
        <td>${databaseMetaData.getDriverName()}</td>
    </tr>
    <tr>
        <td>JdbcDriverVersion</td>
        <td>${databaseMetaData.getDriverVersion()}</td>
    </tr>
    <tr>
        <td>JdbcUrl</td>
        <td>${databaseMetaData.getURL()}</td>
    </tr>
    <tr>
        <td>DefaultTransactionIsolation</td>
        <td>${databaseMetaData.getDefaultTransactionIsolation()}</td>
    </tr>
    <tr>
        <td>MaxConnections</td>
        <td>${databaseMetaData.getMaxConnections()}</td>
    </tr>
    <tr>
        <td>ResultSetHoldability</td>
        <td>${databaseMetaData.getResultSetHoldability()}</td>
    </tr>
</table>
</body>
</html>