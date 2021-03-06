package com.gtv.hanhee.shopquanao._CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.gtv.hanhee.shopquanao.R;

@SuppressLint("AppCompatCustomView")
public class PasswordEditText extends EditText {
    Drawable eye,eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .55f);

    public PasswordEditText(Context context) {
        super(context);
        KhoiTao(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        KhoiTao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        KhoiTao(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        KhoiTao(attrs);
    }

    private void KhoiTao(AttributeSet attrs){


        if (attrs!=null) {
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        eye = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.ic_visibility_black_24dp, null);
        eyeStrike = ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.ic_visibility_off_black_24dp, null);
//        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();
        CaiDat();
    }

    private void CaiDat() {
        setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible ? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable ,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width()-5)) {
            visible = !visible;
            CaiDat();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
