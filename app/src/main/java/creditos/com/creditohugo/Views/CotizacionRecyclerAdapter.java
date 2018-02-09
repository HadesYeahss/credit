package creditos.com.creditohugo.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.R;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class CotizacionRecyclerAdapter extends RecyclerView.Adapter<CotizacionRecyclerAdapter.ViewHolder> {

    public interface cotizacionAdapterInterface{

        void onItemClick(Cotizacion cotizacion);
    }

    private ArrayList<Cotizacion> mCotizaciones;
    private final cotizacionAdapterInterface mListener;

    public CotizacionRecyclerAdapter(ArrayList<Cotizacion> aCotizaciones,cotizacionAdapterInterface listener) {
        mCotizaciones = aCotizaciones;
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextTotal;
        private LinearLayout mRootContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.rv_cotizacion_name);
            mTextTotal = itemView.findViewById(R.id.rv_cotizacion_total);
            mRootContainer = itemView.findViewById(R.id.row_container_cotizacion);
        }
    }

    @Override
    public CotizacionRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cotizacion, parent, false);
        ViewHolder cotizacion = new ViewHolder(v);
        return cotizacion;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cotizacion current = mCotizaciones.get(position);
        holder.mTextName.setText(current.getmName());
        holder.mTextTotal.setText(String.valueOf(current.getmAmount()));
        holder.mRootContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(current);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCotizaciones.size();
    }
}
