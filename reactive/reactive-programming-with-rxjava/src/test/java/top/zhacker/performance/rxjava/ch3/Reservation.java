package top.zhacker.performance.rxjava.ch3;

class Reservation {

    Reservation consume(ReservationEvent event) {
        //mutate myself
        return this;
    }

}
