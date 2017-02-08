package hainguyen.impala.utils;

import org.junit.rules.ExternalResource;

import hainguyen.impala.application.scheduler.ImpalaScheduler;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static hainguyen.impala.application.scheduler.ImpalaScheduler.instance;

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
