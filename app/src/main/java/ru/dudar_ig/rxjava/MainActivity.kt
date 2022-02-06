package ru.dudar_ig.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count = 0
        val text = findViewById<TextView>(R.id.textview)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            count++
            button.text = count.toString()
        }
//        val creation = Creation()
//        creation.exec()
        val ll = listOf("Первый", "Второй", "Третий")
        val observable = Observable.just(ll)
        val single = Single.just(25)

        val disposable = observable
            .subscribe({
                Log.d("QQQ", "$it")
            })

        val dispos = dataSourse()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    text.text = it.toString()
                Log.d("QQQ", "$it")
            })
    }

    private fun dataSourse(): Observable<Int> {
        return Observable.create { subscriber ->
            for(i in 0..100) {
                Thread.sleep(1_000)
                subscriber.onNext(i)
            }

        }

    }
}