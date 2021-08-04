package Listeners;

import lombok.SneakyThrows;
import org.testng.IExecutionListener;

public class IExecutionlistener implements IExecutionListener {

    @Override
    public void onExecutionStart() { }

    @SneakyThrows
    @Override
    public void onExecutionFinish() { }
}