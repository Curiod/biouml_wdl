package com.wdl.parser;

import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    protected Logger log = Logger.getLogger(AppTest.class.getName());
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue(true);
    }

    public String generateText(AstStart astStart)
    {
        StringBuilder sb = new StringBuilder();
        addElement(astStart, sb);
        return sb.toString();
    }

    private void addElement(Node currentNode, StringBuilder sb)
    {
        Token firstToken = ( (SimpleNode)currentNode ).jjtGetFirstToken();
        if( firstToken != null && firstToken.specialToken != null )
            sb.append(getSpecialTokens(firstToken.specialToken));

        sb.append(currentNode.toString());

        for( int i = 0; i < currentNode.jjtGetNumChildren(); i++ )
        {
            try
            {
                addElement(currentNode.jjtGetChild(i), sb);
            }
            catch( Throwable t )
            {
                log.log(Level.SEVERE, "Can't add element(" + currentNode.jjtGetChild(i) + "): " + t);
            }
        }

    }

    private String getSpecialTokens(Token token)
    {
        StringBuilder result = new StringBuilder("");
        if( token != null )
        {
            result.append(getSpecialTokens(token.specialToken));
            result.append(token);
        }
        return result.toString();
    }
}
