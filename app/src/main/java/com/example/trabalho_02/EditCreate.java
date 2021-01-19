package com.example.trabalho_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EditCreate extends AppCompatActivity {
    static int RESULT_ADD = 1;
    static int RESULT_CANCEL = 2;

    EditText id;
    EditText title;
    EditText description;
    EditText resume;
    Button save;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_create);

        id = (EditText) findViewById(R.id.identificacao);
        title = (EditText) findViewById(R.id.titulo);
        description = (EditText) findViewById(R.id.descricao);
        resume = (EditText) findViewById(R.id.resumo);

        save = findViewById(R.id.salvar);
        cancel = findViewById(R.id.cancelar);

            id.setText(getIntent().getExtras().getInt("id") + "");
            Intent intent = getIntent();
            setEditText(intent, title, description, resume);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                Item item = addItens(id, title, description, resume);
                intent1.putExtra("id", item.getId());
                intent1.putExtra("title", item.getTitle());
                intent1.putExtra("description", item.getDescription());
                intent1.putExtra("resume", item.getResume());
                setResult(RESULT_ADD, intent1);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCEL);
                finish();
            }
        });

    }

    public void setEditText(Intent intent, EditText title, EditText description, EditText resume){

        title.setText((String) intent.getExtras().get("title"));
        description.setText((String) intent.getExtras().get("description"));
        resume.setText((String) intent.getExtras().get("resume"));
    }

    public Item addItens(EditText id, EditText title, EditText description, EditText resume){
        Item item = new Item();
        int identify = Integer.parseInt(id.getText().toString());

        item.setId(identify);
        item.setTitle(title.getText().toString());
        item.setDescription(description.getText().toString());
        item.setResume(resume.getText().toString());

        return item;
    }
}