package com.example.magicacompleto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.magicacompleto.model.response.response.Nota

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "NotasDB"
        private const val TABLE_NOTAS = "notas"
        private const val KEY_ID = "_id"
        private const val KEY_TITULO = "titulo"
        private const val KEY_TEXTO = "texto"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_NOTAS_TABLE = ("CREATE TABLE $TABLE_NOTAS("
                + "$KEY_ID INTEGER PRIMARY KEY,"
                + "$KEY_TITULO TEXT,"
                + "$KEY_TEXTO TEXT)")
        db.execSQL(CREATE_NOTAS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTAS")
        onCreate(db)
    }

    fun agregarNota(nota: Nota): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITULO, nota.titulo)
        values.put(KEY_TEXTO, nota.texto)
        val id = db.insert(TABLE_NOTAS, null, values)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun obtenerNota(id: Long): Nota? {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(TABLE_NOTAS, arrayOf(KEY_ID, KEY_TITULO, KEY_TEXTO), "$KEY_ID=?", arrayOf(id.toString()), null, null, null, null)

        return if (cursor != null) {
            cursor.moveToFirst()
            val nota = Nota(
                cursor.getLong(cursor.getColumnIndex(KEY_ID)).toInt(),
                cursor.getString(cursor.getColumnIndex(KEY_TITULO)),
                cursor.getString(cursor.getColumnIndex(KEY_TEXTO))
            )
            cursor.close()
            nota
        } else {
            null
        }
    }

    @SuppressLint("Range")
    fun obtenerTodasNotas(): List<Nota> {
        val listaNotas = ArrayList<Nota>()
        val selectQuery = "SELECT * FROM $TABLE_NOTAS"
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val nota = Nota(
                    cursor.getLong(cursor.getColumnIndex(KEY_ID)).toInt(),
                    cursor.getString(cursor.getColumnIndex(KEY_TITULO)),
                    cursor.getString(cursor.getColumnIndex(KEY_TEXTO))
                )
                listaNotas.add(nota)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listaNotas
    }

    fun actualizarNota(nota: Nota): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_TITULO, nota.titulo)
        values.put(KEY_TEXTO, nota.texto)
        return db.update(TABLE_NOTAS, values, "$KEY_ID=?", arrayOf(nota.id.toString()))
    }

    fun eliminarNota(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NOTAS, "$KEY_ID=?", arrayOf(id.toString()))
    }
}