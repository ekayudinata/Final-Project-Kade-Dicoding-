package com.example.yudinata.footballsc.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDBOpenHelper(ctx:Context):ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db",null,1) {
    companion object {
        private var instance :MyDBOpenHelper?=null

        @Synchronized
        fun getInstance(ctx: Context):MyDBOpenHelper{
            if (instance==null){
                instance= MyDBOpenHelper(ctx.applicationContext)
            }
            return instance as MyDBOpenHelper
        }//end fun
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.createTable(Favorite.TABLE_FAVORITE,true,
                    Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

                    Favorite.EVENT_ID to TEXT + UNIQUE,
                    Favorite.DATE_EVENT to TEXT,
                    Favorite.TIME_EVENT to TEXT,

                    //home
                    Favorite.HOME_ID to TEXT,
                    Favorite.HOME_TEAM to TEXT,
                    Favorite.HOME_SCORE to TEXT,
                    //Away
                    Favorite.AWAY_ID to TEXT,
                    Favorite.AWAY_TEAM to TEXT,
                    Favorite.AWAY_SCORE to TEXT
            )

            db.createTable(TeamsFav.TABLE_TEAM,true,
                    TeamsFav.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,

                    TeamsFav.TEAM_ID to TEXT + UNIQUE,
                    TeamsFav.TEAM_NAME to TEXT,
                    TeamsFav.TEAM_BADGE to TEXT,
                    TeamsFav.TEAM_FORMED to TEXT,
                    TeamsFav.TEAM_STADIUM to TEXT
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.dropTable(Favorite.TABLE_FAVORITE,true)
            db.dropTable(TeamsFav.TABLE_TEAM,true)
        }
    }
}

val Context.database:MyDBOpenHelper
    get() = MyDBOpenHelper.getInstance(applicationContext)