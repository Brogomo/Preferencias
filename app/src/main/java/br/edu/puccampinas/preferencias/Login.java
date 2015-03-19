package br.edu.puccampinas.preferencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class Login extends ActionBarActivity implements View.OnClickListener {

    private EditText etUser;
    private EditText etPassword;
    private CheckBox cbKeepConnected;
    private Button btnLogin;
    private Context context;
    private SharedPreferences sharedPref;
    private Boolean keepConnected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = (EditText)this.findViewById(R.id.etUser);
        etPassword = (EditText)this.findViewById(R.id.etPassword);
        cbKeepConnected = (CheckBox)this.findViewById(R.id.cbKeepConnected);
        btnLogin = (Button)this.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        context = this;
        sharedPref = context.getSharedPreferences("configs", Context.MODE_PRIVATE);

    }


    @Override
    protected void onResume() {
        super.onResume();
        //verificar se a preferencia já foi criada e está true
        keepConnected = sharedPref.getBoolean("keepConnected", false);
        if(keepConnected){
            Intent iPrincipal = new Intent(Login.this, Principal.class);
            Login.this.startActivity(iPrincipal);
            this.finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preferencias, menu);
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

    @Override
    public void onClick(View v) {

        //exemplo didático. jamais fazer isso!!!
        if( (etUser.getText().toString().equals("fulano")) && (etPassword.getText().toString().equals("xpto"))){
            //usuario autorizado. verificar se quer manter conectado
            if(cbKeepConnected.isChecked()){
                //guardar as preferências (manter conectado true)
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("keepConnected", true);
                editor.commit();
            }
            Intent iPrincipal = new Intent(Login.this, Principal.class);
            Login.this.startActivity(iPrincipal);
            this.finish();
        }
    }
}
