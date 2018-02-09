package creditos.com.creditohugo.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */
@DatabaseTable
public class Pago implements Parcelable {


    public static final String COTIZACION_ID_FIELD_NAME = "cotizacion_id";


    @DatabaseField(generatedId = true)
    private int mId;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COTIZACION_ID_FIELD_NAME)
    private Cotizacion mCotizacion;
    @DatabaseField
    private double mPInteres;
    @DatabaseField
    private double mPCapital;
    @DatabaseField
    private double mPIva;
    @DatabaseField
    private double mPTotal;
    @DatabaseField
    private double mSaldo;
    @DatabaseField
    private double mAdelanto;
    @DatabaseField
    private boolean mStatus;
    @DatabaseField(dataType = DataType.DATE_STRING)
    private Date mDiaPago;

    public Pago() {

    }

    public Pago(Cotizacion aCotizacion, double aPInteres, double aPCapital,
                double aPIva, double aPTotal, double aAdelanto, boolean aStatus,
                Date aDiaPago) {
        this.mCotizacion = aCotizacion;
        this.mPInteres = aPInteres;
        this.mPCapital = aPCapital;
        this.mPIva = aPIva;
        this.mPTotal = aPTotal;
        this.mAdelanto = aAdelanto;
        this.mStatus = aStatus;
        this.mDiaPago = aDiaPago;
    }

    protected Pago(Parcel in) {
        mId = in.readInt();
        mCotizacion = in.readParcelable(Cotizacion.class.getClassLoader());
        mPInteres = in.readDouble();
        mPCapital = in.readDouble();
        mPIva = in.readDouble();
        mPTotal = in.readDouble();
        mSaldo = in.readDouble();
        mAdelanto = in.readDouble();
        mStatus = in.readByte() != 0;
    }

    public static final Creator<Pago> CREATOR = new Creator<Pago>() {
        @Override
        public Pago createFromParcel(Parcel in) {
            return new Pago(in);
        }

        @Override
        public Pago[] newArray(int size) {
            return new Pago[size];
        }
    };

    public int getmId() {
        return mId;
    }

    public void setmId(int aId) {
        this.mId = aId;
    }

    public Cotizacion getmCotizacion() {
        return mCotizacion;
    }

    public void setmCotizacion(Cotizacion aCotizacion) {
        this.mCotizacion = aCotizacion;
    }

    public double getmPInteres() {
        return mPInteres;
    }

    public void setmPInteres(double aPInteres) {
        this.mPInteres = aPInteres;
    }

    public double getmPCapital() {
        return mPCapital;
    }

    public void setmPCapital(double aPCapital) {
        this.mPCapital = aPCapital;
    }

    public double getmPIva() {
        return mPIva;
    }

    public void setmPIva(double aPIva) {
        this.mPIva = aPIva;
    }

    public double getmPTotal() {
        return mPTotal;
    }

    public void setmPTotal(double aPTotal) {
        this.mPTotal = aPTotal;
    }

    public double getmSaldo() {
        return mSaldo;
    }

    public void setmSaldo(double aSaldo) {
        this.mSaldo = aSaldo;
    }

    public double getmAdelanto() {
        return mAdelanto;
    }

    public void setmAdelanto(double aAdelanto) {
        this.mAdelanto = aAdelanto;
    }

    public boolean ismStatus() {
        return mStatus;
    }

    public void setmStatus(boolean aStatus) {
        this.mStatus = aStatus;
    }

    public Date getmDiaPago() {
        return mDiaPago;
    }

    public void setmDiaPago(Date aDiaPago) {
        this.mDiaPago = aDiaPago;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeParcelable(mCotizacion, i);
        parcel.writeDouble(mPInteres);
        parcel.writeDouble(mPCapital);
        parcel.writeDouble(mPIva);
        parcel.writeDouble(mPTotal);
        parcel.writeDouble(mSaldo);
        parcel.writeDouble(mAdelanto);
        parcel.writeByte((byte) (mStatus ? 1 : 0));
    }
}
