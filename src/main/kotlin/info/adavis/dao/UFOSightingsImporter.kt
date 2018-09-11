package info.adavis.dao

import info.adavis.model.UFOSighting
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.asResourceStream(): InputStream = this.javaClass::class.java.getResource(this).openStream()

private const val CSV_FILE_NAME = "/ufo_sightings_2013_2014.csv"
private const val DATE_FORMAT = "M/d/yyyy"

open class UFOSightingsImporter : CSVDataImporter(), KoinComponent {

    private val sightingsDatabase by inject<UFOSightingStorage>()

    override fun import() {
        val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
        val settingsStream = CSV_FILE_NAME.asResourceStream()
        readFromCSV(settingsStream) { row ->
            val ufoSighting = UFOSighting(
                    date = LocalDate.parse(row[0], formatter),
                    city = row[1],
                    state = row[2],
                    country = row[3],
                    shape = row[4],
                    duration = row[5].toDouble(),
                    comments = row[6],
                    latitude = row[7].toDouble(),
                    longitude = row[8].toDouble()
            )
            sightingsDatabase.createSighting(ufoSighting)
        }
    }
}
