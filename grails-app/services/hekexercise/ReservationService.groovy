package hekexercise

import grails.transaction.Transactional

@Transactional
class ReservationService {

    def reserve(Person who, Appliance what, Date from, Date to) {
        new Reservation(who:who, what:what, fromDate:from, toDate:to).save()
    }
}
