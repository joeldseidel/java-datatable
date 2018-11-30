import java.util.List;

public class TableRow {
    private List<Field> fields;

    public TableRow(List<Field> fields){
        this.fields = fields;
    }

    public List<Field> getFields(){
        return this.fields;
    }
}
