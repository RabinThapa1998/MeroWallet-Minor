package com.example.merowalletv11;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    PieChart pieChart;

    private static double temp;
    double[] x = new double[9];
    private DrawerLayout drawer;

    public void validate(View view) {
        if(PromptActivity.getBudget()==0){
            Toast.makeText(this,"Please Set Your Budget First!",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent in = new Intent(com.example.merowalletv11.MainActivity.this, ExpenseActivity.class);
            startActivity(in);
            finish();}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar=findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        pieChart=(PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleRadius(40f);

        ArrayList<PieEntry> yvalues= new ArrayList<>();

        x = ExpenseActivity.retArray();

        for(int i=0;i<9;i++) {
            if (x[i] != 0) {

                if (i == 0)
                    yvalues.add(new PieEntry((float) x[0], "Food"));
                if (i == 1)
                    yvalues.add(new PieEntry((float) x[1], "Bill"));
                if (i == 2)
                    yvalues.add(new PieEntry((float) x[2], "Shopping"));
                if (i == 3)
                    yvalues.add(new PieEntry((float) x[3], "Clothing"));
                if (i == 4)
                    yvalues.add(new PieEntry((float) x[4], "Travel"));
                if (i == 5)
                    yvalues.add(new PieEntry((float) x[5], "Education"));
                if (i == 6)
                    yvalues.add(new PieEntry((float) x[6], "Entertainment"));
                if (i == 7)
                    yvalues.add(new PieEntry((float) x[7], "Credit Card"));
                if (i == 8)
                    yvalues.add(new PieEntry((float) x[8], "Other Expenses"));

            }
        }


        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        for(int i=0;i<9;i++) {
            if(x[i]!=0) {
                PieDataSet dataSet = new PieDataSet(yvalues, "");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData data = new PieData((dataSet));
                data.setValueTextSize(12f);
                data.setValueTextColor(Color.BLACK);
                pieChart.setData(data);
            }
        }

        temp= PromptActivity.getBudget()-ExpenseActivity.getExpense();

        TextView txt1=(TextView) findViewById(R.id.editTextResult);
        txt1.setText(""+temp);

        TextView txt2=(TextView) findViewById(R.id.expense);
        txt2.setText(""+ExpenseActivity.getExpense());

        TextView txt3=(TextView) findViewById(R.id.cash_amt);
        TextView txt4=(TextView) findViewById(R.id.card_amt);
        txt4.setText(""+ExpenseActivity.getCardExpense());
        txt3.setText(""+ExpenseActivity.getCashExpense());

       drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer, toolbar,
               R.string.navigation_drawer_open,R.string.navigation_drawer_close);
       drawer.addDrawerListener(toggle);
       toggle.syncState();
    }


    public void validate1(View view) {
        Intent in = new Intent(com.example.merowalletv11.MainActivity.this, PromptActivity.class);
        startActivity(in);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer((GravityCompat.START));
        }else{
        super.onBackPressed();
    }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoriesFragment()).commit();
                break;
            case R.id.nav_records:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RecordsFragment()).commit();
                        break;
           /* case R.id.nav_reports:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReportsFragment()).commit();
                        break;*/
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatisticsFragment()).commit();
                        break;
            /*case R.id.nav_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
                break;*/
            case R.id.nav_aboutus:
                Intent in = new Intent(com.example.merowalletv11.MainActivity.this, AboutUsActivity.class);
                startActivity(in);
                break;
            case R.id.nav_logout:
                Intent in1 = new Intent(com.example.merowalletv11.MainActivity.this, LoginActivity.class);
                startActivity(in1);
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

