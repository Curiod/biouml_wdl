package com.wdl.parser.validator;

public class Field extends NamedPrototype
{
    String type;
    boolean canBeEmpty = true;
    Object defaultValue;
    boolean isOptional = false;
    String alias;

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

	public void setAlias(String alias) {
		this.alias = alias;
	}
}