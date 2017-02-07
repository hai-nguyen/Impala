package hainguyen.impala.utils;

import org.junit.rules.ExternalResource;

import hainguyen.impala.application.scheduler.ImpalaScheduler;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static hainguyen.impala.application.scheduler.ImpalaScheduler.instance;

/**
 * Created by nguyenminhhai on 23/5/16.
 */
public class SynchronousSchedulers extends ExternalResource {
    @Override
    protected void before() throws Throwable {
        instance = new ImpalaScheduler.SchedulerProvider() {
            @Override
            public Scheduler mainThread() {
                return Schedulers.immediate();
            }

            @Override
            public Scheduler io() {
                return Schedulers.immediate();
            }
        };
    }

    @Override
    protected void after() {
        instance = new ImpalaScheduler.DefaultSchedulerProvider();
    }
}
