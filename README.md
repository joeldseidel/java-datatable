# A Data Table Object For Java

In the words of me many times:
>Result sets are stupid and dumb and I hate them

This library provides functionality for converting a ResultSet to an object which can be accessed and interacted with like a table. This functionality is similar to the DataTable object within the .NET framework System.Data library.

## Using the DataTable

**Note:** the row and column indices are 0 based

## Creating a new DataTable 
```
//Connect to database
//Create and prepare a prepared statement
DataTable dataTable = new DataTable(preparedStatement.executeQuery());
```

## Accessing a field by row and column index or name
```
//Get the field located at column 1 row 2 
Field fieldAtIndex = dataTable.getField(1, 2);
//Get the field at column named 'email' and row 2
Field fieldAtName = dataTable.getField("email", 2);
```

## Accessing a field value
```
//Get the value of the field located at column 1 row 2
Object fieldValueAtIndex = dataTable.getFieldValue(1, 2);
//Get the value of the field located at column named 'email' and row 2
Object fieldValueAtName = dataTable.getFieldValue("email", 2);
```

## Accessing a row by index
```
//Get the second row of the table
TableRow row = dataTable.getRow(1);
```

## Access several consecutive rows
```
//Get the second through the fifth rows of the table
List<TableRow> rows = dataTable.getRowRange(1, 4);
```

## Get index of column by name
```
//Get the index within a row of the column named 'email'
int indexOfEmail = dataTable.getColumnIndexByName("email");
```