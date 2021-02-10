package com.example.question;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    TextView  textViewAnswer, textViewLogo, textViewLogo1;
    EditText editTextPhone,editTextQuestion;
    AppCompatButton buttonSend;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        textViewAnswer = findViewById(R.id.textViewAnswer);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextQuestion = findViewById(R.id.editTextQuestion);
        buttonSend = findViewById(R.id.buttonSend);
        textViewLogo =findViewById(R.id.textViewLogo);
        textViewLogo1 =findViewById(R.id.textViewLogo1);
        textViewAnswer.setVisibility(View.INVISIBLE);



        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = myRef.getKey();
                String phone = editTextPhone.getText().toString();
                String question = editTextQuestion.getText().toString();
                User user = new User(id,phone,question);


                if (phone.length() >= 10 && phone.length() <= 16 && question != null) {

                    myRef.push().setValue(user);
                    editTextQuestion.setVisibility(View.INVISIBLE);
                    editTextPhone.setVisibility(View.INVISIBLE);
                    buttonSend.setVisibility(View.INVISIBLE);
                    textViewLogo1.setVisibility(View.INVISIBLE);
                    textViewLogo.setVisibility(View.INVISIBLE);
                    textViewAnswer.setVisibility(View.VISIBLE);
                    //запись телефон:вопрос
                    //myRef.child(phone).setValue(question);
                    textViewAnswer.setText("Ответ: Юрист свяжется с Вами по телефону в ближайшее время");
                }
                else {
                    Toast.makeText(MainActivity.this,"Введите корректную информацию",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}