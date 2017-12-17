package pnb.phonenumberblock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
   Classe do Banco de Dados
*/

public class DB extends SQLiteOpenHelper {

    /*
        Declaração do schema, tabelas e colunas do BD
     */

    public static final String NOME_BCO= "PhoneBlocker.db";
    public static final String TABELA = "Numeros";
    public static final String ID = "_id";
    public static final String NUMERO = "Num";
    public static final int VERSAO = 1;

    public DB(Context context){
        super(context, NOME_BCO,null,VERSAO);
    }
    /*
        Criação do Banco
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NUMERO + " text"
                +")";
        db.execSQL(sql);
    }
    /*
        Função chamada quando há alguma alteração no schema
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }

}
