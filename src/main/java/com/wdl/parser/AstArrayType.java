/* Generated By:JJTree: Do not edit this line. AstArrayType.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=Ast,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.wdl.parser;

public class AstArrayType extends AstType
{
    private boolean isNonEmpty;
    public AstArrayType(int id)
    {
        super(id);
    }

    public AstArrayType(WDLParser p, int id)
    {
        super(p, id);
    }

    public void setNonEmpty(boolean isNonEmpty)
    {
        this.isNonEmpty = isNonEmpty;
    }

}
/* JavaCC - OriginalChecksum=32bdb38c1dab416a0a214f3c8918c0eb (do not edit this line) */
