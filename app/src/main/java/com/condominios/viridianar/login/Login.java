package com.condominios.viridianar.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText user, password;
    Button start;
    String userString, pwString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        user = (EditText)findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.pw);
        start = (Button)findViewById(R.id.iniciar);

        userString = user.getText().toString();
        pwString = password.getText().toString();



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b (email)
                /*
                8 characters length
                2 letters in Upper Case
                1 Special Character (!@#$&*)
                2 numerals (0-9)
                3 letters in Lower Case
                ^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{6}$
                ^                         Start anchor
                (?=.*[A-Z].*[A-Z])        Ensure string has two uppercase letters.
                (?=.*[!@#$&*])            Ensure string has one special case letter.
                (?=.*[0-9].*[0-9])        Ensure string has two digits.
                (?=.*[a-z].*[a-z].*[a-z]) Ensure string has three lowercase letters.
                .{8}                      Ensure string is of length 8.
$                         End anchor.
                 */
                /*if (!Patterns.EMAIL_ADDRESS.matcher(userString).matches() && userString.isEmpty()){
                    Snackbar.make(findViewById(R.id.login_layout), "Ingresa un correo electrónico válido",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
                else*/ if (!pwString.matches("(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{6,}$")){
                    Snackbar.make(findViewById(R.id.login_layout), "Tu contraseña debe ser de longitud 6 y contener al menos 1 mayúscula y 1 número",
                            Snackbar.LENGTH_LONG)
                            .show();

                }else {
                    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        LoginConnection lc = new LoginConnection(getApplicationContext(), userString, pwString);
                        lc.execute();
                    } else {
                        // display error
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
