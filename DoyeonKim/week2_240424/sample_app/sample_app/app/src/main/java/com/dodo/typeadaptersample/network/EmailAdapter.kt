package com.dodo.typeadaptersample.network

import android.util.Log
import com.dodo.typeadaptersample.data.MyEmail
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val TAG = "EmailAdapter"
class EmailAdapter : TypeAdapter<MyEmail>() {

    // 서버에게 요청 보낼때 사용된다
    override fun write(out: JsonWriter?, value: MyEmail?) {
        if (value == null) {
            out?.nullValue()
            return
        }

        val outValue = value.userId +"@"+value.emailDomain

        out?.value(outValue)
    }

    // 서버에서 받은 값을 변환
    override fun read(reader: JsonReader?): MyEmail? {
        if (reader == null) {
            return null
        }

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val rawValue = reader.nextString() ?: return null

        Log.d(TAG, "read: data from server! = ${rawValue}")

        val parts = rawValue.split("@")
        if(parts.size == 2){
            return MyEmail(
                userId = parts[0],
                emailDomain = parts[1]
            )
        }else{
            return null
        }

    }
}