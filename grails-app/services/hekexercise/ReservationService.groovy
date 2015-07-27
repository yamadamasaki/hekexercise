package hekexercise

import org.gcontracts.annotations.Requires

//@Transactional
class ReservationService {
    static transactional = true

    @Requires({who && what && from && to && from < to})
    def reserve(Person who, Appliance what, Date from, Date to) {
        def r = new Reservation(who:who, what:what, fromDate:from, toDate:to)
        if (isBooked(r)) null
        else r.save()
    }

    @Requires({what && when})
    def isReserved(Appliance what, Date when) {
        Reservation.findByWhatAndFromDateLessThanEqualsAndToDateGreaterThan(what, when, when)?true:false
    }

    @Requires({reservation})
    def isBooked(reservation) {
        def query = Reservation.where {
            what == reservation.what && fromDate < reservation.toDate && toDate > reservation.fromDate
        }
        query.find()?true:false
    }
}
