package top.zhacker.performance.rxjava.ch3;

import rx.Observable;

interface FactStore {
	Observable<ReservationEvent> observe();
}
