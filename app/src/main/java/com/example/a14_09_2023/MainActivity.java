package com.example.a14_09_2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText passwordLength;
    private CheckBox smallLetters;
    private CheckBox bigLetters;
    private CheckBox specialCharacters;
    private CheckBox numbers;

    private Button button;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        passwordLength = findViewById(R.id.characters);
        smallLetters = findViewById(R.id.smallLetters);
        bigLetters = findViewById(R.id.bigLetters);
        numbers = findViewById(R.id.numbersChar);
        specialCharacters = findViewById(R.id.specialChar);
        result = findViewById(R.id.result);
        button = findViewById(R.id.button);
        button.setOnClickListener(v -> generate());
        result.setOnLongClickListener(v -> copyText());
    }
    private boolean copyText(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("password", result.getText().toString());
        clipboard.setPrimaryClip(clip);
        return false;
    }
    private void generate() {
        if(passwordLength.getText().toString().trim().length() <= 0)
        {
            Toast.makeText(this , "no input", Toast.LENGTH_SHORT).show();
            return;
        }
//        else if(passwordLength.getText().toString().trim().length() > 6)
//        {
//            Toast.makeText(this , "too large input number", Toast.LENGTH_SHORT).show();
//            return;
//        }

        String resultString = "";
        String bigLettersString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallLettersString = "abcdefghijklmnopqrstuvwxyz";
        String numbersString = "0123456789";
        String specialCharactersString = "~`!@#$%^&*()-_=+|[]{};:,<.>/?";

        if (smallLetters.isChecked()) {
                resultString += smallLettersString;
        }
        if (bigLetters.isChecked()) {
                resultString += bigLettersString;
        }
        if (specialCharacters.isChecked()) {
                resultString += specialCharactersString;
        }
        if (numbers.isChecked()) {
                resultString += numbersString;
        }
        if (resultString.length() != 0) {
            Random random = new Random();
            int passwordLengthInt = Integer.parseInt(passwordLength.getText().toString());
            String resultText = "";

            for (int j = 0; j < passwordLengthInt; j++) {
                int index = random.nextInt(resultString.length());
                resultText += resultString.charAt(index);
            }
            result.setText(resultText);
        }
        else{
            Toast.makeText(this , "no categories provided", Toast.LENGTH_SHORT).show();
        }
        smallLetters.setSelected(false);
        bigLetters.setSelected(false);
        numbers.setSelected(false);
        specialCharacters.setSelected(false);
    }
}
