import java.sql.ResultSet;
import java.util.List;

public class DataTable {
    private List<TableColumn> columns;
    private List<TableRow> rows;

    public DataTable(ResultSet resultSet) {
        //Get table schema
        //Parse result set to populate rows
    }

    private List<TableColumn> getColumns(){
        return this.columns;
    }

    private List<TableRow> getRows(){
        return this.rows;
    }
}
