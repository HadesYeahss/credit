package creditos.com.creditohugo.Views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import creditos.com.creditohugo.Controllers.NewCreditControler;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.R;

public class NewCreditActivity extends AppCompatActivity
        implements View.OnClickListener, NewCreditControler.NewCreditIterface{

    private NewCreditControler mController;
    private Calendar mCalendar;
    private DatePickerDialog.OnDateSetListener mDate;
    public static final int RESULT_NEW_ID = 1;

    /*Campos de el formulario*/
    private EditText mName;
    private EditText mAmount;
    private EditText mInterest;
    private Spinner mNoPayments;
    private RadioGroup mTypePayment;
    private RadioButton mMensual;
    private RadioButton mQuincenal;
    private RadioButton mSemanal;
    private EditText mDatePayment;
    private Button mGuardar;
    private int mNoPaymentsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_credit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new_credit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mController = new NewCreditControler(this);

        mCalendar = Calendar.getInstance();
        mName = (EditText) findViewById(R.id.edit_text_credit_name);
        mAmount = (EditText) findViewById(R.id.edit_text_amount);
        mInterest = (EditText) findViewById(R.id.edit_text_annual_interest);
        mNoPayments = (Spinner) findViewById(R.id.spinner_number_of_payments);
        mTypePayment = (RadioGroup) findViewById(R.id.tipo_pago);
        mMensual = (RadioButton) findViewById(R.id.radio_button_mensual);
        mQuincenal = (RadioButton) findViewById(R.id.radio_button_quincenal);
        mSemanal = (RadioButton) findViewById(R.id.radio_button_semanal);
        mDatePayment = (EditText) findViewById(R.id.edit_text_date_of_payments);
        mGuardar = (Button) findViewById(R.id.btn_guardar);
        mGuardar.setOnClickListener(this);
        // set calendar date and update editDate
        mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        mDatePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewCreditActivity.this, mDate, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.number_of_payments_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNoPayments.setAdapter(adapter);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mDatePayment.setText(sdf.format(mCalendar.getTime()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_guardar:
                if (validateFields()) {
                    Cotizacion cotizacion = new Cotizacion();
                    cotizacion.setName(mName.getText().toString());
                    cotizacion.setAmount(Double.parseDouble(mAmount.getText().toString()));
                    cotizacion.setInterest(Double.parseDouble(mInterest.getText().toString()));
                    cotizacion.setNoPayments(Integer.parseInt(mNoPayments.getSelectedItem().toString()));
                    long dateMil = mCalendar.getTimeInMillis();
                    if (mMensual.isChecked()) {
                        cotizacion.setTypePayment(1);
                    } else if (mQuincenal.isChecked()) {
                        cotizacion.setTypePayment(2);
                    } else{
                        cotizacion.setTypePayment(3);
                    }
                    Date date = new Date(dateMil);
                    cotizacion.setDatePayment(date);
                    mController.createCotizacion(cotizacion);
                }
                break;
        }
    }

    private boolean validateFields() {
        return true;
    }

    @Override
    public void saveComplete(boolean isSave) {
        Intent data = new Intent();
        data.putExtra("isSave", isSave);
        setResult(RESULT_OK, data);
        finish();
    }

}
