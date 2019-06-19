package info.adavis.di

import com.google.gson.Gson
import info.adavis.graphql.AppSchema
import info.adavis.dao.UFOSightingDatabase
import info.adavis.dao.UFOSightingStorage
import org.koin.dsl.module.module

val mainModule = module {
    single { Gson() }
    single { AppSchema(get()) }
    single { UFOSightingDatabase() as UFOSightingStorage }
}