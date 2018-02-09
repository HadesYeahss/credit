package creditos.com.creditohugo.Views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import creditos.com.creditohugo.Objects.Pago;
import creditos.com.creditohugo.R;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class PagoRecyclerAdapter extends RecyclerView.Adapter<PagoRecyclerAdapter.ViewHolder> {

    public interface PagoAdapterInterface{

        void onItemClick(Pago pago);
    }

    private ArrayList<Pago> mPagos;
    private final PagoAdapterInterface mListener;

    public PagoRecyclerAdapter(ArrayList<Pago> aPagos, PagoAdapterInterface listener) {
        mPagos = aPagos;
        this.mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextTotal;
        private LinearLayout mRootContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.rv_pago_name);
            mTextTotal = itemView.findViewById(R.id.rv_pago_total);
            mRootContainer = itemView.findViewById(R.id.row_container_pago);
        }
    }

    @Override
    public PagoRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pago, parent, false);
        ViewHolder pago = new ViewHolder(v);
        return pago;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pago current = mPagos.get(position);
        holder.mTextName.setText("test");
        holder.mTextTotal.setText(String.valueOf(current.getmPTotal()));
        holder.mRootContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(current);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mPagos.size();
    }
}
