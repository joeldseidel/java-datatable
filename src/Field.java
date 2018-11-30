public class Field {
    private TableColumn column;
    private Object value;

    Field(TableColumn column, Object value){
        this.column = column;
        this.value = value;
    }

    public Object getValue(){
        return this.value;
    }

    public TableColumn getColumn(){
        return this.column;
    }
}
