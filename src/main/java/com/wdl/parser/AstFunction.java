/* Generated By:JJTree: Do not edit this line. AstFunction.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=Ast,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.wdl.parser;

public class AstFunction extends SimpleNode
{
    private String name;


    public AstFunction(int id)
    {
        super(id);
    }

    public AstFunction(WDLParser p, int id)
    {
        super(p, id);
    }


    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

}
/* JavaCC - OriginalChecksum=019643b3a2b1cda49106a981dddee0e3 (do not edit this line) */