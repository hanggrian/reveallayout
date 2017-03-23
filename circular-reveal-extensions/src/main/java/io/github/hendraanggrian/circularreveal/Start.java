package io.github.hendraanggrian.circularreveal;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public enum Start {
    TOP_START {
        @Override
        public int getX(@NonNull View view) {
            return 0;
        }

        @Override
        public int getY(@NonNull View view) {
            return 0;
        }
    },
    TOP_CENTER {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth() / 2;
        }

        @Override
        public int getY(@NonNull View view) {
            return 0;
        }
    },
    TOP_END {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth();
        }

        @Override
        public int getY(@NonNull View view) {
            return 0;
        }
    },

    START {
        @Override
        public int getX(@NonNull View view) {
            return 0;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight() / 2;
        }
    },
    CENTER {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth() / 2;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight() / 2;
        }
    },
    END {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth();
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight() / 2;
        }
    },

    BOTTOM_START {
        @Override
        public int getX(@NonNull View view) {
            return 0;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight();
        }
    },
    BOTTOM_CENTER {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth() / 2;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight();
        }
    },
    BOTTOM_END {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth();
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight();
        }
    };

    public abstract int getX(@NonNull View view);

    public abstract int getY(@NonNull View view);
}