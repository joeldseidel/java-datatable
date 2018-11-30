import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class DataTable {
    private String tableName;
    private String schemaName;
    private String catalogName;
    private List<TableColumn> columns;
    private List<TableRow> rows;

    public DataTable(ResultSet resultSet) throws SQLException {
        parseTableSchema(resultSet);
    }

    private void parseTableSchema(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        this.tableName = metaData.getTableName(0);
        this.schemaName = metaData.getSchemaName(0);
        this.catalogName = metaData.getCatalogName(0);
        getTableData(metaData);
        getColumns(metaData);
    }

    private void getTableData(ResultSetMetaData metaData) throws SQLException {
        this.tableName = metaData.getTableName(0);
        this.schemaName = metaData.getSchemaName(0);
        this.catalogName = metaData.getCatalogName(0);
    }

    private void getColumns(ResultSetMetaData metaData) throws SQLException {
        for(int i = 0; i < metaData.getColumnCount(); i++){
            TableColumn thisColumn = new TableColumn(metaData.getColumnClassName(i), metaData.getColumnDisplaySize(i), metaData.getColumnLabel(i), metaData.getColumnName(i), metaData.getColumnType(i), metaData.getColumnTypeName(i), metaData.getPrecision(i), metaData.getScale(i), metaData.isAutoIncrement(i), metaData.isCaseSensitive(i), metaData.isCurrency(i), metaData.isDefinitelyWritable(i), metaData.isNullable(i), metaData.isReadOnly(i), metaData.isSearchable(i), metaData.isSigned(i), metaData.isWritable(i));
            this.columns.add(thisColumn);
        }
    }

    private List<TableColumn> getColumns(){
        return this.columns;
    }

    private List<TableRow> getRows(){
        return this.rows;
    }
}
