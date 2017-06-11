package top.zhacker.performance.rxjava.ch3;

import rx.Observable;

class CassandraFactStore implements FactStore {
	@Override
	public Observable<ReservationEvent> observe() {
		return Observable.just(new ReservationEvent());
	}
}
