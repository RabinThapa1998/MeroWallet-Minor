package com.example.merowalletv11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper MwDb;
    Button btnAddData;
    private TextInputLayout editfirst,editlast,edituser,editpassword,editconfirm,editaddress,editphone,editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        MwDb= new DatabaseHelper(this);

        editfirst= findViewById(R.id.text_input_firstname);
        editlast=  findViewById(R.id.text_input_lastname);
        edituser=  findViewById(R.id.text_input_username);
        editpassword= findViewById(R.id.text_input_password);
        editconfirm= findViewById(R.id.text_input_confirmPassword);
        editaddress= findViewById(R.id.text_input_address);
        editphone = findViewById(R.id.text_input_phone);
        editemail= findViewById(R.id.text_input_email);


    }
    private boolean validateEmail() {
        String emailInput = editemail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()){
            editemail.setError("Field Can't Be Empty");
            return false;
        }else{
            editemail.setError(null);
            return true;
        }
    }

    private boolean validateUsername(){
        String username = edituser.getEditText().getText().toString().trim();
        if(username.isEmpty()){
            edituser.setError("Filed Can't Be Empty");
            return false;
        }else if(username.length()>15){
            edituser.setError("Username too long");
            return false;

        }else {
            edituser.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String passwordInput = editpassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()){
            editpassword.setError("Field Can't Be Empty");
            return false;
        }else{
            editpassword.setError(null);
            return true;
        }
    }

    public void AddData(View view)
    {
        if(!validateEmail() | !validateUsername() | !validatePassword())
        {
            return;
        }

        String input = "Email: " + editemail.getEditText().getText().toString();
        input += "\n";
        input += "username: " + edituser.getEditText().getText().toString();
        input += "\n";
        input += "password: " + editpassword.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();


                        boolean isInserted= MwDb.insertData(editfirst.getEditText().getText().toString(),
                                editlast.getEditText().getText().toString(),
                                edituser.getEditText().getText().toString(),
                                editpassword.getEditText().getText().toString(),
                                editconfirm.getEditText().getText().toString(),
                                editaddress.getEditText().getText().toString(),
                                editphone.getEditText().getText().toString(),
                                editemail.getEditText().getText().toString());

                        if(isInserted= true)
                        {
                            Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignupActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();


                        }

                        else
                            Toast.makeText(SignupActivity.this,"UNSUCCESSFUL", Toast.LENGTH_LONG).show();


    }
}
