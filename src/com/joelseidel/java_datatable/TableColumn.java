package com.joelseidel.java_datatable;

/**
 * @author Joel Seidel
 */

public class TableColumn {
    //Column data attributes
    private String className;
    private int displaySize;
    private String label;
    private String name;
    private int type;
    private String typeName;
    private int precision;
    private int scale;
    private boolean isAutoIncrement;
    private boolean isCaseSensitive;
    private boolean isCurrency;
    private boolean isDefinitelyWritable;
    private int isNullable;
    private boolean isReadOnly;
    private boolean isSearchable;
    private boolean isSigned;
    private boolean isWritable;

    /**
     * Constructor to create a column from the result set meta data
     * @param className fully-qualified name of the Java class of the value of a field within associated with this column
     * @param displaysize normal maximum width in characters
     * @param label suggested title for use in printouts and displays
     * @param name name of the column
     * @param type SQL type
     * @param typeName database-specific type name
     * @param precision specified column size
     * @param scale number of digits to right of the decimal point
     * @param isAutoIncrement is column automatically numbered?
     * @param isCaseSensitive does case matter?
     * @param isCurrency is value a currency format?
     * @param isDefinitelyWritable will write on the column definitely succeed?
     * @param isNullable nullability of column
     * @param isReadOnly is column definitely not writable?
     * @param isSearchable can column be used in a where clause?
     * @param isSigned are values signed numbers?
     * @param isWritable is it possible for a write on the column to succeed?
     */
    public TableColumn(String className, int displaysize, String label, String name, int type, String typeName, int precision, int scale, boolean isAutoIncrement, boolean isCaseSensitive, boolean isCurrency, boolean isDefinitelyWritable, int isNullable, boolean isReadOnly, boolean isSearchable, boolean isSigned, boolean isWritable){
        //Set the arguments to local variables
        this.className = className;
        this.displaySize = displaysize;
        this.label = label;
        this.name = name;
        this.type = type;
        this.typeName = typeName;
        this.precision = precision;
        this.scale = scale;
        this.isAutoIncrement = isAutoIncrement;
        this.isCaseSensitive = isCaseSensitive;
        this.isCurrency = isCurrency;
        this.isDefinitelyWritable = isDefinitelyWritable;
        this.isNullable = isNullable;
        this.isReadOnly = isReadOnly;
        this.isSearchable = isSearchable;
        this.isSigned = isSigned;
        this.isWritable = isWritable;
    }

    /**
     * Constructor to create the most default type of column
     * @param dataType data type of the column
     * @param name name of the column
     */
    public TableColumn(COLUMN_DATA_TYPE dataType, String name){
        this.className = convertDataTypeToClassName(dataType);
        this.name = name;
    }

    private String convertDataTypeToClassName(COLUMN_DATA_TYPE dataType){
        switch(dataType){
            case INTEGER:
                return "java.lang.Integer";
            case NUMERIC:
                return "java.lang.Double";
            case STRING:
                return "java.lang.String";
        }
        return null;
    }

    /**
     * Get the class name of the column
     * @return class name of the column
     */
    public String getClassName() {
        return className;
    }

    /**
     * Get the display size of the column
     * @return display size of the column
     */
    public int getDisplaySize() {
        return displaySize;
    }

    /**
     * Get the label of the column
     * @return label of the column
     */
    public String getLabel() {
        return label;
    }

    /**
     * Get the name of the column
     * @return name of the column
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of the column
     * @return type of the column
     */
    public int getType() {
        return type;
    }

    /**
     * Get the type name of the column
     * @return type name of the column
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Get the precision of the column
     * @return precision of the column
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Get the scale of the column
     * @return scale of the column
     */
    public int getScale() {
        return scale;
    }

    /**
     * Get if the column is auto increment
     * @return is the column auto increment
     */
    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    /**
     * Get if the column is case sensitive
     * @return is the column case sensitive
     */
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    /**
     * Get if the column is a currency value
     * @return is the column a currency value
     */
    public boolean isCurrency() {
        return isCurrency;
    }

    /**
     * Get if a write on the column will definitely succeed
     * @return will a write on the column definitely succeed
     */
    public boolean isDefinitelyWritable() {
        return isDefinitelyWritable;
    }

    /**
     * Get the nullability of values in the column
     * @return nullability of the values in the column
     */
    public int getIsNullable() {
        return isNullable;
    }

    /**
     * Get if the a write on the column will definitely not succeed
     * @return will a write on the column definitely not succeed
     */
    public boolean isReadOnly() {
        return isReadOnly;
    }

    /**
     * Get if the column can be used in a where clause
     * @return can the field be used in a where clause
     */
    public boolean isSearchable() {
        return isSearchable;
    }

    /**
     * Get if the column contains signed values
     * @return if the column contains signed values
     */
    public boolean isSigned() {
        return isSigned;
    }

    /**
     * Get if a write on the column can possibly succeed
     * @return can a write on the column possibly succeed
     */
    public boolean isWritable() {
        return isWritable;
    }
}