package com.hendraanggrian.reveallayout;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author Hendra Anggrian (hendraanggrian@gmail.com)
 */
public enum RevealCenter {
    TOP_START(0) {
        @Override
        public int getX(@NonNull View view) {
            return 0;
        }

        @Override
        public int getY(@NonNull View view) {
            return 0;
        }
    },
    TOP_CENTER(1) {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth() / 2;
        }

        @Override
        public int getY(@NonNull View view) {
            return 0;
        }
    },
    TOP_END(2) {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth();
        }

        @Override
        public int getY(@NonNull View view) {
            return 0;
        }
    },

    START(3) {
        @Override
        public int getX(@NonNull View view) {
            return 0;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight() / 2;
        }
    },
    CENTER(4) {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth() / 2;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight() / 2;
        }
    },
    END(5) {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth();
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight() / 2;
        }
    },

    BOTTOM_START(6) {
        @Override
        public int getX(@NonNull View view) {
            return 0;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight();
        }
    },
    BOTTOM_CENTER(7) {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth() / 2;
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight();
        }
    },
    BOTTOM_END(8) {
        @Override
        public int getX(@NonNull View view) {
            return view.getWidth();
        }

        @Override
        public int getY(@NonNull View view) {
            return view.getHeight();
        }
    };

    public final int attrId;

    RevealCenter(int attrId) {
        this.attrId = attrId;
    }

    public abstract int getX(@NonNull View view);

    public abstract int getY(@NonNull View view);

    @NonNull
    public static RevealCenter valueOf(int attrId) {
        for (RevealCenter revealCenter : values())
            if (revealCenter.attrId == attrId)
                return revealCenter;
        throw new RuntimeException();
    }
}