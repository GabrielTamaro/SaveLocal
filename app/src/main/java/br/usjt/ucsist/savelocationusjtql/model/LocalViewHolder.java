package br.usjt.ucsist.savelocationusjtql.model;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.usjt.ucsist.savelocationusjtql.R;

public class LocalViewHolder extends RecyclerView.ViewHolder {
    TextView DataCadastro;
    TextView TextViewCEP;
    TextView TextViewRua;
    TextView TextViewBairro;
    TextView TextViewCidade;
    TextView TextViewEstado;
    TextView DadosDeLatitude;
    TextView DadosDeLongitude;

    LocalViewHolder(View raiz){
        super(raiz);
        this.DataCadastro = raiz.findViewById(R.id.DataCadastro);
        this.TextViewCEP = raiz.findViewById(R.id.TextViewCEP);
        this.TextViewRua = raiz.findViewById(R.id.TextViewRua);
        this.TextViewBairro = raiz.findViewById(R.id.TextViewBairro);
        this.TextViewCidade = raiz.findViewById(R.id.TextViewCidade);
        this.TextViewEstado = raiz.findViewById(R.id.TextViewEstado);
        this.DadosDeLatitude = raiz.findViewById(R.id.DadosDeLatitude);
        this.DadosDeLongitude = raiz.findViewById(R.id.DadosDeLongitude);
    }
}
