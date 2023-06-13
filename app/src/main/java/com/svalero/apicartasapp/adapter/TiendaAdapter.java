package com.svalero.apicartasapp.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.apicartasapp.R;
import com.svalero.apicartasapp.contract.Carta.DeleteCartaContract;
import com.svalero.apicartasapp.contract.Tienda.DeleteTiendaContract;
import com.svalero.apicartasapp.domain.Tienda;
import com.svalero.apicartasapp.presenter.Tienda.DeleteTiendaPresenter;
import com.svalero.apicartasapp.view.Tienda.ModifyTiendaView;

import java.util.List;

public class TiendaAdapter extends RecyclerView.Adapter<TiendaAdapter.TiendaHolder> implements DeleteCartaContract.View {

    private Context context;
    private List<Tienda> tiendaList;
    private DeleteTiendaPresenter presenter;

    public TiendaAdapter(Context context, List<Tienda> dataList) {
        this.context = context;
        this.tiendaList = dataList;
        presenter = new DeleteTiendaPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public TiendaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_tienda_item, parent, false);
        return new TiendaHolder(view);
    }


    @Override
    public void onBindViewHolder(TiendaHolder holder, int position) {
        holder.tiendaNombre.setText(tiendaList.get(position).getNombre());
        holder.tiendaDireccion.setText(tiendaList.get(position).getDireccion());
        holder.tiendaCodigoPostal.setText(tiendaList.get(position).getCodigoPostal());
        holder.tiendaEmail.setText(tiendaList.get(position).getEmail());
        holder.tiendaTelefono.setText(tiendaList.get(position).getTelefono());

    }

    @Override
    public int getItemCount() {
        return tiendaList.size();
    }

    public void showMessage(String message) {

    }

    public void showError(String errorMessage) {

    }

    public class TiendaHolder extends RecyclerView.ViewHolder {
        public TextView tiendaNombre;
        public TextView tiendaDireccion;
        public TextView tiendaCodigoPostal;
        public TextView tiendaEmail;
        public TextView tiendaTelefono;


        public Button modifyTiendaButton;
        public Button deleteTiendaButton;
        public View parentView;

        public TiendaHolder(View view) {
            super(view);
            parentView = view;

            tiendaNombre = view.findViewById((R.id.tienda_nombre));
            tiendaDireccion = view.findViewById(R.id.tienda_direccion);
            tiendaCodigoPostal = view.findViewById(R.id.tienda_codigoPostal);
            tiendaEmail = view.findViewById(R.id.tienda_email);
            tiendaTelefono = view.findViewById(R.id.tienda_telefono);


            modifyTiendaButton = view.findViewById(R.id.modify_tienda_button);
            deleteTiendaButton = view.findViewById(R.id.delete_tienda_button);

            modifyTiendaButton.setOnClickListener(v -> modifyTienda(getAdapterPosition()));
            deleteTiendaButton.setOnClickListener(v -> deleteTienda(getAdapterPosition()));
        }
        private void modifyTienda(int position) {
            Tienda tienda = tiendaList.get(position);

            Intent intent = new Intent(context, ModifyTiendaView.class);
            intent.putExtra("tienda", String.valueOf(tienda));
            context.startActivity(intent);
        }

        private void deleteTienda(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.ask_delete_tienda_message)
                    .setTitle(R.string.delete_tienda_message)
                    .setPositiveButton("Yes", (dialog, id) -> { //añadir boton de si
                        Tienda tienda = tiendaList.get(position);
                        presenter.deleteTienda(tienda.getId());

                        tiendaList.remove(position); //borrar de la lista que se muestra
                        notifyItemRemoved(position); //refrescar la lista sin el elemento borrado
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
