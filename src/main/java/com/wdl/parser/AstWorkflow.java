/* Generated By:JJTree: Do not edit this line. AstWorkflow.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=Ast,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.wdl.parser;

public class AstWorkflow extends AstScope
{
    private String name;

    public AstWorkflow(int id)
    {
        super(id);
    }

    public AstWorkflow(WDLParser p, int id)
    {
        super(p, id);
    }

    @Override
    public String toString()
    {
        return "workflow";
    }

    public String getName()
    {
        return name;
    }

	public void setName(String name) {
		this.name = name;
	}

}
/* JavaCC - OriginalChecksum=ed2404d81aba96ee27d74e453bc9fd68 (do not edit this line) */