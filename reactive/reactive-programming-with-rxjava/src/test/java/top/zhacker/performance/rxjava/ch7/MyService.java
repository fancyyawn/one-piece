package top.zhacker.performance.rxjava.ch7;

import rx.Observable;

import java.time.LocalDate;

interface MyService {
    Observable<LocalDate> externalCall();
}
