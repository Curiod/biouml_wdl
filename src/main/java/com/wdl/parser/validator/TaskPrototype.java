package com.wdl.parser.validator;

import java.util.Map;

public class TaskPrototype extends Scope
{
    Map<String, Field> inputs;
    Map<String, Field> outputs;
    String command;
    Map<String, Field> runtime; // or requirements in versions > 1.1
    Map<String, Field> hints;

    public TaskPrototype(String name)
    {
        super(name);
    }

    public void addInput(Field input)
    {
        inputs.put(input.getName(), input);
    }

    public void addOutput(Field output)
    {
        outputs.put(output.getName(), output);
    }

    public void setCommand(String command)
    {
        this.command = command;
    }
}
