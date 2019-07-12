package e2m2.net.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Socket urls
        //https://gist.github.com/Silverbaq/a14fe6b3ec57703e8cc1a63b59605876
        //https://www.baeldung.com/a-guide-to-java-sockets

        //LocationLib
        //https://github.com/mrmans0n/smart-location-lib


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
//
//        GlobalScope.launch {
//            runHello()
//            async {
//
//            }
//        }


//        repeat(100_000) { // launch a lot of coroutines
////            GlobalScope.launch {
////                delay(1000L)
////                println("------>.")
////            }
//            thread {
//                Thread.sleep(1000L)
//                println("------>.")
//
//            }
//        }

        GlobalScope.launch {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }

            delay(1300L) // just quit after delay

        }



    }

    suspend fun runHello()
    {
        val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            println("----World!")
        }
        println("Hello,")
        job.join() // wait until child coroutine completes
    }

    override fun onResume() {
        super.onResume()
        if(intent.hasExtra("kombiID"))
        {
            Log.d("------->", "opened from notification")
        }
    }
}
