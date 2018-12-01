package com.joelseidel.java_datatable;

/**
 * @author Joel Seidel
 */

public class Field {
    //Field data attributes
    private TableColumn column;
    private Object value;

    /**
     * Default constructor to set the associated column and value of the field
     * @param column associated column
     * @param value value of the field
     */
    Field(TableColumn column, Object value){
        this.column = column;
        this.value = value;
    }

    /**
     * Get the value object of this field
     * @return value object of this field
     */
    public Object getValue(){
        return this.value;
    }

    /**
     * Get the table column associated with the field
     * @return table column associated with the field
     */
    public TableColumn getColumn(){
        return this.column;
    }
}
