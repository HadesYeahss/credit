package creditos.com.creditohugo.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import creditos.com.creditohugo.Controllers.DetailCreditController;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.R;

public class DetailCreditActivity extends AppCompatActivity implements DetailCreditController.DetailCreditIterface{

    private DetailCreditController mController;
    private Toolbar mToolbar;
    public static final int RESULT_NEW_ID = 1;

    /*Campos de el formulario*/
    private TextView mName;
    private TextView mAmount;
    private TextView mInterest;
    private TextView mNoPayments;
    private TextView mTypePayments;
    private TextView mDatePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_credit);
        Intent i = getIntent();
        Cotizacion cotizacion = (Cotizacion) i.getSerializableExtra("cotizacion");
        mController = new DetailCreditController(this);
        mName = (TextView) findViewById(R.id.text_view_credit_name_detail);
        mAmount = (TextView) findViewById(R.id.text_view_amount_detail);
        mInterest = (TextView) findViewById(R.id.text_view_annual_interest_detail);
        mNoPayments = (TextView) findViewById(R.id.text_view_number_of_payments_detail);
        mTypePayments = (TextView) findViewById(R.id.text_view_type_payment_detail);
        mDatePayment = (TextView) findViewById(R.id.text_view_date_of_payments_detail);
        setData(cotizacion);
    }

    private void setData(Cotizacion cotizacion) {
        mName.setText(cotizacion.getName().toString());
        String smonto = String.valueOf(cotizacion.getAmount());
        String monto = getResources().getString(R.string.monto_credito_detalle,smonto);
        mAmount.setText(monto);
        String sinteres = String.valueOf(cotizacion.getInterest());
        String interes = getResources().getString(R.string.interes_anual_detalle);
        interes = interes.concat(" ").concat(sinteres);
        mInterest.setText(interes);
        String sNoPayments = String.valueOf(cotizacion.getNoPayments());
        String noPayments = getResources().getString(R.string.numero_pagos_detalle,sNoPayments);
        mNoPayments.setText(noPayments);
        String tipo = getResources().getString(R.string.tipo_pago_detalle);
        if(cotizacion.getTypePayment() == 1){
            tipo = tipo.concat("Mensual");
        }else if (cotizacion.getTypePayment() == 2){
           tipo = tipo.concat("Quincenal");
        }else{
            tipo = tipo.concat("Semanal");
        }
        mTypePayments.setText(tipo);
        mDatePayment.setText(cotizacion.getDatePayment().toString());



    }
}
