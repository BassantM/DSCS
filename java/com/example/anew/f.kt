package com.example.anew
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.storage.FirebaseStorage

class f : AppCompatActivity() {
    private lateinit var getVideoF: Button
    private lateinit var video6: VideoView
    private lateinit var fab6 : FloatingActionButton



    private val storageReference = FirebaseStorage.getInstance().reference
    private val videoFileName = "f.mp4" // اسم الملف المخزن في Firebase Storage

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_f)



        getVideoF = findViewById(R.id.getVideoF)
        video6 = findViewById(R.id.video6)
        fab6 = findViewById(R.id.fab6)


        getVideoF.setOnClickListener {
            // حصول على عنوان URL للفيديو المخزن في Firebase Storage
            storageReference.child("videos/$videoFileName").downloadUrl.addOnSuccessListener { uri ->
                val videoUri = uri.toString()
                video6.setVideoURI(Uri.parse(videoUri))
                video6.setOnCompletionListener {
                    // عند انتهاء الفيديو، قم بتشغيله مرة أخرى
                    video6.start()
                }
                video6.start() // بدء تشغيل الفيديو لأول مرة
            }.addOnFailureListener {
            }
        }

        fab6.setOnClickListener {
            val intent = Intent(this@f, LearnSign::class.java)
            startActivity(intent)
        }


    }
}
