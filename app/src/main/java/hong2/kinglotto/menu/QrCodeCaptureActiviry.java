package hong2.kinglotto.menu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import hong2.kinglotto.R;

public class QrCodeCaptureActiviry extends AppCompatActivity {
    WebView wv;
    EditText et;
    Button bt;
    IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_activiry);

        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);
        bt = findViewById(R.id.bt);
        WebSettings webSettings = wv.getSettings();
        // java script 를 사용할수 있또록 함
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient( new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // todo scan 할 파일 전송하기
                Toast.makeText(QrCodeCaptureActiviry.this, "로딩 끝", Toast.LENGTH_SHORT).show();
            }
        });

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // bt 의 onCLick 을 실행
                    bt.callOnClick();
                    // 키보드 숨기기
                    InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imn.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        intentIntegrator = new IntentIntegrator(this);

        // 바코드 안 텍스트
        intentIntegrator.setPrompt("바코드를 사각형 안에 맞춰주세요");
        // 바코드 인식시 소리 여부
        intentIntegrator.setBeepEnabled(false);

        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.setCaptureActivity(CaptureActivity.class);

        // 바코드 스캐너 시작
        intentIntegrator.initiateScan();

    }

    public void onClick(View view) {
        String addtress = et.getText().toString();

        if (!addtress.startsWith("http://")) {
            addtress = "http://" + addtress;
            Log.i("TED.KING", addtress);
        }
        wv.loadUrl(addtress);
    }

    @Override
    public void onBackPressed() {
        if (wv.isActivated()) {
            if (wv.canGoBack()) {
                wv.goBack();
            } else {
                // 스캐너 재시작
                intentIntegrator.initiateScan();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {

            } else  {
                // todo scan할 위치 전송하기
                // todo 전송할때 v 는 회차, m뒤에서 2개씩 끊어서 로또 번호를 의미함.
                // example https://m.dhlottery.co.kr/qr.do?method=winQr&v=0866m060915182941m091529343739m091529343739m091529343739m091529343739m
                // qr code 를 읽어서 EditText 에 입력함
                et.setText(result.getContents());
                // button 의 onclick 을 호출
                bt.callOnClick();
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else  {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
