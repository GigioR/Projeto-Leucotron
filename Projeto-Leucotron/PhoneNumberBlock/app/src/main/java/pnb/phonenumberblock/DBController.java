package pnb.phonenumberblock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
    Classe de Controle do Banco de Dados

    por algum motivo algumas funções não permitiam chamar a função deletaRegistro
    do banco caso as variáveis aqui não fossem estáticas, então tive que colocar tudo o que pudesse
    ser estático estático.
*/
public class DBController {
    private static SQLiteDatabase db;
    private static DB banco;           //  instanciação do BD


    /*
        Construtor do BD
     */

    public DBController(Context context){
        banco = new DB(context);
    }

    /*
        Função de Inserção de algum dado no BD
     */

    public int insereDado(String num){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DB.NUMERO, num);

        resultado = db.insert(DB.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return -1;
        else
            return 1;

    }

    /*
        Função para listar todas as tuplas do BD na tela
     */

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NUMERO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /*
        Função para deletar uma tupla do BD
     */

    public static void deletaRegistro(int id){
        String where = DB.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(DB.TABELA,where,null);
        db.close();
    }
}

