public class Field {
    private TableColumn column;
    private Object value;

    public Field(TableColumn column, Object value){
        this.column = column;
        this.value = value;
    }

    public Object getValue(){
        return this.value;
    }
}
