package com.montfel.minhasanotaes;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.snackbar.Snackbar;
import com.montfel.minhasanotaes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private AnotacaoPreferencias preferencias;
    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editAnotacao = findViewById(R.id.editAnotacao);
        preferencias = new AnotacaoPreferencias(getApplicationContext());

        binding.fab.setOnClickListener(view -> {
            String textoRecuperado = editAnotacao.getText().toString();
            if (textoRecuperado.equals("")) {
                Snackbar.make(view, R.string.preencha_anotacao, Snackbar.LENGTH_LONG).show();
            } else {
                preferencias.salvarAnotacao(textoRecuperado);
                Snackbar.make(view, R.string.anotacao_salva, Snackbar.LENGTH_LONG).show();
            }
        });

        String anotacao = preferencias.recuperarAnotacao();
        if (!anotacao.equals("")){
            editAnotacao.setText(anotacao);
        }
    }
}