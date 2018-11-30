package com.joelseidel.java_datatable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel Seidel
 *
 * Data table object
 * Allows for interacting with data from MySQL queries in a data table similar to the .NET datatable
 */

public class DataTable {
    //Table data attributes
    private String tableName;
    private String schemaName;
    private String catalogName;

    //Column and row lists
    private List<TableColumn> columns;
    private List<TableRow> rows;

    /**
     * Create a data table from a result set
     * @param resultSet result set to be converted into a datatable
     * @throws SQLException sql exception that may be thrown while converting
     */
    public DataTable(ResultSet resultSet) throws SQLException {
        //Initialize the local column list
        columns = new ArrayList<TableColumn>();
        //Initialize the local row list
        rows = new ArrayList<TableRow>();
        //Parse the table data from the result set
        parseTableSchema(resultSet);
        //Parse the data from rows of the result set
        parseRows(resultSet);
    }

    /**
     * Constructor to create an empty data table with known table data
     * @param tableName name of the table
     * @param schemaName name of the table schema
     * @param catalogName name of the table catalog
     */
    public DataTable(String tableName, String schemaName, String catalogName){
        this.tableName = tableName;
        this.schemaName = schemaName;
        this.catalogName = catalogName;
        //Initialize the local column list
        columns = new ArrayList<TableColumn>();
        //Initialize the local row list
        rows = new ArrayList<TableRow>();
    }

    /**
     * Constructor to create an empty data table with only the table name
     * @param tableName name of the table
     */
    public DataTable(String tableName) {
        this.tableName = tableName;
        this.schemaName = "";
        this.catalogName = "";
        //Initialize the local column list
        columns = new ArrayList<TableColumn>();
        //Initialize the local row list
        rows = new ArrayList<TableRow>();
    }

    /**
     * Default constructor to create an empty data table
     */
    public DataTable() {
        //Initialize the local column list
        columns = new ArrayList<TableColumn>();
        //Initialize the local row list
        rows = new ArrayList<TableRow>();
    }

    /**
     * Get a field by column and row index (direct address within table)
     * @param columnIndex 0 based index of the column
     * @param rowIndex 0 based index of the row
     * @return com.joelseidel.javadatatable.Field object located at specified indices
     * @throws IndexOutOfBoundsException thrown when attempting to access an index address that is outside of the table bounds
     */
    public Field getField(int columnIndex, int rowIndex) throws IndexOutOfBoundsException{
        //Get specified field within the specified row (retrieved by index)
        return rows.get(rowIndex).getField(columnIndex);
    }

    /**
     * Get a field by column name and row index
     * @param columnName name of the column
     * @param rowIndex 0 based index of the row
     * @return com.joelseidel.javadatatable.Field object located at the specified location
     * @throws IndexOutOfBoundsException thrown when attempting to access an index address that is outside of the table bounds
     */
    public Field getField(String columnName, int rowIndex) throws IndexOutOfBoundsException{
        //Get the index of the column by name that was specified
        int columnIndex = getColumnIndexByName(columnName);
        //Throw exception if the column name cannot be found within the table
        if(columnIndex == -1 ){
            throw new IndexOutOfBoundsException();
        }
        //Get specified field with indices address
        return getField(columnIndex, rowIndex);
    }

    /**
     * Get the value object of a field by column and row index (direct address within table)
     * @param columnIndex 0 based index of the column
     * @param rowIndex 0 based index of the row
     * @return Value object of the field located at the specified indices
     * @throws IndexOutOfBoundsException thrown when attempting to access an index address that is outside of the table bounds
     */
    public Object getFieldValue(int columnIndex, int rowIndex) throws IndexOutOfBoundsException{
        //Get the specified field value object within the specified row (retrieved by index)
        return rows.get(rowIndex).getField(columnIndex).getValue();
    }

    /**
     * Get the value object of a field by column name and row index
     * @param columnName name of the column
     * @param rowIndex 0 based index of the row
     * @return Value object of the field located at the specified location
     * @throws IndexOutOfBoundsException thrown when attempting to access an index address that is outside of the table bounds
     */
    public Object getFieldValue(String columnName, int rowIndex) throws IndexOutOfBoundsException{
        //Get the index of the column by name that was specified
        int columnIndex = getColumnIndexByName(columnName);
        //Throw exception if the column name cannot be found within the table
        if(columnIndex == -1){
            throw new IndexOutOfBoundsException();
        }
        //Get specified field with indices address
        return getFieldValue(columnIndex, rowIndex);
    }

    /**
     * Get the table row object of the row at the specified index
     * @param index 0 based index of the row
     * @return Table row object of the row at the specified index
     */
    public TableRow getRow(int index){
        //Get the row at the specified index
        return rows.get(index);
    }

    /**
     * Get the tables within a specified range
     * @param lowerBound the starting index of the row range
     * @param upperBound the ending index of the row range
     * @return a list of table row objects containing the rows within the range
     */
    public List<TableRow> getRowRange(int lowerBound, int upperBound){
        List<TableRow> rowsInRange = new ArrayList<TableRow>();
        //Add each of the rows within the index range to the row range list
        for(int i = lowerBound; i <= upperBound; i++){
            rowsInRange.add(rows.get(i));
        }
        return rowsInRange;
    }

