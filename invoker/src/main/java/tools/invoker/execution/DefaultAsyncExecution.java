package tools.invoker.execution;

import tools.invoker.command.CommandDescriptor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * User: jiangyixin.stephen
 * Date: 2013-05-28 10:22
 */
public class DefaultAsyncExecution extends AbstExecutionStrategy {
    private ExecutorService pool;
    private List<Future> futures = new LinkedList<>();

    public DefaultAsyncExecution(int poolSize){
        pool = Executors.newFixedThreadPool(poolSize);
    }

    @Override
    public void execute(List<CommandDescriptor> cmds) {
        for(final CommandDescriptor cmd : cmds){
            futures.add(pool.submit(new Runnable() {
                @Override
                public void run() {
                    execCmd(cmd);
                }
            }));
        }
    }

    @Override
    public void waitForComplete() {
        for(Future f : futures){
            try {
                f.get();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
            }
        }
        pool.shutdownNow();
    }
}
