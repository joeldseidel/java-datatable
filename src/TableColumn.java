class TableColumn {
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

    TableColumn(String className, int displaysize, String label, String name, int type, String typeName, int precision, int scale, boolean isAutoIncrement, boolean isCaseSensitive, boolean isCurrency, boolean isDefinitelyWritable, int isNullable, boolean isReadOnly, boolean isSearchable, boolean isSigned, boolean isWritable){
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

    public String getClassName() {
        return className;
    }

    public int getDisplaySize() {
        return displaySize;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getPrecision() {
        return precision;
    }

    public int getScale() {
        return scale;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    public boolean isCurrency() {
        return isCurrency;
    }

    public boolean isDefinitelyWritable() {
        return isDefinitelyWritable;
    }

    public int getIsNullable() {
        return isNullable;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public boolean isSearchable() {
        return isSearchable;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public boolean isWritable() {
        return isWritable;
    }
}