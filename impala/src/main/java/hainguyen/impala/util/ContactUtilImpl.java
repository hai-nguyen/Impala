package hainguyen.impala.util;

import android.content.ContentResolver;
import java.util.List;
import rx.Observable;
import rx.Subscriber;

public class ContactUtilImpl implements ContactUtil {

    @Override public Observable<List<String>> getEmailList(final ContentResolver resolver) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override public void call(Subscriber<? super List<String>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    List<String> names = Utils.getNameEmailDetails(resolver);
                    subscriber.onNext(names);
                }
            }
        });
    }
}
