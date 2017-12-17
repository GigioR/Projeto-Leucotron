package pnb.phonenumberblock;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNumber extends AppCompatActivity {

    /*
        Tela para adicionar os números no BD
     */

    private Button okButton;
    private Button backButton;
    private EditText phoneNum;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);
        okButton = (Button) findViewById(R.id.okBtn);
        backButton = (Button) findViewById(R.id.backBtn);
        phoneNum = (EditText) findViewById(R.id.phoneNumber);
        mContext = this;

        /*
        Listener do botão de OK para inserir o número na caixa de texto no BD
         */
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ADDNum();
                phoneNum.setText("");
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    /*
        Função para captar o número da textBox e adicionar no BD
     */
    private void ADDNum(){
        DBController add = new DBController(mContext);
        String number = phoneNum.getText().toString();
        int result = add.insereDado(number);
        String message;

        if(result == 1){
            message = "Adicionado Com Sucesso!";
        }else{
            message = "Não foi possível adicionar!";
        }

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}
