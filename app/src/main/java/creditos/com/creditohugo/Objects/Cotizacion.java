package creditos.com.creditohugo.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */
@DatabaseTable
public class Cotizacion implements Parcelable {

    public static final String ID_COTIZACION = "mId";
    public static final String NAME_COTIZACION = "mName";
    public static final String AMOUNT_COTIZACION = "mAmount";
    public static final String INTEREST_COTIZACION = "mInterest";
    public static final String NO_PAYMENTS_COTIZACION = "mNoPayments";
    public static final String TYPE_PAYMENTS_COTIZACION = "mTypePayment";
    public static final String DATE_COTIZACION = "mDatePayment";
    public static final String TOTAL_COTIZACION = "mTotal";
    public static final String TOTAL_CAPITAL_COTIZACION = "mCapitalTotal";
    public static final String TOTAL_INTEREST_COTIZACION = "mInterestTotal";
    public static final String ITOTAL_IVA_COTIZACION = "mIvaTotal";

    @DatabaseField(generatedId = true)
    private int mId;
    @DatabaseField(columnName = NAME_COTIZACION, canBeNull = false)
    private String mName;
    @DatabaseField(columnName = AMOUNT_COTIZACION)
    private double mAmount;
    @DatabaseField(columnName = INTEREST_COTIZACION)
    private double mInterest;
    @DatabaseField(columnName = NO_PAYMENTS_COTIZACION)
    private double mNoPayments;
    @DatabaseField(columnName = TYPE_PAYMENTS_COTIZACION)
    private int mTypePayment;
    @DatabaseField(columnName = DATE_COTIZACION)
    private Date mDatePayment;
    @DatabaseField(columnName = TOTAL_COTIZACION)
    private double mTotal;
    @DatabaseField(columnName = TOTAL_CAPITAL_COTIZACION)
    private double mCapitalTotal;
    @DatabaseField(columnName = TOTAL_INTEREST_COTIZACION)
    private double mInterestTotal;
    @DatabaseField(columnName = ITOTAL_IVA_COTIZACION)
    private double mIvaTotal;
    @ForeignCollectionField
    private ForeignCollection<Pago> mPagos;

    public Cotizacion() {
        // all persisted classes must define a no-arg constructor with at least package visibility
    }

    public Cotizacion(String aName, double aAamount, double aInterest, double aNoPayments,
                      int aTypePayment, Date aDatePayment, double aTotal, double aCapitalTotal,
                      double aInterestTotal, double aIvaTotal) {
        this.mName = aName;
        this.mAmount = aAamount;
        this.mInterest = aInterest;
        this.mNoPayments = aNoPayments;
        this.mTypePayment = aTypePayment;
        this.mDatePayment = aDatePayment;
        this.mTotal = aTotal;
        this.mCapitalTotal = aCapitalTotal;
        this.mInterestTotal = aInterestTotal;
        this.mIvaTotal = aIvaTotal;
    }

    protected Cotizacion(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mAmount = in.readDouble();
        mInterest = in.readDouble();
        mNoPayments = in.readDouble();
        mTypePayment = in.readInt();
        mTotal = in.readDouble();
        mCapitalTotal = in.readDouble();
        mInterestTotal = in.readDouble();
        mIvaTotal = in.readDouble();
    }

    public static final Creator<Cotizacion> CREATOR = new Creator<Cotizacion>() {
        @Override
        public Cotizacion createFromParcel(Parcel in) {
            return new Cotizacion(in);
        }

        @Override
        public Cotizacion[] newArray(int size) {
            return new Cotizacion[size];
        }
    };

    public int getmId() {
        return mId;
    }

    public void setmId(int aId) {
        this.mId = aId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String aName) {
        this.mName = aName;
    }

    public double getmAmount() {
        return mAmount;
    }

    public void setmAmount(double aAamount) {
        this.mAmount = aAamount;
    }

    public double getmInterest() {
        return mInterest;
    }

    public void setmInterest(double aInterest) {
        this.mInterest = aInterest;
    }

    public double getmNoPayments() {
        return mNoPayments;
    }

    public void setmNoPayments(double aNoPayments) {
        this.mNoPayments = aNoPayments;
    }

    public int getmTypePayment() {
        return mTypePayment;
    }

    public void setmTypePayment(int aTypePayment) {
        this.mTypePayment = aTypePayment;
    }

    public Date getmDatePayment() {
        return mDatePayment;
    }

    public void setmDatePayment(Date aDatePayment) {
        this.mDatePayment = aDatePayment;
    }

    public double getmTotal() {
        return mTotal;
    }

    public void setmTotal(double aTotal) {
        this.mTotal = aTotal;
    }

    public double getmCapitalTotal() {
        return mCapitalTotal;
    }

    public void setmCapitalTotal(double aCapitalTotal) {
        this.mCapitalTotal = aCapitalTotal;
    }

    public double getmInterestTotal() {
        return mInterestTotal;
    }

    public void setmInterestTotal(double aInterestTotal) {
        this.mInterestTotal = aInterestTotal;
    }

    public double getmIvaTotal() {
        return mIvaTotal;
    }

    public void setmIvaTotal(double aIvaTotal) {
        this.mIvaTotal = aIvaTotal;
    }

    public ForeignCollection<Pago> getmPagos() {
        return mPagos;
    }

    public void setmPagos(ForeignCollection<Pago> aPagos) {
        this.mPagos = aPagos;
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return mName.equals(((Cotizacion) other).mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeDouble(mAmount);
        parcel.writeDouble(mInterest);
        parcel.writeDouble(mNoPayments);
        parcel.writeInt(mTypePayment);
        parcel.writeDouble(mTotal);
        parcel.writeDouble(mCapitalTotal);
        parcel.writeDouble(mInterestTotal);
        parcel.writeDouble(mIvaTotal);
    }
}
