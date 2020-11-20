package br.usjt.ucsist.savelocationusjtql.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.usjt.ucsist.savelocationusjtql.R;
import br.usjt.ucsist.savelocationusjtql.model.Local;
import br.usjt.ucsist.savelocationusjtql.model.LocalAdapter;

public class CadastroDeLocaisActivity extends AppCompatActivity {

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

    private static final String TAG = "MyActivity";


    private DocumentReference mDocRef = FirebaseFirestore.getInstance().collection("sampleData").document("Locais");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_locais);

        voltarHome = (Button) findViewById(R.id.buttonVoltar);
        voltarHome.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(CadastroDeLocaisActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });


        editTextCEP = (EditText) findViewById(R.id.editTextCEP);
        editTextRua = (EditText) findViewById(R.id.editTextRua);
        editTextNumero = (EditText) findViewById(R.id.editTextNumero);
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);


//        cardsLocaisRecyclerView = findViewById(R.id.cardsLocaisRecycleView);
//        locais = new ArrayList<>();
//        adapter = new LocalAdapter(locais, this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        cardsLocaisRecyclerView.setLayoutManager(linearLayoutManager);
//        cardsLocaisRecyclerView.setAdapter(adapter);
//        editTextRua = findViewById(R.id.editTextRua);
//        editTextNumero = findViewById(R.id.editTextNumero);
//        editTextEstado = findViewById(R.id.editTextEstado);
//        editTextCidade = findViewById(R.id.editTextCidade);
//        editTextCEP = findViewById(R.id.editTextCEP);
//        editTextBairro = findViewById(R.id.editTextBairro);
//        DadosDeLatitude = findViewById(R.id.DadosDeLatitude);
//        DadosDeLongitude = findViewById(R.id.DadosDeLongitude);
    }

    public void confirmarCadastro(View view){
        if(validarCampos()) {
            String cepText = editTextCEP.getText().toString();
            String ruaText = editTextRua.getText().toString();
            String numeroText = editTextNumero.getText().toString();
            String bairroText = editTextBairro.getText().toString();
            String cidadeText = editTextCidade.getText().toString();
            String estadoText = editTextEstado.getText().toString();

            if (cepText.isEmpty() || ruaText.isEmpty() || numeroText.isEmpty() || bairroText.isEmpty() || cidadeText.isEmpty() || estadoText.isEmpty()) {
                return;
            }
            Map<String, Object> dataToSave = new HashMap<String, Object>();
            dataToSave.put("cep", cepText);
            dataToSave.put("rua", ruaText);
            dataToSave.put("numero", numeroText);
            dataToSave.put("bairro", bairroText);
            dataToSave.put("cidade", cidadeText);
            dataToSave.put("estado", estadoText);
            mDocRef.collection("Locais")
                    .add(dataToSave)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            Intent intent = new Intent(CadastroDeLocaisActivity.this, MainActivity.class);
            startActivity(intent);

                    mDocRef.collection("Locais")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG, document.getId() + " => " + document.getData());
                                        }
                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                    }
                                }
                            });
        }
    }
    public boolean validarCampos(){
        boolean valido = true;
        if(editTextCEP.getText().toString().trim().length() == 0){
            valido = false;
            Toast.makeText(this, "CEP inválido", Toast.LENGTH_SHORT).show();
        }
        if(editTextRua.getText().toString().trim().length() == 0){
            valido = false;
            Toast.makeText(this, "Rua inválido", Toast.LENGTH_SHORT).show();
        }
        if(editTextNumero.getText().toString().trim().length() == 0){
            valido = false;
            Toast.makeText(this, "Número inválido", Toast.LENGTH_SHORT).show();
        }
        if(editTextBairro.getText().toString().trim().length() == 0){
            valido = false;
            Toast.makeText(this, "Bairro inválido", Toast.LENGTH_SHORT).show();
        }
        if(editTextCidade.getText().toString().trim().length() == 0){
            valido = false;
            Toast.makeText(this, "Cidade inválido", Toast.LENGTH_SHORT).show();
        }
        if(editTextEstado.getText().toString().trim().length() == 0){
            valido = false;
            Toast.makeText(this, "Estado inválido", Toast.LENGTH_SHORT).show();
        }
        return  valido;
    }
}