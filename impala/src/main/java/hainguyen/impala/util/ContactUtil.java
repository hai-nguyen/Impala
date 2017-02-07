package hainguyen.impala.util;

import android.content.ContentResolver;
import java.util.List;
import rx.Observable;

public interface ContactUtil {
    Observable<List<String>> getEmailList(ContentResolver resolver);
}
