package com.gtv.hanhee.shopquanao._Retrofit;

import com.gtv.hanhee.shopquanao.Model.DangNhap.ModelDangNhap;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.DanhGia;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.KhuyenMai;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.SanPham;
import com.gtv.hanhee.shopquanao.Model.ObjectClass.ThuongHieu;
import com.gtv.hanhee.shopquanao.Model.XuLyMenu.LoaiSanPham;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LayDuLieuTuUrl {
    @GET("laydanhsachkhuyenmai.php")
    Observable<List<KhuyenMai>> LayDanhSachKhuyenMai();

    @GET("Quara/thuonghieulon")
    Observable<List<ThuongHieu>> LayDanhSachCAcThuongHieuLon();
    @GET("laylogothuonghieulon.php")
    Observable<List<ThuongHieu>> LayLogoThuongHieuLon();

    @GET("laydanhsachphukien.php")
    Observable<List<ThuongHieu>> LayDSPhuKien();
    @GET("laydanhsachtopphukien.php")
    Observable<List<SanPham>> LayDSTopPhuKien();

    @GET("laydanhsachtienich.php")
    Observable<List<ThuongHieu>> LayDSTienIch();
    @GET("laydanhsachtoptienich.php")
    Observable<List<SanPham>> LayDSTopTienIch();


    @GET("Quara/sanpham/moive")
    Observable<List<SanPham>> LaySanPhamMoiVe();

    @GET("laydanhsachtop_dienthoai_va_maytinhbang.php")
    Observable<List<SanPham>> LayDanhSachSanPhamTop();

    @FormUrlEncoded
    @POST("laysanphamvachitiettheomasp.php")
    Observable<List<SanPham>> LaySPVaChiTietTheoMaSP(@Field("masp") int masp);
    @FormUrlEncoded
    @POST("laydanhsachdanhgiatheoma.php")
    Observable<List<DanhGia>> LayDSDanhGiaTheoMa(@Field("masp") int masp, @Field("limit") int limit, @Field("gioihanload") int gioihanload);

    @FormUrlEncoded
    @POST("Quara/sanpham/searchtensp")
    Observable<List<SanPham>> LayDSSPTheoTenSP(@Field("tensp") String tensp,@Field("limit") int limit);

    @FormUrlEncoded
    @POST("Quara/sanpham/maloai")
    Observable<List<SanPham>> LayDSSPTheoMaLoai(@Field("maloaisp") int maloaisp,@Field("limit") int limit);

    @FormUrlEncoded
    @POST("Quara/sanpham/math")
    Observable<List<SanPham>> LayDSSPTheoMaTH(@Field("maloaith") int maloaisp, @Field("start") int limit);


    @FormUrlEncoded
    @POST("loaisanpham.php")
    Observable<List<LoaiSanPham>> LayDanhSachSP(@Field("maloaicha") int maloaicha);

    @FormUrlEncoded
    @POST("themdanhgia.php")
    Observable<String> ThemDanhGia(@Field("madg") String madg,
                                       @Field("masp") int masp,
                                       @Field("tenthietbi") String tenthietbi,
                                       @Field("tieude") String tieude,
                                       @Field("noidung") String noidung,
                                       @Field("sosao") int sosao);

    @FormUrlEncoded
    @POST("themhoadon.php")
    Observable<String> ThemHoaDon(@Field("danhsachsanpham") String danhsachsanpham,
                                  @Field("tennguoinhan") String tennguoinhan,
                                  @Field("diachi") String diachi,
                                  @Field("sodt") String sodt,
                                  @Field("chuyenkhoan") int chuyenkhoan);

    @FormUrlEncoded
    @POST("dangkynhanvien.php")
    Observable<String> DangKyNhanVien(@Field("tennv") String tennv,
                                      @Field("tendangnhap") String tendangnhap,
                                      @Field("matkhau") String matkhau,
                                      @Field("maloainv") int maloainv,
                                      @Field("emaildocquyen") String emaildocquyen);

    @FormUrlEncoded
    @POST("kiemtradangnhap.php")
    Observable<ModelDangNhap> KiemTraDangNhap(@Field("tendangnhap") String tendangnhap, @Field("matkhau") String matkhau);



}
