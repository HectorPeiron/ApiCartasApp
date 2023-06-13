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
import com.svalero.apicartasapp.contract.Fav.FavCartaAddContract;
import com.svalero.apicartasapp.domain.Carta;
import com.svalero.apicartasapp.domain.FavCarta;
import com.svalero.apicartasapp.presenter.Carta.DeleteCartaPresenter;
import com.svalero.apicartasapp.presenter.Fav.FavCartaAddPresenter;
import com.svalero.apicartasapp.view.Carta.ModifyCartaView;

import java.util.List;

public class CartaAdapter extends RecyclerView.Adapter<CartaAdapter.CartaHolder> implements DeleteCartaContract.View, FavCartaAddContract.View {

    private Context context;
    private List<Carta> cartaList;
    private View snackBarView;
    private DeleteCartaPresenter presenter;
    private FavCartaAddPresenter favPresenter;


    public CartaAdapter(Context context, List<Carta> cartaList) {
        this.context = context;
        this.cartaList = cartaList;
        presenter = new DeleteCartaPresenter(this);
        favPresenter = new FavCartaAddPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public CartaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_carta_item, parent, false);
        return new CartaHolder(view);
    }

    @Override
    public void onBindViewHolder(CartaHolder cartaHolder, int position) {

        cartaHolder.cartaNombre.setText(cartaList.get(position).getNombre());
        cartaHolder.cartaTipo.setText(cartaList.get(position).getTipo());
        cartaHolder.cartaColeccion.setText(cartaList.get(position).getColeccion());
        cartaHolder.cartaAnioSalida.setText(cartaList.get(position).getAnioSalidaString());

    }

    @Override
    public int getItemCount() {
        return cartaList.size();
    }

    @Override
    public void showError(String errorMessage) {
    }

    @Override
    public void showMessage(String message) {

    }


    public class CartaHolder extends RecyclerView.ViewHolder {
        public TextView cartaNombre;
        public TextView cartaTipo;
        public TextView cartaColeccion;
        public TextView cartaAnioSalida;

        public Button modifyCartaButton;
        public Button deleteCartaButton;
        public Button addFavCartaButton;

        public View parentView;

        public CartaHolder(View view) {
            super(view);
            parentView = view;

            cartaNombre = view.findViewById((R.id.carta_nombre));
            cartaTipo = view.findViewById((R.id.carta_tipo));
            cartaColeccion = view.findViewById((R.id.carta_coleccion));
            cartaAnioSalida = view.findViewById(R.id.carta_anioSalida);


            modifyCartaButton = view.findViewById(R.id.modify_carta_button);
            deleteCartaButton = view.findViewById(R.id.delete_carta_button);
            addFavCartaButton = view.findViewById(R.id.add_fav_carta_button);

            //botones
            modifyCartaButton.setOnClickListener(v -> modifyCarta(getAdapterPosition()));
            deleteCartaButton.setOnClickListener(v -> deleteCarta(getAdapterPosition()));
            addFavCartaButton.setOnClickListener(v -> addFavCarta(getAdapterPosition()));
        }


        private void modifyCarta(int position) {
            Carta carta = cartaList.get(position);

            Intent intent = new Intent(context, ModifyCartaView.class);
            intent.putExtra("carta", carta);
            context.startActivity(intent);
        }

        private void deleteCarta(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.ask_delete_carta_message)
                    .setTitle(R.string.delete_carta_title)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        Carta carta = cartaList.get(position);
                        presenter.deleteCarta(carta.getId());

                        cartaList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        private void addFavCarta(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Se añadirá a favoritos")
                    .setTitle("Añadir a Favoritos")
                    .setPositiveButton("Yes", (dialog, id) -> {
                        Carta carta = cartaList.get(position);

                        FavCarta favCarta = new FavCarta();
                        favCarta.setNombre(carta.getNombre());
                        favCarta.setTipo(carta.getTipo());
                        favCarta.setColeccion(carta.getColeccion());
                        favCarta.setAnioSalida(carta.getAnioSalida());
                        favPresenter.addFavCarta(favCarta);

                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }
}