    /**
     * Get the index of a column with a specified name
     * @param columnName name of the column
     * @return index of the column with the specified name
     */
    public int getColumnIndexByName(String columnName){
        //Find the index of the column with name matching the specified column name
        for(int i = 0; i < columns.size(); i++){
            if(columns.get(i).getName().equals(columnName)){
                //Column name matches the specified name, return this index
                return i;
            }
        }
        //The specified column name was not found, return -1 as the index
        return -1;
    }

    /**
     * Import columns into the data table
     * @param columns columns to import into the data table
     */
    public void importColumns(List<TableColumn> columns){
        this.columns = columns;
    }

    /**
     * Append columns onto the existing columns within the datatable
     * @param columns columns to append onto the data table
     */
    public void appendColumns(List<TableColumn> columns){
        this.columns.addAll(columns);
    }

    /**
     * Add a column to the table
     * @param column column added to the table
     */
    public void addColumn(TableColumn column){
        this.columns.add(column);
    }

    /**
     * Add a column to the table by column data type and name
     * @param name column name
     * @param dataType column data type class name
     */
    public void addColumn(String name, COLUMN_DATA_TYPE dataType){
        this.columns.add(new TableColumn(dataType, name));
    }

    /**
     * Parse the result set from the result set meta data and create the list of columns
     * @param resultSet result set to parse
     * @throws SQLException thrown if result set is null or is not valid
     */
    private void parseTableSchema(ResultSet resultSet) throws SQLException {
        //Get meta data from result set object
        ResultSetMetaData metaData = resultSet.getMetaData();
        //Get the table data from the meta data object using the first column
        getTableData(metaData);
        //Get the column data from the result set meta data
        getColumns(metaData);
    }

    /**
     * Parse the rows from the result set into table rows
     * @param resultSet result set to parse
     * @throws SQLException thrown if rows are invalid
     */
    private void parseRows(ResultSet resultSet) throws SQLException{
        //Convert each result set record into a table row
        while(resultSet.next()){
            //Create a local table row
            TableRow thisRow = new TableRow();
            //Create a field for each of the columns within this row
            for (int i = 0; i < columns.size(); i++) {
                //Add the field of the current column within the current row
                thisRow.addField(columns.get(i), resultSet.getObject(i + 1));
            }
            //Add this row to the local row list
            this.rows.add(thisRow);
        }
    }

    /**
     * Get table data from result set meta data
     * @param metaData meta data object from result set
     * @throws SQLException thrown if meta data is invalid
     */
    private void getTableData(ResultSetMetaData metaData) throws SQLException {
        //Get table data from the result set meta data object
        this.tableName = metaData.getTableName(1);
        this.schemaName = metaData.getSchemaName(1);
        this.catalogName = metaData.getCatalogName(1);
    }

    /**
     * Parse the columns from the result set meta data into table columns
     * @param metaData meta data object from result set
     * @throws SQLException thrown if meta data is invalid
     */
    private void getColumns(ResultSetMetaData metaData) throws SQLException {
        //Create a table column object within the column count of the meta data
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            //Create a table column object using column data from the meta data
            TableColumn thisColumn = new TableColumn(metaData.getColumnClassName(i), metaData.getColumnDisplaySize(i), metaData.getColumnLabel(i), metaData.getColumnName(i), metaData.getColumnType(i), metaData.getColumnTypeName(i), metaData.getPrecision(i), metaData.getScale(i), metaData.isAutoIncrement(i), metaData.isCaseSensitive(i), metaData.isCurrency(i), metaData.isDefinitelyWritable(i), metaData.isNullable(i), metaData.isReadOnly(i), metaData.isSearchable(i), metaData.isSigned(i), metaData.isWritable(i));
            //Add this column to the local column list
            this.columns.add(thisColumn);
        }
    }

    /**
     * Get the column at the specified index
     * @param index index of the column
     * @return the column at the specified index
     */
    public TableColumn getColumn(int index){
        return this.columns.get(index);
    }

    /**
     * Get the column with the specified column name
     * @param columnName the column with the column name
     * @return the column with the specified column name
     */
    public TableColumn getColumn(String columnName){
        return getColumn(getColumnIndexByName(columnName));
    }

    /**
     * Get the list of all table columns
     * @return list of all table columns
     */
    public List<TableColumn> getColumns(){
        return this.columns;
    }

    /**
     * Get the count of table columns
     * @return count of table columns
     */
    public int getColumnCount(){
        return this.columns.size();
    }

    /**
     * Get the list of all table rows
     * @return list of all table rows
     */
    public List<TableRow> getRows(){
        return this.rows;
    }

    /**
     * Get the count of table rows
     * @return count of table rows
     */
    public int getRowCount(){
        return this.rows.size();
    }

    /**
     * Get the table name
     * @return table name
     */
    public String getTableName(){
        return this.tableName;
    }

    /**
     * Get the schema name
     * @return schema name
     */
    public String getSchemaName(){
        return this.schemaName;
    }

    /**
     * Get the catalog name
     * @return catalog name
     */
    public String getCatalogName(){
        return this.catalogName;
    }
}
