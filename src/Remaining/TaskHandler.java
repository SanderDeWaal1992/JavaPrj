package Remaining;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TaskHandler {
    public interface TestInterface{
        public abstract Boolean execute();
    }
    private List<Function<String, Boolean>> taskList = new ArrayList<Function<String, Boolean>>();

    public void addTask(Function<String, Boolean> task){
        taskList.add(task);
    }

    public Boolean executeTasks(String tasksArgument){
        Boolean updated = false;
        for (Function<String, Boolean> order : taskList) {
            updated |= order.apply(tasksArgument);
        }
        return updated;
    }




}
