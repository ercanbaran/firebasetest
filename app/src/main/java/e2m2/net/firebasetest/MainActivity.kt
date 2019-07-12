package e2m2.net.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("----->", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                Log.d("------->", token)
            })


        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnCompleteListener {

                Log.d("------>","registered")
            }
    }

    override fun onResume() {
        super.onResume()
        if(intent.hasExtra("kombiID"))
        {
            Log.d("------->", "opened from notification")
        }
    }
}
