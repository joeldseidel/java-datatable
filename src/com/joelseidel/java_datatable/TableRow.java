package com.joelseidel.java_datatable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joel Seidel
 *
 * Table row object
 */

public class TableRow {
    //Table row data attributes
    private List<Field> fields;

    /**
     * Default constructor to initialize the field array
     */
    TableRow(){ fields = new ArrayList<Field>(); }

    /**
     * Add a field object to this row
     * @param column table column to associate the field with
     * @param value value object of the field
     */
    public void addField(TableColumn column, Object value){
        //Create the individual field object
        Field thisField = new Field(column, value);
        //Add the created field object to the row list of fields
        this.fields.add(thisField);
    }

    /**
     * Get the list of fields for this row
     * @return list of fields for this row
     */
    public List<Field> getFields(){
        return this.fields;
    }

    /**
     * Get a field by index within the row
     * @param index index within the row
     * @return field at the specified index within the row
     */
    public Field getField(int index){
        return this.fields.get(index);
    }
}
