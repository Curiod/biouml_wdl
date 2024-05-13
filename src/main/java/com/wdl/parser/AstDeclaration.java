/* Generated By:JJTree: Do not edit this line. AstDeclaration.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=Ast,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.wdl.parser;

public class AstDeclaration extends SimpleNode
{
    private String type;

    public AstDeclaration(int id)
    {
        super(id);
    }

    public AstDeclaration(WDLParser p, int id)
    {
        super(p, id);
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        for( Node child : getChildren() )
        {
            if( child instanceof AstSymbol )
                return ( (AstSymbol)child ).getName();
        }
        return null;
    }

    public AstExpression getExpression()
    {
        for( Node child : getChildren() )
        {
            if( child instanceof AstExpression )
                return (AstExpression)child;
        }
        return null;
    }
}
/* JavaCC - OriginalChecksum=050ba8acbf98d4792f732a9ebdfd1460 (do not edit this line) */
