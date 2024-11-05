package com.example.wetravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wetravel.model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName,editTextSurname, editTextEmail, editTextPassword, editTextPassword2;

    Button register;

    TextView signIn;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.register_name);

        editTextSurname = findViewById(R.id.register_surname);

        editTextEmail = findViewById(R.id.register_email);

        editTextPassword = findViewById(R.id.register_password);

        editTextPassword2 = findViewById(R.id.register_password_rep);

        register = findViewById(R.id.registerButton);

        signIn = findViewById(R.id.go_to_login_button);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password,password2, name, surname;

                name = String.valueOf(editTextName.getText());
                surname = String.valueOf(editTextSurname.getText());
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                password2 = String.valueOf(editTextPassword2.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Irasykite El.pasta!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "Irasykite Varda!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(surname)){
                    Toast.makeText(RegisterActivity.this, "Irasykite Pavarde!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Irasykite slaptazodi!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password2)){
                    Toast.makeText(RegisterActivity.this, "Irasykite pakartotina slaptazodi!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Check if the passwords match
                if (!password.equals(password2)) {
                    Toast.makeText(RegisterActivity.this, "Slaptazodiai nesutampa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(!task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "Registracija nepavyko, bandykite dar karta", Toast.LENGTH_SHORT).show();
                                } else {
                                    UserData usrData = new UserData(name, surname, email, password);
                                    String uid = task.getResult().getUser().getUid();
                                    firebaseDatabase.getReference(uid).setValue(usrData)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Intent  intenToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                                                    startActivity(intenToLogin);
                                                }
                                            });
                                }
                            }
                        });


            }
        });




    }
}