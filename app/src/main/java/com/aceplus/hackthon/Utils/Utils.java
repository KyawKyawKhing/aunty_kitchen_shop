package com.aceplus.hackthon.Utils;

import android.view.View;
import android.widget.ImageView;

import com.aceplus.hackthon.R;


/**
 * Created by GIGAMOLE on 8/18/16.
 */
public class Utils {

    public static void setupItem(final View view, final LibraryObject libraryObject) {

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        img.setImageResource(libraryObject.getRes());
    }

    public static class LibraryObject {

        private int mRes;

        public LibraryObject(final int res) {
            mRes = res;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }
    }
}
