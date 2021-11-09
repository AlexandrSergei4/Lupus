package org.alki.lupus

import android.widget.CalendarView
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate


class ConverterTest {
    companion object{
        const val longDate = 1602288000000L
        val localDate: LocalDate = LocalDate.of(2020, 10 ,10)
    }
    @Test()
    fun localDate2LongTest(){

        Assert.assertEquals(longDate, localDate.toLong())
    }


    @Test()
    fun long2LocalDate(){
        Assert.assertEquals(localDate, longDate.toLocalDate())
    }
}