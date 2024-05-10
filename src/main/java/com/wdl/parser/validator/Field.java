package com.wdl.parser.validator;

public class Field extends NamedPrototype
{
    String type;
    boolean canBeEmpty = true;
    Object defaultValue;
    boolean isOptional = false;

    public Field(String name, NamedPrototype parent)
    {
        super(name, parent);
    }

    public Field(String name, String type, NamedPrototype parent)
    {
        this(name, parent);
        this.type = type;
    }

    public void setCanBeEmpty(boolean canBeEmpty)
    {
        this.canBeEmpty = canBeEmpty;
    }
}