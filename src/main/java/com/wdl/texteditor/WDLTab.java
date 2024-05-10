package com.wdl.texteditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;

public class WDLTab extends JSplitPane
{
    protected Logger log = Logger.getLogger(WDLTab.class.getName());

    protected TextPaneAppender appender;
    protected JTextPane textPane;

    protected String[] categoryList = {"com.wdl"};

    private HashMap<Integer, Color> colorMap;


    public WDLTab()
    {
        super(JSplitPane.HORIZONTAL_SPLIT, false);

        setPreferredSize(new Dimension(500, 500));

        textPane = new WDLPane();

        appender = new TextPaneAppender(new PatternFormatter("%4$s :  %5$s%n"), "Application Log");
        appender.setLevel(Level.SEVERE);
        appender.addToCategories(categoryList);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(textPane);
        setLeftComponent(scroll);
        setRightComponent(appender.getLogTextPanel());
        setResizeWeight(0.4);

    }


    String getText()
    {
        return textPane.getText();
    }

    void setText(String text) throws InterruptedException
    {
        textPane.setText(text);
    }



    public JEditorPane getEditorPane()
    {
        return textPane;
    }

    class PatternFormatter extends Formatter
    {

        private String pattern;

        public PatternFormatter(String pattern)
        {
            this.pattern = pattern;
        }

        @Override
        public String format(LogRecord record)
        {
            return String.format(pattern, new Date(record.getMillis()), record.getSourceClassName(), record.getLoggerName(),
                    record.getLevel(), record.getMessage(), record.getThrown());
        }

    }

}