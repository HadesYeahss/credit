package creditos.com.creditohugo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import creditos.com.creditohugo.Controllers.FragmentHomeController;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.R;
import creditos.com.creditohugo.Views.CotizacionRecyclerAdapter;
import creditos.com.creditohugo.Views.DetailCreditActivity;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentHome extends Fragment implements FragmentHomeController.MainAcvitivyIterface,
        CotizacionRecyclerAdapter.cotizacionAdapterInterface
{

    private FragmentHomeController mActivityControler;
    private ArrayList<Cotizacion> mCotizacionList;
    public static final String TAG = "FragmentHost";
    public static final int FRAGMENT_HOME = 2;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivityControler = new FragmentHomeController(this);
        View v = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        mCotizacionList = new ArrayList<>();
        mCotizacionList = mActivityControler.loadCotizaciones();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.cotizacion_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CotizacionRecyclerAdapter(mCotizacionList,this);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }
    public void dataSaved(){
        mCotizacionList = mActivityControler.loadCotizaciones();
        mAdapter = new CotizacionRecyclerAdapter(mCotizacionList,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(Cotizacion cotizacion) {
        Intent intent = new Intent(getActivity(), DetailCreditActivity.class);
        intent.putExtra("cotizacion",cotizacion);
        startActivityForResult(intent,FRAGMENT_HOME);
    }
}
