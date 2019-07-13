package com.example.merowalletv11;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper MwDb;
    Button btnAddData;
    EditText editfirst,editlast,edituser,editpassword,editconfirm,editaddress,editphone,editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        MwDb= new DatabaseHelper(this);

        editfirst=(EditText) findViewById(R.id.firstname);
        editlast=(EditText) findViewById(R.id.lastname);
        edituser=(EditText) findViewById(R.id.username);
        editpassword=(EditText) findViewById(R.id.password);
        editconfirm=(EditText) findViewById(R.id.confirm);
        editaddress=(EditText) findViewById(R.id.address);
        editphone =(EditText) findViewById(R.id.phone);
        editemail=(EditText) findViewById(R.id.email);
        btnAddData=(Button)findViewById(R.id.signup) ;
        validateEmail();


    }
    public void AddData(View view)
    {

                        boolean isInserted= MwDb.insertData(editfirst.getText().toString(),editlast.getText().toString(),
                                edituser.getText().toString(),editpassword.getText().toString(),editconfirm.getText().toString(),
                                editaddress.getText().toString(),editphone.getText().toString(),editemail.getText().toString());

                        if(isInserted= true)
                        {
                            Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignupActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();


                        }

                        else
                            Toast.makeText(SignupActivity.this,"UNSUCCESSFUL", Toast.LENGTH_LONG).show();


    }
    public boolean validateEmail()
    {
        String emailInput=editemail.getText().toString().trim();

            if(emailInput.isEmpty())
            {
                editemail.setError("Field can't be empty");
                return false;
            }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            editemail.setError("Please Enter a valid email address");
            return  false;
        }

            else
        {editemail.setError(null);
        return true;
        }
    }
}
