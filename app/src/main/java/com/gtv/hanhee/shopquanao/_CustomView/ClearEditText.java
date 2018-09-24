package com.gtv.hanhee.shopquanao._CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.gtv.hanhee.shopquanao.R;

public class ClearEditText extends EditText {
    Drawable CrossX, NoneCrossX;
    Boolean visible = false;
    Drawable drawable;

    public ClearEditText(Context context) {
        super(context);
        KhoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        KhoiTao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        KhoiTao();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        KhoiTao();
    }

    private void KhoiTao() {
        CrossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        NoneCrossX = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
        CauHinh();
    }

    private void CauHinh() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? CrossX : NoneCrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width()-5)) {
            visible = !visible;
            setText("");
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter ==0 && start==0) {
            visible = false;
            CauHinh();
        } else {
            visible = true;
            CauHinh();
        }
    }
}
