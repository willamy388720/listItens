package com.example.trabalho_02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static int RESULT_ADD = 1;
    static int RESULT_EDIT = 2;

    ArrayAdapter<Item> arrayAdapter;

    ListView lista;
    Button add;
    Button editar;
    ArrayList<Item> itens = new ArrayList<Item>();
    EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.itens);
        add = (Button) findViewById(R.id.adicionar);
        editar = (Button) findViewById(R.id.editar);
        search = (EditText) findViewById(R.id.search);

        arrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, itens);
        lista.setAdapter(arrayAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditCreate.class);
                int id = itens.size()+1;
                intent.putExtra("id", id);
                startActivityForResult(intent, RESULT_ADD);
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditCreate.class);
                int id = Integer.parseInt(""+search.getText());
                Item item = itens.get(id - 1);
                intent.putExtra("id", item.getId());
                intent.putExtra("title", item.getTitle());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("resume", item.getResume());

                startActivityForResult(intent, RESULT_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==  RESULT_ADD && resultCode == RESULT_ADD){
            Item item = new Item();

            item.setId(data.getExtras().getInt("id"));
            item.setTitle((String) data.getExtras().get("title"));
            item.setDescription((String) data.getExtras().get("description"));
            item.setResume((String) data.getExtras().get("resume"));

            itens.add(item);
            arrayAdapter.notifyDataSetChanged();
        } else if (requestCode == RESULT_EDIT && resultCode == RESULT_ADD){
            int idEdit = Integer.parseInt(data.getExtras().getInt("id")+"") - 1;
            itens.get(idEdit).setTitle((String) data.getExtras().get("title"));
            itens.get(idEdit).setDescription((String) data.getExtras().get("description"));
            itens.get(idEdit).setResume((String) data.getExtras().get("resume"));
            arrayAdapter.notifyDataSetChanged();
        }
    }

}