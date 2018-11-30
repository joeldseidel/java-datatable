import java.util.ArrayList;
import java.util.List;

public class TableRow {
    private List<Field> fields;

    public TableRow(){ fields = new ArrayList<Field>(); }

    public void addField(TableColumn column, Object value){
        Field thisField = new Field(column, value);
        this.fields.add(thisField);
    }

    public List<Field> getFields(){
        return this.fields;
    }

    public Field getField(int index){
        return this.fields.get(index);
    }
}
