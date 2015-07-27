package hekexercise

//@Transactional
class ReservationService {
    static transactional = true

    def reserve(Person who, Appliance what, Date from, Date to) {
        def r = new Reservation(who:who, what:what, fromDate:from, toDate:to)
        if (isBooked(r)) null
        else r.save()
    }

    def isReserved(Appliance what, Date when) {
        Reservation.findByWhatAndFromDateLessThanEqualsAndToDateGreaterThan(what, when, when)?true:false
    }

    def isBooked(reservation) {
        def query = Reservation.where {
            what == reservation.what && fromDate < reservation.toDate && toDate > reservation.fromDate
        }
        query.find()?true:false
    }
}
