package pe.edu.upeu.registro.registromvc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TableMVCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_mvc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


/*
        TableLayout table = (TableLayout) findViewById(R.id.tableLayout);
        for (int i = 0; i < 5; i++) {
            TableRow row = new TableRow(this);
            TextView tvName = new TextView(this);
            TextView tvAge = new TextView(this);
            TextView tvMail = new TextView(this);

            tvName.setText("Omar");
            tvAge.setText(String.valueOf(23));
            tvMail.setText("omarinccjcc");
            row.addView(tvName);
            row.addView(tvAge);
            row.addView(tvMail);
            table.addView(row);
        }
        */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
