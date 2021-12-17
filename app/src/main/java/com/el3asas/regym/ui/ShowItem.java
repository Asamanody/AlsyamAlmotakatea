package com.el3asas.regym.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.el3asas.regym.R;
import com.el3asas.regym.helpers.Ads;
import com.el3asas.regym.helpers.So3rOPJ;
import com.el3asas.regym.ui.adapter.So3ratAdapter;
import com.google.android.ads.nativetemplates.TemplateView;

import java.util.ArrayList;

public class ShowItem extends AppCompatActivity {
    private TemplateView templateView;
    private Ads ads;
    private TextView titlee;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        titlee = findViewById(R.id.title);
        imageView = findViewById(R.id.img);
        ImageButton back = findViewById(R.id.back);
        templateView = findViewById(R.id.my_template);
        ads = new Ads();
        back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().hasExtra("title") && getIntent().hasExtra("img")) {
            String title = getIntent().getStringExtra("title");
            int imgSrc = getIntent().getIntExtra("img", 0);
            titlee.setText(title);
            imageView.setImageResource(imgSrc);
            assert title != null;
            show(title);
        }
    }

    private void show(String title) {
        ArrayList<So3rOPJ> so3rOPJS = new ArrayList<>();
        switch (title) {
            case "السعرات الحراريه للاسماك":
                so3rOPJS.add(new So3rOPJ("ام الخلول", "100 جرام", "50 سعر"));
                so3rOPJS.add(new So3rOPJ("بطارخ مشويه", "100 جرام", "260 سعر"));
                so3rOPJS.add(new So3rOPJ("بلطى", "100 جرام", "100 سعر"));
                so3rOPJS.add(new So3rOPJ("بورى مدخن", "100 جرام", "85 سعر"));
                so3rOPJS.add(new So3rOPJ("بورى مشوى", "100 جرام", "170 سعر"));
                so3rOPJS.add(new So3rOPJ("تونه معلبه", "100 جرام", "280 سعر"));
                so3rOPJS.add(new So3rOPJ("جمبرى", "100 جرام", "90 سعر"));
                so3rOPJS.add(new So3rOPJ("سردين معلب", "100 جرام", "189 سعر"));
                so3rOPJS.add(new So3rOPJ("سالمون معلب", "100 جرام", "294 سعر"));
                so3rOPJS.add(new So3rOPJ("سالمون مشوى", "100 جرام", "108 سعر"));
                so3rOPJS.add(new So3rOPJ("سبيط", "100 جرام", "61 سعر"));
                so3rOPJS.add(new So3rOPJ("كابوريا", "100 جرام", "100 سعر"));

                break;
            case "السعرات الحراريه للحوم":
                so3rOPJS.add(new So3rOPJ("ارانب مشويه", "100 جرام", "193 سعر"));
                so3rOPJS.add(new So3rOPJ("ارانب مسلوقه", "100 جرام", "194 سعر"));
                so3rOPJS.add(new So3rOPJ("الاوز", "100 جرام", "349 سعر"));
                so3rOPJS.add(new So3rOPJ("بتلو مشوى", "100 جرام", "278 سعر"));
                so3rOPJS.add(new So3rOPJ("بتلو محمر", "100 جرام", "512 سعر"));
                so3rOPJS.add(new So3rOPJ("بط", "100 جرام", "170 سعر"));
                so3rOPJS.add(new So3rOPJ("حمام مشوى", "100 جرام", "105 سعر"));
                so3rOPJS.add(new So3rOPJ("ديك رومى", "100 جرام", "280 سعر"));
                so3rOPJS.add(new So3rOPJ("فراخ مشويه", "100 جرام", "130 سعر"));
                so3rOPJS.add(new So3rOPJ("قلب بقرى", "100 جرام", "150 سعر"));
                so3rOPJS.add(new So3rOPJ("كبده مشويه", "100 جرام", "200 سعر"));
                so3rOPJS.add(new So3rOPJ("كرشه", "100 جرام", "99 سعر"));
                so3rOPJS.add(new So3rOPJ("كلاوى ضأن", "100 جرام", "105 سعر"));
                so3rOPJS.add(new So3rOPJ("كلاوى بقرى", "100 جرام", "140 سعر"));
                so3rOPJS.add(new So3rOPJ("لحم بقرى ستيك", "100 جرام", "273 سعر"));
                so3rOPJS.add(new So3rOPJ("لحم ضأن", "100 جرام", "300 سعر"));
                so3rOPJS.add(new So3rOPJ("لحم كندوز", "100 جرام", "240 سعر"));
                so3rOPJS.add(new So3rOPJ("مخ بقرى", "100 جرام", "100 سعر"));
                break;
            case "السعرات الحراريه للحوم المصنعه":
                so3rOPJS.add(new So3rOPJ("بسطرمه", "100 جرام", "280 سعر"));
                so3rOPJS.add(new So3rOPJ("سجق", "100 جرام", "592 سعر"));
                so3rOPJS.add(new So3rOPJ("لانشون", "100 جرام", "172 سعر"));
                so3rOPJS.add(new So3rOPJ("لانشون معلب", "100 جرام", "294 سعر"));
                so3rOPJS.add(new So3rOPJ("هامبورجر", "100 جرام", "286 سعر"));
                break;
            case "السعرات الحراريه للمشروبات والعصائر":

                so3rOPJS.add(new So3rOPJ("عصير اناناس", "100 جرام", "70 سعر"));
                so3rOPJS.add(new So3rOPJ("عصير برتقال", "100 جرام", "37 سعر"));
                so3rOPJS.add(new So3rOPJ("عصير تفاح", "100 جرام", "58 سعر"));
                so3rOPJS.add(new So3rOPJ("عصير جريب فروت", "100 جرام", "60 سعر"));
                so3rOPJS.add(new So3rOPJ("عصير جزر", "100 جرام", "50 سعر"));
                so3rOPJS.add(new So3rOPJ("عصير طماطم", "100 جرام", "37 سعر"));
                so3rOPJS.add(new So3rOPJ("عصير ليمون", "100 جرام", "7 سعر"));
                so3rOPJS.add(new So3rOPJ("كاكاو", "100 جرام", "452 سعر"));
                so3rOPJS.add(new So3rOPJ("كوكاكولا", "100 جرام", "82 سعر"));
                so3rOPJS.add(new So3rOPJ("كوكاكولا دايت", "100 جرام", "2 سعر"));
                break;
            case "السعرات الحراريه للالبان ومنتجاتها":

                so3rOPJS.add(new So3rOPJ("لبن بالشوكولاته", "100 جرام", "550 سعر"));
                so3rOPJS.add(new So3rOPJ("ايس كريم", "100 جرام", "210 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه اسطنبولى", "100 جرام", "235 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه ثلاجه", "100 جرام", "380 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه دمياطى", "100 جرام", "420 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه ريكفورد", "100 جرام", "370 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه رومى", "100 جرام", "313 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه شيدر", "100 جرام", "398 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه فلمنك", "100 جرام", "370 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه قديمه", "100 جرام", "420 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه قريش", "100 جرام", "116 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه كيرى", "100 جرام", "810 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه مطبوخه", "100 جرام", "260 سعر"));
                so3rOPJS.add(new So3rOPJ("زبادى", "علبه متوسطه", "55 سعر"));
                so3rOPJS.add(new So3rOPJ("زبادى بالفواكه", "علبه متوسطه", "106 سعر"));
                so3rOPJS.add(new So3rOPJ("جبنه كيرى", "100 جرام", "162 سعر"));
                so3rOPJS.add(new So3rOPJ("شاى باللبن", "كوب", "50 سعر"));
                so3rOPJS.add(new So3rOPJ("لبن كامل الدسم", "كوب", "130 سعر"));
                so3rOPJS.add(new So3rOPJ("لبن منزوع الدسم", "كوب", "45 سعر"));
                so3rOPJS.add(new So3rOPJ("لبن رائب", "100 جرام", "60 سعر"));
                so3rOPJS.add(new So3rOPJ("لبن بودره", "ملعقه صغيره", "22 سعر"));
                so3rOPJS.add(new So3rOPJ("نسكافيه باللبن", "كوب", "60 سعر"));
                break;
            case "السعرات الحراريه لحلويات":

                so3rOPJS.add(new So3rOPJ("ارز باللبن", "100 جرام", "144 سعر"));
                so3rOPJS.add(new So3rOPJ("بسبوسه ساده", "100 جرام", "480 سعر"));
                so3rOPJS.add(new So3rOPJ("بسبوسه بالمكسرات", "100 جرام", "620 سعر"));
                so3rOPJS.add(new So3rOPJ("بقلاوة", "100 جرام", "390 سعر"));
                so3rOPJS.add(new So3rOPJ("تورته", "100 جرام", "394 سعر"));
                so3rOPJS.add(new So3rOPJ("توفى", "100 جرام", "435 سعر"));
                so3rOPJS.add(new So3rOPJ("حلاوه طحينيه", "100 جرام", "516 سعر"));
                so3rOPJS.add(new So3rOPJ("جاتوه", "100 جرام", "350 سعر"));
                so3rOPJS.add(new So3rOPJ("جيلى", "100 جرام", "59 سعر"));
                so3rOPJS.add(new So3rOPJ("سكر", "100 جرام", "385 سعر"));
                so3rOPJS.add(new So3rOPJ("عسل اسود", "100 جرام", "232 سعر"));
                so3rOPJS.add(new So3rOPJ("عسل نحل", "100 جرام", "320 سعر"));
                so3rOPJS.add(new So3rOPJ("غريبه", "100 جرام", "521سعر"));
                so3rOPJS.add(new So3rOPJ("كنافه محشوه", "100 جرام", "780 سعر"));
                so3rOPJS.add(new So3rOPJ("كورن فليكس", "100 جرام", "367 سعر"));
                so3rOPJS.add(new So3rOPJ("مربى الفراوله", "100 جرام", "275 سعر"));
                break;
            case "السعرات الحراريه للمكسرات":

                so3rOPJS.add(new So3rOPJ("جوز", "100 جرام", "651 سعر"));
                so3rOPJS.add(new So3rOPJ("جوز هند", "100 جرام", "650 سعر"));
                so3rOPJS.add(new So3rOPJ("زبيب", "100 جرام", "300 سعر"));
                so3rOPJS.add(new So3rOPJ("سمسم", "100 جرام", "595 سعر"));
                so3rOPJS.add(new So3rOPJ("فستق", "100 جرام", "594 سعر"));
                so3rOPJS.add(new So3rOPJ("فول سودانى", "100 جرام", "585 سعر"));
                so3rOPJS.add(new So3rOPJ("قراصيا مجفف", "100 جرام", "150 سعر"));
                so3rOPJS.add(new So3rOPJ("لب اسمر", "100 جرام", "563 سعر"));
                so3rOPJS.add(new So3rOPJ("لب ابيض", "100 جرام", "554 سعر"));
                break;
            case "السعرات الحراريه للبقوليات والنشويات":

                so3rOPJS.add(new So3rOPJ("فول سودانى", "100 جرام", "585 سعر"));
                so3rOPJS.add(new So3rOPJ("ارز مسلوق", "100 جرام", "116 سعر"));
                so3rOPJS.add(new So3rOPJ("باتيه", "100 جرام", "230 سعر"));
                so3rOPJS.add(new So3rOPJ("بيتزا بالجبن", "100 جرام", "236 سعر"));
                so3rOPJS.add(new So3rOPJ("بيتزا سجق", "100 جرام", "234 سعر"));
                so3rOPJS.add(new So3rOPJ("ترمس", "100 جرام", "372 سعر"));
                so3rOPJS.add(new So3rOPJ("حمص الشام", "100 جرام", "350 سعر"));
                so3rOPJS.add(new So3rOPJ("خبز ابيض", "100 جرام", "243 سعر"));
                so3rOPJS.add(new So3rOPJ("خبز اسمر", "100 جرام", "229 سعر"));
                so3rOPJS.add(new So3rOPJ("خبز توست", "100 جرام", "229 سعر"));
                so3rOPJS.add(new So3rOPJ("عدس جاف", "100 جرام", "340 سعر"));
                so3rOPJS.add(new So3rOPJ("فشار", "100 جرام", "286 سعر"));
                so3rOPJS.add(new So3rOPJ("مكرونه مسلوقه", "100 جرام", "115 سعر"));
                so3rOPJS.add(new So3rOPJ("طحين قمح", "100 جرام", "345 سعر"));
                so3rOPJS.add(new So3rOPJ("عدس شوربه", "100 جرام", "100 سعر"));
                break;
            case "السعرات الحراريه للدهون":

                so3rOPJS.add(new So3rOPJ("زيت", "100 جرام", "900 سعر"));
                so3rOPJS.add(new So3rOPJ("سمن بلدى", "100 جرام", "900 سعر"));
                so3rOPJS.add(new So3rOPJ("سمن نباتى", "100 جرام", "795 سعر"));

                break;
            case "السعرات الحراريه للخضراوات":

                so3rOPJS.add(new So3rOPJ("باذنجان رومى", "100 جرام", "15 سعر"));
                so3rOPJS.add(new So3rOPJ("باميه", "100 جرام", "32 سعر"));
                so3rOPJS.add(new So3rOPJ("بسله مسلوقه", "100 جرام", "49 سعر"));
                so3rOPJS.add(new So3rOPJ("بصل طازج", "100 جرام", "23 سعر"));
                so3rOPJS.add(new So3rOPJ("بصل اخضر", "100 جرام", "36 سعر"));
                so3rOPJS.add(new So3rOPJ("بطاطا مسلوقه", "100 جرام", "85 سعر"));
                so3rOPJS.add(new So3rOPJ("بطاطس شيبسى", "100 جرام", "220 سعر"));
                so3rOPJS.add(new So3rOPJ("بقدونس", "100 جرام", "28 سعر"));
                so3rOPJS.add(new So3rOPJ("بنجر", "100 جرام", "44 سعر"));
                so3rOPJS.add(new So3rOPJ("جرجير", "100 جرام", "18 سعر"));
                so3rOPJS.add(new So3rOPJ("جزر", "100 جرام", "45 سعر"));
                so3rOPJS.add(new So3rOPJ("خس", "100 جرام", "11 سعر"));
                so3rOPJS.add(new So3rOPJ("خيار", "100 جرام", "12 سعر"));
                so3rOPJS.add(new So3rOPJ("سبانخ", "100 جرام", "36 سعر"));
                so3rOPJS.add(new So3rOPJ("طماطم", "100 جرام", "20 سعر"));
                so3rOPJS.add(new So3rOPJ("فاصوليا خضراء", "100 جرام", "7 سعر"));
                so3rOPJS.add(new So3rOPJ("قرنبيط", "100 جرام", "25 سعر"));
                so3rOPJS.add(new So3rOPJ("كوسه", "100 جرام", "26 سعر"));
                so3rOPJS.add(new So3rOPJ("كرنب مسلوق", "100 جرام", "16 سعر"));
                so3rOPJS.add(new So3rOPJ("لوبيا بلدى", "100 جرام", "33 سعر"));
                so3rOPJS.add(new So3rOPJ("ملوخيه", "100 جرام", "47 سعر"));
                so3rOPJS.add(new So3rOPJ("ورق فجل", "100 جرام", "25 سعر"));
                break;
            case "السعرات الحراريه للفواكه":

                so3rOPJS.add(new So3rOPJ("اناناس", "100 جرام", "38 سعر"));
                so3rOPJS.add(new So3rOPJ("برتقال", "100 جرام", "27 سعر"));
                so3rOPJS.add(new So3rOPJ("بطيخ", "100 جرام", "16 سعر"));
                so3rOPJS.add(new So3rOPJ("بلح", "100 جرام", "214 سعر"));
                so3rOPJS.add(new So3rOPJ("تين", "100 جرام", "79 سعر"));
                so3rOPJS.add(new So3rOPJ("تفاح", "100 جرام", "58 سعر"));
                so3rOPJS.add(new So3rOPJ("توت", "100 جرام", "36 سعر"));
                so3rOPJS.add(new So3rOPJ("جريب فروت", "100 جرام", "17 سعر"));
                so3rOPJS.add(new So3rOPJ("جوافه", "100 جرام", "70 سعر"));
                so3rOPJS.add(new So3rOPJ("جوز هند", "100 جرام", "336 سعر"));
                so3rOPJS.add(new So3rOPJ("خوخ", "100 جرام", "36 سعر"));
                so3rOPJS.add(new So3rOPJ("رمان", "100 جرام", "20 سعر"));
                so3rOPJS.add(new So3rOPJ("شمام", "100 جرام", "13 سعر"));
                so3rOPJS.add(new So3rOPJ("عنب بناتى", "100 جرام", "239 سعر"));
                so3rOPJS.add(new So3rOPJ("عنب ببذر", "100 جرام", "90 سعر"));
                so3rOPJS.add(new So3rOPJ("فراوله", "100 جرام", "34 سعر"));
                so3rOPJS.add(new So3rOPJ("كمثرى", "100 جرام", "30 سعر"));
                so3rOPJS.add(new So3rOPJ("كريز", "100 جرام", "40 سعر"));
                so3rOPJS.add(new So3rOPJ("ليمون", "100 جرام", "15 سعر"));
                so3rOPJS.add(new So3rOPJ("مانجو", "100 جرام", "66 سعر"));
                so3rOPJS.add(new So3rOPJ("مشمش", "100 جرام", "51 سعر"));
                so3rOPJS.add(new So3rOPJ("موز", "100 جرام", "45 سعر"));
                so3rOPJS.add(new So3rOPJ("يوسفى", "100 جرام", "24 سعر"));
                break;
        }
        So3ratAdapter so3ratAdapter = new So3ratAdapter(so3rOPJS);
        RecyclerView.LayoutManager lm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        RecyclerView recyclerView = findViewById(R.id.seconderyRecycleView);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(so3ratAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ads.refreshAd(this, "show", templateView, getString(R.string.smlladg));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ads.destroyAd();
    }
}
