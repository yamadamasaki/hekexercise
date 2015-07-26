package hekexercise

//@Transactional
class ReservationService {
    static transactional = true

    def reserve(Person who, Appliance what, Date from, Date to) {
        new Reservation(who:who, what:what, fromDate:from, toDate:to).save()
    }
}
