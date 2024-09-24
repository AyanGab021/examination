package com.example.webviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Landing_page extends AppCompatActivity {

    CardView portfolioLink,linkedInLink,instagramLink,facebookLink,websitelink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        portfolioLink = findViewById(R.id.portfolio);
        linkedInLink = findViewById(R.id.linkedIn);
        instagramLink = findViewById(R.id.instagram);
        facebookLink = findViewById(R.id.facebook);
        websitelink = findViewById(R.id.website);

        cardViewButtton(portfolioLink,"link","https://hgsoss.com/20246-2/","No");
        cardViewButtton(linkedInLink,"link","https://www.linkedin.com/in/brian-ken-raviz-aa16ab254/","No");
        cardViewButtton(instagramLink,"link","https://www.instagram.com/brianraviz/","Yes");
        cardViewButtton(facebookLink,"link","https://www.facebook.com/brianken.raviz","Yes");
        cardViewButtton(websitelink,"link","https://www.facebook.com/brianken.raviz","website");


    }

    void cardViewButtton(CardView card , String name , String link, String popup){
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(popup=="No"){
                    Intent intent = new Intent(Landing_page.this, Web_Viewer.class);
                    intent.putExtra(name, link);
                    startActivity(intent);

                } else if (popup == "website") {

                    showPopUPUrl(v);
                } else{
                    showPopup(v,link);
                }


            }
        });
    }

    private void showPopup(View anchorView ,String link) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up, null);


        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    Intent intent = new Intent(Landing_page.this, Web_Viewer.class);
                    intent.putExtra("link", link);
                    startActivity(intent);
                    popupWindow.dismiss();
                }
            }
        }, 3000);


    }
    private void showPopUPUrl(View anchorView) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.websitelink, null);


        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);


        EditText editText = popupView.findViewById(R.id.editTextText);
        Button searchButton = popupView.findViewById(R.id.search);


        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);


        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString().trim();


                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://" + url; // or "http://" depending on your preference
                }

                if (!url.isEmpty()) {
                    Intent intent = new Intent(Landing_page.this, Web_Viewer.class);
                    intent.putExtra("link", url);
                    startActivity(intent);
                    popupWindow.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter a valid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



}