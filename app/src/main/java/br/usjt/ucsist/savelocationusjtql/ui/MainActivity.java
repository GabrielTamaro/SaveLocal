package br.usjt.ucsist.savelocationusjtql.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.usjt.ucsist.savelocationusjtql.R;
import br.usjt.ucsist.savelocationusjtql.model.Local;
import br.usjt.ucsist.savelocationusjtql.model.LocalAdapter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView cardsLocaisRecyclerView;
    private LocalAdapter adapter;
    private List<Local> locais;
    private CollectionReference locaisReference;

    private Button voltarHome;
    private EditText editTextCEP;
    private EditText editTextRua;
    private EditText editTextNumero;
    private EditText editTextBairro;
    private EditText editTextCidade;
    private EditText editTextEstado;
    private TextView DadosDeLongitude;
    private TextView DadosDeLatitude;
    private Button adicionarLocais;

    public static final String CEP_KEY = "cep";
    public static final String RUA_KEY = "rua";
    public static final String NUMERO_KEY = "numero";
    public static final String BAIRRO_KEY = "bairro";
    public static final String CIDADE_KEY = "cidade";
    public static final String ESTADO_KEY = "estado";
    private static final String TAG = "MyActivity";

    private DocumentReference mDocRef = FirebaseFirestore.getInstance().collection("sampleData").document("Locais");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adicionarLocais = (Button) findViewById(R.id.buttonAdicionarLocais);
        adicionarLocais.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CadastroDeLocaisActivity.class);
                startActivity(intent);
            }

        });

        cardsLocaisRecyclerView = findViewById(R.id.cardsLocaisRecycleView);
        locais = new ArrayList<>();
        adapter = new LocalAdapter(locais, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        cardsLocaisRecyclerView.setLayoutManager(linearLayoutManager);
        cardsLocaisRecyclerView.setAdapter(adapter);
        editTextRua = findViewById(R.id.editTextRua);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextEstado = findViewById(R.id.editTextEstado);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextCEP = findViewById(R.id.editTextCEP);
        editTextBairro = findViewById(R.id.editTextBairro);
        DadosDeLatitude = findViewById(R.id.DadosDeLatitude);
        DadosDeLongitude = findViewById(R.id.DadosDeLongitude);

    }

    private void setupFirebase() {
        locaisReference = FirebaseFirestore.getInstance().collection("sampleData");

    }

    @Override
    protected void onStart() {
        super.onStart();
        setupFirebase();
    }
}