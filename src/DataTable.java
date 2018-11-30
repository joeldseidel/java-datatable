import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class DataTable {
    private String tableName;
    private String schemaName;
    private String catalogName;
    private List<TableColumn> columns;
    private List<TableRow> rows;

    DataTable(ResultSet resultSet) throws SQLException {
        parseTableSchema(resultSet);
        parseRows(resultSet);
    }

    private Field getField(int columnIndex, int rowIndex) throws IndexOutOfBoundsException{
        return rows.get(rowIndex).getField(columnIndex);
    }

    private Field getField(String columnName, int rowIndex) throws IndexOutOfBoundsException{
        int columnIndex = getColumnIndexByName(columnName);
        if(columnIndex == -1 ){
            throw new IndexOutOfBoundsException();
        }
        return getField(columnIndex, rowIndex);
    }

    private Object getFieldValue(int columnIndex, int rowIndex) throws IndexOutOfBoundsException{
        return rows.get(rowIndex).getField(columnIndex).getValue();
    }

    private Object getFieldValue(String columnName, int rowIndex) throws IndexOutOfBoundsException{
        int columnIndex = getColumnIndexByName(columnName);
        if(columnIndex == -1){
            throw new IndexOutOfBoundsException();
        }
        return getFieldValue(columnIndex, rowIndex);
    }

    private TableRow getRow(int index){
        return rows.get(index);
    }

    private List<TableRow> getRowRange(int lowerBound, int upperBound){
        List<TableRow> rowsInRange = new ArrayList<TableRow>();
        for(int i = lowerBound; i <= upperBound; i++){
            rowsInRange.add(rows.get(i));
        }
        return rowsInRange;
    }

    private int getColumnIndexByName(String columnName){
        for(int i = 0; i < columns.size(); i++){
            if(columns.get(i).getName().equals(columnName)){
                return i;
            }
        }
        return -1;
    }

    private void parseTableSchema(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        this.tableName = metaData.getTableName(1);
        this.schemaName = metaData.getSchemaName(1);
        this.catalogName = metaData.getCatalogName(1);
        getTableData(metaData);
        getColumns(metaData);
    }

    private void parseRows(ResultSet resultSet) throws SQLException{
        rows = new ArrayList<TableRow>();
        while(resultSet.next()){
            TableRow thisRow = new TableRow();
            for (int i = 0; i < columns.size(); i++) {
                thisRow.addField(columns.get(i), resultSet.getObject(i + 1));
            }
            this.rows.add(thisRow);
        }
    }

    private void getTableData(ResultSetMetaData metaData) throws SQLException {
        this.tableName = metaData.getTableName(1);
        this.schemaName = metaData.getSchemaName(1);
        this.catalogName = metaData.getCatalogName(1);
    }

    private void getColumns(ResultSetMetaData metaData) throws SQLException {
        columns = new ArrayList<TableColumn>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            TableColumn thisColumn = new TableColumn(metaData.getColumnClassName(i), metaData.getColumnDisplaySize(i), metaData.getColumnLabel(i), metaData.getColumnName(i), metaData.getColumnType(i), metaData.getColumnTypeName(i), metaData.getPrecision(i), metaData.getScale(i), metaData.isAutoIncrement(i), metaData.isCaseSensitive(i), metaData.isCurrency(i), metaData.isDefinitelyWritable(i), metaData.isNullable(i), metaData.isReadOnly(i), metaData.isSearchable(i), metaData.isSigned(i), metaData.isWritable(i));
            this.columns.add(thisColumn);
        }
    }

    public List<TableColumn> getColumns(){
        return this.columns;
    }

    public int getColumnCount(){
        return this.columns.size();
    }

    public List<TableRow> getRows(){
        return this.rows;
    }

    public int getRowCount(){
        return this.rows.size();
    }
}
