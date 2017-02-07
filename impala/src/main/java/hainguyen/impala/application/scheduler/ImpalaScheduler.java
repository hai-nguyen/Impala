package hainguyen.impala.application.scheduler;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ImpalaScheduler {
    public interface SchedulerProvider {
        Scheduler mainThread();

        Scheduler io();
    }

    public static SchedulerProvider instance = new DefaultSchedulerProvider();

    public static Scheduler mainThread() {
        return instance.mainThread();
    }

    public static Scheduler io() {
        return instance.io();
    }

    public static class DefaultSchedulerProvider implements SchedulerProvider {
        @Override public Scheduler mainThread() {
            return AndroidSchedulers.mainThread();
        }

        @Override public Scheduler io() {
            return Schedulers.io();
        }
    }
}
