package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class LoginActivity extends AppCompatActivity {

    EditText edtName,edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName=findViewById(R.id.editTextUserName);
        edtPass=findViewById(R.id.editTextPassword);
        btnLogin =findViewById(R.id.btnLogin);
        edtName.setFocusable(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm
                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseJson parseJson = new ParseJson();
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        // Do network action in this function
                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                        String userName = edtName.getText().toString();
                        String password = edtPass.getText().toString();
//                        String url = String.format("https://192.168.1.9:5566/api/TaiKhoanKhachHang/login?tenTaiKhoan=%s&matKhau=%s",userName,password);
                        String url = "https://192.168.1.9:5566/api/KhachHang/all";
                        String p = parseJson.readStringFileContent(url);
//                        Toast.makeText(LoginActivity.this, p, Toast.LENGTH_SHORT).show();
                    }
                }).start();
            }
        });
    }
}