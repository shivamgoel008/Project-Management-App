package com.example.projectmanagementapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_video_calling_main.*

class VideoCallingMainActivity : AppCompatActivity() {
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_calling_main)
        Constants.isIntiatedNow = true
        Constants.isCallEnded = true
        start_room.setOnClickListener {
            if (room_id.text.toString().trim().isNullOrEmpty())
                room_id.error = "Please enter meeting id"
            else {
                Log.d("SHIVAM", "${room_id.text.toString()}")
                db.collection("calls")
                    .document(room_id.text.toString())
                    .get()
                    .addOnSuccessListener {
                        if (it["type"] == "OFFER" || it["type"] == "ANSWER" || it["type"] == "END_CALL") {
                            room_id.error = "Please enter new meeting ID"
                        } else {
                            val intent = Intent(
                                this@VideoCallingMainActivity,
                                VideoCallingStartActivity::class.java
                            )
                            intent.putExtra("meetingID", room_id.text.toString())
                            intent.putExtra("isJoin", false)
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener {
                        room_id.error = "Please enter new meeting ID"
                    }
            }
        }
        join_room.setOnClickListener {
            Log.d("SHIVAM", "${room_id.text.toString()}")
            if (room_id.text.toString().trim().isNullOrEmpty())
                room_id.error = "Please enter meeting id"
            else {
                val intent =
                    Intent(this@VideoCallingMainActivity, VideoCallingStartActivity::class.java)
                intent.putExtra("meetingID", room_id.text.toString())
                intent.putExtra("isJoin", true)
                startActivity(intent)
            }
        }
    }
}