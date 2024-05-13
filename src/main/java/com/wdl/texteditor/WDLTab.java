package com.wdl.texteditor;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class WDLTab extends JSplitPane
{
    protected Logger log = Logger.getLogger(WDLTab.class.getName());

    protected TextPaneAppender appender;
    protected JTextPane wdlPane;
    protected JTextPane nfPane;

    protected String[] categoryList = {"com.wdl"};

    private HashMap<Integer, Color> colorMap;


    public WDLTab()
    {
        super(JSplitPane.HORIZONTAL_SPLIT, false);

        setPreferredSize(new Dimension(500, 500));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        wdlPane = new WDLPane();

        appender = new TextPaneAppender(new PatternFormatter("%4$s :  %5$s%n"), "Application Log");
        appender.setLevel(Level.SEVERE);
        appender.addToCategories(categoryList);
        wdlPane.setFont(new Font("Monospaced", Font.PLAIN, 12));

        nfPane = new JTextPane();
        JScrollPane scrollWdl = new JScrollPane(wdlPane);
        JScrollPane scrollNf = new JScrollPane(nfPane);

        JLabel wdlLabel = new JLabel("WDL");
        wdlLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        topPanel.add(wdlLabel, BorderLayout.NORTH);
        topPanel.add(scrollWdl);

        JLabel nfLabel = new JLabel("Nextflow");
        nfLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        bottomPanel.add(nfLabel, BorderLayout.NORTH);
        bottomPanel.add(scrollNf);

        JSplitPane leftPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        leftPane.setResizeWeight(0.6);

        setLeftComponent(leftPane);
        setRightComponent(appender.getLogTextPanel());
        setResizeWeight(0.4);

    }


    String getText()
    {
        return wdlPane.getText();
    }

    void setText(String text) throws InterruptedException
    {
        wdlPane.setText(text);
    }



    public JEditorPane getEditorPane()
    {
        return wdlPane;
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