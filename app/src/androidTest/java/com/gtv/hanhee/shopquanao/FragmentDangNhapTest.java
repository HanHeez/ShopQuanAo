package com.gtv.hanhee.shopquanao;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.gtv.hanhee.shopquanao.View.TaiKhoan.DangKy.FragmentDangKy;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class FragmentDangNhapTest {


    @Rule
    public FragmentTestRule<?, FragmentDangKy> fragmentTestRule =
            FragmentTestRule.create(FragmentDangKy.class);

    private String email="a@Gmail.com";
    private String password="123";

    @Test
    public void emailIsEmpty() {
        Espresso.onView(withId(R.id.edHoTenDK)).perform(typeText(email), closeSoftKeyboard());
        Espresso.onView(withId(R.id.btnDK)).perform(click());
        Espresso.onView(withId(R.id.edHoTenDK)).check(matches(hasErrorText(not(Matchers.isEmptyOrNullString()))));

//        Espresso.onView(withId(R.id.edHoTenDK)).check(matches(hasErrorText("Bạn chưa nhập mục này")));
    }

    public static Matcher<View> hasErrorText(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<View, EditText>(EditText.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with error: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(EditText view) {
                if (view.getError() == null) return stringMatcher.matches(view.getError());
                return stringMatcher.matches(view.getError().toString());
            }
        };
    }

    @Test
    public void login_success() {

        Log.d("@Test", "Login Succes Test");
        Espresso.onView((withId(R.id.edtDiaChiEmailDangNhap)))
                .perform(typeText(email));

        Espresso.onView(withId(R.id.edtMatKhauDangNhap))
                .perform(typeText(password));
;
        Espresso.onView(withId(R.id.btnDangNhapQuara))
                .perform(click());

    }
}
