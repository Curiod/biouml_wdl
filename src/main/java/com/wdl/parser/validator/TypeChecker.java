package com.wdl.parser.validator;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.wdl.parser.AstAlias;
import com.wdl.parser.AstAs;
import com.wdl.parser.AstConditional;
import com.wdl.parser.AstDeclaration;
import com.wdl.parser.AstHints;
import com.wdl.parser.AstImport;
import com.wdl.parser.AstInput;
import com.wdl.parser.AstOutput;
import com.wdl.parser.AstRuntime;
import com.wdl.parser.AstScatter;
import com.wdl.parser.AstStart;
import com.wdl.parser.AstStruct;
import com.wdl.parser.AstTask;
import com.wdl.parser.AstText;
import com.wdl.parser.AstWorkflow;
import com.wdl.parser.Node;


public class TypeChecker
{
    protected Logger log = Logger.getLogger(TypeChecker.class.getName());
    private VersionValidator validator;
    private DocumentPrototype doc;

    public TypeChecker(String name)
    {
        this("1.0", name);
    }

    public TypeChecker(String version, String name)
    {
        try
        {
            validator = VersionValidator.getValidator(version);
            doc = new DocumentPrototype(version, name);
        }
        catch( Exception e )
        {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public void check(AstStart astStart)
    {
        for( int i = 0; i < astStart.jjtGetNumChildren(); i++ )
        {
            try
            {
                addTopElement(astStart.jjtGetChild(i));
            }
            catch( Throwable t )
            {
                log.log(Level.SEVERE, t.getMessage());
            }
        }

    }

    private void addTopElement(Node currentNode) throws Exception
    {
        if( currentNode instanceof AstImport )
        {
            addImport((AstImport)currentNode);
        }
        else if( currentNode instanceof AstWorkflow )
        {
            addWorkflow((AstWorkflow)currentNode);
        }
        else if( currentNode instanceof AstTask )
        {
            addTask((AstTask)currentNode);
        }
        else if( currentNode instanceof AstStruct )
        {
            addStruct((AstStruct)currentNode);
        }

    }

    private void addHints(AstRuntime currentNode)
    {
        // TODO Auto-generated method stub

    }

    private void addRuntime(AstRuntime currentNode) throws Exception
    {

        validator.checkRuntimeAttributes(null, null);

    }

    private void addStruct(AstStruct currentNode) throws Exception
    {
        Scope struct = new Scope(currentNode.getStructName(), doc);

        for( Node child : currentNode.getChildren() )
        {
            if( child instanceof AstDeclaration )
            {
                AstDeclaration decl = (AstDeclaration)child;
                struct.addField(new Field(decl.getName(), decl.getType(), struct));
            }
        }

        doc.addStruct(struct);

    }

    private void addConditional(AstConditional currentNode)
    {
        // TODO Auto-generated method stub

    }

    private void addScatter(AstScatter currentNode)
    {
        // TODO Auto-generated method stub

    }

    private void addTask(AstTask tsk) throws Exception
    {
        TaskPrototype tskPrototype = new TaskPrototype(tsk.getName());
        for( Node child : tsk.getChildren() )
        {
            if( child instanceof AstRuntime )
            {
                addRuntime((AstRuntime)child);
            }
            else if( child instanceof AstHints )
            {
                addHints((AstRuntime)child);
            }
            else if( child instanceof AstDeclaration )
            {
                AstDeclaration decl = (AstDeclaration)child;
                tskPrototype.addField(new Field(decl.getName(), decl.getType(), tskPrototype));
            }
            else if( child instanceof AstInput )
            {
                addInputs((AstInput)child, tskPrototype);
            }
            else if( child instanceof AstOutput )
            {
                addOutputs((AstOutput)child, tskPrototype);
            }
        }

    }

    private void addWorkflow(AstWorkflow wf) throws Exception
    {
        WorkflowPrototype wfPrototype = new WorkflowPrototype(wf.getName());
        for( Node child : wf.getChildren() )
        {
            if( child instanceof AstScatter )
            {
                addScatter((AstScatter)child);
            }
            else if( child instanceof AstConditional )
            {
                addConditional((AstConditional)child);
            }
            else if( child instanceof AstRuntime )
            {
                addRuntime((AstRuntime)child);
            }
            else if( child instanceof AstHints )
            {
                addHints((AstRuntime)child);
            }
            else if( child instanceof AstDeclaration )
            {
                AstDeclaration decl = (AstDeclaration)child;
                wfPrototype.addField(new Field(decl.getName(), decl.getType(), wfPrototype));
            }
            else if( child instanceof AstInput )
            {
                addInputs((AstInput)child, wfPrototype);
            }
            else if( child instanceof AstOutput )
            {
                addOutputs((AstOutput)child, wfPrototype);
            }
        }

    }

    private void addOutputs(AstOutput child, TaskPrototype wfPrototype)
    {
        // TODO Auto-generated method stub

    }

    private void addInputs(AstInput child, TaskPrototype wfPrototype)
    {
        // TODO Auto-generated method stub

    }

    private void addImport(AstImport astNode) throws Exception
    {
        ImportPrototype imp = null;

        for( Node child : astNode.getChildren() )
        {
            if( child instanceof AstText )
                imp = new ImportPrototype( ( (AstText)child ).getText());
            else if( imp != null )
            {
                if( child instanceof AstAs )
                    imp.setAliasName( ( (AstAs)child ).getAlias());
                else if( child instanceof AstAlias )
                {
                    for( Node n : ( (AstAlias)child ).getChildren() )
                    {
                        if( n instanceof AstAs )
                            doc.addField(new Field( ( (AstAs)n ).getAlias(), doc));
                    }

                    imp.setAliasName( ( (AstAlias)child ).getAlias());
                }
            }
        }

        if( imp != null )
            doc.addImport(imp);


    }
}