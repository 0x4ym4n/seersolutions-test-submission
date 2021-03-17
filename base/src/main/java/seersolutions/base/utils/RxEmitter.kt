package seersolutions.base.utils

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

object RxEmitter {

    private val eventMap = HashMap<String, PublishSubject<Any>>()
    private val eventsMap = HashMap<Any, CompositeDisposable>()

    private fun getEvent(subjectKey: String): PublishSubject<Any> {
        var event = eventMap.get(subjectKey)
        if (event == null) {
            event = PublishSubject.create()
            event.subscribeOn(AndroidSchedulers.mainThread())
            eventMap[subjectKey] = event
        }
        return event
    }

    private fun getCompositeDisposable(`object`: Any): CompositeDisposable {
        var compositeDisposable = eventsMap[`object`]
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
            eventsMap[`object`] = compositeDisposable
        }
        return compositeDisposable
    }

    fun subscribe(subject: String, lifecycle: Any, action: Consumer<Any>) {
        val disposable = getEvent(subject).subscribe(action)
        getCompositeDisposable(lifecycle).add(disposable)
    }

    fun unregister(lifecycle: Any) {
        eventsMap.remove(lifecycle)?.dispose()
    }

    fun publish(subject: String, message: Any) {
        getEvent(subject).onNext(message)
    }

}