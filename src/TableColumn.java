public class TableColumn {
    private String name;
    private String dataType;

    public TableColumn(String name, String dataType){
        this.name = name;
        this.dataType = dataType;
    }

    public String getName(){
        return this.name;
    }
    public String getDataType(){
        return this.dataType;
    }
}