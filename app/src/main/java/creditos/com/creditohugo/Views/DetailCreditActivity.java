package creditos.com.creditohugo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import creditos.com.creditohugo.Controllers.DetailCreditController;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.Objects.Pago;
import creditos.com.creditohugo.R;
import creditos.com.creditohugo.Utils;

public class DetailCreditActivity extends AppCompatActivity implements
        //PagoRecyclerAdapter.PagoAdapterInterface,
        DetailCreditController.DetailCreditIterface{

    private DetailCreditController mController;

    private TextView mTotal;
    private TextView mTotalName;
    private TextView mInterestPercentage;
    private TextView mTotalCapital;
    private TextView mTotalInterest;
    private TextView mNoPayments;

    private ArrayList<Pago> mPagosList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_credit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail_credit);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        Cotizacion cotizacion = (Cotizacion) i.getSerializableExtra("cotizacion");
        mController = new DetailCreditController(this);
        mTotal = (TextView) findViewById(R.id.total_credit);
        mTotalName = (TextView) findViewById(R.id.total_credit_footer);
        mInterestPercentage = (TextView) findViewById(R.id.total_interes_porcentaje);
        mTotalCapital = (TextView) findViewById(R.id.total_credit_solicitado);
        mTotalInterest = (TextView) findViewById(R.id.total_interes);
        mNoPayments = (TextView) findViewById(R.id.numero_pagos);
        setData(cotizacion);


        mRecyclerView = (RecyclerView) findViewById(R.id.cotizacion_detail_recycler_view);
       /* mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PagoRecyclerAdapter(mPagosList,this);
        mRecyclerView.setAdapter(mAdapter);*/
    }

    private void setData(Cotizacion cotizacion) {

        mTotal.setText(getResources().getString(R.string.detalle_monto_total,
                String.valueOf(Utils.round(cotizacion.getmTotal(),2))));

        mTotalName.setText(getResources().getString(R.string.detalle_monto_total_footer,
                cotizacion.getmName()));

        mInterestPercentage.setText(getResources().getString(R.string.detalle_monto_interes,
                String.valueOf(cotizacion.getmInterest()).concat(" %")));

        mTotalCapital.setText( getResources().getString(R.string.detalle_monto_solicitado,
                String.valueOf(cotizacion.getmAmount())));

        mTotalInterest.setText(getResources().getString(R.string.detalle_monto_interes_total,
                String.valueOf(Utils.round(cotizacion.getmInterestTotal(),2))));


        mNoPayments.setText(getResources().getString(R.string.detalle_pagos,
                (int)Utils.round(cotizacion.getmNoPayments(),0)));

    }

    /*@Override
    public void onItemClick(Pago pago) {

    }*/
}
