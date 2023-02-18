package com.kangqing;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author kangqing
 * @since 2023/2/8 20:10
 */
@Component
public abstract class CommandManagerLookup {

    public Object process(Map commandState) {
        Command command = createCommand();
        command.setState(commandState);
        return command.execute();
    }

    @Lookup("myCommand")
    protected abstract Command createCommand();

}
