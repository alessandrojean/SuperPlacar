package io.github.alessandrojean.superplacar;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.alessandrojean.superplacar.domain.Gol;
import io.github.alessandrojean.superplacar.domain.Jogo;

/**
 * Created by Desktop on 14/12/2017.
 */

public class JogosAdapter extends RecyclerView.Adapter<JogosAdapter.ViewHolder> {
    private Context context;
    private List<Jogo> jogos;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatus;
        ImageView ivTime1;
        TextView tvNomeTime1;
        TextView tvGolsTime1;
        RecyclerView rvTime1;
        ImageView ivTime2;
        TextView tvGolsTime2;
        TextView tvNomeTime2;
        RecyclerView rvTime2;

        ViewHolder(View itemView) {
            super(itemView);

            tvStatus = itemView.findViewById(R.id.tv_status);
            ivTime1 = itemView.findViewById(R.id.iv_time_1);
            tvNomeTime1 = itemView.findViewById(R.id.tv_nome_time_1);
            tvGolsTime1 = itemView.findViewById(R.id.tv_gols_time_1);
            rvTime1 = initRecyclerView(R.id.rv_gols_time_1, R.layout.item_gol_left);

            ivTime2 = itemView.findViewById(R.id.iv_time_2);
            tvGolsTime2 = itemView.findViewById(R.id.tv_gols_time_2);
            tvNomeTime2 = itemView.findViewById(R.id.tv_nome_time_2);
            rvTime2 = initRecyclerView(R.id.rv_gols_time_2, R.layout.item_gol_right);
        }

        private RecyclerView initRecyclerView(int rvId, int idLayout) {
            RecyclerView recyclerView = itemView.findViewById(rvId);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
            mLayoutManager.setAutoMeasureEnabled(true);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(new GolsAdapter(context, idLayout));

            return recyclerView;
        }

        private void setData( Jogo jogo ){
            tvStatus.setText(
                    Html.fromHtml("<b>"+jogo.getStatus()+"</b> ("+jogo.getInicio()+")"));

            Picasso.with(context)
                    .load(jogo.getTime1().getImagemUrl())
                    .into(ivTime1);
            tvNomeTime1.setText(String.valueOf(jogo.getTime1().getNome()));
            tvGolsTime1.setText(String.valueOf(jogo.getTime1().getGols()));
            updateRecyclerView(rvTime1,jogo.getTime1().getGolsLista());

            Picasso.with(context)
                    .load(jogo.getTime2().getImagemUrl())
                    .into(ivTime2);
            tvNomeTime2.setText(String.valueOf(jogo.getTime2().getNome()));
            tvGolsTime2.setText(String.valueOf( jogo.getTime2().getGols()));
            updateRecyclerView(rvTime2, jogo.getTime2().getGolsLista());
        }

        private void updateRecyclerView(RecyclerView rv, List<Gol> gols) {
            GolsAdapter adapter = (GolsAdapter) rv.getAdapter();
            adapter.setGols(gols);
        }
    }

    public JogosAdapter(Context context, List<Jogo> jogos) {
        this.context = context;
        this.jogos = jogos;
    }

    @Override
    public JogosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from( context )
                .inflate(R.layout.item_jogo, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( jogos.get( position ) );
    }

    @Override
    public int getItemCount() {
        return jogos.size();
    }
}
