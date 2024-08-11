package com.example.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.models.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDAO

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: createDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()

        //Fallback to destructive migration: If you can't determine the exact changes and need to update the schema, you can use a destructive migration. This involves deleting the old database and creating a new one with the current schema.

    }
}

/*
* This code defines an ArticleDatabase class, which is an abstract class that extends RoomDatabase. This class is responsible for creating and managing the Room database for your application. Let's break down the code:
@Database annotation: This annotation marks the class as a Room database and provides information about the entities included in the database and the database version.
entities = [Article::class]: Specifies that the database contains a single entity called Article.
version = 1: Sets the database version to 1.
@TypeConverters annotation: This annotation specifies a class (Converters in this case) that contains type converter methods for handling custom data types that Room doesn't support natively.
abstract fun getArticleDao(): ArticleDAO: This abstract function defines a method to access the Data Access Object (DAO) for the Article entity.
Companion object: This object contains the logic for creating and managing the database instance.
**INSTANCE: A volatile variable to hold the database instance.
**LOCK: An object used for synchronization to prevent multiple threads from creating multiple instances of the database.
invoke(context: Context): This function provides a convenient way to access the database instance. It uses the double-checked locking pattern to ensure that only one instance of the database is created.
createDatabase(context: Context): This function creates the database instance using the Room database builder. It sets the database name to "article_db.db". In summary, this code defines a Room database class that includes the Article entity and uses type converters. The companion object provides a singleton instance of the database and ensures thread safety during initialization. This pattern is commonly used in Android development to manage Room databases and ensure that only one instance of the database is created and accessed throughout the application.*/