package com.dodo.typeadaptersample.network

import android.util.Log
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "DateAdapter"
class DateAdapter: TypeAdapter<Date>() {
    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    // 서버에게 요청 보낼때 사용된다
    override fun write(out: JsonWriter?, value: Date?) {
        if (value == null) {
            out?.nullValue()
            return
        }

        val outValue = format.format(value) + "...hi"
        Log.d(TAG, "write: data from android = ${outValue}")

        out?.value(outValue)
    }

    // 서버에서 받은 값을 변환
    override fun read(reader: JsonReader?): Date? {
        if (reader == null) {
            return null
        }

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val rawValue = reader.nextString() ?: return null

        Log.d(TAG, "read: data from server! = ${rawValue}")

        val date = format.parse(rawValue) ?: return null
        return date
    }
}