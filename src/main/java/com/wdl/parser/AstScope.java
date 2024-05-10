package com.wdl.parser;

import java.util.Map;

public class AstScope extends SimpleNode
{
    private Map<String, AstType> varMap;

    public AstScope(int id)
    {
        super(id);
    }

    public AstScope(WDLParser p, int id)
    {
        super(p, id);
    }

    public void putVariable(String name, AstType type)
    {
        this.varMap.put(name, type);
    }

}
