package br.edu.puccampinas.preferencias;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Principal extends ActionBarActivity implements View.OnClickListener{

    private SharedPreferences sharedPref;

    private Button btnExit;
    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        sharedPref = Principal.this.getSharedPreferences("configs", Principal.this.MODE_PRIVATE);

        btnClose = (Button)this.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        btnExit = (Button)this.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
        switch (v.getId()){
            case R.id.btnExit:
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("keepConnected", false);
                editor.commit();
                this.finish();
                break;
            case R.id.btnClose:
                this.finish();
        }
    }
}
