package pnb.phonenumberblock;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class MainAc extends AppCompatActivity {

    /*
        Tela de início do app, com os botões de adicionar e remover números
     */

    private Button adicionaBtn;
    private Button removeBtn;
    private Context mContext;
    private Intent intent;
    private PhoneCallReceiver phoneCallReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adicionaBtn = (Button) findViewById(R.id.addBtn);
        removeBtn = (Button) findViewById(R.id.rmvBtn);
        phoneCallReceiver = new PhoneCallReceiver();
        mContext = this;

        phoneCallReceiver.onReceive(mContext, intent); //Chamada da função da Classe PhoneCallReceiver
        

        /*
            Listener do botão de Adicionar Números
            Quando pressionado, o usuário é levado a outra tela para adicionar o número no BD
         */

        adicionaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAc.this, AddNumber.class);
                startActivity(intent);
            }
        });

        /*
            Listener do botão de Remover Números
            Quando pressionado, o usuário é levado a outra tela com uma listagem dos registros do BD
            É possível excluir os números selecionando-os um por vez e excluindo
         */

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAc.this, DeleteNumber.class);
                startActivity(intent);
            }
        });
    }
}
