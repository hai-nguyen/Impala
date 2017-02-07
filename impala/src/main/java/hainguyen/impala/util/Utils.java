package hainguyen.impala.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    private Utils() {

    }

    public static List<String> getNameEmailDetails(ContentResolver resolver) {
        ArrayList<String> names = new ArrayList<>();
        Cursor cur = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor cur1 =
                    resolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[] { id }, null);
                getEmailFromCursor(names, cur1);
                cur1.close();
            }
        }
        return names;
    }

    private static void getEmailFromCursor(List<String> names, Cursor cur1) {
        while (cur1.moveToNext()) {
            //to get the contact names
            String email =
                cur1.getString(cur1.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
            Log.e("Email", email);
            if (email != null) {
                names.add(email);
            }
        }
    }

    /**
     * hideDialog keyboard
     */
    public static void hideKeyboard(Context context, View v) {
        if (v == null)
            return;
        InputMethodManager imm =
            (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * @param rootView
     * @return
     */
    public static boolean isKeyboardShown(View rootView) {
            /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
        final int softKeyboardHeightThreshold = 128;

        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
            /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
        int heightDiff = rootView.getBottom() - r.bottom;
            /* Threshold size: dp to pixels, multiply with display density */
        return heightDiff > softKeyboardHeightThreshold * dm.density;
    }

    @NonNull public static File checkDirectory(Context ctx, String directoryName) {
        File dirFile;
        if (checkSdCard()) {
            dirFile = new File(Environment.getExternalStorageDirectory(), directoryName);
        } else {
            dirFile = new File(ctx.getCacheDir(), directoryName);
        }
        if (!dirFile.exists()) {
            dirFile.mkdirs();
            Log.d("DIRECTORY", "CREATE DIRECTORY: " + dirFile.getPath());
        }
        return dirFile;
    }

    private static boolean checkSdCard() {
        boolean mExternalStorageWritable;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mExternalStorageWritable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            mExternalStorageWritable = false;
        } else {
            mExternalStorageWritable = false;
        }
        return mExternalStorageWritable;
    }
}
