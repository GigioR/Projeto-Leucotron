package pnb.phonenumberblock;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/*
    Tela de Remoção de númedos do BD
 */
public class DeleteNumber extends AppCompatActivity {
    /*
        Declaração dos botões da interface
     */
    private Button okBtn;
    private Button bckBtn;
    private ListView listOne;
    private Context mContext;
    private int toBeDeleted;
    private String message = "Deletado com Sucesso!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_number);
        okBtn = (Button) findViewById(R.id.OKBtn);
        bckBtn = (Button) findViewById(R.id.VoltarBtn);
        listOne = (ListView)findViewById(R.id.listId);
        mContext = this;

        /*
            Declaração do Cursor que irá printar os dados na tela
         */
        DBController crud = new DBController(mContext);
        final Cursor cursor = crud.carregaDados();
        /*
           Como o desejado é printar somente o número na tela, nessas variáveis é selecionado somente
           o número sem o ID do mesmo, por exemplo.
         */
        String[] nomeCampos = new String[] {DB.NUMERO};
        int[] idViews = new int[] {R.id.txtnumber};

        /*
            Não entendi muito bem como funciona o Adapter, mas entendi que os parâmetros envolvem
            de onde ele pega os dados e o que ele vai printar desses dados e, claro, onde será printado
         */

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(mContext, R.layout.listview1,cursor,nomeCampos,idViews, 0);
        listOne.setAdapter(adaptador);


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleta(toBeDeleted);
                Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
            }
        });

        bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listOne.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getInt(cursor.getColumnIndexOrThrow(DB.ID));
                toBeDeleted = codigo;

            }
        });
    }

    public static void deleta(int codigo){
        DBController.deletaRegistro(codigo);
    }
}
