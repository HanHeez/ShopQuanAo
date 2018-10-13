package com.gtv.hanhee.shopquanao;

import android.content.Context;

import com.gtv.hanhee.shopquanao.View.ChiTietSanPham.ChiTietSanPhamActivity;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ChiTietSanPhamTest {

    @Mock
    Context context;
    ChiTietSanPhamActivity chiTietSanPhamActivity;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void TestIdColor() {
        ChiTietSanPhamActivity chiTietSanPhamActivity = mock(ChiTietSanPhamActivity.class);

        int color = chiTietSanPhamActivity.getIdColor(R.color.colorGreen);

        Assert.assertEquals(5, color);

    }
}
