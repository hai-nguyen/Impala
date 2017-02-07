package hainguyen.impala.feature.base;

import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import hainguyen.impala.util.Utils;

public class BaseActivity extends AppCompatActivity {

    @Override public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && getCurrentFocus() != null) {
            View v = getCurrentFocus();

            // check keyboard is shown before dispatch touch event
            boolean beforeDispatch = Utils.isKeyboardShown(v.getRootView());

            boolean ret = super.dispatchTouchEvent(event);

            if (v instanceof EditText) {
                // check keyboard is shown after dispatch touch event
                boolean isAfterDispatch = Utils.isKeyboardShown(v.getRootView());

                if (event.getAction() == MotionEvent.ACTION_DOWN
                    && isAfterDispatch
                    && beforeDispatch) {
                    Utils.hideKeyboard(this, getWindow().getCurrentFocus());
                }
            }
            return ret;
        } else {
            return super.dispatchTouchEvent(event);
        }
    }
}
