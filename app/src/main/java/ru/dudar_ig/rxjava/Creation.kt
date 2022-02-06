package ru.dudar_ig.rxjava

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class Creation {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }
    }

    class Consumer(val producer: Producer) {
        val stringObserver = object : Observer<String> {
            lateinit var disposable: Disposable
            override fun onComplete() {
               Log.d("QQQ", "onComplete")
            }
            override fun onSubscribe(d: Disposable) {
                disposable = d
                Log.d("QQQ", "onSubscribe")
            }
            override fun onNext(s: String?) {
                Log.d("QQQ", "onNext: $s")
                if (s?.toInt()==2) {
                    //disposable.dispose()
                }
            }
            override fun onError(e: Throwable?) {
                Log.d("QQQ", "onError: ${e?.message}")
            }
        }
        fun exec() {
            producer.just()
                .subscribe(stringObserver)
        }
    }
}